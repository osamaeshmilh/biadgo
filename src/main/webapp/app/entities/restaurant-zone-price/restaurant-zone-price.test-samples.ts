import {IRestaurantZonePrice, NewRestaurantZonePrice} from './restaurant-zone-price.model';

export const sampleWithRequiredData: IRestaurantZonePrice = {
  id: 17291,
};

export const sampleWithPartialData: IRestaurantZonePrice = {
  id: 37323,
  isAvailable: false,
};

export const sampleWithFullData: IRestaurantZonePrice = {
  id: 17351,
  price: 30097,
  isAvailable: false,
};

export const sampleWithNewData: NewRestaurantZonePrice = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
