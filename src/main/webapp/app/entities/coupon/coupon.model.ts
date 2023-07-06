import dayjs from 'dayjs/esm';
import {CouponType} from 'app/entities/enumerations/coupon-type.model';

export interface ICoupon {
  id: number;
  code?: string | null;
  couponType?: keyof typeof CouponType | null;
  amount?: number | null;
  minimumAmount?: number | null;
  useLimit?: number | null;
  useCount?: number | null;
  expiryDate?: dayjs.Dayjs | null;
  isActive?: boolean | null;
  notes?: string | null;
}

export type NewCoupon = Omit<ICoupon, 'id'> & { id: null };
