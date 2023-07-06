import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IFoodImage} from '../food-image.model';
import {FoodImageService} from '../service/food-image.service';

export const foodImageResolve = (route: ActivatedRouteSnapshot): Observable<null | IFoodImage> => {
  const id = route.params['id'];
  if (id) {
    return inject(FoodImageService)
      .find(id)
      .pipe(
        mergeMap((foodImage: HttpResponse<IFoodImage>) => {
          if (foodImage.body) {
            return of(foodImage.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default foodImageResolve;
