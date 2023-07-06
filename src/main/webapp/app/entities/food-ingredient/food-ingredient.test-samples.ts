import {IFoodIngredient, NewFoodIngredient} from './food-ingredient.model';

export const sampleWithRequiredData: IFoodIngredient = {
  id: 51149,
};

export const sampleWithPartialData: IFoodIngredient = {
  id: 82553,
  name: 'Bicycle absolute',
  nameAr: 'heavy',
};

export const sampleWithFullData: IFoodIngredient = {
  id: 16622,
  name: 'hack',
  nameAr: 'innovative Martin',
  nameEn: 'Soul',
  notes: 'Dodge',
};

export const sampleWithNewData: NewFoodIngredient = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
