import {IOrder} from 'app/entities/order/order.model';

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
}

export type NewFoodOrder = Omit<IFoodOrder, 'id'> & { id: null };
