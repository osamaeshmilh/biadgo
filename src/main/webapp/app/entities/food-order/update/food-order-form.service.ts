import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IFoodOrder, NewFoodOrder} from '../food-order.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFoodOrder for edit and NewFoodOrderFormGroupInput for create.
 */
type FoodOrderFormGroupInput = IFoodOrder | PartialWithRequiredKeyOf<NewFoodOrder>;

type FoodOrderFormDefaults = Pick<NewFoodOrder, 'id'>;

type FoodOrderFormGroupContent = {
  id: FormControl<IFoodOrder['id'] | NewFoodOrder['id']>;
  price: FormControl<IFoodOrder['price']>;
  quantity: FormControl<IFoodOrder['quantity']>;
  total: FormControl<IFoodOrder['total']>;
  specialNotes: FormControl<IFoodOrder['specialNotes']>;
  foodExtraIdsList: FormControl<IFoodOrder['foodExtraIdsList']>;
  foodIngredientIds: FormControl<IFoodOrder['foodIngredientIds']>;
  foodIngredientRemovedIds: FormControl<IFoodOrder['foodIngredientRemovedIds']>;
  order: FormControl<IFoodOrder['order']>;
};

export type FoodOrderFormGroup = FormGroup<FoodOrderFormGroupContent>;

@Injectable({providedIn: 'root'})
export class FoodOrderFormService {
  createFoodOrderFormGroup(foodOrder: FoodOrderFormGroupInput = {id: null}): FoodOrderFormGroup {
    const foodOrderRawValue = {
      ...this.getFormDefaults(),
      ...foodOrder,
    };
    return new FormGroup<FoodOrderFormGroupContent>({
      id: new FormControl(
        {value: foodOrderRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      price: new FormControl(foodOrderRawValue.price),
      quantity: new FormControl(foodOrderRawValue.quantity),
      total: new FormControl(foodOrderRawValue.total),
      specialNotes: new FormControl(foodOrderRawValue.specialNotes),
      foodExtraIdsList: new FormControl(foodOrderRawValue.foodExtraIdsList),
      foodIngredientIds: new FormControl(foodOrderRawValue.foodIngredientIds),
      foodIngredientRemovedIds: new FormControl(foodOrderRawValue.foodIngredientRemovedIds),
      order: new FormControl(foodOrderRawValue.order),
    });
  }

  getFoodOrder(form: FoodOrderFormGroup): IFoodOrder | NewFoodOrder {
    return form.getRawValue() as IFoodOrder | NewFoodOrder;
  }

  resetForm(form: FoodOrderFormGroup, foodOrder: FoodOrderFormGroupInput): void {
    const foodOrderRawValue = {...this.getFormDefaults(), ...foodOrder};
    form.reset(
      {
        ...foodOrderRawValue,
        id: {value: foodOrderRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FoodOrderFormDefaults {
    return {
      id: null,
    };
  }
}
