import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IRestaurantWallet} from '../restaurant-wallet.model';
import {RestaurantWalletService} from '../service/restaurant-wallet.service';

export const restaurantWalletResolve = (route: ActivatedRouteSnapshot): Observable<null | IRestaurantWallet> => {
  const id = route.params['id'];
  if (id) {
    return inject(RestaurantWalletService)
      .find(id)
      .pipe(
        mergeMap((restaurantWallet: HttpResponse<IRestaurantWallet>) => {
          if (restaurantWallet.body) {
            return of(restaurantWallet.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default restaurantWalletResolve;
