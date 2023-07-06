import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {RestaurantDistancePriceComponent} from './list/restaurant-distance-price.component';
import {RestaurantDistancePriceDetailComponent} from './detail/restaurant-distance-price-detail.component';
import {RestaurantDistancePriceUpdateComponent} from './update/restaurant-distance-price-update.component';
import RestaurantDistancePriceResolve from './route/restaurant-distance-price-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const restaurantDistancePriceRoute: Routes = [
  {
    path: '',
    component: RestaurantDistancePriceComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RestaurantDistancePriceDetailComponent,
    resolve: {
      restaurantDistancePrice: RestaurantDistancePriceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RestaurantDistancePriceUpdateComponent,
    resolve: {
      restaurantDistancePrice: RestaurantDistancePriceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RestaurantDistancePriceUpdateComponent,
    resolve: {
      restaurantDistancePrice: RestaurantDistancePriceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default restaurantDistancePriceRoute;
