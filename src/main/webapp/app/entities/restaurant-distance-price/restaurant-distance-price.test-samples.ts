import {IRestaurantDistancePrice, NewRestaurantDistancePrice} from './restaurant-distance-price.model';

export const sampleWithRequiredData: IRestaurantDistancePrice = {
  id: 2349,
};

export const sampleWithPartialData: IRestaurantDistancePrice = {
  id: 18045,
  toKm: 49973,
  isAvailable: true,
};

export const sampleWithFullData: IRestaurantDistancePrice = {
  id: 37670,
  price: 15457,
  fromKm: 17320,
  toKm: 88659,
  isAvailable: false,
};

export const sampleWithNewData: NewRestaurantDistancePrice = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
