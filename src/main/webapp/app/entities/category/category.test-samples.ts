import {ICategory, NewCategory} from './category.model';

export const sampleWithRequiredData: ICategory = {
  id: 17071,
  name: 'er Chrysler Male',
};

export const sampleWithPartialData: ICategory = {
  id: 56131,
  name: 'Mini',
  nameAr: 'Creative RAM',
  nameEn: 'Albania Handmade',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
  notes: 'interface SUV',
  isActive: true,
};

export const sampleWithFullData: ICategory = {
  id: 39723,
  name: 'esse woot',
  nameAr: 'voluptatibus Guinea Markets',
  nameEn: 'female Rubber',
  menuOrder: 42787,
  imageUrl: 'Bethesda',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
  notes: 'matrix Northeast',
  isActive: true,
};

export const sampleWithNewData: NewCategory = {
  name: 'SSL',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
