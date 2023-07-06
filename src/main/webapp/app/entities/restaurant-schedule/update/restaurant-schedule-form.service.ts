import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IRestaurantSchedule, NewRestaurantSchedule} from '../restaurant-schedule.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRestaurantSchedule for edit and NewRestaurantScheduleFormGroupInput for create.
 */
type RestaurantScheduleFormGroupInput = IRestaurantSchedule | PartialWithRequiredKeyOf<NewRestaurantSchedule>;

type RestaurantScheduleFormDefaults = Pick<NewRestaurantSchedule, 'id'>;

type RestaurantScheduleFormGroupContent = {
  id: FormControl<IRestaurantSchedule['id'] | NewRestaurantSchedule['id']>;
  dayOfWeek: FormControl<IRestaurantSchedule['dayOfWeek']>;
  openingTime: FormControl<IRestaurantSchedule['openingTime']>;
  closingTime: FormControl<IRestaurantSchedule['closingTime']>;
  restaurant: FormControl<IRestaurantSchedule['restaurant']>;
};

export type RestaurantScheduleFormGroup = FormGroup<RestaurantScheduleFormGroupContent>;

@Injectable({providedIn: 'root'})
export class RestaurantScheduleFormService {
  createRestaurantScheduleFormGroup(restaurantSchedule: RestaurantScheduleFormGroupInput = {id: null}): RestaurantScheduleFormGroup {
    const restaurantScheduleRawValue = {
      ...this.getFormDefaults(),
      ...restaurantSchedule,
    };
    return new FormGroup<RestaurantScheduleFormGroupContent>({
      id: new FormControl(
        {value: restaurantScheduleRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      dayOfWeek: new FormControl(restaurantScheduleRawValue.dayOfWeek),
      openingTime: new FormControl(restaurantScheduleRawValue.openingTime),
      closingTime: new FormControl(restaurantScheduleRawValue.closingTime),
      restaurant: new FormControl(restaurantScheduleRawValue.restaurant),
    });
  }

  getRestaurantSchedule(form: RestaurantScheduleFormGroup): IRestaurantSchedule | NewRestaurantSchedule {
    return form.getRawValue() as IRestaurantSchedule | NewRestaurantSchedule;
  }

  resetForm(form: RestaurantScheduleFormGroup, restaurantSchedule: RestaurantScheduleFormGroupInput): void {
    const restaurantScheduleRawValue = {...this.getFormDefaults(), ...restaurantSchedule};
    form.reset(
      {
        ...restaurantScheduleRawValue,
        id: {value: restaurantScheduleRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RestaurantScheduleFormDefaults {
    return {
      id: null,
    };
  }
}
