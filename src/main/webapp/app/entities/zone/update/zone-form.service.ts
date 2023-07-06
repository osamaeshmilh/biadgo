import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IZone, NewZone} from '../zone.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IZone for edit and NewZoneFormGroupInput for create.
 */
type ZoneFormGroupInput = IZone | PartialWithRequiredKeyOf<NewZone>;

type ZoneFormDefaults = Pick<NewZone, 'id' | 'isActive'>;

type ZoneFormGroupContent = {
  id: FormControl<IZone['id'] | NewZone['id']>;
  name: FormControl<IZone['name']>;
  nameAr: FormControl<IZone['nameAr']>;
  nameEn: FormControl<IZone['nameEn']>;
  latitude: FormControl<IZone['latitude']>;
  longitude: FormControl<IZone['longitude']>;
  plusCode: FormControl<IZone['plusCode']>;
  radius: FormControl<IZone['radius']>;
  isActive: FormControl<IZone['isActive']>;
  notes: FormControl<IZone['notes']>;
  city: FormControl<IZone['city']>;
};

export type ZoneFormGroup = FormGroup<ZoneFormGroupContent>;

@Injectable({providedIn: 'root'})
export class ZoneFormService {
  createZoneFormGroup(zone: ZoneFormGroupInput = {id: null}): ZoneFormGroup {
    const zoneRawValue = {
      ...this.getFormDefaults(),
      ...zone,
    };
    return new FormGroup<ZoneFormGroupContent>({
      id: new FormControl(
        {value: zoneRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(zoneRawValue.name),
      nameAr: new FormControl(zoneRawValue.nameAr),
      nameEn: new FormControl(zoneRawValue.nameEn),
      latitude: new FormControl(zoneRawValue.latitude),
      longitude: new FormControl(zoneRawValue.longitude),
      plusCode: new FormControl(zoneRawValue.plusCode),
      radius: new FormControl(zoneRawValue.radius),
      isActive: new FormControl(zoneRawValue.isActive),
      notes: new FormControl(zoneRawValue.notes),
      city: new FormControl(zoneRawValue.city),
    });
  }

  getZone(form: ZoneFormGroup): IZone | NewZone {
    return form.getRawValue() as IZone | NewZone;
  }

  resetForm(form: ZoneFormGroup, zone: ZoneFormGroupInput): void {
    const zoneRawValue = {...this.getFormDefaults(), ...zone};
    form.reset(
      {
        ...zoneRawValue,
        id: {value: zoneRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ZoneFormDefaults {
    return {
      id: null,
      isActive: false,
    };
  }
}
