import {ICustomer} from 'app/entities/customer/customer.model';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';

export interface IRestaurantReview {
  id: number;
  review?: string | null;
  rate?: number | null;
  isEdited?: boolean | null;
  customer?: Pick<ICustomer, 'id' | 'name'> | null;
  restaurant?: Pick<IRestaurant, 'id' | 'name'> | null;
}

export type NewRestaurantReview = Omit<IRestaurantReview, 'id'> & { id: null };
