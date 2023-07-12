import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IRestaurantImage} from '../restaurant-image.model';
import {RestaurantImageService} from '../service/restaurant-image.service';

export const restaurantImageResolve = (route: ActivatedRouteSnapshot): Observable<null | IRestaurantImage> => {
  const id = route.params['id'];
  if (id) {
    return inject(RestaurantImageService)
      .find(id)
      .pipe(
        mergeMap((restaurantImage: HttpResponse<IRestaurantImage>) => {
          if (restaurantImage.body) {
            return of(restaurantImage.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default restaurantImageResolve;
