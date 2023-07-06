import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IDriverReview, NewDriverReview} from '../driver-review.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDriverReview for edit and NewDriverReviewFormGroupInput for create.
 */
type DriverReviewFormGroupInput = IDriverReview | PartialWithRequiredKeyOf<NewDriverReview>;

type DriverReviewFormDefaults = Pick<NewDriverReview, 'id'>;

type DriverReviewFormGroupContent = {
  id: FormControl<IDriverReview['id'] | NewDriverReview['id']>;
  details: FormControl<IDriverReview['details']>;
  rate: FormControl<IDriverReview['rate']>;
  customer: FormControl<IDriverReview['customer']>;
  driver: FormControl<IDriverReview['driver']>;
};

export type DriverReviewFormGroup = FormGroup<DriverReviewFormGroupContent>;

@Injectable({providedIn: 'root'})
export class DriverReviewFormService {
  createDriverReviewFormGroup(driverReview: DriverReviewFormGroupInput = {id: null}): DriverReviewFormGroup {
    const driverReviewRawValue = {
      ...this.getFormDefaults(),
      ...driverReview,
    };
    return new FormGroup<DriverReviewFormGroupContent>({
      id: new FormControl(
        {value: driverReviewRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      details: new FormControl(driverReviewRawValue.details),
      rate: new FormControl(driverReviewRawValue.rate),
      customer: new FormControl(driverReviewRawValue.customer),
      driver: new FormControl(driverReviewRawValue.driver),
    });
  }

  getDriverReview(form: DriverReviewFormGroup): IDriverReview | NewDriverReview {
    return form.getRawValue() as IDriverReview | NewDriverReview;
  }

  resetForm(form: DriverReviewFormGroup, driverReview: DriverReviewFormGroupInput): void {
    const driverReviewRawValue = {...this.getFormDefaults(), ...driverReview};
    form.reset(
      {
        ...driverReviewRawValue,
        id: {value: driverReviewRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DriverReviewFormDefaults {
    return {
      id: null,
    };
  }
}
