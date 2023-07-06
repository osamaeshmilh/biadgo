import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IRestaurantZonePrice} from '../restaurant-zone-price.model';
import {RestaurantZonePriceService} from '../service/restaurant-zone-price.service';

export const restaurantZonePriceResolve = (route: ActivatedRouteSnapshot): Observable<null | IRestaurantZonePrice> => {
  const id = route.params['id'];
  if (id) {
    return inject(RestaurantZonePriceService)
      .find(id)
      .pipe(
        mergeMap((restaurantZonePrice: HttpResponse<IRestaurantZonePrice>) => {
          if (restaurantZonePrice.body) {
            return of(restaurantZonePrice.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default restaurantZonePriceResolve;
