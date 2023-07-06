import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {IZone} from 'app/entities/zone/zone.model';

export interface IRestaurantZonePrice {
  id: number;
  price?: number | null;
  isAvailable?: boolean | null;
  restaurant?: Pick<IRestaurant, 'id' | 'name'> | null;
  zone?: Pick<IZone, 'id' | 'name'> | null;
}

export type NewRestaurantZonePrice = Omit<IRestaurantZonePrice, 'id'> & { id: null };
