import {WalletAction} from 'app/entities/enumerations/wallet-action.model';
import {PaymentType} from 'app/entities/enumerations/payment-type.model';

import {IDriverWallet, NewDriverWallet} from './driver-wallet.model';

export const sampleWithRequiredData: IDriverWallet = {
  id: 81754,
};

export const sampleWithPartialData: IDriverWallet = {
  id: 12852,
  amount: 52943,
  walletAction: 'WITHDRAW',
  totalAfterAction: 27056,
  paymentType: 'MOBICASH',
  paymentReference: 'pixel',
  orderId: 'National',
};

export const sampleWithFullData: IDriverWallet = {
  id: 4871,
  transactionNo: 'Oriental array',
  amount: 96347,
  walletAction: 'DEPOSIT',
  totalBeforeAction: 32986,
  totalAfterAction: 31917,
  paymentType: 'CASH',
  paymentReference: 'transmit defect',
  orderId: 'joule whoever sharply',
  notes: 'deposit VGA Bicycle',
};

export const sampleWithNewData: NewDriverWallet = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
