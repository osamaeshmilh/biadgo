import {Component, OnInit, ElementRef} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {SliderFormService, SliderFormGroup} from './slider-form.service';
import {ISlider} from '../slider.model';
import {SliderService} from '../service/slider.service';
import {AlertError} from 'app/shared/alert/alert-error.model';
import {EventManager, EventWithContent} from 'app/core/util/event-manager.service';
import {DataUtils, FileLoadError} from 'app/core/util/data-util.service';

@Component({
  standalone: true,
  selector: 'jhi-slider-update',
  templateUrl: './slider-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class SliderUpdateComponent implements OnInit {
  isSaving = false;
  slider: ISlider | null = null;

  editForm: SliderFormGroup = this.sliderFormService.createSliderFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected sliderService: SliderService,
    protected sliderFormService: SliderFormService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({slider}) => {
      this.slider = slider;
      if (slider) {
        this.updateForm(slider);
      }
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('biadjoApp.error', {message: err.message})),
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const slider = this.sliderFormService.getSlider(this.editForm);
    if (slider.id !== null) {
      this.subscribeToSaveResponse(this.sliderService.update(slider));
    } else {
      this.subscribeToSaveResponse(this.sliderService.create(slider));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISlider>>): void {
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

  protected updateForm(slider: ISlider): void {
    this.slider = slider;
    this.sliderFormService.resetForm(this.editForm, slider);
  }
}
