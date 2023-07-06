import dayjs from 'dayjs/esm';
import {IDriver} from 'app/entities/driver/driver.model';

export interface IDriverLocation {
  id: number;
  latitude?: number | null;
  longitude?: number | null;
  plusCode?: string | null;
  locationDateTime?: dayjs.Dayjs | null;
  driver?: Pick<IDriver, 'id' | 'name'> | null;
}

export type NewDriverLocation = Omit<IDriverLocation, 'id'> & { id: null };
