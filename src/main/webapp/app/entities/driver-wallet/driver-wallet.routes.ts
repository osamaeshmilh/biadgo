import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {DriverWalletComponent} from './list/driver-wallet.component';
import {DriverWalletDetailComponent} from './detail/driver-wallet-detail.component';
import {DriverWalletUpdateComponent} from './update/driver-wallet-update.component';
import DriverWalletResolve from './route/driver-wallet-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const driverWalletRoute: Routes = [
  {
    path: '',
    component: DriverWalletComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DriverWalletDetailComponent,
    resolve: {
      driverWallet: DriverWalletResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DriverWalletUpdateComponent,
    resolve: {
      driverWallet: DriverWalletResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DriverWalletUpdateComponent,
    resolve: {
      driverWallet: DriverWalletResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default driverWalletRoute;
