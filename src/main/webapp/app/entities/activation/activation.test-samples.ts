import dayjs from 'dayjs/esm';

import {IActivation, NewActivation} from './activation.model';

export const sampleWithRequiredData: IActivation = {
  id: 42231,
};

export const sampleWithPartialData: IActivation = {
  id: 31774,
  email: 'Brook_Weber@hotmail.com',
  sentOn: dayjs('2023-07-05T10:38'),
  validUntil: dayjs('2023-07-05T17:17'),
};

export const sampleWithFullData: IActivation = {
  id: 29020,
  mobileNo: 'Baby Planner',
  email: 'Hyman.Turcotte63@gmail.com',
  code: 'up',
  sentOn: dayjs('2023-07-05T14:05'),
  validUntil: dayjs('2023-07-05T21:32'),
  isUsed: true,
};

export const sampleWithNewData: NewActivation = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
