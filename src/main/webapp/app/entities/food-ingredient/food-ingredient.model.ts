import {IFood} from 'app/entities/food/food.model';

export interface IFoodIngredient {
  id: number;
  name?: string | null;
  nameAr?: string | null;
  nameEn?: string | null;
  notes?: string | null;
  food?: Pick<IFood, 'id' | 'name'> | null;
}

export type NewFoodIngredient = Omit<IFoodIngredient, 'id'> & { id: null };
