import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {ICategory} from 'app/entities/category/category.model';

export interface IFood {
  id: number;
  name?: string | null;
  nameAr?: string | null;
  nameEn?: string | null;
  price?: number | null;
  discountPrice?: number | null;
  description?: string | null;
  descriptionAr?: string | null;
  descriptionEn?: string | null;
  packageItemsCount?: number | null;
  dailyQuantity?: number | null;
  isAvailable?: boolean | null;
  isDiscount?: boolean | null;
  isFeatured?: boolean | null;
  isActive?: boolean | null;
  viewCounter?: number | null;
  notes?: string | null;
  restaurant?: Pick<IRestaurant, 'id' | 'name'> | null;
  category?: Pick<ICategory, 'id' | 'name'> | null;
}

export type NewFood = Omit<IFood, 'id'> & { id: null };
