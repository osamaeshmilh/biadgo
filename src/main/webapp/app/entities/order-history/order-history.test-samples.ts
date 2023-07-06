import {OrderStatus} from 'app/entities/enumerations/order-status.model';

import {IOrderHistory, NewOrderHistory} from './order-history.model';

export const sampleWithRequiredData: IOrderHistory = {
  id: 91148,
};

export const sampleWithPartialData: IOrderHistory = {
  id: 82322,
};

export const sampleWithFullData: IOrderHistory = {
  id: 40017,
  orderStatusFrom: 'FAILED',
  orderStatusTo: 'DELIVERED',
  notes: 'Investor alarm',
};

export const sampleWithNewData: NewOrderHistory = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
