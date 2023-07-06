import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {OrderHistoryComponent} from './list/order-history.component';
import {OrderHistoryDetailComponent} from './detail/order-history-detail.component';
import {OrderHistoryUpdateComponent} from './update/order-history-update.component';
import OrderHistoryResolve from './route/order-history-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const orderHistoryRoute: Routes = [
  {
    path: '',
    component: OrderHistoryComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OrderHistoryDetailComponent,
    resolve: {
      orderHistory: OrderHistoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OrderHistoryUpdateComponent,
    resolve: {
      orderHistory: OrderHistoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OrderHistoryUpdateComponent,
    resolve: {
      orderHistory: OrderHistoryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default orderHistoryRoute;
