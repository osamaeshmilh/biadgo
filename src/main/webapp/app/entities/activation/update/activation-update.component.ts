import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {ActivationFormService, ActivationFormGroup} from './activation-form.service';
import {IActivation} from '../activation.model';
import {ActivationService} from '../service/activation.service';

@Component({
  standalone: true,
  selector: 'jhi-activation-update',
  templateUrl: './activation-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ActivationUpdateComponent implements OnInit {
  isSaving = false;
  activation: IActivation | null = null;

  editForm: ActivationFormGroup = this.activationFormService.createActivationFormGroup();

  constructor(
    protected activationService: ActivationService,
    protected activationFormService: ActivationFormService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({activation}) => {
      this.activation = activation;
      if (activation) {
        this.updateForm(activation);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const activation = this.activationFormService.getActivation(this.editForm);
    if (activation.id !== null) {
      this.subscribeToSaveResponse(this.activationService.update(activation));
    } else {
      this.subscribeToSaveResponse(this.activationService.create(activation));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IActivation>>): void {
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

  protected updateForm(activation: IActivation): void {
    this.activation = activation;
    this.activationFormService.resetForm(this.editForm, activation);
  }
}
