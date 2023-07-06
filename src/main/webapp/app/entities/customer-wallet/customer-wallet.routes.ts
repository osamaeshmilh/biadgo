import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {CustomerWalletComponent} from './list/customer-wallet.component';
import {CustomerWalletDetailComponent} from './detail/customer-wallet-detail.component';
import {CustomerWalletUpdateComponent} from './update/customer-wallet-update.component';
import CustomerWalletResolve from './route/customer-wallet-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const customerWalletRoute: Routes = [
  {
    path: '',
    component: CustomerWalletComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CustomerWalletDetailComponent,
    resolve: {
      customerWallet: CustomerWalletResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CustomerWalletUpdateComponent,
    resolve: {
      customerWallet: CustomerWalletResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CustomerWalletUpdateComponent,
    resolve: {
      customerWallet: CustomerWalletResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default customerWalletRoute;
