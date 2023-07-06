import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IDeliveryAddress, NewDeliveryAddress} from '../delivery-address.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDeliveryAddress for edit and NewDeliveryAddressFormGroupInput for create.
 */
type DeliveryAddressFormGroupInput = IDeliveryAddress | PartialWithRequiredKeyOf<NewDeliveryAddress>;

type DeliveryAddressFormDefaults = Pick<NewDeliveryAddress, 'id' | 'isDefault' | 'isActive'>;

type DeliveryAddressFormGroupContent = {
  id: FormControl<IDeliveryAddress['id'] | NewDeliveryAddress['id']>;
  title: FormControl<IDeliveryAddress['title']>;
  address: FormControl<IDeliveryAddress['address']>;
  details: FormControl<IDeliveryAddress['details']>;
  phone: FormControl<IDeliveryAddress['phone']>;
  isDefault: FormControl<IDeliveryAddress['isDefault']>;
  latitude: FormControl<IDeliveryAddress['latitude']>;
  longitude: FormControl<IDeliveryAddress['longitude']>;
  plusCode: FormControl<IDeliveryAddress['plusCode']>;
  isActive: FormControl<IDeliveryAddress['isActive']>;
  notes: FormControl<IDeliveryAddress['notes']>;
  customer: FormControl<IDeliveryAddress['customer']>;
  zone: FormControl<IDeliveryAddress['zone']>;
};

export type DeliveryAddressFormGroup = FormGroup<DeliveryAddressFormGroupContent>;

@Injectable({providedIn: 'root'})
export class DeliveryAddressFormService {
  createDeliveryAddressFormGroup(deliveryAddress: DeliveryAddressFormGroupInput = {id: null}): DeliveryAddressFormGroup {
    const deliveryAddressRawValue = {
      ...this.getFormDefaults(),
      ...deliveryAddress,
    };
    return new FormGroup<DeliveryAddressFormGroupContent>({
      id: new FormControl(
        {value: deliveryAddressRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      title: new FormControl(deliveryAddressRawValue.title),
      address: new FormControl(deliveryAddressRawValue.address),
      details: new FormControl(deliveryAddressRawValue.details),
      phone: new FormControl(deliveryAddressRawValue.phone),
      isDefault: new FormControl(deliveryAddressRawValue.isDefault),
      latitude: new FormControl(deliveryAddressRawValue.latitude),
      longitude: new FormControl(deliveryAddressRawValue.longitude),
      plusCode: new FormControl(deliveryAddressRawValue.plusCode),
      isActive: new FormControl(deliveryAddressRawValue.isActive),
      notes: new FormControl(deliveryAddressRawValue.notes),
      customer: new FormControl(deliveryAddressRawValue.customer),
      zone: new FormControl(deliveryAddressRawValue.zone),
    });
  }

  getDeliveryAddress(form: DeliveryAddressFormGroup): IDeliveryAddress | NewDeliveryAddress {
    return form.getRawValue() as IDeliveryAddress | NewDeliveryAddress;
  }

  resetForm(form: DeliveryAddressFormGroup, deliveryAddress: DeliveryAddressFormGroupInput): void {
    const deliveryAddressRawValue = {...this.getFormDefaults(), ...deliveryAddress};
    form.reset(
      {
        ...deliveryAddressRawValue,
        id: {value: deliveryAddressRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DeliveryAddressFormDefaults {
    return {
      id: null,
      isDefault: false,
      isActive: false,
    };
  }
}
