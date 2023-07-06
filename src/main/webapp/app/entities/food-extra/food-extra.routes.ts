import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {FoodExtraComponent} from './list/food-extra.component';
import {FoodExtraDetailComponent} from './detail/food-extra-detail.component';
import {FoodExtraUpdateComponent} from './update/food-extra-update.component';
import FoodExtraResolve from './route/food-extra-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const foodExtraRoute: Routes = [
  {
    path: '',
    component: FoodExtraComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FoodExtraDetailComponent,
    resolve: {
      foodExtra: FoodExtraResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FoodExtraUpdateComponent,
    resolve: {
      foodExtra: FoodExtraResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FoodExtraUpdateComponent,
    resolve: {
      foodExtra: FoodExtraResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default foodExtraRoute;
