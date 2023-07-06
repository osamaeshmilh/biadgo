import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {RestaurantZonePriceComponent} from './list/restaurant-zone-price.component';
import {RestaurantZonePriceDetailComponent} from './detail/restaurant-zone-price-detail.component';
import {RestaurantZonePriceUpdateComponent} from './update/restaurant-zone-price-update.component';
import RestaurantZonePriceResolve from './route/restaurant-zone-price-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const restaurantZonePriceRoute: Routes = [
  {
    path: '',
    component: RestaurantZonePriceComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RestaurantZonePriceDetailComponent,
    resolve: {
      restaurantZonePrice: RestaurantZonePriceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RestaurantZonePriceUpdateComponent,
    resolve: {
      restaurantZonePrice: RestaurantZonePriceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RestaurantZonePriceUpdateComponent,
    resolve: {
      restaurantZonePrice: RestaurantZonePriceResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default restaurantZonePriceRoute;
