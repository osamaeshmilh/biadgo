export interface IAppSetting {
  id: number;
  name?: string | null;
  key?: string | null;
  type?: string | null;
  value?: string | null;
}

export type NewAppSetting = Omit<IAppSetting, 'id'> & { id: null };
