import dayjs from 'dayjs/esm';

import {CouponType} from 'app/entities/enumerations/coupon-type.model';

import {ICoupon, NewCoupon} from './coupon.model';

export const sampleWithRequiredData: ICoupon = {
  id: 40417,
};

export const sampleWithPartialData: ICoupon = {
  id: 44335,
  code: 'index transparent',
  amount: 76083,
  useCount: 13374,
  expiryDate: dayjs('2023-07-05'),
  isActive: false,
};

export const sampleWithFullData: ICoupon = {
  id: 12461,
  code: 'Facilitator Assistant',
  couponType: 'FIXED',
  amount: 72410,
  minimumAmount: 18922,
  useLimit: 61779,
  useCount: 93253,
  expiryDate: dayjs('2023-07-05'),
  isActive: true,
  notes: 'Michigan invoice',
};

export const sampleWithNewData: NewCoupon = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
