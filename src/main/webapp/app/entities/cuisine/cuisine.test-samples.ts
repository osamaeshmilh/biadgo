import {ICuisine, NewCuisine} from './cuisine.model';

export const sampleWithRequiredData: ICuisine = {
  id: 43370,
  name: 'migration auxiliary',
};

export const sampleWithPartialData: ICuisine = {
  id: 6175,
  name: 'Fall',
  nameAr: 'abaft Lesotho',
  nameEn: 'schemas',
  isActive: true,
};

export const sampleWithFullData: ICuisine = {
  id: 16560,
  name: 'Baby Indium',
  nameAr: 'Rubber',
  nameEn: 'Mongolia HTTP',
  isActive: true,
};

export const sampleWithNewData: NewCuisine = {
  name: 'whose West Northwest',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
