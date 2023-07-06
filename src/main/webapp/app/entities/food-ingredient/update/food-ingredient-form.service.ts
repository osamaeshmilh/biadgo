import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IFoodIngredient, NewFoodIngredient} from '../food-ingredient.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFoodIngredient for edit and NewFoodIngredientFormGroupInput for create.
 */
type FoodIngredientFormGroupInput = IFoodIngredient | PartialWithRequiredKeyOf<NewFoodIngredient>;

type FoodIngredientFormDefaults = Pick<NewFoodIngredient, 'id'>;

type FoodIngredientFormGroupContent = {
  id: FormControl<IFoodIngredient['id'] | NewFoodIngredient['id']>;
  name: FormControl<IFoodIngredient['name']>;
  nameAr: FormControl<IFoodIngredient['nameAr']>;
  nameEn: FormControl<IFoodIngredient['nameEn']>;
  notes: FormControl<IFoodIngredient['notes']>;
  food: FormControl<IFoodIngredient['food']>;
};

export type FoodIngredientFormGroup = FormGroup<FoodIngredientFormGroupContent>;

@Injectable({providedIn: 'root'})
export class FoodIngredientFormService {
  createFoodIngredientFormGroup(foodIngredient: FoodIngredientFormGroupInput = {id: null}): FoodIngredientFormGroup {
    const foodIngredientRawValue = {
      ...this.getFormDefaults(),
      ...foodIngredient,
    };
    return new FormGroup<FoodIngredientFormGroupContent>({
      id: new FormControl(
        {value: foodIngredientRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(foodIngredientRawValue.name),
      nameAr: new FormControl(foodIngredientRawValue.nameAr),
      nameEn: new FormControl(foodIngredientRawValue.nameEn),
      notes: new FormControl(foodIngredientRawValue.notes),
      food: new FormControl(foodIngredientRawValue.food),
    });
  }

  getFoodIngredient(form: FoodIngredientFormGroup): IFoodIngredient | NewFoodIngredient {
    return form.getRawValue() as IFoodIngredient | NewFoodIngredient;
  }

  resetForm(form: FoodIngredientFormGroup, foodIngredient: FoodIngredientFormGroupInput): void {
    const foodIngredientRawValue = {...this.getFormDefaults(), ...foodIngredient};
    form.reset(
      {
        ...foodIngredientRawValue,
        id: {value: foodIngredientRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FoodIngredientFormDefaults {
    return {
      id: null,
    };
  }
}
