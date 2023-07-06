import {IUser} from 'app/entities/user/user.model';

export interface ICustomer {
  id: number;
  name?: string | null;
  email?: string | null;
  mobileNo?: string | null;
  googleId?: string | null;
  facebookId?: string | null;
  appleId?: string | null;
  isBanned?: boolean | null;
  isVerified?: boolean | null;
  verifiedByEmail?: boolean | null;
  verifiedByMobileNo?: boolean | null;
  walletPublicKey?: string | null;
  image?: string | null;
  imageContentType?: string | null;
  imageUrl?: string | null;
  languageCode?: string | null;
  notes?: string | null;
  user?: Pick<IUser, 'id'> | null;
}

export type NewCustomer = Omit<ICustomer, 'id'> & { id: null };
