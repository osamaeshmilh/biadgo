import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IFavoriteRestaurant} from '../favorite-restaurant.model';
import {FavoriteRestaurantService} from '../service/favorite-restaurant.service';

export const favoriteRestaurantResolve = (route: ActivatedRouteSnapshot): Observable<null | IFavoriteRestaurant> => {
  const id = route.params['id'];
  if (id) {
    return inject(FavoriteRestaurantService)
      .find(id)
      .pipe(
        mergeMap((favoriteRestaurant: HttpResponse<IFavoriteRestaurant>) => {
          if (favoriteRestaurant.body) {
            return of(favoriteRestaurant.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default favoriteRestaurantResolve;
