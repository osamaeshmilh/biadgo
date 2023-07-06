import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IReferral} from '../referral.model';
import {ReferralService} from '../service/referral.service';

export const referralResolve = (route: ActivatedRouteSnapshot): Observable<null | IReferral> => {
  const id = route.params['id'];
  if (id) {
    return inject(ReferralService)
      .find(id)
      .pipe(
        mergeMap((referral: HttpResponse<IReferral>) => {
          if (referral.body) {
            return of(referral.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default referralResolve;
