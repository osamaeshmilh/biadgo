import {ICart, NewCart} from './cart.model';

export const sampleWithRequiredData: ICart = {
  id: 46694,
};

export const sampleWithPartialData: ICart = {
  id: 52515,
  quantity: 16560,
  foodIngredientIds: 'UDP green size',
  foodIngredientRemovedIds: 'implement Southeast',
};

export const sampleWithFullData: ICart = {
  id: 77867,
  quantity: 63033,
  customerNotes: 'Infrastructure District',
  foodExtraIdsList: 'digital',
  foodIngredientIds: 'Identity baseboard interfaces',
  foodIngredientRemovedIds: 'Bicycle',
};

export const sampleWithNewData: NewCart = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
