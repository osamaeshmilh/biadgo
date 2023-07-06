import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IDriverLocation} from '../driver-location.model';
import {DriverLocationService} from '../service/driver-location.service';

export const driverLocationResolve = (route: ActivatedRouteSnapshot): Observable<null | IDriverLocation> => {
  const id = route.params['id'];
  if (id) {
    return inject(DriverLocationService)
      .find(id)
      .pipe(
        mergeMap((driverLocation: HttpResponse<IDriverLocation>) => {
          if (driverLocation.body) {
            return of(driverLocation.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default driverLocationResolve;
