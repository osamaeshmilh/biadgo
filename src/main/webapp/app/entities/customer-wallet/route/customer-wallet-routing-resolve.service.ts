import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {ICustomerWallet} from '../customer-wallet.model';
import {CustomerWalletService} from '../service/customer-wallet.service';

export const customerWalletResolve = (route: ActivatedRouteSnapshot): Observable<null | ICustomerWallet> => {
  const id = route.params['id'];
  if (id) {
    return inject(CustomerWalletService)
      .find(id)
      .pipe(
        mergeMap((customerWallet: HttpResponse<ICustomerWallet>) => {
          if (customerWallet.body) {
            return of(customerWallet.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default customerWalletResolve;
