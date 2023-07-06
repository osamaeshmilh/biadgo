import dayjs from 'dayjs/esm';

export interface IActivation {
  id: number;
  mobileNo?: string | null;
  email?: string | null;
  code?: string | null;
  sentOn?: dayjs.Dayjs | null;
  validUntil?: dayjs.Dayjs | null;
  isUsed?: boolean | null;
}

export type NewActivation = Omit<IActivation, 'id'> & { id: null };
