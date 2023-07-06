import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {DayOfWeek} from 'app/entities/enumerations/day-of-week.model';

export interface IRestaurantSchedule {
  id: number;
  dayOfWeek?: keyof typeof DayOfWeek | null;
  openingTime?: string | null;
  closingTime?: string | null;
  restaurant?: Pick<IRestaurant, 'id' | 'name'> | null;
}

export type NewRestaurantSchedule = Omit<IRestaurantSchedule, 'id'> & { id: null };
