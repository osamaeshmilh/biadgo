import {DayOfWeek} from 'app/entities/enumerations/day-of-week.model';

import {IRestaurantSchedule, NewRestaurantSchedule} from './restaurant-schedule.model';

export const sampleWithRequiredData: IRestaurantSchedule = {
  id: 16424,
};

export const sampleWithPartialData: IRestaurantSchedule = {
  id: 97271,
  closingTime: 'Sleek',
};

export const sampleWithFullData: IRestaurantSchedule = {
  id: 1456,
  dayOfWeek: 'MONDAY',
  openingTime: 'Male Littleton Developer',
  closingTime: 'Account quis',
};

export const sampleWithNewData: NewRestaurantSchedule = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
