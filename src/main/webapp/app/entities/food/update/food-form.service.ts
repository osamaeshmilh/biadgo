import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IFood, NewFood} from '../food.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFood for edit and NewFoodFormGroupInput for create.
 */
type FoodFormGroupInput = IFood | PartialWithRequiredKeyOf<NewFood>;

type FoodFormDefaults = Pick<NewFood, 'id' | 'isAvailable' | 'isDiscount' | 'isFeatured' | 'isActive'>;

type FoodFormGroupContent = {
  id: FormControl<IFood['id'] | NewFood['id']>;
  name: FormControl<IFood['name']>;
  nameAr: FormControl<IFood['nameAr']>;
  nameEn: FormControl<IFood['nameEn']>;
  price: FormControl<IFood['price']>;
  discountPrice: FormControl<IFood['discountPrice']>;
  description: FormControl<IFood['description']>;
  descriptionAr: FormControl<IFood['descriptionAr']>;
  descriptionEn: FormControl<IFood['descriptionEn']>;
  packageItemsCount: FormControl<IFood['packageItemsCount']>;
  dailyQuantity: FormControl<IFood['dailyQuantity']>;
  isAvailable: FormControl<IFood['isAvailable']>;
  isDiscount: FormControl<IFood['isDiscount']>;
  isFeatured: FormControl<IFood['isFeatured']>;
  isActive: FormControl<IFood['isActive']>;
  viewCounter: FormControl<IFood['viewCounter']>;
  notes: FormControl<IFood['notes']>;
  restaurant: FormControl<IFood['restaurant']>;
  category: FormControl<IFood['category']>;
};

export type FoodFormGroup = FormGroup<FoodFormGroupContent>;

@Injectable({providedIn: 'root'})
export class FoodFormService {
  createFoodFormGroup(food: FoodFormGroupInput = {id: null}): FoodFormGroup {
    const foodRawValue = {
      ...this.getFormDefaults(),
      ...food,
    };
    return new FormGroup<FoodFormGroupContent>({
      id: new FormControl(
        {value: foodRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(foodRawValue.name),
      nameAr: new FormControl(foodRawValue.nameAr),
      nameEn: new FormControl(foodRawValue.nameEn),
      price: new FormControl(foodRawValue.price),
      discountPrice: new FormControl(foodRawValue.discountPrice),
      description: new FormControl(foodRawValue.description),
      descriptionAr: new FormControl(foodRawValue.descriptionAr),
      descriptionEn: new FormControl(foodRawValue.descriptionEn),
      packageItemsCount: new FormControl(foodRawValue.packageItemsCount),
      dailyQuantity: new FormControl(foodRawValue.dailyQuantity),
      isAvailable: new FormControl(foodRawValue.isAvailable),
      isDiscount: new FormControl(foodRawValue.isDiscount),
      isFeatured: new FormControl(foodRawValue.isFeatured),
      isActive: new FormControl(foodRawValue.isActive),
      viewCounter: new FormControl(foodRawValue.viewCounter),
      notes: new FormControl(foodRawValue.notes),
      restaurant: new FormControl(foodRawValue.restaurant),
      category: new FormControl(foodRawValue.category),
    });
  }

  getFood(form: FoodFormGroup): IFood | NewFood {
    return form.getRawValue() as IFood | NewFood;
  }

  resetForm(form: FoodFormGroup, food: FoodFormGroupInput): void {
    const foodRawValue = {...this.getFormDefaults(), ...food};
    form.reset(
      {
        ...foodRawValue,
        id: {value: foodRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FoodFormDefaults {
    return {
      id: null,
      isAvailable: false,
      isDiscount: false,
      isFeatured: false,
      isActive: false,
    };
  }
}
