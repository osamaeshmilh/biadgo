import {ICustomer} from 'app/entities/customer/customer.model';
import {IDriver} from 'app/entities/driver/driver.model';

export interface IDriverReview {
  id: number;
  details?: string | null;
  rate?: number | null;
  customer?: Pick<ICustomer, 'id' | 'name'> | null;
  driver?: Pick<IDriver, 'id' | 'name'> | null;
}

export type NewDriverReview = Omit<IDriverReview, 'id'> & { id: null };
