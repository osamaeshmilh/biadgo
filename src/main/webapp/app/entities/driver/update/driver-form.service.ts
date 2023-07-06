import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import dayjs from 'dayjs/esm';
import {DATE_TIME_FORMAT} from 'app/config/input.constants';
import {IDriver, NewDriver} from '../driver.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDriver for edit and NewDriverFormGroupInput for create.
 */
type DriverFormGroupInput = IDriver | PartialWithRequiredKeyOf<NewDriver>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IDriver | NewDriver> = Omit<T, 'lastLocationDateTime'> & {
  lastLocationDateTime?: string | null;
};

type DriverFormRawValue = FormValueOf<IDriver>;

type NewDriverFormRawValue = FormValueOf<NewDriver>;

type DriverFormDefaults = Pick<NewDriver, 'id' | 'lastLocationDateTime' | 'isAvailable'>;

type DriverFormGroupContent = {
  id: FormControl<DriverFormRawValue['id'] | NewDriver['id']>;
  name: FormControl<DriverFormRawValue['name']>;
  nameAr: FormControl<DriverFormRawValue['nameAr']>;
  nameEn: FormControl<DriverFormRawValue['nameEn']>;
  mobileNo: FormControl<DriverFormRawValue['mobileNo']>;
  email: FormControl<DriverFormRawValue['email']>;
  imageUrl: FormControl<DriverFormRawValue['imageUrl']>;
  image: FormControl<DriverFormRawValue['image']>;
  imageContentType: FormControl<DriverFormRawValue['imageContentType']>;
  driverType: FormControl<DriverFormRawValue['driverType']>;
  driverPaymentType: FormControl<DriverFormRawValue['driverPaymentType']>;
  vehicleType: FormControl<DriverFormRawValue['vehicleType']>;
  driverStatus: FormControl<DriverFormRawValue['driverStatus']>;
  commissionAmount: FormControl<DriverFormRawValue['commissionAmount']>;
  salaryAmount: FormControl<DriverFormRawValue['salaryAmount']>;
  latitude: FormControl<DriverFormRawValue['latitude']>;
  longitude: FormControl<DriverFormRawValue['longitude']>;
  plusCode: FormControl<DriverFormRawValue['plusCode']>;
  lastLocationDateTime: FormControl<DriverFormRawValue['lastLocationDateTime']>;
  isAvailable: FormControl<DriverFormRawValue['isAvailable']>;
  notes: FormControl<DriverFormRawValue['notes']>;
  user: FormControl<DriverFormRawValue['user']>;
  zone: FormControl<DriverFormRawValue['zone']>;
};

export type DriverFormGroup = FormGroup<DriverFormGroupContent>;

@Injectable({providedIn: 'root'})
export class DriverFormService {
  createDriverFormGroup(driver: DriverFormGroupInput = {id: null}): DriverFormGroup {
    const driverRawValue = this.convertDriverToDriverRawValue({
      ...this.getFormDefaults(),
      ...driver,
    });
    return new FormGroup<DriverFormGroupContent>({
      id: new FormControl(
        {value: driverRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(driverRawValue.name, {
        validators: [Validators.required],
      }),
      nameAr: new FormControl(driverRawValue.nameAr),
      nameEn: new FormControl(driverRawValue.nameEn),
      mobileNo: new FormControl(driverRawValue.mobileNo),
      email: new FormControl(driverRawValue.email),
      imageUrl: new FormControl(driverRawValue.imageUrl),
      image: new FormControl(driverRawValue.image),
      imageContentType: new FormControl(driverRawValue.imageContentType),
      driverType: new FormControl(driverRawValue.driverType),
      driverPaymentType: new FormControl(driverRawValue.driverPaymentType),
      vehicleType: new FormControl(driverRawValue.vehicleType),
      driverStatus: new FormControl(driverRawValue.driverStatus),
      commissionAmount: new FormControl(driverRawValue.commissionAmount),
      salaryAmount: new FormControl(driverRawValue.salaryAmount),
      latitude: new FormControl(driverRawValue.latitude),
      longitude: new FormControl(driverRawValue.longitude),
      plusCode: new FormControl(driverRawValue.plusCode),
      lastLocationDateTime: new FormControl(driverRawValue.lastLocationDateTime),
      isAvailable: new FormControl(driverRawValue.isAvailable),
      notes: new FormControl(driverRawValue.notes),
      user: new FormControl(driverRawValue.user),
      zone: new FormControl(driverRawValue.zone),
    });
  }

  getDriver(form: DriverFormGroup): IDriver | NewDriver {
    return this.convertDriverRawValueToDriver(form.getRawValue() as DriverFormRawValue | NewDriverFormRawValue);
  }

  resetForm(form: DriverFormGroup, driver: DriverFormGroupInput): void {
    const driverRawValue = this.convertDriverToDriverRawValue({...this.getFormDefaults(), ...driver});
    form.reset(
      {
        ...driverRawValue,
        id: {value: driverRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DriverFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lastLocationDateTime: currentTime,
      isAvailable: false,
    };
  }

  private convertDriverRawValueToDriver(rawDriver: DriverFormRawValue | NewDriverFormRawValue): IDriver | NewDriver {
    return {
      ...rawDriver,
      lastLocationDateTime: dayjs(rawDriver.lastLocationDateTime, DATE_TIME_FORMAT),
    };
  }

  private convertDriverToDriverRawValue(
    driver: IDriver | (Partial<NewDriver> & DriverFormDefaults)
  ): DriverFormRawValue | PartialWithRequiredKeyOf<NewDriverFormRawValue> {
    return {
      ...driver,
      lastLocationDateTime: driver.lastLocationDateTime ? driver.lastLocationDateTime.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
