import {IDeliveryAddress, NewDeliveryAddress} from './delivery-address.model';

export const sampleWithRequiredData: IDeliveryAddress = {
  id: 34338,
};

export const sampleWithPartialData: IDeliveryAddress = {
  id: 81916,
  title: 'Diesel Jewelery Principal',
  longitude: 15304,
  plusCode: 'Gasoline',
};

export const sampleWithFullData: IDeliveryAddress = {
  id: 10867,
  title: 'lumen drat',
  address: 'Loan functionalities',
  details: 'Group',
  phone: '(577) 860-0819 x8042',
  isDefault: true,
  latitude: 75482,
  longitude: 56001,
  plusCode: 'female lest',
  isActive: true,
  notes: 'withdrawal',
};

export const sampleWithNewData: NewDeliveryAddress = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
