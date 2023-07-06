import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {ICustomer, NewCustomer} from '../customer.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICustomer for edit and NewCustomerFormGroupInput for create.
 */
type CustomerFormGroupInput = ICustomer | PartialWithRequiredKeyOf<NewCustomer>;

type CustomerFormDefaults = Pick<NewCustomer, 'id' | 'isBanned' | 'isVerified' | 'verifiedByEmail' | 'verifiedByMobileNo'>;

type CustomerFormGroupContent = {
  id: FormControl<ICustomer['id'] | NewCustomer['id']>;
  name: FormControl<ICustomer['name']>;
  email: FormControl<ICustomer['email']>;
  mobileNo: FormControl<ICustomer['mobileNo']>;
  googleId: FormControl<ICustomer['googleId']>;
  facebookId: FormControl<ICustomer['facebookId']>;
  appleId: FormControl<ICustomer['appleId']>;
  isBanned: FormControl<ICustomer['isBanned']>;
  isVerified: FormControl<ICustomer['isVerified']>;
  verifiedByEmail: FormControl<ICustomer['verifiedByEmail']>;
  verifiedByMobileNo: FormControl<ICustomer['verifiedByMobileNo']>;
  walletPublicKey: FormControl<ICustomer['walletPublicKey']>;
  image: FormControl<ICustomer['image']>;
  imageContentType: FormControl<ICustomer['imageContentType']>;
  imageUrl: FormControl<ICustomer['imageUrl']>;
  languageCode: FormControl<ICustomer['languageCode']>;
  notes: FormControl<ICustomer['notes']>;
  user: FormControl<ICustomer['user']>;
};

export type CustomerFormGroup = FormGroup<CustomerFormGroupContent>;

@Injectable({providedIn: 'root'})
export class CustomerFormService {
  createCustomerFormGroup(customer: CustomerFormGroupInput = {id: null}): CustomerFormGroup {
    const customerRawValue = {
      ...this.getFormDefaults(),
      ...customer,
    };
    return new FormGroup<CustomerFormGroupContent>({
      id: new FormControl(
        {value: customerRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(customerRawValue.name, {
        validators: [Validators.required],
      }),
      email: new FormControl(customerRawValue.email),
      mobileNo: new FormControl(customerRawValue.mobileNo),
      googleId: new FormControl(customerRawValue.googleId),
      facebookId: new FormControl(customerRawValue.facebookId),
      appleId: new FormControl(customerRawValue.appleId),
      isBanned: new FormControl(customerRawValue.isBanned),
      isVerified: new FormControl(customerRawValue.isVerified),
      verifiedByEmail: new FormControl(customerRawValue.verifiedByEmail),
      verifiedByMobileNo: new FormControl(customerRawValue.verifiedByMobileNo),
      walletPublicKey: new FormControl(customerRawValue.walletPublicKey),
      image: new FormControl(customerRawValue.image),
      imageContentType: new FormControl(customerRawValue.imageContentType),
      imageUrl: new FormControl(customerRawValue.imageUrl),
      languageCode: new FormControl(customerRawValue.languageCode),
      notes: new FormControl(customerRawValue.notes),
      user: new FormControl(customerRawValue.user),
    });
  }

  getCustomer(form: CustomerFormGroup): ICustomer | NewCustomer {
    return form.getRawValue() as ICustomer | NewCustomer;
  }

  resetForm(form: CustomerFormGroup, customer: CustomerFormGroupInput): void {
    const customerRawValue = {...this.getFormDefaults(), ...customer};
    form.reset(
      {
        ...customerRawValue,
        id: {value: customerRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CustomerFormDefaults {
    return {
      id: null,
      isBanned: false,
      isVerified: false,
      verifiedByEmail: false,
      verifiedByMobileNo: false,
    };
  }
}
