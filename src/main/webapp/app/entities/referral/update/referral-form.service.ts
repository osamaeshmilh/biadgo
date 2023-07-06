import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import dayjs from 'dayjs/esm';
import {DATE_TIME_FORMAT} from 'app/config/input.constants';
import {IReferral, NewReferral} from '../referral.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IReferral for edit and NewReferralFormGroupInput for create.
 */
type ReferralFormGroupInput = IReferral | PartialWithRequiredKeyOf<NewReferral>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IReferral | NewReferral> = Omit<T, 'usedDateTime'> & {
  usedDateTime?: string | null;
};

type ReferralFormRawValue = FormValueOf<IReferral>;

type NewReferralFormRawValue = FormValueOf<NewReferral>;

type ReferralFormDefaults = Pick<NewReferral, 'id' | 'isUsed' | 'usedDateTime'>;

type ReferralFormGroupContent = {
  id: FormControl<ReferralFormRawValue['id'] | NewReferral['id']>;
  referralCode: FormControl<ReferralFormRawValue['referralCode']>;
  referrerAmount: FormControl<ReferralFormRawValue['referrerAmount']>;
  referredCustomerAmount: FormControl<ReferralFormRawValue['referredCustomerAmount']>;
  expiryDate: FormControl<ReferralFormRawValue['expiryDate']>;
  isUsed: FormControl<ReferralFormRawValue['isUsed']>;
  usedDateTime: FormControl<ReferralFormRawValue['usedDateTime']>;
  notes: FormControl<ReferralFormRawValue['notes']>;
  referredCustomer: FormControl<ReferralFormRawValue['referredCustomer']>;
  referrerCustomer: FormControl<ReferralFormRawValue['referrerCustomer']>;
};

export type ReferralFormGroup = FormGroup<ReferralFormGroupContent>;

@Injectable({providedIn: 'root'})
export class ReferralFormService {
  createReferralFormGroup(referral: ReferralFormGroupInput = {id: null}): ReferralFormGroup {
    const referralRawValue = this.convertReferralToReferralRawValue({
      ...this.getFormDefaults(),
      ...referral,
    });
    return new FormGroup<ReferralFormGroupContent>({
      id: new FormControl(
        {value: referralRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      referralCode: new FormControl(referralRawValue.referralCode),
      referrerAmount: new FormControl(referralRawValue.referrerAmount),
      referredCustomerAmount: new FormControl(referralRawValue.referredCustomerAmount),
      expiryDate: new FormControl(referralRawValue.expiryDate),
      isUsed: new FormControl(referralRawValue.isUsed),
      usedDateTime: new FormControl(referralRawValue.usedDateTime),
      notes: new FormControl(referralRawValue.notes),
      referredCustomer: new FormControl(referralRawValue.referredCustomer),
      referrerCustomer: new FormControl(referralRawValue.referrerCustomer),
    });
  }

  getReferral(form: ReferralFormGroup): IReferral | NewReferral {
    return this.convertReferralRawValueToReferral(form.getRawValue() as ReferralFormRawValue | NewReferralFormRawValue);
  }

  resetForm(form: ReferralFormGroup, referral: ReferralFormGroupInput): void {
    const referralRawValue = this.convertReferralToReferralRawValue({...this.getFormDefaults(), ...referral});
    form.reset(
      {
        ...referralRawValue,
        id: {value: referralRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ReferralFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      isUsed: false,
      usedDateTime: currentTime,
    };
  }

  private convertReferralRawValueToReferral(rawReferral: ReferralFormRawValue | NewReferralFormRawValue): IReferral | NewReferral {
    return {
      ...rawReferral,
      usedDateTime: dayjs(rawReferral.usedDateTime, DATE_TIME_FORMAT),
    };
  }

  private convertReferralToReferralRawValue(
    referral: IReferral | (Partial<NewReferral> & ReferralFormDefaults)
  ): ReferralFormRawValue | PartialWithRequiredKeyOf<NewReferralFormRawValue> {
    return {
      ...referral,
      usedDateTime: referral.usedDateTime ? referral.usedDateTime.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
