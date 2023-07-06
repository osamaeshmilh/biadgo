import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IRestaurantWallet, NewRestaurantWallet} from '../restaurant-wallet.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRestaurantWallet for edit and NewRestaurantWalletFormGroupInput for create.
 */
type RestaurantWalletFormGroupInput = IRestaurantWallet | PartialWithRequiredKeyOf<NewRestaurantWallet>;

type RestaurantWalletFormDefaults = Pick<NewRestaurantWallet, 'id'>;

type RestaurantWalletFormGroupContent = {
  id: FormControl<IRestaurantWallet['id'] | NewRestaurantWallet['id']>;
  transactionNo: FormControl<IRestaurantWallet['transactionNo']>;
  amount: FormControl<IRestaurantWallet['amount']>;
  walletAction: FormControl<IRestaurantWallet['walletAction']>;
  totalBeforeAction: FormControl<IRestaurantWallet['totalBeforeAction']>;
  totalAfterAction: FormControl<IRestaurantWallet['totalAfterAction']>;
  paymentType: FormControl<IRestaurantWallet['paymentType']>;
  paymentReference: FormControl<IRestaurantWallet['paymentReference']>;
  notes: FormControl<IRestaurantWallet['notes']>;
  restaurant: FormControl<IRestaurantWallet['restaurant']>;
};

export type RestaurantWalletFormGroup = FormGroup<RestaurantWalletFormGroupContent>;

@Injectable({providedIn: 'root'})
export class RestaurantWalletFormService {
  createRestaurantWalletFormGroup(restaurantWallet: RestaurantWalletFormGroupInput = {id: null}): RestaurantWalletFormGroup {
    const restaurantWalletRawValue = {
      ...this.getFormDefaults(),
      ...restaurantWallet,
    };
    return new FormGroup<RestaurantWalletFormGroupContent>({
      id: new FormControl(
        {value: restaurantWalletRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      transactionNo: new FormControl(restaurantWalletRawValue.transactionNo),
      amount: new FormControl(restaurantWalletRawValue.amount),
      walletAction: new FormControl(restaurantWalletRawValue.walletAction),
      totalBeforeAction: new FormControl(restaurantWalletRawValue.totalBeforeAction),
      totalAfterAction: new FormControl(restaurantWalletRawValue.totalAfterAction),
      paymentType: new FormControl(restaurantWalletRawValue.paymentType),
      paymentReference: new FormControl(restaurantWalletRawValue.paymentReference),
      notes: new FormControl(restaurantWalletRawValue.notes),
      restaurant: new FormControl(restaurantWalletRawValue.restaurant),
    });
  }

  getRestaurantWallet(form: RestaurantWalletFormGroup): IRestaurantWallet | NewRestaurantWallet {
    return form.getRawValue() as IRestaurantWallet | NewRestaurantWallet;
  }

  resetForm(form: RestaurantWalletFormGroup, restaurantWallet: RestaurantWalletFormGroupInput): void {
    const restaurantWalletRawValue = {...this.getFormDefaults(), ...restaurantWallet};
    form.reset(
      {
        ...restaurantWalletRawValue,
        id: {value: restaurantWalletRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RestaurantWalletFormDefaults {
    return {
      id: null,
    };
  }
}
