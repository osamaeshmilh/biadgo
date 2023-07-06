import {INotification, NewNotification} from './notification.model';

export const sampleWithRequiredData: INotification = {
  id: 76300,
};

export const sampleWithPartialData: INotification = {
  id: 84348,
  customerId: 66690,
};

export const sampleWithFullData: INotification = {
  id: 62369,
  title: 'Arrow boldly withdrawal',
  details: 'North Electronic',
  isRead: false,
  customerId: 51768,
};

export const sampleWithNewData: NewNotification = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
