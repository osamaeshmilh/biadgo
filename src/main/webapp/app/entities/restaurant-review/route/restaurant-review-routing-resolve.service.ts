import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IRestaurantReview} from '../restaurant-review.model';
import {RestaurantReviewService} from '../service/restaurant-review.service';

export const restaurantReviewResolve = (route: ActivatedRouteSnapshot): Observable<null | IRestaurantReview> => {
  const id = route.params['id'];
  if (id) {
    return inject(RestaurantReviewService)
      .find(id)
      .pipe(
        mergeMap((restaurantReview: HttpResponse<IRestaurantReview>) => {
          if (restaurantReview.body) {
            return of(restaurantReview.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default restaurantReviewResolve;
