import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppSettingFormService, AppSettingFormGroup} from './app-setting-form.service';
import {IAppSetting} from '../app-setting.model';
import {AppSettingService} from '../service/app-setting.service';

@Component({
  standalone: true,
  selector: 'jhi-app-setting-update',
  templateUrl: './app-setting-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class AppSettingUpdateComponent implements OnInit {
  isSaving = false;
  appSetting: IAppSetting | null = null;

  editForm: AppSettingFormGroup = this.appSettingFormService.createAppSettingFormGroup();

  constructor(
    protected appSettingService: AppSettingService,
    protected appSettingFormService: AppSettingFormService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({appSetting}) => {
      this.appSetting = appSetting;
      if (appSetting) {
        this.updateForm(appSetting);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const appSetting = this.appSettingFormService.getAppSetting(this.editForm);
    if (appSetting.id !== null) {
      this.subscribeToSaveResponse(this.appSettingService.update(appSetting));
    } else {
      this.subscribeToSaveResponse(this.appSettingService.create(appSetting));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAppSetting>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(appSetting: IAppSetting): void {
    this.appSetting = appSetting;
    this.appSettingFormService.resetForm(this.editForm, appSetting);
  }
}
