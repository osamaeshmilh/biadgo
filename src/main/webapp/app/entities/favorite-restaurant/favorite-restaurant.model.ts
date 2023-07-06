import {ICustomer} from 'app/entities/customer/customer.model';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';

export interface IFavoriteRestaurant {
  id: number;
  customer?: Pick<ICustomer, 'id' | 'name'> | null;
  restaurant?: Pick<IRestaurant, 'id' | 'name'> | null;
}

export type NewFavoriteRestaurant = Omit<IFavoriteRestaurant, 'id'> & { id: null };
