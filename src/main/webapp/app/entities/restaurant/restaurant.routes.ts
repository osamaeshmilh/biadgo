import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {RestaurantComponent} from './list/restaurant.component';
import {RestaurantDetailComponent} from './detail/restaurant-detail.component';
import {RestaurantUpdateComponent} from './update/restaurant-update.component';
import RestaurantResolve from './route/restaurant-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const restaurantRoute: Routes = [
  {
    path: '',
    component: RestaurantComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RestaurantDetailComponent,
    resolve: {
      restaurant: RestaurantResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RestaurantUpdateComponent,
    resolve: {
      restaurant: RestaurantResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RestaurantUpdateComponent,
    resolve: {
      restaurant: RestaurantResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default restaurantRoute;
