import {ICustomer, NewCustomer} from './customer.model';

export const sampleWithRequiredData: ICustomer = {
  id: 14554,
  name: 'Bronze',
};

export const sampleWithPartialData: ICustomer = {
  id: 79650,
  name: 'Kasandra deport',
  googleId: 'quantifying',
  facebookId: 'Lompoc',
  verifiedByEmail: false,
  walletPublicKey: 'calculate',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
  imageUrl: 'yippee 1080p veniam',
  languageCode: 'Incredible',
  notes: 'Maine overriding azure',
};

export const sampleWithFullData: ICustomer = {
  id: 5914,
  name: 'connect North HTTP',
  email: 'Ressie_Tremblay@yahoo.com',
  mobileNo: 'ouch Cheese',
  googleId: 'back niches strategy',
  facebookId: 'Checking',
  appleId: 'Jewelery',
  isBanned: true,
  isVerified: false,
  verifiedByEmail: true,
  verifiedByMobileNo: false,
  walletPublicKey: 'female tomorrow',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
  imageUrl: 'plum USB',
  languageCode: 'partially lime Funk',
  notes: 'West',
};

export const sampleWithNewData: NewCustomer = {
  name: 'Movies',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
