import {IZone, NewZone} from './zone.model';

export const sampleWithRequiredData: IZone = {
  id: 78337,
};

export const sampleWithPartialData: IZone = {
  id: 563,
  nameEn: 'nautical',
  isActive: false,
  notes: 'magenta',
};

export const sampleWithFullData: IZone = {
  id: 88174,
  name: 'salmon',
  nameAr: 'Electronic Tasty',
  nameEn: 'gratify Plastic',
  latitude: 82219,
  longitude: 23846,
  plusCode: 'blockchains',
  radius: 18677,
  isActive: true,
  notes: 'male',
};

export const sampleWithNewData: NewZone = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
