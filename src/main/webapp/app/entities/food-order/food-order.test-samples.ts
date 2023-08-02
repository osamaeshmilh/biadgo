import {IFoodOrder, NewFoodOrder} from './food-order.model';

export const sampleWithRequiredData: IFoodOrder = {
  id: 26011,
};

export const sampleWithPartialData: IFoodOrder = {
  id: 13345,
  price: 19203,
  quantity: 6223,
  foodExtraIdsList: 'Buckinghamshire exude',
  foodIngredientRemovedIds: 'how unaccountably',
};

export const sampleWithFullData: IFoodOrder = {
  id: 30711,
  price: 7231,
  quantity: 7573,
  total: 32718,
  specialNotes: 'excepting defect',
  foodExtraIdsList: 'systems Buckinghamshire',
  foodIngredientIds: 'Electric Gasoline',
  foodIngredientRemovedIds: 'afore Funk',
};

export const sampleWithNewData: NewFoodOrder = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
