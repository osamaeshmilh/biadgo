import dayjs from 'dayjs/esm';

import {IDriverLocation, NewDriverLocation} from './driver-location.model';

export const sampleWithRequiredData: IDriverLocation = {
  id: 19703,
};

export const sampleWithPartialData: IDriverLocation = {
  id: 93374,
  latitude: 73965,
  plusCode: 'deposit aggregate',
};

export const sampleWithFullData: IDriverLocation = {
  id: 19915,
  latitude: 76454,
  longitude: 82734,
  plusCode: 'viral Rue',
  locationDateTime: dayjs('2023-07-05T07:26'),
};

export const sampleWithNewData: NewDriverLocation = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
