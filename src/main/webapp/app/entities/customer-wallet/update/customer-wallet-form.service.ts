import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {ICustomerWallet, NewCustomerWallet} from '../customer-wallet.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICustomerWallet for edit and NewCustomerWalletFormGroupInput for create.
 */
type CustomerWalletFormGroupInput = ICustomerWallet | PartialWithRequiredKeyOf<NewCustomerWallet>;

type CustomerWalletFormDefaults = Pick<NewCustomerWallet, 'id'>;

type CustomerWalletFormGroupContent = {
  id: FormControl<ICustomerWallet['id'] | NewCustomerWallet['id']>;
  transactionNo: FormControl<ICustomerWallet['transactionNo']>;
  amount: FormControl<ICustomerWallet['amount']>;
  walletAction: FormControl<ICustomerWallet['walletAction']>;
  totalBeforeAction: FormControl<ICustomerWallet['totalBeforeAction']>;
  totalAfterAction: FormControl<ICustomerWallet['totalAfterAction']>;
  paymentType: FormControl<ICustomerWallet['paymentType']>;
  paymentReference: FormControl<ICustomerWallet['paymentReference']>;
  orderId: FormControl<ICustomerWallet['orderId']>;
  notes: FormControl<ICustomerWallet['notes']>;
  customer: FormControl<ICustomerWallet['customer']>;
};

export type CustomerWalletFormGroup = FormGroup<CustomerWalletFormGroupContent>;

@Injectable({providedIn: 'root'})
export class CustomerWalletFormService {
  createCustomerWalletFormGroup(customerWallet: CustomerWalletFormGroupInput = {id: null}): CustomerWalletFormGroup {
    const customerWalletRawValue = {
      ...this.getFormDefaults(),
      ...customerWallet,
    };
    return new FormGroup<CustomerWalletFormGroupContent>({
      id: new FormControl(
        {value: customerWalletRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      transactionNo: new FormControl(customerWalletRawValue.transactionNo),
      amount: new FormControl(customerWalletRawValue.amount),
      walletAction: new FormControl(customerWalletRawValue.walletAction),
      totalBeforeAction: new FormControl(customerWalletRawValue.totalBeforeAction),
      totalAfterAction: new FormControl(customerWalletRawValue.totalAfterAction),
      paymentType: new FormControl(customerWalletRawValue.paymentType),
      paymentReference: new FormControl(customerWalletRawValue.paymentReference),
      orderId: new FormControl(customerWalletRawValue.orderId),
      notes: new FormControl(customerWalletRawValue.notes),
      customer: new FormControl(customerWalletRawValue.customer),
    });
  }

  getCustomerWallet(form: CustomerWalletFormGroup): ICustomerWallet | NewCustomerWallet {
    return form.getRawValue() as ICustomerWallet | NewCustomerWallet;
  }

  resetForm(form: CustomerWalletFormGroup, customerWallet: CustomerWalletFormGroupInput): void {
    const customerWalletRawValue = {...this.getFormDefaults(), ...customerWallet};
    form.reset(
      {
        ...customerWalletRawValue,
        id: {value: customerWalletRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CustomerWalletFormDefaults {
    return {
      id: null,
    };
  }
}
