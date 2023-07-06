import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IDriverWallet, NewDriverWallet} from '../driver-wallet.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDriverWallet for edit and NewDriverWalletFormGroupInput for create.
 */
type DriverWalletFormGroupInput = IDriverWallet | PartialWithRequiredKeyOf<NewDriverWallet>;

type DriverWalletFormDefaults = Pick<NewDriverWallet, 'id'>;

type DriverWalletFormGroupContent = {
  id: FormControl<IDriverWallet['id'] | NewDriverWallet['id']>;
  transactionNo: FormControl<IDriverWallet['transactionNo']>;
  amount: FormControl<IDriverWallet['amount']>;
  walletAction: FormControl<IDriverWallet['walletAction']>;
  totalBeforeAction: FormControl<IDriverWallet['totalBeforeAction']>;
  totalAfterAction: FormControl<IDriverWallet['totalAfterAction']>;
  paymentType: FormControl<IDriverWallet['paymentType']>;
  paymentReference: FormControl<IDriverWallet['paymentReference']>;
  orderId: FormControl<IDriverWallet['orderId']>;
  notes: FormControl<IDriverWallet['notes']>;
  driver: FormControl<IDriverWallet['driver']>;
};

export type DriverWalletFormGroup = FormGroup<DriverWalletFormGroupContent>;

@Injectable({providedIn: 'root'})
export class DriverWalletFormService {
  createDriverWalletFormGroup(driverWallet: DriverWalletFormGroupInput = {id: null}): DriverWalletFormGroup {
    const driverWalletRawValue = {
      ...this.getFormDefaults(),
      ...driverWallet,
    };
    return new FormGroup<DriverWalletFormGroupContent>({
      id: new FormControl(
        {value: driverWalletRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      transactionNo: new FormControl(driverWalletRawValue.transactionNo),
      amount: new FormControl(driverWalletRawValue.amount),
      walletAction: new FormControl(driverWalletRawValue.walletAction),
      totalBeforeAction: new FormControl(driverWalletRawValue.totalBeforeAction),
      totalAfterAction: new FormControl(driverWalletRawValue.totalAfterAction),
      paymentType: new FormControl(driverWalletRawValue.paymentType),
      paymentReference: new FormControl(driverWalletRawValue.paymentReference),
      orderId: new FormControl(driverWalletRawValue.orderId),
      notes: new FormControl(driverWalletRawValue.notes),
      driver: new FormControl(driverWalletRawValue.driver),
    });
  }

  getDriverWallet(form: DriverWalletFormGroup): IDriverWallet | NewDriverWallet {
    return form.getRawValue() as IDriverWallet | NewDriverWallet;
  }

  resetForm(form: DriverWalletFormGroup, driverWallet: DriverWalletFormGroupInput): void {
    const driverWalletRawValue = {...this.getFormDefaults(), ...driverWallet};
    form.reset(
      {
        ...driverWalletRawValue,
        id: {value: driverWalletRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DriverWalletFormDefaults {
    return {
      id: null,
    };
  }
}
