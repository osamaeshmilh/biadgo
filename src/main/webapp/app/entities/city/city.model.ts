export interface ICity {
  id: number;
  name?: string | null;
  nameAr?: string | null;
  nameEn?: string | null;
  latitude?: number | null;
  longitude?: number | null;
  plusCode?: string | null;
  radius?: number | null;
  isActive?: boolean | null;
}

export type NewCity = Omit<ICity, 'id'> & { id: null };
