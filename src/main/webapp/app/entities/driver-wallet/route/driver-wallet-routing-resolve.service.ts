import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IDriverWallet} from '../driver-wallet.model';
import {DriverWalletService} from '../service/driver-wallet.service';

export const driverWalletResolve = (route: ActivatedRouteSnapshot): Observable<null | IDriverWallet> => {
  const id = route.params['id'];
  if (id) {
    return inject(DriverWalletService)
      .find(id)
      .pipe(
        mergeMap((driverWallet: HttpResponse<IDriverWallet>) => {
          if (driverWallet.body) {
            return of(driverWallet.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default driverWalletResolve;
