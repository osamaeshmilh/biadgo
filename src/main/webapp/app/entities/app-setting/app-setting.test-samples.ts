import {IAppSetting, NewAppSetting} from './app-setting.model';

export const sampleWithRequiredData: IAppSetting = {
  id: 25066,
};

export const sampleWithPartialData: IAppSetting = {
  id: 47092,
  name: 'Tactics Avon Ruthenium',
  type: 'protocol Avon',
  value: 'whoa ah explicit',
};

export const sampleWithFullData: IAppSetting = {
  id: 74065,
  name: 'vero SUV AGP',
  key: 'Fresh',
  type: 'katal',
  value: 'silver',
};

export const sampleWithNewData: NewAppSetting = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
