import {ICustomer} from 'app/entities/customer/customer.model';
import {IZone} from 'app/entities/zone/zone.model';

export interface IDeliveryAddress {
  id: number;
  title?: string | null;
  address?: string | null;
  details?: string | null;
  phone?: string | null;
  isDefault?: boolean | null;
  latitude?: number | null;
  longitude?: number | null;
  plusCode?: string | null;
  isActive?: boolean | null;
  notes?: string | null;
  customer?: Pick<ICustomer, 'id' | 'name'> | null;
  zone?: Pick<IZone, 'id' | 'name'> | null;
}

export type NewDeliveryAddress = Omit<IDeliveryAddress, 'id'> & { id: null };
