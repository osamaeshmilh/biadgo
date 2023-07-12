import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {RestaurantImageComponent} from './list/restaurant-image.component';
import {RestaurantImageDetailComponent} from './detail/restaurant-image-detail.component';
import {RestaurantImageUpdateComponent} from './update/restaurant-image-update.component';
import RestaurantImageResolve from './route/restaurant-image-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const restaurantImageRoute: Routes = [
  {
    path: '',
    component: RestaurantImageComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RestaurantImageDetailComponent,
    resolve: {
      restaurantImage: RestaurantImageResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RestaurantImageUpdateComponent,
    resolve: {
      restaurantImage: RestaurantImageResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RestaurantImageUpdateComponent,
    resolve: {
      restaurantImage: RestaurantImageResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default restaurantImageRoute;
