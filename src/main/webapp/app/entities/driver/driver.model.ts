import dayjs from 'dayjs/esm';
import {IUser} from 'app/entities/user/user.model';
import {IZone} from 'app/entities/zone/zone.model';
import {DriverType} from 'app/entities/enumerations/driver-type.model';
import {DriverPaymentType} from 'app/entities/enumerations/driver-payment-type.model';
import {VehicleType} from 'app/entities/enumerations/vehicle-type.model';
import {DriverStatus} from 'app/entities/enumerations/driver-status.model';

export interface IDriver {
  id: number;
  name?: string | null;
  nameAr?: string | null;
  nameEn?: string | null;
  mobileNo?: string | null;
  email?: string | null;
  imageUrl?: string | null;
  image?: string | null;
  imageContentType?: string | null;
  driverType?: keyof typeof DriverType | null;
  driverPaymentType?: keyof typeof DriverPaymentType | null;
  vehicleType?: keyof typeof VehicleType | null;
  driverStatus?: keyof typeof DriverStatus | null;
  commissionAmount?: number | null;
  salaryAmount?: number | null;
  latitude?: number | null;
  longitude?: number | null;
  plusCode?: string | null;
  lastLocationDateTime?: dayjs.Dayjs | null;
  isAvailable?: boolean | null;
  notes?: string | null;
  user?: Pick<IUser, 'id'> | null;
  zone?: Pick<IZone, 'id' | 'name'> | null;
}

export type NewDriver = Omit<IDriver, 'id'> & { id: null };
