import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {ICuisine} from '../cuisine.model';
import {CuisineService} from '../service/cuisine.service';

export const cuisineResolve = (route: ActivatedRouteSnapshot): Observable<null | ICuisine> => {
  const id = route.params['id'];
  if (id) {
    return inject(CuisineService)
      .find(id)
      .pipe(
        mergeMap((cuisine: HttpResponse<ICuisine>) => {
          if (cuisine.body) {
            return of(cuisine.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default cuisineResolve;
