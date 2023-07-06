import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {ReferralFormService, ReferralFormGroup} from './referral-form.service';
import {IReferral} from '../referral.model';
import {ReferralService} from '../service/referral.service';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';

@Component({
  standalone: true,
  selector: 'jhi-referral-update',
  templateUrl: './referral-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ReferralUpdateComponent implements OnInit {
  isSaving = false;
  referral: IReferral | null = null;

  customersSharedCollection: ICustomer[] = [];

  editForm: ReferralFormGroup = this.referralFormService.createReferralFormGroup();

  constructor(
    protected referralService: ReferralService,
    protected referralFormService: ReferralFormService,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareCustomer = (o1: ICustomer | null, o2: ICustomer | null): boolean => this.customerService.compareCustomer(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({referral}) => {
      this.referral = referral;
      if (referral) {
        this.updateForm(referral);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const referral = this.referralFormService.getReferral(this.editForm);
    if (referral.id !== null) {
      this.subscribeToSaveResponse(this.referralService.update(referral));
    } else {
      this.subscribeToSaveResponse(this.referralService.create(referral));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReferral>>): void {
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

  protected updateForm(referral: IReferral): void {
    this.referral = referral;
    this.referralFormService.resetForm(this.editForm, referral);

    this.customersSharedCollection = this.customerService.addCustomerToCollectionIfMissing<ICustomer>(
      this.customersSharedCollection,
      referral.referredCustomer,
      referral.referrerCustomer
    );
  }

  protected loadRelationshipsOptions(): void {
    this.customerService
      .query()
      .pipe(map((res: HttpResponse<ICustomer[]>) => res.body ?? []))
      .pipe(
        map((customers: ICustomer[]) =>
          this.customerService.addCustomerToCollectionIfMissing<ICustomer>(
            customers,
            this.referral?.referredCustomer,
            this.referral?.referrerCustomer
          )
        )
      )
      .subscribe((customers: ICustomer[]) => (this.customersSharedCollection = customers));
  }
}
