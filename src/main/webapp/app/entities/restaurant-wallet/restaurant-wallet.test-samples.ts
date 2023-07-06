import {WalletAction} from 'app/entities/enumerations/wallet-action.model';
import {PaymentType} from 'app/entities/enumerations/payment-type.model';

import {IRestaurantWallet, NewRestaurantWallet} from './restaurant-wallet.model';

export const sampleWithRequiredData: IRestaurantWallet = {
  id: 19868,
};

export const sampleWithPartialData: IRestaurantWallet = {
  id: 45969,
  transactionNo: 'ken Virginia',
  totalBeforeAction: 64270,
  totalAfterAction: 87245,
  paymentType: 'CASH',
};

export const sampleWithFullData: IRestaurantWallet = {
  id: 72240,
  transactionNo: 'HDD scale male',
  amount: 37716,
  walletAction: 'WITHDRAW',
  totalBeforeAction: 37899,
  totalAfterAction: 82012,
  paymentType: 'SADAD',
  paymentReference: 'hack across',
  notes: 'Forward aha',
};

export const sampleWithNewData: NewRestaurantWallet = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
