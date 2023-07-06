import {IFoodOrder, NewFoodOrder} from './food-order.model';

export const sampleWithRequiredData: IFoodOrder = {
  id: 79380,
};

export const sampleWithPartialData: IFoodOrder = {
  id: 40728,
  total: 58605,
  specialNotes: 'Metal',
  foodIngredientIds: 'extort saepe Refined',
};

export const sampleWithFullData: IFoodOrder = {
  id: 23111,
  price: 99848,
  quantity: 39695,
  total: 97806,
  specialNotes: 'Visionary Classical',
  foodExtraIdsList: 'Rock',
  foodIngredientIds: 'Usability Gasoline XML',
  foodIngredientRemovedIds: 'Funk',
};

export const sampleWithNewData: NewFoodOrder = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
