import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import dayjs from 'dayjs/esm';
import {DATE_TIME_FORMAT} from 'app/config/input.constants';
import {IDriverLocation, NewDriverLocation} from '../driver-location.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDriverLocation for edit and NewDriverLocationFormGroupInput for create.
 */
type DriverLocationFormGroupInput = IDriverLocation | PartialWithRequiredKeyOf<NewDriverLocation>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IDriverLocation | NewDriverLocation> = Omit<T, 'locationDateTime'> & {
  locationDateTime?: string | null;
};

type DriverLocationFormRawValue = FormValueOf<IDriverLocation>;

type NewDriverLocationFormRawValue = FormValueOf<NewDriverLocation>;

type DriverLocationFormDefaults = Pick<NewDriverLocation, 'id' | 'locationDateTime'>;

type DriverLocationFormGroupContent = {
  id: FormControl<DriverLocationFormRawValue['id'] | NewDriverLocation['id']>;
  latitude: FormControl<DriverLocationFormRawValue['latitude']>;
  longitude: FormControl<DriverLocationFormRawValue['longitude']>;
  plusCode: FormControl<DriverLocationFormRawValue['plusCode']>;
  locationDateTime: FormControl<DriverLocationFormRawValue['locationDateTime']>;
  driver: FormControl<DriverLocationFormRawValue['driver']>;
};

export type DriverLocationFormGroup = FormGroup<DriverLocationFormGroupContent>;

@Injectable({providedIn: 'root'})
export class DriverLocationFormService {
  createDriverLocationFormGroup(driverLocation: DriverLocationFormGroupInput = {id: null}): DriverLocationFormGroup {
    const driverLocationRawValue = this.convertDriverLocationToDriverLocationRawValue({
      ...this.getFormDefaults(),
      ...driverLocation,
    });
    return new FormGroup<DriverLocationFormGroupContent>({
      id: new FormControl(
        {value: driverLocationRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      latitude: new FormControl(driverLocationRawValue.latitude),
      longitude: new FormControl(driverLocationRawValue.longitude),
      plusCode: new FormControl(driverLocationRawValue.plusCode),
      locationDateTime: new FormControl(driverLocationRawValue.locationDateTime),
      driver: new FormControl(driverLocationRawValue.driver),
    });
  }

  getDriverLocation(form: DriverLocationFormGroup): IDriverLocation | NewDriverLocation {
    return this.convertDriverLocationRawValueToDriverLocation(
      form.getRawValue() as DriverLocationFormRawValue | NewDriverLocationFormRawValue
    );
  }

  resetForm(form: DriverLocationFormGroup, driverLocation: DriverLocationFormGroupInput): void {
    const driverLocationRawValue = this.convertDriverLocationToDriverLocationRawValue({...this.getFormDefaults(), ...driverLocation});
    form.reset(
      {
        ...driverLocationRawValue,
        id: {value: driverLocationRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DriverLocationFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      locationDateTime: currentTime,
    };
  }

  private convertDriverLocationRawValueToDriverLocation(
    rawDriverLocation: DriverLocationFormRawValue | NewDriverLocationFormRawValue
  ): IDriverLocation | NewDriverLocation {
    return {
      ...rawDriverLocation,
      locationDateTime: dayjs(rawDriverLocation.locationDateTime, DATE_TIME_FORMAT),
    };
  }

  private convertDriverLocationToDriverLocationRawValue(
    driverLocation: IDriverLocation | (Partial<NewDriverLocation> & DriverLocationFormDefaults)
  ): DriverLocationFormRawValue | PartialWithRequiredKeyOf<NewDriverLocationFormRawValue> {
    return {
      ...driverLocation,
      locationDateTime: driverLocation.locationDateTime ? driverLocation.locationDateTime.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
