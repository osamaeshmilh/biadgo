import {IDriverReview, NewDriverReview} from './driver-review.model';

export const sampleWithRequiredData: IDriverReview = {
  id: 12841,
};

export const sampleWithPartialData: IDriverReview = {
  id: 66978,
  details: 'Bike Elgin',
};

export const sampleWithFullData: IDriverReview = {
  id: 60996,
  details: 'programming',
  rate: 75988,
};

export const sampleWithNewData: NewDriverReview = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
