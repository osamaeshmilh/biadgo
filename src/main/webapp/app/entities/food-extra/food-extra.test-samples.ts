import {IFoodExtra, NewFoodExtra} from './food-extra.model';

export const sampleWithRequiredData: IFoodExtra = {
  id: 68063,
};

export const sampleWithPartialData: IFoodExtra = {
  id: 91140,
  name: 'fuchsia geez',
  price: 36637,
  notes: 'Other outset HTTP',
};

export const sampleWithFullData: IFoodExtra = {
  id: 93635,
  name: 'Smart Savings',
  nameAr: 'Ngultrum Northwest Philippine',
  nameEn: 'brand Arlington withdrawal',
  price: 82813,
  notes: 'Granite protocol',
};

export const sampleWithNewData: NewFoodExtra = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
