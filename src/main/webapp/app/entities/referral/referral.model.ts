import dayjs from 'dayjs/esm';
import {ICustomer} from 'app/entities/customer/customer.model';

export interface IReferral {
  id: number;
  referralCode?: string | null;
  referrerAmount?: number | null;
  referredCustomerAmount?: number | null;
  expiryDate?: dayjs.Dayjs | null;
  isUsed?: boolean | null;
  usedDateTime?: dayjs.Dayjs | null;
  notes?: string | null;
  referredCustomer?: Pick<ICustomer, 'id'> | null;
  referrerCustomer?: Pick<ICustomer, 'id'> | null;
}

export type NewReferral = Omit<IReferral, 'id'> & { id: null };
