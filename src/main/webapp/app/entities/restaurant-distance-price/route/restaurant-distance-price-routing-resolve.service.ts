import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IRestaurantDistancePrice} from '../restaurant-distance-price.model';
import {RestaurantDistancePriceService} from '../service/restaurant-distance-price.service';

export const restaurantDistancePriceResolve = (route: ActivatedRouteSnapshot): Observable<null | IRestaurantDistancePrice> => {
  const id = route.params['id'];
  if (id) {
    return inject(RestaurantDistancePriceService)
      .find(id)
      .pipe(
        mergeMap((restaurantDistancePrice: HttpResponse<IRestaurantDistancePrice>) => {
          if (restaurantDistancePrice.body) {
            return of(restaurantDistancePrice.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default restaurantDistancePriceResolve;
