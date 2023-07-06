import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {CustomerWalletFormService, CustomerWalletFormGroup} from './customer-wallet-form.service';
import {ICustomerWallet} from '../customer-wallet.model';
import {CustomerWalletService} from '../service/customer-wallet.service';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';
import {WalletAction} from 'app/entities/enumerations/wallet-action.model';
import {PaymentType} from 'app/entities/enumerations/payment-type.model';

@Component({
  standalone: true,
  selector: 'jhi-customer-wallet-update',
  templateUrl: './customer-wallet-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CustomerWalletUpdateComponent implements OnInit {
  isSaving = false;
  customerWallet: ICustomerWallet | null = null;
  walletActionValues = Object.keys(WalletAction);
  paymentTypeValues = Object.keys(PaymentType);

  customersSharedCollection: ICustomer[] = [];

  editForm: CustomerWalletFormGroup = this.customerWalletFormService.createCustomerWalletFormGroup();

  constructor(
    protected customerWalletService: CustomerWalletService,
    protected customerWalletFormService: CustomerWalletFormService,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareCustomer = (o1: ICustomer | null, o2: ICustomer | null): boolean => this.customerService.compareCustomer(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({customerWallet}) => {
      this.customerWallet = customerWallet;
      if (customerWallet) {
        this.updateForm(customerWallet);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const customerWallet = this.customerWalletFormService.getCustomerWallet(this.editForm);
    if (customerWallet.id !== null) {
      this.subscribeToSaveResponse(this.customerWalletService.update(customerWallet));
    } else {
      this.subscribeToSaveResponse(this.customerWalletService.create(customerWallet));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomerWallet>>): void {
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

  protected updateForm(customerWallet: ICustomerWallet): void {
    this.customerWallet = customerWallet;
    this.customerWalletFormService.resetForm(this.editForm, customerWallet);

    this.customersSharedCollection = this.customerService.addCustomerToCollectionIfMissing<ICustomer>(
      this.customersSharedCollection,
      customerWallet.customer
    );
  }

  protected loadRelationshipsOptions(): void {
    this.customerService
      .query()
      .pipe(map((res: HttpResponse<ICustomer[]>) => res.body ?? []))
      .pipe(
        map((customers: ICustomer[]) =>
          this.customerService.addCustomerToCollectionIfMissing<ICustomer>(customers, this.customerWallet?.customer)
        )
      )
      .subscribe((customers: ICustomer[]) => (this.customersSharedCollection = customers));
  }
}
