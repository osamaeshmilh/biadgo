import {WalletAction} from 'app/entities/enumerations/wallet-action.model';
import {PaymentType} from 'app/entities/enumerations/payment-type.model';

import {ICustomerWallet, NewCustomerWallet} from './customer-wallet.model';

export const sampleWithRequiredData: ICustomerWallet = {
  id: 959,
};

export const sampleWithPartialData: ICustomerWallet = {
  id: 44738,
  transactionNo: 'ew Radon Cambridgeshire',
  amount: 63843,
  walletAction: 'DEPOSIT',
  totalBeforeAction: 13728,
  paymentType: 'CASH',
  notes: 'iste Tesla',
};

export const sampleWithFullData: ICustomerWallet = {
  id: 22259,
  transactionNo: 'Polestar proactive Diesel',
  amount: 32231,
  walletAction: 'WITHDRAW',
  totalBeforeAction: 77053,
  totalAfterAction: 85154,
  paymentType: 'WALLET',
  paymentReference: 'Comoro',
  orderId: 'Southwest East Keebler',
  notes: 'back',
};

export const sampleWithNewData: NewCustomerWallet = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
