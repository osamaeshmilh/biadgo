import {IRestaurantReview, NewRestaurantReview} from './restaurant-review.model';

export const sampleWithRequiredData: IRestaurantReview = {
  id: 48484,
};

export const sampleWithPartialData: IRestaurantReview = {
  id: 11202,
  review: 'male Security',
  rate: 22752,
  isEdited: true,
};

export const sampleWithFullData: IRestaurantReview = {
  id: 96459,
  review: 'concept Latin',
  rate: 88491,
  isEdited: true,
};

export const sampleWithNewData: NewRestaurantReview = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
