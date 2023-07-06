import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IRestaurantSchedule} from '../restaurant-schedule.model';
import {RestaurantScheduleService} from '../service/restaurant-schedule.service';

export const restaurantScheduleResolve = (route: ActivatedRouteSnapshot): Observable<null | IRestaurantSchedule> => {
  const id = route.params['id'];
  if (id) {
    return inject(RestaurantScheduleService)
      .find(id)
      .pipe(
        mergeMap((restaurantSchedule: HttpResponse<IRestaurantSchedule>) => {
          if (restaurantSchedule.body) {
            return of(restaurantSchedule.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default restaurantScheduleResolve;
