import dayjs from 'dayjs/esm';

import {IReferral, NewReferral} from './referral.model';

export const sampleWithRequiredData: IReferral = {
  id: 23560,
};

export const sampleWithPartialData: IReferral = {
  id: 16096,
  referralCode: 'Poland',
  referrerAmount: 53392,
  referredCustomerAmount: 70888,
  isUsed: false,
  usedDateTime: dayjs('2023-07-05T13:17'),
};

export const sampleWithFullData: IReferral = {
  id: 34976,
  referralCode: 'North Wehner hacking',
  referrerAmount: 45767,
  referredCustomerAmount: 9674,
  expiryDate: dayjs('2023-07-05'),
  isUsed: false,
  usedDateTime: dayjs('2023-07-05T18:39'),
  notes: 'District Northwest',
};

export const sampleWithNewData: NewReferral = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
