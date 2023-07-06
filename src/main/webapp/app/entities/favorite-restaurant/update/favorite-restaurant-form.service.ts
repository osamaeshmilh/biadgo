import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IFavoriteRestaurant, NewFavoriteRestaurant} from '../favorite-restaurant.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFavoriteRestaurant for edit and NewFavoriteRestaurantFormGroupInput for create.
 */
type FavoriteRestaurantFormGroupInput = IFavoriteRestaurant | PartialWithRequiredKeyOf<NewFavoriteRestaurant>;

type FavoriteRestaurantFormDefaults = Pick<NewFavoriteRestaurant, 'id'>;

type FavoriteRestaurantFormGroupContent = {
  id: FormControl<IFavoriteRestaurant['id'] | NewFavoriteRestaurant['id']>;
  customer: FormControl<IFavoriteRestaurant['customer']>;
  restaurant: FormControl<IFavoriteRestaurant['restaurant']>;
};

export type FavoriteRestaurantFormGroup = FormGroup<FavoriteRestaurantFormGroupContent>;

@Injectable({providedIn: 'root'})
export class FavoriteRestaurantFormService {
  createFavoriteRestaurantFormGroup(favoriteRestaurant: FavoriteRestaurantFormGroupInput = {id: null}): FavoriteRestaurantFormGroup {
    const favoriteRestaurantRawValue = {
      ...this.getFormDefaults(),
      ...favoriteRestaurant,
    };
    return new FormGroup<FavoriteRestaurantFormGroupContent>({
      id: new FormControl(
        {value: favoriteRestaurantRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      customer: new FormControl(favoriteRestaurantRawValue.customer),
      restaurant: new FormControl(favoriteRestaurantRawValue.restaurant),
    });
  }

  getFavoriteRestaurant(form: FavoriteRestaurantFormGroup): IFavoriteRestaurant | NewFavoriteRestaurant {
    return form.getRawValue() as IFavoriteRestaurant | NewFavoriteRestaurant;
  }

  resetForm(form: FavoriteRestaurantFormGroup, favoriteRestaurant: FavoriteRestaurantFormGroupInput): void {
    const favoriteRestaurantRawValue = {...this.getFormDefaults(), ...favoriteRestaurant};
    form.reset(
      {
        ...favoriteRestaurantRawValue,
        id: {value: favoriteRestaurantRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FavoriteRestaurantFormDefaults {
    return {
      id: null,
    };
  }
}
