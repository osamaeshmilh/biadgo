import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IRestaurant} from '../restaurant.model';
import {RestaurantService} from '../service/restaurant.service';

export const restaurantResolve = (route: ActivatedRouteSnapshot): Observable<null | IRestaurant> => {
  const id = route.params['id'];
  if (id) {
    return inject(RestaurantService)
      .find(id)
      .pipe(
        mergeMap((restaurant: HttpResponse<IRestaurant>) => {
          if (restaurant.body) {
            return of(restaurant.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default restaurantResolve;
