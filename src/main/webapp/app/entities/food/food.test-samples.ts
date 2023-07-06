import {IFood, NewFood} from './food.model';

export const sampleWithRequiredData: IFood = {
  id: 15999,
};

export const sampleWithPartialData: IFood = {
  id: 51498,
  name: 'tesla Polestar',
  nameEn: 'Cotton BMW Northeast',
  price: 18411,
  discountPrice: 43805,
  description: 'habitat payment North',
  descriptionAr: 'Country ugh',
  packageItemsCount: 3615,
  isAvailable: true,
  isDiscount: true,
  isFeatured: false,
  isActive: true,
  notes: 'via',
};

export const sampleWithFullData: IFood = {
  id: 45420,
  name: 'Planner',
  nameAr: 'weber',
  nameEn: 'Outdoors',
  price: 65128,
  discountPrice: 58456,
  description: 'convergence Reggae',
  descriptionAr: 'indexing driver',
  descriptionEn: 'Southeast',
  packageItemsCount: 30165,
  dailyQuantity: 82740,
  isAvailable: false,
  isDiscount: false,
  isFeatured: true,
  isActive: true,
  viewCounter: 40694,
  notes: 'Wooden pink',
};

export const sampleWithNewData: NewFood = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
