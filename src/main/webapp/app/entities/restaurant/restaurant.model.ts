import {IUser} from 'app/entities/user/user.model';
import {ICuisine} from 'app/entities/cuisine/cuisine.model';
import {ICategory} from 'app/entities/category/category.model';
import {DeliveryPriceType} from 'app/entities/enumerations/delivery-price-type.model';

export interface IRestaurant {
  id: number;
  name?: string | null;
  nameAr?: string | null;
  nameEn?: string | null;
  description?: string | null;
  address?: string | null;
  latitude?: number | null;
  longitude?: number | null;
  plusCode?: string | null;
  mobile?: string | null;
  estimitedTimeMins?: number | null;
  deliveryFee?: number | null;
  deliveryRange?: number | null;
  availableForDelivery?: boolean | null;
  availableForPickup?: boolean | null;
  minimumOrderTotalForDelivery?: number | null;
  deliveryPriceType?: keyof typeof DeliveryPriceType | null;
  isOpen?: boolean | null;
  isFeatured?: boolean | null;
  isListedInOffers?: boolean | null;
  isActive?: boolean | null;
  offerBanner?: string | null;
  priority?: number | null;
  viewCounter?: number | null;
  scheduledClosingTime?: string | null;
  facebookPageUrl?: string | null;
  notes?: string | null;
  user?: Pick<IUser, 'id'> | null;
  cuisine?: Pick<ICuisine, 'id' | 'name'> | null;
  categories?: Pick<ICategory, 'id'>[] | null;
}

export type NewRestaurant = Omit<IRestaurant, 'id'> & { id: null };
