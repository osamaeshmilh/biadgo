import {IRestaurantImage, NewRestaurantImage} from './restaurant-image.model';

export const sampleWithRequiredData: IRestaurantImage = {
  id: 34876,
};

export const sampleWithPartialData: IRestaurantImage = {
  id: 73710,
  descriptionAr: 'Rubber Plastic',
  descriptionEn: 'Southwest infrastructure',
  imageType: 'Coordinator abaft',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
};

export const sampleWithFullData: IRestaurantImage = {
  id: 65355,
  description: 'quos becquerel',
  descriptionAr: 'ecstatic Checking',
  descriptionEn: 'ick Internal',
  imageType: 'ivory Quality',
  menuOrder: 23988,
  imageUrl: 'as',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
};

export const sampleWithNewData: NewRestaurantImage = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
