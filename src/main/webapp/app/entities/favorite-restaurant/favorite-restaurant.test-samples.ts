import {IFavoriteRestaurant, NewFavoriteRestaurant} from './favorite-restaurant.model';

export const sampleWithRequiredData: IFavoriteRestaurant = {
  id: 73875,
};

export const sampleWithPartialData: IFavoriteRestaurant = {
  id: 83634,
};

export const sampleWithFullData: IFavoriteRestaurant = {
  id: 40973,
};

export const sampleWithNewData: NewFavoriteRestaurant = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
