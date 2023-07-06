import dayjs from 'dayjs/esm';

import {DriverType} from 'app/entities/enumerations/driver-type.model';
import {DriverPaymentType} from 'app/entities/enumerations/driver-payment-type.model';
import {VehicleType} from 'app/entities/enumerations/vehicle-type.model';
import {DriverStatus} from 'app/entities/enumerations/driver-status.model';

import {IDriver, NewDriver} from './driver.model';

export const sampleWithRequiredData: IDriver = {
  id: 91644,
  name: 'Mountain magnetic black',
};

export const sampleWithPartialData: IDriver = {
  id: 78213,
  name: 'Pennsylvania synergize',
  nameAr: 'BMX',
  nameEn: 'Central',
  mobileNo: 'daintily',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
  driverType: 'COMPANY',
  driverPaymentType: 'PERCENT',
  driverStatus: 'AT_CUSTOMER',
  salaryAmount: 26005,
  latitude: 25954,
  lastLocationDateTime: dayjs('2023-07-05T16:37'),
  isAvailable: true,
  notes: 'Bahrain',
};

export const sampleWithFullData: IDriver = {
  id: 63038,
  name: 'save female compressing',
  nameAr: 'Chevrolet Electric though',
  nameEn: 'alliance phooey Northeast',
  mobileNo: 'male',
  email: 'Ed37@yahoo.com',
  imageUrl: 'Account concerning Bedfordshire',
  image: '../fake-data/blob/hipster.png',
  imageContentType: 'unknown',
  driverType: 'COMPANY',
  driverPaymentType: 'FIXED',
  vehicleType: 'TRUCK',
  driverStatus: 'BREAK',
  commissionAmount: 88576,
  salaryAmount: 4979,
  latitude: 47940,
  longitude: 93323,
  plusCode: 'homogeneous Murray',
  lastLocationDateTime: dayjs('2023-07-05T20:56'),
  isAvailable: true,
  notes: 'mindshare Pound Moldovan',
};

export const sampleWithNewData: NewDriver = {
  name: 'Chief Rock JBOD',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
