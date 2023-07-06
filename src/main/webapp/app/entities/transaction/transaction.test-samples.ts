import {PaymentType} from 'app/entities/enumerations/payment-type.model';
import {TransactionStatus} from 'app/entities/enumerations/transaction-status.model';

import {ITransaction, NewTransaction} from './transaction.model';

export const sampleWithRequiredData: ITransaction = {
  id: 72126,
};

export const sampleWithPartialData: ITransaction = {
  id: 76535,
  paymentType: 'MOAMALAT',
  transactionStatus: 'CANCELLED',
  vendorReference: 'Hybrid',
  vendorMessage: 'Bohrium Cambridgeshire Soft',
  total: 96647,
};

export const sampleWithFullData: ITransaction = {
  id: 92433,
  transactionReference: 'speedily Mobility',
  paymentType: 'SADAD',
  transactionStatus: 'CANCELLED',
  vendorReference: 'Pizza',
  vendorMessage: 'ransack Raleigh',
  amount: 36143,
  fees: 17056,
  total: 81747,
  notes: 'Dynamic',
};

export const sampleWithNewData: NewTransaction = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
