import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IRestaurantReview, NewRestaurantReview} from '../restaurant-review.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRestaurantReview for edit and NewRestaurantReviewFormGroupInput for create.
 */
type RestaurantReviewFormGroupInput = IRestaurantReview | PartialWithRequiredKeyOf<NewRestaurantReview>;

type RestaurantReviewFormDefaults = Pick<NewRestaurantReview, 'id' | 'isEdited'>;

type RestaurantReviewFormGroupContent = {
  id: FormControl<IRestaurantReview['id'] | NewRestaurantReview['id']>;
  review: FormControl<IRestaurantReview['review']>;
  rate: FormControl<IRestaurantReview['rate']>;
  isEdited: FormControl<IRestaurantReview['isEdited']>;
  customer: FormControl<IRestaurantReview['customer']>;
  restaurant: FormControl<IRestaurantReview['restaurant']>;
};

export type RestaurantReviewFormGroup = FormGroup<RestaurantReviewFormGroupContent>;

@Injectable({providedIn: 'root'})
export class RestaurantReviewFormService {
  createRestaurantReviewFormGroup(restaurantReview: RestaurantReviewFormGroupInput = {id: null}): RestaurantReviewFormGroup {
    const restaurantReviewRawValue = {
      ...this.getFormDefaults(),
      ...restaurantReview,
    };
    return new FormGroup<RestaurantReviewFormGroupContent>({
      id: new FormControl(
        {value: restaurantReviewRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      review: new FormControl(restaurantReviewRawValue.review),
      rate: new FormControl(restaurantReviewRawValue.rate),
      isEdited: new FormControl(restaurantReviewRawValue.isEdited),
      customer: new FormControl(restaurantReviewRawValue.customer),
      restaurant: new FormControl(restaurantReviewRawValue.restaurant),
    });
  }

  getRestaurantReview(form: RestaurantReviewFormGroup): IRestaurantReview | NewRestaurantReview {
    return form.getRawValue() as IRestaurantReview | NewRestaurantReview;
  }

  resetForm(form: RestaurantReviewFormGroup, restaurantReview: RestaurantReviewFormGroupInput): void {
    const restaurantReviewRawValue = {...this.getFormDefaults(), ...restaurantReview};
    form.reset(
      {
        ...restaurantReviewRawValue,
        id: {value: restaurantReviewRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RestaurantReviewFormDefaults {
    return {
      id: null,
      isEdited: false,
    };
  }
}
