import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IFoodIngredient} from '../food-ingredient.model';
import {FoodIngredientService} from '../service/food-ingredient.service';

export const foodIngredientResolve = (route: ActivatedRouteSnapshot): Observable<null | IFoodIngredient> => {
  const id = route.params['id'];
  if (id) {
    return inject(FoodIngredientService)
      .find(id)
      .pipe(
        mergeMap((foodIngredient: HttpResponse<IFoodIngredient>) => {
          if (foodIngredient.body) {
            return of(foodIngredient.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default foodIngredientResolve;
