import {ISlider, NewSlider} from './slider.model';

export const sampleWithRequiredData: ISlider = {
  id: 76430,
};

export const sampleWithPartialData: ISlider = {
  id: 11973,
  detailsAr: 'driver invoice',
  detailsEn: 'Hop newton',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
  url: 'http://staid-microphone.org',
  restaurantId: 72671,
  categoryId: 1561,
  foodId: 38081,
};

export const sampleWithFullData: ISlider = {
  id: 79067,
  details: 'Alfreda',
  detailsAr: 'Turkmenistan',
  detailsEn: 'architectures auxiliary ohm',
  menuOrder: 24571,
  imageUrl: 'Health',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
  url: 'http://outgoing-lawn.name',
  restaurantId: 38522,
  categoryId: 38669,
  foodId: 34061,
  notes: 'Mendelevium collectivization compress',
};

export const sampleWithNewData: NewSlider = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
