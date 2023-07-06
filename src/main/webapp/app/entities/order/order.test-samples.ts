import dayjs from 'dayjs/esm';

import {PaymentType} from 'app/entities/enumerations/payment-type.model';
import {OrderStatus} from 'app/entities/enumerations/order-status.model';
import {OrderType} from 'app/entities/enumerations/order-type.model';

import {IOrder, NewOrder} from './order.model';

export const sampleWithRequiredData: IOrder = {
  id: 13239,
};

export const sampleWithPartialData: IOrder = {
  id: 77211,
  restaurantAmount: 14012,
  deliveryFee: 40516,
  discount: 22461,
  latitude: 62954,
  plusCode: 'worthwhile veritatis',
  isApprovedByDriver: true,
  amountRecived: 44795,
  restaurantNotes: 'becquerel',
  orderType: 'PICKUP',
  deliveredAt: dayjs('2023-07-05T15:55'),
};

export const sampleWithFullData: IOrder = {
  id: 86652,
  orderNo: 'Nevada Cotton New',
  companyAmount: 91609,
  restaurantAmount: 78682,
  deliveryFee: 71561,
  itemsPrice: 40079,
  discount: 56350,
  total: 27341,
  latitude: 42874,
  longitude: 59570,
  plusCode: 'Minivan',
  paymentType: 'TADAWUL',
  isApprovedByDriver: false,
  isApprovedByRestaurant: false,
  isPayed: true,
  amountRecived: 93024,
  amountRemaining: 26553,
  customerNotes: 'Wooden',
  restaurantNotes: 'though Keyboard',
  driverNotes: 'Concrete ASCII',
  orderStatus: 'OUT_FOR_DELIVERY',
  orderType: 'DELIVERY',
  deliveredAt: dayjs('2023-07-05T15:09'),
  notes: 'transition',
};

export const sampleWithNewData: NewOrder = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
