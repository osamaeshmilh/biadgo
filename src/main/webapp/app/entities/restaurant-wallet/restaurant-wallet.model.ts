import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {WalletAction} from 'app/entities/enumerations/wallet-action.model';
import {PaymentType} from 'app/entities/enumerations/payment-type.model';

export interface IRestaurantWallet {
  id: number;
  transactionNo?: string | null;
  amount?: number | null;
  walletAction?: keyof typeof WalletAction | null;
  totalBeforeAction?: number | null;
  totalAfterAction?: number | null;
  paymentType?: keyof typeof PaymentType | null;
  paymentReference?: string | null;
  notes?: string | null;
  restaurant?: Pick<IRestaurant, 'id' | 'name'> | null;
}

export type NewRestaurantWallet = Omit<IRestaurantWallet, 'id'> & { id: null };
