import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {ICoupon} from '../coupon.model';
import {CouponService} from '../service/coupon.service';

export const couponResolve = (route: ActivatedRouteSnapshot): Observable<null | ICoupon> => {
  const id = route.params['id'];
  if (id) {
    return inject(CouponService)
      .find(id)
      .pipe(
        mergeMap((coupon: HttpResponse<ICoupon>) => {
          if (coupon.body) {
            return of(coupon.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default couponResolve;
