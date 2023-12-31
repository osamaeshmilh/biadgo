import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IOrderHistory} from '../order-history.model';
import {OrderHistoryService} from '../service/order-history.service';

export const orderHistoryResolve = (route: ActivatedRouteSnapshot): Observable<null | IOrderHistory> => {
  const id = route.params['id'];
  if (id) {
    return inject(OrderHistoryService)
      .find(id)
      .pipe(
        mergeMap((orderHistory: HttpResponse<IOrderHistory>) => {
          if (orderHistory.body) {
            return of(orderHistory.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default orderHistoryResolve;
