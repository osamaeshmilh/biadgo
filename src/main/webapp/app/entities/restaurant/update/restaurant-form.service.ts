import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IRestaurant, NewRestaurant} from '../restaurant.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRestaurant for edit and NewRestaurantFormGroupInput for create.
 */
type RestaurantFormGroupInput = IRestaurant | PartialWithRequiredKeyOf<NewRestaurant>;

type RestaurantFormDefaults = Pick<NewRestaurant,
  'id' | 'availableForDelivery' | 'availableForPickup' | 'isOpen' | 'isFeatured' | 'isListedInOffers' | 'isActive' | 'categories'>;

type RestaurantFormGroupContent = {
  id: FormControl<IRestaurant['id'] | NewRestaurant['id']>;
  name: FormControl<IRestaurant['name']>;
  nameAr: FormControl<IRestaurant['nameAr']>;
  nameEn: FormControl<IRestaurant['nameEn']>;
  description: FormControl<IRestaurant['description']>;
  address: FormControl<IRestaurant['address']>;
  latitude: FormControl<IRestaurant['latitude']>;
  longitude: FormControl<IRestaurant['longitude']>;
  plusCode: FormControl<IRestaurant['plusCode']>;
  mobile: FormControl<IRestaurant['mobile']>;
  estimitedTimeMins: FormControl<IRestaurant['estimitedTimeMins']>;
  deliveryFee: FormControl<IRestaurant['deliveryFee']>;
  deliveryRange: FormControl<IRestaurant['deliveryRange']>;
  availableForDelivery: FormControl<IRestaurant['availableForDelivery']>;
  availableForPickup: FormControl<IRestaurant['availableForPickup']>;
  minimumOrderTotalForDelivery: FormControl<IRestaurant['minimumOrderTotalForDelivery']>;
  deliveryPriceType: FormControl<IRestaurant['deliveryPriceType']>;
  isOpen: FormControl<IRestaurant['isOpen']>;
  isFeatured: FormControl<IRestaurant['isFeatured']>;
  isListedInOffers: FormControl<IRestaurant['isListedInOffers']>;
  isActive: FormControl<IRestaurant['isActive']>;
  offerBanner: FormControl<IRestaurant['offerBanner']>;
  priority: FormControl<IRestaurant['priority']>;
  viewCounter: FormControl<IRestaurant['viewCounter']>;
  scheduledClosingTime: FormControl<IRestaurant['scheduledClosingTime']>;
  facebookPageUrl: FormControl<IRestaurant['facebookPageUrl']>;
  notes: FormControl<IRestaurant['notes']>;
  user: FormControl<IRestaurant['user']>;
  cuisine: FormControl<IRestaurant['cuisine']>;
  categories: FormControl<IRestaurant['categories']>;
};

export type RestaurantFormGroup = FormGroup<RestaurantFormGroupContent>;

@Injectable({providedIn: 'root'})
export class RestaurantFormService {
  createRestaurantFormGroup(restaurant: RestaurantFormGroupInput = {id: null}): RestaurantFormGroup {
    const restaurantRawValue = {
      ...this.getFormDefaults(),
      ...restaurant,
    };
    return new FormGroup<RestaurantFormGroupContent>({
      id: new FormControl(
        {value: restaurantRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(restaurantRawValue.name),
      nameAr: new FormControl(restaurantRawValue.nameAr),
      nameEn: new FormControl(restaurantRawValue.nameEn),
      description: new FormControl(restaurantRawValue.description),
      address: new FormControl(restaurantRawValue.address),
      latitude: new FormControl(restaurantRawValue.latitude),
      longitude: new FormControl(restaurantRawValue.longitude),
      plusCode: new FormControl(restaurantRawValue.plusCode),
      mobile: new FormControl(restaurantRawValue.mobile),
      estimitedTimeMins: new FormControl(restaurantRawValue.estimitedTimeMins),
      deliveryFee: new FormControl(restaurantRawValue.deliveryFee),
      deliveryRange: new FormControl(restaurantRawValue.deliveryRange),
      availableForDelivery: new FormControl(restaurantRawValue.availableForDelivery),
      availableForPickup: new FormControl(restaurantRawValue.availableForPickup),
      minimumOrderTotalForDelivery: new FormControl(restaurantRawValue.minimumOrderTotalForDelivery),
      deliveryPriceType: new FormControl(restaurantRawValue.deliveryPriceType),
      isOpen: new FormControl(restaurantRawValue.isOpen),
      isFeatured: new FormControl(restaurantRawValue.isFeatured),
      isListedInOffers: new FormControl(restaurantRawValue.isListedInOffers),
      isActive: new FormControl(restaurantRawValue.isActive),
      offerBanner: new FormControl(restaurantRawValue.offerBanner),
      priority: new FormControl(restaurantRawValue.priority),
      viewCounter: new FormControl(restaurantRawValue.viewCounter),
      scheduledClosingTime: new FormControl(restaurantRawValue.scheduledClosingTime),
      facebookPageUrl: new FormControl(restaurantRawValue.facebookPageUrl),
      notes: new FormControl(restaurantRawValue.notes),
      user: new FormControl(restaurantRawValue.user),
      cuisine: new FormControl(restaurantRawValue.cuisine),
      categories: new FormControl(restaurantRawValue.categories ?? []),
    });
  }

  getRestaurant(form: RestaurantFormGroup): IRestaurant | NewRestaurant {
    return form.getRawValue() as IRestaurant | NewRestaurant;
  }

  resetForm(form: RestaurantFormGroup, restaurant: RestaurantFormGroupInput): void {
    const restaurantRawValue = {...this.getFormDefaults(), ...restaurant};
    form.reset(
      {
        ...restaurantRawValue,
        id: {value: restaurantRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RestaurantFormDefaults {
    return {
      id: null,
      availableForDelivery: false,
      availableForPickup: false,
      isOpen: false,
      isFeatured: false,
      isListedInOffers: false,
      isActive: false,
      categories: [],
    };
  }
}
