import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {FoodIngredientComponent} from './list/food-ingredient.component';
import {FoodIngredientDetailComponent} from './detail/food-ingredient-detail.component';
import {FoodIngredientUpdateComponent} from './update/food-ingredient-update.component';
import FoodIngredientResolve from './route/food-ingredient-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const foodIngredientRoute: Routes = [
  {
    path: '',
    component: FoodIngredientComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FoodIngredientDetailComponent,
    resolve: {
      foodIngredient: FoodIngredientResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FoodIngredientUpdateComponent,
    resolve: {
      foodIngredient: FoodIngredientResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FoodIngredientUpdateComponent,
    resolve: {
      foodIngredient: FoodIngredientResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default foodIngredientRoute;
