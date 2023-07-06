import {ICustomer} from 'app/entities/customer/customer.model';
import {IFood} from 'app/entities/food/food.model';

export interface ICart {
  id: number;
  quantity?: number | null;
  customerNotes?: string | null;
  foodExtraIdsList?: string | null;
  foodIngredientIds?: string | null;
  foodIngredientRemovedIds?: string | null;
  customer?: Pick<ICustomer, 'id' | 'name'> | null;
  food?: Pick<IFood, 'id' | 'name'> | null;
}

export type NewCart = Omit<ICart, 'id'> & { id: null };
