import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IDeliveryAddress} from '../delivery-address.model';
import {DeliveryAddressService} from '../service/delivery-address.service';

export const deliveryAddressResolve = (route: ActivatedRouteSnapshot): Observable<null | IDeliveryAddress> => {
  const id = route.params['id'];
  if (id) {
    return inject(DeliveryAddressService)
      .find(id)
      .pipe(
        mergeMap((deliveryAddress: HttpResponse<IDeliveryAddress>) => {
          if (deliveryAddress.body) {
            return of(deliveryAddress.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default deliveryAddressResolve;
