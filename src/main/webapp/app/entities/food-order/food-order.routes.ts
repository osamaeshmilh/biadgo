import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {FoodOrderComponent} from './list/food-order.component';
import {FoodOrderDetailComponent} from './detail/food-order-detail.component';
import {FoodOrderUpdateComponent} from './update/food-order-update.component';
import FoodOrderResolve from './route/food-order-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const foodOrderRoute: Routes = [
  {
    path: '',
    component: FoodOrderComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FoodOrderDetailComponent,
    resolve: {
      foodOrder: FoodOrderResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FoodOrderUpdateComponent,
    resolve: {
      foodOrder: FoodOrderResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FoodOrderUpdateComponent,
    resolve: {
      foodOrder: FoodOrderResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default foodOrderRoute;
