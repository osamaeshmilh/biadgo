import {IFoodImage, NewFoodImage} from './food-image.model';

export const sampleWithRequiredData: IFoodImage = {
  id: 24625,
};

export const sampleWithPartialData: IFoodImage = {
  id: 34801,
  description: 'Cargo 1080p synergize',
  descriptionEn: 'Operations Distributed Cisgender',
  menuOrder: 44146,
  imageUrl: 'hierarchy',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
};

export const sampleWithFullData: IFoodImage = {
  id: 17024,
  description: 'Shields Burke encryption',
  descriptionAr: 'Kip Human',
  descriptionEn: 'purple',
  menuOrder: 42100,
  imageUrl: 'Plastic firmware Nissan',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
};

export const sampleWithNewData: NewFoodImage = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
