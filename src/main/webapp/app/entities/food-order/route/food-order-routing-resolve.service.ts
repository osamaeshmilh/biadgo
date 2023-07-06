import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IFoodOrder} from '../food-order.model';
import {FoodOrderService} from '../service/food-order.service';

export const foodOrderResolve = (route: ActivatedRouteSnapshot): Observable<null | IFoodOrder> => {
  const id = route.params['id'];
  if (id) {
    return inject(FoodOrderService)
      .find(id)
      .pipe(
        mergeMap((foodOrder: HttpResponse<IFoodOrder>) => {
          if (foodOrder.body) {
            return of(foodOrder.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default foodOrderResolve;
