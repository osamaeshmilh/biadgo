import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {RestaurantScheduleComponent} from './list/restaurant-schedule.component';
import {RestaurantScheduleDetailComponent} from './detail/restaurant-schedule-detail.component';
import {RestaurantScheduleUpdateComponent} from './update/restaurant-schedule-update.component';
import RestaurantScheduleResolve from './route/restaurant-schedule-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const restaurantScheduleRoute: Routes = [
  {
    path: '',
    component: RestaurantScheduleComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RestaurantScheduleDetailComponent,
    resolve: {
      restaurantSchedule: RestaurantScheduleResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RestaurantScheduleUpdateComponent,
    resolve: {
      restaurantSchedule: RestaurantScheduleResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RestaurantScheduleUpdateComponent,
    resolve: {
      restaurantSchedule: RestaurantScheduleResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default restaurantScheduleRoute;
