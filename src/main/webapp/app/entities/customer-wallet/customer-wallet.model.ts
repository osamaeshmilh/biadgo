import {ICustomer} from 'app/entities/customer/customer.model';
import {WalletAction} from 'app/entities/enumerations/wallet-action.model';
import {PaymentType} from 'app/entities/enumerations/payment-type.model';

export interface ICustomerWallet {
  id: number;
  transactionNo?: string | null;
  amount?: number | null;
  walletAction?: keyof typeof WalletAction | null;
  totalBeforeAction?: number | null;
  totalAfterAction?: number | null;
  paymentType?: keyof typeof PaymentType | null;
  paymentReference?: string | null;
  orderId?: string | null;
  notes?: string | null;
  customer?: Pick<ICustomer, 'id' | 'name'> | null;
}

export type NewCustomerWallet = Omit<ICustomerWallet, 'id'> & { id: null };
