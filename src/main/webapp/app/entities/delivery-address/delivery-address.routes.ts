import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {DeliveryAddressComponent} from './list/delivery-address.component';
import {DeliveryAddressDetailComponent} from './detail/delivery-address-detail.component';
import {DeliveryAddressUpdateComponent} from './update/delivery-address-update.component';
import DeliveryAddressResolve from './route/delivery-address-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const deliveryAddressRoute: Routes = [
  {
    path: '',
    component: DeliveryAddressComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DeliveryAddressDetailComponent,
    resolve: {
      deliveryAddress: DeliveryAddressResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DeliveryAddressUpdateComponent,
    resolve: {
      deliveryAddress: DeliveryAddressResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DeliveryAddressUpdateComponent,
    resolve: {
      deliveryAddress: DeliveryAddressResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default deliveryAddressRoute;
