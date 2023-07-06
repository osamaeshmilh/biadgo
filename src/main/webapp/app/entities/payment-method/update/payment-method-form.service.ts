import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IPaymentMethod, NewPaymentMethod} from '../payment-method.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPaymentMethod for edit and NewPaymentMethodFormGroupInput for create.
 */
type PaymentMethodFormGroupInput = IPaymentMethod | PartialWithRequiredKeyOf<NewPaymentMethod>;

type PaymentMethodFormDefaults = Pick<NewPaymentMethod, 'id' | 'isActive'>;

type PaymentMethodFormGroupContent = {
  id: FormControl<IPaymentMethod['id'] | NewPaymentMethod['id']>;
  name: FormControl<IPaymentMethod['name']>;
  nameAr: FormControl<IPaymentMethod['nameAr']>;
  nameEn: FormControl<IPaymentMethod['nameEn']>;
  menuOrder: FormControl<IPaymentMethod['menuOrder']>;
  imageUrl: FormControl<IPaymentMethod['imageUrl']>;
  image: FormControl<IPaymentMethod['image']>;
  imageContentType: FormControl<IPaymentMethod['imageContentType']>;
  details: FormControl<IPaymentMethod['details']>;
  feePercentage: FormControl<IPaymentMethod['feePercentage']>;
  paymentType: FormControl<IPaymentMethod['paymentType']>;
  isActive: FormControl<IPaymentMethod['isActive']>;
  notes: FormControl<IPaymentMethod['notes']>;
};

export type PaymentMethodFormGroup = FormGroup<PaymentMethodFormGroupContent>;

@Injectable({providedIn: 'root'})
export class PaymentMethodFormService {
  createPaymentMethodFormGroup(paymentMethod: PaymentMethodFormGroupInput = {id: null}): PaymentMethodFormGroup {
    const paymentMethodRawValue = {
      ...this.getFormDefaults(),
      ...paymentMethod,
    };
    return new FormGroup<PaymentMethodFormGroupContent>({
      id: new FormControl(
        {value: paymentMethodRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(paymentMethodRawValue.name),
      nameAr: new FormControl(paymentMethodRawValue.nameAr),
      nameEn: new FormControl(paymentMethodRawValue.nameEn),
      menuOrder: new FormControl(paymentMethodRawValue.menuOrder),
      imageUrl: new FormControl(paymentMethodRawValue.imageUrl),
      image: new FormControl(paymentMethodRawValue.image),
      imageContentType: new FormControl(paymentMethodRawValue.imageContentType),
      details: new FormControl(paymentMethodRawValue.details),
      feePercentage: new FormControl(paymentMethodRawValue.feePercentage),
      paymentType: new FormControl(paymentMethodRawValue.paymentType),
      isActive: new FormControl(paymentMethodRawValue.isActive),
      notes: new FormControl(paymentMethodRawValue.notes),
    });
  }

  getPaymentMethod(form: PaymentMethodFormGroup): IPaymentMethod | NewPaymentMethod {
    return form.getRawValue() as IPaymentMethod | NewPaymentMethod;
  }

  resetForm(form: PaymentMethodFormGroup, paymentMethod: PaymentMethodFormGroupInput): void {
    const paymentMethodRawValue = {...this.getFormDefaults(), ...paymentMethod};
    form.reset(
      {
        ...paymentMethodRawValue,
        id: {value: paymentMethodRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): PaymentMethodFormDefaults {
    return {
      id: null,
      isActive: false,
    };
  }
}
