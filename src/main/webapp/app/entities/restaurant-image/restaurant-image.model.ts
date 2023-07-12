import {IRestaurant} from 'app/entities/restaurant/restaurant.model';

export interface IRestaurantImage {
  id: number;
  description?: string | null;
  descriptionAr?: string | null;
  descriptionEn?: string | null;
  imageType?: string | null;
  menuOrder?: number | null;
  imageUrl?: string | null;
  image?: string | null;
  imageContentType?: string | null;
  restaurant?: Pick<IRestaurant, 'id' | 'name'> | null;
}

export type NewRestaurantImage = Omit<IRestaurantImage, 'id'> & { id: null };
