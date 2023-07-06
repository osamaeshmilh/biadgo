import {IOrder} from 'app/entities/order/order.model';
import {OrderStatus} from 'app/entities/enumerations/order-status.model';

export interface IOrderHistory {
  id: number;
  orderStatusFrom?: keyof typeof OrderStatus | null;
  orderStatusTo?: keyof typeof OrderStatus | null;
  notes?: string | null;
  order?: Pick<IOrder, 'id' | 'orderNo'> | null;
}

export type NewOrderHistory = Omit<IOrderHistory, 'id'> & { id: null };
