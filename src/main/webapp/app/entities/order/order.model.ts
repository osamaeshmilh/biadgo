import dayjs from 'dayjs/esm';
import {ICustomer} from 'app/entities/customer/customer.model';
import {ICoupon} from 'app/entities/coupon/coupon.model';
import {IDriver} from 'app/entities/driver/driver.model';
import {IDeliveryAddress} from 'app/entities/delivery-address/delivery-address.model';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {PaymentType} from 'app/entities/enumerations/payment-type.model';
import {OrderStatus} from 'app/entities/enumerations/order-status.model';
import {OrderType} from 'app/entities/enumerations/order-type.model';

export interface IOrder {
  id: number;
  orderNo?: string | null;
  companyAmount?: number | null;
  restaurantAmount?: number | null;
  deliveryFee?: number | null;
  itemsPrice?: number | null;
  discount?: number | null;
  total?: number | null;
  latitude?: number | null;
  longitude?: number | null;
  plusCode?: string | null;
  paymentType?: keyof typeof PaymentType | null;
  isApprovedByDriver?: boolean | null;
  isApprovedByRestaurant?: boolean | null;
  isPayed?: boolean | null;
  amountRecived?: number | null;
  amountRemaining?: number | null;
  customerNotes?: string | null;
  restaurantNotes?: string | null;
  driverNotes?: string | null;
  orderStatus?: keyof typeof OrderStatus | null;
  orderType?: keyof typeof OrderType | null;
  deliveredAt?: dayjs.Dayjs | null;
  notes?: string | null;
  customer?: Pick<ICustomer, 'id' | 'name'> | null;
  coupon?: Pick<ICoupon, 'id' | 'code'> | null;
  driver?: Pick<IDriver, 'id' | 'name'> | null;
  deliveryAddress?: Pick<IDeliveryAddress, 'id' | 'address'> | null;
  restaurant?: Pick<IRestaurant, 'id' | 'name'> | null;
}

export type NewOrder = Omit<IOrder, 'id'> & { id: null };
