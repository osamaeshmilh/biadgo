import {PaymentType} from 'app/entities/enumerations/payment-type.model';

import {IPaymentMethod, NewPaymentMethod} from './payment-method.model';

export const sampleWithRequiredData: IPaymentMethod = {
  id: 31004,
};

export const sampleWithPartialData: IPaymentMethod = {
  id: 25328,
  menuOrder: 46902,
  imageUrl: 'Intersex Isle neural',
  details: 'reconnoitre Chips',
  feePercentage: 73025,
  isActive: false,
};

export const sampleWithFullData: IPaymentMethod = {
  id: 36418,
  name: 'Electric SMS Buckinghamshire',
  nameAr: 'copying experiences PNG',
  nameEn: 'Minivan',
  menuOrder: 7255,
  imageUrl: 'Passenger',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
  details: 'calculating Gasoline Lilangeni',
  feePercentage: 73267,
  paymentType: 'SADAD',
  isActive: true,
  notes: 'infer OCR Credit',
};

export const sampleWithNewData: NewPaymentMethod = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
