import {PaymentType} from 'app/entities/enumerations/payment-type.model';

export interface IPaymentMethod {
  id: number;
  name?: string | null;
  nameAr?: string | null;
  nameEn?: string | null;
  menuOrder?: number | null;
  imageUrl?: string | null;
  image?: string | null;
  imageContentType?: string | null;
  details?: string | null;
  feePercentage?: number | null;
  paymentType?: keyof typeof PaymentType | null;
  isActive?: boolean | null;
  notes?: string | null;
}

export type NewPaymentMethod = Omit<IPaymentMethod, 'id'> & { id: null };
