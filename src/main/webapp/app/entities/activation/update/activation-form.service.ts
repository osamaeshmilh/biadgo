import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import dayjs from 'dayjs/esm';
import {DATE_TIME_FORMAT} from 'app/config/input.constants';
import {IActivation, NewActivation} from '../activation.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IActivation for edit and NewActivationFormGroupInput for create.
 */
type ActivationFormGroupInput = IActivation | PartialWithRequiredKeyOf<NewActivation>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IActivation | NewActivation> = Omit<T, 'sentOn' | 'validUntil'> & {
  sentOn?: string | null;
  validUntil?: string | null;
};

type ActivationFormRawValue = FormValueOf<IActivation>;

type NewActivationFormRawValue = FormValueOf<NewActivation>;

type ActivationFormDefaults = Pick<NewActivation, 'id' | 'sentOn' | 'validUntil' | 'isUsed'>;

type ActivationFormGroupContent = {
  id: FormControl<ActivationFormRawValue['id'] | NewActivation['id']>;
  mobileNo: FormControl<ActivationFormRawValue['mobileNo']>;
  email: FormControl<ActivationFormRawValue['email']>;
  code: FormControl<ActivationFormRawValue['code']>;
  sentOn: FormControl<ActivationFormRawValue['sentOn']>;
  validUntil: FormControl<ActivationFormRawValue['validUntil']>;
  isUsed: FormControl<ActivationFormRawValue['isUsed']>;
};

export type ActivationFormGroup = FormGroup<ActivationFormGroupContent>;

@Injectable({providedIn: 'root'})
export class ActivationFormService {
  createActivationFormGroup(activation: ActivationFormGroupInput = {id: null}): ActivationFormGroup {
    const activationRawValue = this.convertActivationToActivationRawValue({
      ...this.getFormDefaults(),
      ...activation,
    });
    return new FormGroup<ActivationFormGroupContent>({
      id: new FormControl(
        {value: activationRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      mobileNo: new FormControl(activationRawValue.mobileNo),
      email: new FormControl(activationRawValue.email),
      code: new FormControl(activationRawValue.code),
      sentOn: new FormControl(activationRawValue.sentOn),
      validUntil: new FormControl(activationRawValue.validUntil),
      isUsed: new FormControl(activationRawValue.isUsed),
    });
  }

  getActivation(form: ActivationFormGroup): IActivation | NewActivation {
    return this.convertActivationRawValueToActivation(form.getRawValue() as ActivationFormRawValue | NewActivationFormRawValue);
  }

  resetForm(form: ActivationFormGroup, activation: ActivationFormGroupInput): void {
    const activationRawValue = this.convertActivationToActivationRawValue({...this.getFormDefaults(), ...activation});
    form.reset(
      {
        ...activationRawValue,
        id: {value: activationRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ActivationFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      sentOn: currentTime,
      validUntil: currentTime,
      isUsed: false,
    };
  }

  private convertActivationRawValueToActivation(
    rawActivation: ActivationFormRawValue | NewActivationFormRawValue
  ): IActivation | NewActivation {
    return {
      ...rawActivation,
      sentOn: dayjs(rawActivation.sentOn, DATE_TIME_FORMAT),
      validUntil: dayjs(rawActivation.validUntil, DATE_TIME_FORMAT),
    };
  }

  private convertActivationToActivationRawValue(
    activation: IActivation | (Partial<NewActivation> & ActivationFormDefaults)
  ): ActivationFormRawValue | PartialWithRequiredKeyOf<NewActivationFormRawValue> {
    return {
      ...activation,
      sentOn: activation.sentOn ? activation.sentOn.format(DATE_TIME_FORMAT) : undefined,
      validUntil: activation.validUntil ? activation.validUntil.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
