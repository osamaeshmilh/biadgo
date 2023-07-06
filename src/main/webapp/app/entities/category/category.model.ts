import {IRestaurant} from 'app/entities/restaurant/restaurant.model';

export interface ICategory {
  id: number;
  name?: string | null;
  nameAr?: string | null;
  nameEn?: string | null;
  menuOrder?: number | null;
  imageUrl?: string | null;
  image?: string | null;
  imageContentType?: string | null;
  notes?: string | null;
  isActive?: boolean | null;
  restaurants?: Pick<IRestaurant, 'id'>[] | null;
}

export type NewCategory = Omit<ICategory, 'id'> & { id: null };
