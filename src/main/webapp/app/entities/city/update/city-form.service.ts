import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {ICity, NewCity} from '../city.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICity for edit and NewCityFormGroupInput for create.
 */
type CityFormGroupInput = ICity | PartialWithRequiredKeyOf<NewCity>;

type CityFormDefaults = Pick<NewCity, 'id' | 'isActive'>;

type CityFormGroupContent = {
  id: FormControl<ICity['id'] | NewCity['id']>;
  name: FormControl<ICity['name']>;
  nameAr: FormControl<ICity['nameAr']>;
  nameEn: FormControl<ICity['nameEn']>;
  latitude: FormControl<ICity['latitude']>;
  longitude: FormControl<ICity['longitude']>;
  plusCode: FormControl<ICity['plusCode']>;
  radius: FormControl<ICity['radius']>;
  isActive: FormControl<ICity['isActive']>;
};

export type CityFormGroup = FormGroup<CityFormGroupContent>;

@Injectable({providedIn: 'root'})
export class CityFormService {
  createCityFormGroup(city: CityFormGroupInput = {id: null}): CityFormGroup {
    const cityRawValue = {
      ...this.getFormDefaults(),
      ...city,
    };
    return new FormGroup<CityFormGroupContent>({
      id: new FormControl(
        {value: cityRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(cityRawValue.name, {
        validators: [Validators.required],
      }),
      nameAr: new FormControl(cityRawValue.nameAr),
      nameEn: new FormControl(cityRawValue.nameEn),
      latitude: new FormControl(cityRawValue.latitude),
      longitude: new FormControl(cityRawValue.longitude),
      plusCode: new FormControl(cityRawValue.plusCode),
      radius: new FormControl(cityRawValue.radius),
      isActive: new FormControl(cityRawValue.isActive),
    });
  }

  getCity(form: CityFormGroup): ICity | NewCity {
    return form.getRawValue() as ICity | NewCity;
  }

  resetForm(form: CityFormGroup, city: CityFormGroupInput): void {
    const cityRawValue = {...this.getFormDefaults(), ...city};
    form.reset(
      {
        ...cityRawValue,
        id: {value: cityRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CityFormDefaults {
    return {
      id: null,
      isActive: false,
    };
  }
}
