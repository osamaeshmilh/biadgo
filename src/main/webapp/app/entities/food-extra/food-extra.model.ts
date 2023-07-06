import {IFood} from 'app/entities/food/food.model';

export interface IFoodExtra {
  id: number;
  name?: string | null;
  nameAr?: string | null;
  nameEn?: string | null;
  price?: number | null;
  notes?: string | null;
  food?: Pick<IFood, 'id' | 'name'> | null;
}

export type NewFoodExtra = Omit<IFoodExtra, 'id'> & { id: null };
