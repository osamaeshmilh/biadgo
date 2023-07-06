import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {RestaurantWalletComponent} from './list/restaurant-wallet.component';
import {RestaurantWalletDetailComponent} from './detail/restaurant-wallet-detail.component';
import {RestaurantWalletUpdateComponent} from './update/restaurant-wallet-update.component';
import RestaurantWalletResolve from './route/restaurant-wallet-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const restaurantWalletRoute: Routes = [
  {
    path: '',
    component: RestaurantWalletComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RestaurantWalletDetailComponent,
    resolve: {
      restaurantWallet: RestaurantWalletResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RestaurantWalletUpdateComponent,
    resolve: {
      restaurantWallet: RestaurantWalletResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RestaurantWalletUpdateComponent,
    resolve: {
      restaurantWallet: RestaurantWalletResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default restaurantWalletRoute;
