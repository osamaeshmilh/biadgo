import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IFoodImage, NewFoodImage} from '../food-image.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFoodImage for edit and NewFoodImageFormGroupInput for create.
 */
type FoodImageFormGroupInput = IFoodImage | PartialWithRequiredKeyOf<NewFoodImage>;

type FoodImageFormDefaults = Pick<NewFoodImage, 'id'>;

type FoodImageFormGroupContent = {
  id: FormControl<IFoodImage['id'] | NewFoodImage['id']>;
  description: FormControl<IFoodImage['description']>;
  descriptionAr: FormControl<IFoodImage['descriptionAr']>;
  descriptionEn: FormControl<IFoodImage['descriptionEn']>;
  menuOrder: FormControl<IFoodImage['menuOrder']>;
  imageUrl: FormControl<IFoodImage['imageUrl']>;
  image: FormControl<IFoodImage['image']>;
  imageContentType: FormControl<IFoodImage['imageContentType']>;
  food: FormControl<IFoodImage['food']>;
};

export type FoodImageFormGroup = FormGroup<FoodImageFormGroupContent>;

@Injectable({providedIn: 'root'})
export class FoodImageFormService {
  createFoodImageFormGroup(foodImage: FoodImageFormGroupInput = {id: null}): FoodImageFormGroup {
    const foodImageRawValue = {
      ...this.getFormDefaults(),
      ...foodImage,
    };
    return new FormGroup<FoodImageFormGroupContent>({
      id: new FormControl(
        {value: foodImageRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      description: new FormControl(foodImageRawValue.description),
      descriptionAr: new FormControl(foodImageRawValue.descriptionAr),
      descriptionEn: new FormControl(foodImageRawValue.descriptionEn),
      menuOrder: new FormControl(foodImageRawValue.menuOrder),
      imageUrl: new FormControl(foodImageRawValue.imageUrl),
      image: new FormControl(foodImageRawValue.image),
      imageContentType: new FormControl(foodImageRawValue.imageContentType),
      food: new FormControl(foodImageRawValue.food),
    });
  }

  getFoodImage(form: FoodImageFormGroup): IFoodImage | NewFoodImage {
    return form.getRawValue() as IFoodImage | NewFoodImage;
  }

  resetForm(form: FoodImageFormGroup, foodImage: FoodImageFormGroupInput): void {
    const foodImageRawValue = {...this.getFormDefaults(), ...foodImage};
    form.reset(
      {
        ...foodImageRawValue,
        id: {value: foodImageRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FoodImageFormDefaults {
    return {
      id: null,
    };
  }
}
