import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {ICoupon, NewCoupon} from '../coupon.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICoupon for edit and NewCouponFormGroupInput for create.
 */
type CouponFormGroupInput = ICoupon | PartialWithRequiredKeyOf<NewCoupon>;

type CouponFormDefaults = Pick<NewCoupon, 'id' | 'isActive'>;

type CouponFormGroupContent = {
  id: FormControl<ICoupon['id'] | NewCoupon['id']>;
  code: FormControl<ICoupon['code']>;
  couponType: FormControl<ICoupon['couponType']>;
  amount: FormControl<ICoupon['amount']>;
  minimumAmount: FormControl<ICoupon['minimumAmount']>;
  useLimit: FormControl<ICoupon['useLimit']>;
  useCount: FormControl<ICoupon['useCount']>;
  expiryDate: FormControl<ICoupon['expiryDate']>;
  isActive: FormControl<ICoupon['isActive']>;
  notes: FormControl<ICoupon['notes']>;
};

export type CouponFormGroup = FormGroup<CouponFormGroupContent>;

@Injectable({providedIn: 'root'})
export class CouponFormService {
  createCouponFormGroup(coupon: CouponFormGroupInput = {id: null}): CouponFormGroup {
    const couponRawValue = {
      ...this.getFormDefaults(),
      ...coupon,
    };
    return new FormGroup<CouponFormGroupContent>({
      id: new FormControl(
        {value: couponRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      code: new FormControl(couponRawValue.code),
      couponType: new FormControl(couponRawValue.couponType),
      amount: new FormControl(couponRawValue.amount),
      minimumAmount: new FormControl(couponRawValue.minimumAmount),
      useLimit: new FormControl(couponRawValue.useLimit),
      useCount: new FormControl(couponRawValue.useCount),
      expiryDate: new FormControl(couponRawValue.expiryDate),
      isActive: new FormControl(couponRawValue.isActive),
      notes: new FormControl(couponRawValue.notes),
    });
  }

  getCoupon(form: CouponFormGroup): ICoupon | NewCoupon {
    return form.getRawValue() as ICoupon | NewCoupon;
  }

  resetForm(form: CouponFormGroup, coupon: CouponFormGroupInput): void {
    const couponRawValue = {...this.getFormDefaults(), ...coupon};
    form.reset(
      {
        ...couponRawValue,
        id: {value: couponRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CouponFormDefaults {
    return {
      id: null,
      isActive: false,
    };
  }
}
