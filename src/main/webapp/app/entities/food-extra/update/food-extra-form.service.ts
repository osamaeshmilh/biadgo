import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IFoodExtra, NewFoodExtra} from '../food-extra.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFoodExtra for edit and NewFoodExtraFormGroupInput for create.
 */
type FoodExtraFormGroupInput = IFoodExtra | PartialWithRequiredKeyOf<NewFoodExtra>;

type FoodExtraFormDefaults = Pick<NewFoodExtra, 'id'>;

type FoodExtraFormGroupContent = {
  id: FormControl<IFoodExtra['id'] | NewFoodExtra['id']>;
  name: FormControl<IFoodExtra['name']>;
  nameAr: FormControl<IFoodExtra['nameAr']>;
  nameEn: FormControl<IFoodExtra['nameEn']>;
  price: FormControl<IFoodExtra['price']>;
  notes: FormControl<IFoodExtra['notes']>;
  food: FormControl<IFoodExtra['food']>;
};

export type FoodExtraFormGroup = FormGroup<FoodExtraFormGroupContent>;

@Injectable({providedIn: 'root'})
export class FoodExtraFormService {
  createFoodExtraFormGroup(foodExtra: FoodExtraFormGroupInput = {id: null}): FoodExtraFormGroup {
    const foodExtraRawValue = {
      ...this.getFormDefaults(),
      ...foodExtra,
    };
    return new FormGroup<FoodExtraFormGroupContent>({
      id: new FormControl(
        {value: foodExtraRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(foodExtraRawValue.name),
      nameAr: new FormControl(foodExtraRawValue.nameAr),
      nameEn: new FormControl(foodExtraRawValue.nameEn),
      price: new FormControl(foodExtraRawValue.price),
      notes: new FormControl(foodExtraRawValue.notes),
      food: new FormControl(foodExtraRawValue.food),
    });
  }

  getFoodExtra(form: FoodExtraFormGroup): IFoodExtra | NewFoodExtra {
    return form.getRawValue() as IFoodExtra | NewFoodExtra;
  }

  resetForm(form: FoodExtraFormGroup, foodExtra: FoodExtraFormGroupInput): void {
    const foodExtraRawValue = {...this.getFormDefaults(), ...foodExtra};
    form.reset(
      {
        ...foodExtraRawValue,
        id: {value: foodExtraRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FoodExtraFormDefaults {
    return {
      id: null,
    };
  }
}
