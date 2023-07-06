import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {ICart} from '../cart.model';
import {CartService} from '../service/cart.service';

export const cartResolve = (route: ActivatedRouteSnapshot): Observable<null | ICart> => {
  const id = route.params['id'];
  if (id) {
    return inject(CartService)
      .find(id)
      .pipe(
        mergeMap((cart: HttpResponse<ICart>) => {
          if (cart.body) {
            return of(cart.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default cartResolve;
