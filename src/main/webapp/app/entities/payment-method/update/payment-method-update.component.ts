import {Component, OnInit, ElementRef} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {PaymentMethodFormService, PaymentMethodFormGroup} from './payment-method-form.service';
import {IPaymentMethod} from '../payment-method.model';
import {PaymentMethodService} from '../service/payment-method.service';
import {AlertError} from 'app/shared/alert/alert-error.model';
import {EventManager, EventWithContent} from 'app/core/util/event-manager.service';
import {DataUtils, FileLoadError} from 'app/core/util/data-util.service';
import {PaymentType} from 'app/entities/enumerations/payment-type.model';

@Component({
  standalone: true,
  selector: 'jhi-payment-method-update',
  templateUrl: './payment-method-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class PaymentMethodUpdateComponent implements OnInit {
  isSaving = false;
  paymentMethod: IPaymentMethod | null = null;
  paymentTypeValues = Object.keys(PaymentType);

  editForm: PaymentMethodFormGroup = this.paymentMethodFormService.createPaymentMethodFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected paymentMethodService: PaymentMethodService,
    protected paymentMethodFormService: PaymentMethodFormService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({paymentMethod}) => {
      this.paymentMethod = paymentMethod;
      if (paymentMethod) {
        this.updateForm(paymentMethod);
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
    const paymentMethod = this.paymentMethodFormService.getPaymentMethod(this.editForm);
    if (paymentMethod.id !== null) {
      this.subscribeToSaveResponse(this.paymentMethodService.update(paymentMethod));
    } else {
      this.subscribeToSaveResponse(this.paymentMethodService.create(paymentMethod));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaymentMethod>>): void {
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

  protected updateForm(paymentMethod: IPaymentMethod): void {
    this.paymentMethod = paymentMethod;
    this.paymentMethodFormService.resetForm(this.editForm, paymentMethod);
  }
}
