import {IRestaurant} from 'app/entities/restaurant/restaurant.model';

export interface IRestaurantDistancePrice {
  id: number;
  price?: number | null;
  fromKm?: number | null;
  toKm?: number | null;
  isAvailable?: boolean | null;
  restaurant?: Pick<IRestaurant, 'id' | 'name'> | null;
}

export type NewRestaurantDistancePrice = Omit<IRestaurantDistancePrice, 'id'> & { id: null };
