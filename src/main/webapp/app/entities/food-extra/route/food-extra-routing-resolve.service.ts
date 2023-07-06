import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IFoodExtra} from '../food-extra.model';
import {FoodExtraService} from '../service/food-extra.service';

export const foodExtraResolve = (route: ActivatedRouteSnapshot): Observable<null | IFoodExtra> => {
  const id = route.params['id'];
  if (id) {
    return inject(FoodExtraService)
      .find(id)
      .pipe(
        mergeMap((foodExtra: HttpResponse<IFoodExtra>) => {
          if (foodExtra.body) {
            return of(foodExtra.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default foodExtraResolve;
