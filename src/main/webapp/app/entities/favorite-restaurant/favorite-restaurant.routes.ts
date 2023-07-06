import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {FavoriteRestaurantComponent} from './list/favorite-restaurant.component';
import {FavoriteRestaurantDetailComponent} from './detail/favorite-restaurant-detail.component';
import {FavoriteRestaurantUpdateComponent} from './update/favorite-restaurant-update.component';
import FavoriteRestaurantResolve from './route/favorite-restaurant-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const favoriteRestaurantRoute: Routes = [
  {
    path: '',
    component: FavoriteRestaurantComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FavoriteRestaurantDetailComponent,
    resolve: {
      favoriteRestaurant: FavoriteRestaurantResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FavoriteRestaurantUpdateComponent,
    resolve: {
      favoriteRestaurant: FavoriteRestaurantResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FavoriteRestaurantUpdateComponent,
    resolve: {
      favoriteRestaurant: FavoriteRestaurantResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default favoriteRestaurantRoute;
