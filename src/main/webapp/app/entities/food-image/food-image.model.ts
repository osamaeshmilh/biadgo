import {IFood} from 'app/entities/food/food.model';

export interface IFoodImage {
  id: number;
  description?: string | null;
  descriptionAr?: string | null;
  descriptionEn?: string | null;
  menuOrder?: number | null;
  imageUrl?: string | null;
  image?: string | null;
  imageContentType?: string | null;
  food?: Pick<IFood, 'id' | 'name'> | null;
}

export type NewFoodImage = Omit<IFoodImage, 'id'> & { id: null };
