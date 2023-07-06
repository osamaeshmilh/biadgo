import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IRestaurantDistancePrice, NewRestaurantDistancePrice} from '../restaurant-distance-price.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRestaurantDistancePrice for edit and NewRestaurantDistancePriceFormGroupInput for create.
 */
type RestaurantDistancePriceFormGroupInput =
  IRestaurantDistancePrice
  | PartialWithRequiredKeyOf<NewRestaurantDistancePrice>;

type RestaurantDistancePriceFormDefaults = Pick<NewRestaurantDistancePrice, 'id' | 'isAvailable'>;

type RestaurantDistancePriceFormGroupContent = {
  id: FormControl<IRestaurantDistancePrice['id'] | NewRestaurantDistancePrice['id']>;
  price: FormControl<IRestaurantDistancePrice['price']>;
  fromKm: FormControl<IRestaurantDistancePrice['fromKm']>;
  toKm: FormControl<IRestaurantDistancePrice['toKm']>;
  isAvailable: FormControl<IRestaurantDistancePrice['isAvailable']>;
  restaurant: FormControl<IRestaurantDistancePrice['restaurant']>;
};

export type RestaurantDistancePriceFormGroup = FormGroup<RestaurantDistancePriceFormGroupContent>;

@Injectable({providedIn: 'root'})
export class RestaurantDistancePriceFormService {
  createRestaurantDistancePriceFormGroup(
    restaurantDistancePrice: RestaurantDistancePriceFormGroupInput = {id: null}
  ): RestaurantDistancePriceFormGroup {
    const restaurantDistancePriceRawValue = {
      ...this.getFormDefaults(),
      ...restaurantDistancePrice,
    };
    return new FormGroup<RestaurantDistancePriceFormGroupContent>({
      id: new FormControl(
        {value: restaurantDistancePriceRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      price: new FormControl(restaurantDistancePriceRawValue.price),
      fromKm: new FormControl(restaurantDistancePriceRawValue.fromKm),
      toKm: new FormControl(restaurantDistancePriceRawValue.toKm),
      isAvailable: new FormControl(restaurantDistancePriceRawValue.isAvailable),
      restaurant: new FormControl(restaurantDistancePriceRawValue.restaurant),
    });
  }

  getRestaurantDistancePrice(form: RestaurantDistancePriceFormGroup): IRestaurantDistancePrice | NewRestaurantDistancePrice {
    return form.getRawValue() as IRestaurantDistancePrice | NewRestaurantDistancePrice;
  }

  resetForm(form: RestaurantDistancePriceFormGroup, restaurantDistancePrice: RestaurantDistancePriceFormGroupInput): void {
    const restaurantDistancePriceRawValue = {...this.getFormDefaults(), ...restaurantDistancePrice};
    form.reset(
      {
        ...restaurantDistancePriceRawValue,
        id: {value: restaurantDistancePriceRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RestaurantDistancePriceFormDefaults {
    return {
      id: null,
      isAvailable: false,
    };
  }
}
