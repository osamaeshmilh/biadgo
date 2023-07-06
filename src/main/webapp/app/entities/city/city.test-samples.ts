import {ICity, NewCity} from './city.model';

export const sampleWithRequiredData: ICity = {
  id: 53283,
  name: 'female Northeast Consultant',
};

export const sampleWithPartialData: ICity = {
  id: 57700,
  name: 'Colorado',
  nameAr: 'repurpose Northwest Buckinghamshire',
  nameEn: 'pixel envisioneer',
  latitude: 39927,
  longitude: 60763,
  plusCode: 'Mount',
  radius: 19551,
};

export const sampleWithFullData: ICity = {
  id: 98868,
  name: 'Frozen Royce female',
  nameAr: 'turquoise Cab',
  nameEn: 'boohoo',
  latitude: 84375,
  longitude: 21300,
  plusCode: 'West',
  radius: 41995,
  isActive: true,
};

export const sampleWithNewData: NewCity = {
  name: 'Refined monitor Infrastructure',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
