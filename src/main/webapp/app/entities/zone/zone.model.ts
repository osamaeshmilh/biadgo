import {ICity} from 'app/entities/city/city.model';

export interface IZone {
  id: number;
  name?: string | null;
  nameAr?: string | null;
  nameEn?: string | null;
  latitude?: number | null;
  longitude?: number | null;
  plusCode?: string | null;
  radius?: number | null;
  isActive?: boolean | null;
  notes?: string | null;
  city?: Pick<ICity, 'id' | 'name'> | null;
}

export type NewZone = Omit<IZone, 'id'> & { id: null };
