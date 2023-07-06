import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {ISlider, NewSlider} from '../slider.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ISlider for edit and NewSliderFormGroupInput for create.
 */
type SliderFormGroupInput = ISlider | PartialWithRequiredKeyOf<NewSlider>;

type SliderFormDefaults = Pick<NewSlider, 'id'>;

type SliderFormGroupContent = {
  id: FormControl<ISlider['id'] | NewSlider['id']>;
  details: FormControl<ISlider['details']>;
  detailsAr: FormControl<ISlider['detailsAr']>;
  detailsEn: FormControl<ISlider['detailsEn']>;
  menuOrder: FormControl<ISlider['menuOrder']>;
  imageUrl: FormControl<ISlider['imageUrl']>;
  image: FormControl<ISlider['image']>;
  imageContentType: FormControl<ISlider['imageContentType']>;
  url: FormControl<ISlider['url']>;
  restaurantId: FormControl<ISlider['restaurantId']>;
  categoryId: FormControl<ISlider['categoryId']>;
  foodId: FormControl<ISlider['foodId']>;
  notes: FormControl<ISlider['notes']>;
};

export type SliderFormGroup = FormGroup<SliderFormGroupContent>;

@Injectable({providedIn: 'root'})
export class SliderFormService {
  createSliderFormGroup(slider: SliderFormGroupInput = {id: null}): SliderFormGroup {
    const sliderRawValue = {
      ...this.getFormDefaults(),
      ...slider,
    };
    return new FormGroup<SliderFormGroupContent>({
      id: new FormControl(
        {value: sliderRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      details: new FormControl(sliderRawValue.details),
      detailsAr: new FormControl(sliderRawValue.detailsAr),
      detailsEn: new FormControl(sliderRawValue.detailsEn),
      menuOrder: new FormControl(sliderRawValue.menuOrder),
      imageUrl: new FormControl(sliderRawValue.imageUrl),
      image: new FormControl(sliderRawValue.image),
      imageContentType: new FormControl(sliderRawValue.imageContentType),
      url: new FormControl(sliderRawValue.url),
      restaurantId: new FormControl(sliderRawValue.restaurantId),
      categoryId: new FormControl(sliderRawValue.categoryId),
      foodId: new FormControl(sliderRawValue.foodId),
      notes: new FormControl(sliderRawValue.notes),
    });
  }

  getSlider(form: SliderFormGroup): ISlider | NewSlider {
    return form.getRawValue() as ISlider | NewSlider;
  }

  resetForm(form: SliderFormGroup, slider: SliderFormGroupInput): void {
    const sliderRawValue = {...this.getFormDefaults(), ...slider};
    form.reset(
      {
        ...sliderRawValue,
        id: {value: sliderRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): SliderFormDefaults {
    return {
      id: null,
    };
  }
}
