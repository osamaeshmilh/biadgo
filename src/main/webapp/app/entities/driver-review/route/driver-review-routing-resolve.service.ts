import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IDriverReview} from '../driver-review.model';
import {DriverReviewService} from '../service/driver-review.service';

export const driverReviewResolve = (route: ActivatedRouteSnapshot): Observable<null | IDriverReview> => {
  const id = route.params['id'];
  if (id) {
    return inject(DriverReviewService)
      .find(id)
      .pipe(
        mergeMap((driverReview: HttpResponse<IDriverReview>) => {
          if (driverReview.body) {
            return of(driverReview.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default driverReviewResolve;
