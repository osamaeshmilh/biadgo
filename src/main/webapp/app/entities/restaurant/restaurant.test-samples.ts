import {DeliveryPriceType} from 'app/entities/enumerations/delivery-price-type.model';

import {IRestaurant, NewRestaurant} from './restaurant.model';

export const sampleWithRequiredData: IRestaurant = {
  id: 90353,
};

export const sampleWithPartialData: IRestaurant = {
  id: 22439,
  nameAr: 'Soft Market West',
  description: 'parallelism mindshare Jeep',
  latitude: 27536,
  mobile: 'Assurance Awesome',
  estimitedTimeMins: 73986,
  deliveryFee: 32106,
  deliveryRange: 6283,
  availableForPickup: false,
  minimumOrderTotalForDelivery: 35816,
  deliveryPriceType: 'ZONE',
  isOpen: true,
  isListedInOffers: false,
  offerBanner: 'and',
  viewCounter: 98874,
  scheduledClosingTime: 'JBOD Northwest',
  facebookPageUrl: 'Analyst',
  notes: 'fixture',
};

export const sampleWithFullData: IRestaurant = {
  id: 98732,
  name: 'Tuvalu',
  nameAr: 'hacking Assurance',
  nameEn: 'Yemeni West',
  description: 'Handmade Borders',
  address: 'North Executive',
  latitude: 60521,
  longitude: 98397,
  plusCode: 'Checking',
  mobile: 'Kentucky Seychelles Titanium',
  estimitedTimeMins: 77896,
  deliveryFee: 51726,
  deliveryRange: 82702,
  availableForDelivery: true,
  availableForPickup: true,
  minimumOrderTotalForDelivery: 2401,
  deliveryPriceType: 'ZONE',
  isOpen: false,
  isFeatured: true,
  isListedInOffers: false,
  isActive: false,
  offerBanner: 'customized female aside',
  priority: 74942,
  viewCounter: 17442,
  scheduledClosingTime: 'Trigender',
  facebookPageUrl: 'Idaho Loan Recumbent',
  notes: 'cyan North synergies',
};

export const sampleWithNewData: NewRestaurant = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
