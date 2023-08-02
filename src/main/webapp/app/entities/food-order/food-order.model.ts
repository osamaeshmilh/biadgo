import {IOrder} from 'app/entities/order/order.model';
import {IFood} from 'app/entities/food/food.model';

export interface IFoodOrder {
  id: number;
  price?: number | null;
  quantity?: number | null;
  total?: number | null;
  specialNotes?: string | null;
  foodExtraIdsList?: string | null;
  foodIngredientIds?: string | null;
  foodIngredientRemovedIds?: string | null;
  order?: Pick<IOrder, 'id' | 'orderNo'> | null;
  food?: Pick<IFood, 'id' | 'name'> | null;
}

export type NewFoodOrder = Omit<IFoodOrder, 'id'> & { id: null };
