import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IRestaurantImage, NewRestaurantImage} from '../restaurant-image.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRestaurantImage for edit and NewRestaurantImageFormGroupInput for create.
 */
type RestaurantImageFormGroupInput = IRestaurantImage | PartialWithRequiredKeyOf<NewRestaurantImage>;

type RestaurantImageFormDefaults = Pick<NewRestaurantImage, 'id'>;

type RestaurantImageFormGroupContent = {
  id: FormControl<IRestaurantImage['id'] | NewRestaurantImage['id']>;
  description: FormControl<IRestaurantImage['description']>;
  descriptionAr: FormControl<IRestaurantImage['descriptionAr']>;
  descriptionEn: FormControl<IRestaurantImage['descriptionEn']>;
  imageType: FormControl<IRestaurantImage['imageType']>;
  menuOrder: FormControl<IRestaurantImage['menuOrder']>;
  imageUrl: FormControl<IRestaurantImage['imageUrl']>;
  image: FormControl<IRestaurantImage['image']>;
  imageContentType: FormControl<IRestaurantImage['imageContentType']>;
  restaurant: FormControl<IRestaurantImage['restaurant']>;
};

export type RestaurantImageFormGroup = FormGroup<RestaurantImageFormGroupContent>;

@Injectable({providedIn: 'root'})
export class RestaurantImageFormService {
  createRestaurantImageFormGroup(restaurantImage: RestaurantImageFormGroupInput = {id: null}): RestaurantImageFormGroup {
    const restaurantImageRawValue = {
      ...this.getFormDefaults(),
      ...restaurantImage,
    };
    return new FormGroup<RestaurantImageFormGroupContent>({
      id: new FormControl(
        {value: restaurantImageRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      description: new FormControl(restaurantImageRawValue.description),
      descriptionAr: new FormControl(restaurantImageRawValue.descriptionAr),
      descriptionEn: new FormControl(restaurantImageRawValue.descriptionEn),
      imageType: new FormControl(restaurantImageRawValue.imageType),
      menuOrder: new FormControl(restaurantImageRawValue.menuOrder),
      imageUrl: new FormControl(restaurantImageRawValue.imageUrl),
      image: new FormControl(restaurantImageRawValue.image),
      imageContentType: new FormControl(restaurantImageRawValue.imageContentType),
      restaurant: new FormControl(restaurantImageRawValue.restaurant),
    });
  }

  getRestaurantImage(form: RestaurantImageFormGroup): IRestaurantImage | NewRestaurantImage {
    return form.getRawValue() as IRestaurantImage | NewRestaurantImage;
  }

  resetForm(form: RestaurantImageFormGroup, restaurantImage: RestaurantImageFormGroupInput): void {
    const restaurantImageRawValue = {...this.getFormDefaults(), ...restaurantImage};
    form.reset(
      {
        ...restaurantImageRawValue,
        id: {value: restaurantImageRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RestaurantImageFormDefaults {
    return {
      id: null,
    };
  }
}
