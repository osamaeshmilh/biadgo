import {ICustomer} from 'app/entities/customer/customer.model';
import {PaymentType} from 'app/entities/enumerations/payment-type.model';
import {TransactionStatus} from 'app/entities/enumerations/transaction-status.model';

export interface ITransaction {
  id: number;
  transactionReference?: string | null;
  paymentType?: keyof typeof PaymentType | null;
  transactionStatus?: keyof typeof TransactionStatus | null;
  vendorReference?: string | null;
  vendorMessage?: string | null;
  amount?: number | null;
  fees?: number | null;
  total?: number | null;
  notes?: string | null;
  customer?: Pick<ICustomer, 'id' | 'name'> | null;
}

export type NewTransaction = Omit<ITransaction, 'id'> & { id: null };
