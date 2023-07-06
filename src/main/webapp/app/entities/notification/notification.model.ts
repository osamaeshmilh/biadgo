export interface INotification {
  id: number;
  title?: string | null;
  details?: string | null;
  isRead?: boolean | null;
  customerId?: number | null;
}

export type NewNotification = Omit<INotification, 'id'> & { id: null };
