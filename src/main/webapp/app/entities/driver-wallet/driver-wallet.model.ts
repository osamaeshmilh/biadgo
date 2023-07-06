import {IDriver} from 'app/entities/driver/driver.model';
import {WalletAction} from 'app/entities/enumerations/wallet-action.model';
import {PaymentType} from 'app/entities/enumerations/payment-type.model';

export interface IDriverWallet {
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
  driver?: Pick<IDriver, 'id' | 'name'> | null;
}

export type NewDriverWallet = Omit<IDriverWallet, 'id'> & { id: null };
