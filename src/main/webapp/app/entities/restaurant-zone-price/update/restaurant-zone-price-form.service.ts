import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IRestaurantZonePrice, NewRestaurantZonePrice} from '../restaurant-zone-price.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRestaurantZonePrice for edit and NewRestaurantZonePriceFormGroupInput for create.
 */
type RestaurantZonePriceFormGroupInput = IRestaurantZonePrice | PartialWithRequiredKeyOf<NewRestaurantZonePrice>;

type RestaurantZonePriceFormDefaults = Pick<NewRestaurantZonePrice, 'id' | 'isAvailable'>;

type RestaurantZonePriceFormGroupContent = {
  id: FormControl<IRestaurantZonePrice['id'] | NewRestaurantZonePrice['id']>;
  price: FormControl<IRestaurantZonePrice['price']>;
  isAvailable: FormControl<IRestaurantZonePrice['isAvailable']>;
  restaurant: FormControl<IRestaurantZonePrice['restaurant']>;
  zone: FormControl<IRestaurantZonePrice['zone']>;
};

export type RestaurantZonePriceFormGroup = FormGroup<RestaurantZonePriceFormGroupContent>;

@Injectable({providedIn: 'root'})
export class RestaurantZonePriceFormService {
  createRestaurantZonePriceFormGroup(restaurantZonePrice: RestaurantZonePriceFormGroupInput = {id: null}): RestaurantZonePriceFormGroup {
    const restaurantZonePriceRawValue = {
      ...this.getFormDefaults(),
      ...restaurantZonePrice,
    };
    return new FormGroup<RestaurantZonePriceFormGroupContent>({
      id: new FormControl(
        {value: restaurantZonePriceRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      price: new FormControl(restaurantZonePriceRawValue.price),
      isAvailable: new FormControl(restaurantZonePriceRawValue.isAvailable),
      restaurant: new FormControl(restaurantZonePriceRawValue.restaurant),
      zone: new FormControl(restaurantZonePriceRawValue.zone),
    });
  }

  getRestaurantZonePrice(form: RestaurantZonePriceFormGroup): IRestaurantZonePrice | NewRestaurantZonePrice {
    return form.getRawValue() as IRestaurantZonePrice | NewRestaurantZonePrice;
  }

  resetForm(form: RestaurantZonePriceFormGroup, restaurantZonePrice: RestaurantZonePriceFormGroupInput): void {
    const restaurantZonePriceRawValue = {...this.getFormDefaults(), ...restaurantZonePrice};
    form.reset(
      {
        ...restaurantZonePriceRawValue,
        id: {value: restaurantZonePriceRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RestaurantZonePriceFormDefaults {
    return {
      id: null,
      isAvailable: false,
    };
  }
}
