import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {DriverLocationComponent} from './list/driver-location.component';
import {DriverLocationDetailComponent} from './detail/driver-location-detail.component';
import {DriverLocationUpdateComponent} from './update/driver-location-update.component';
import DriverLocationResolve from './route/driver-location-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const driverLocationRoute: Routes = [
  {
    path: '',
    component: DriverLocationComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DriverLocationDetailComponent,
    resolve: {
      driverLocation: DriverLocationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DriverLocationUpdateComponent,
    resolve: {
      driverLocation: DriverLocationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DriverLocationUpdateComponent,
    resolve: {
      driverLocation: DriverLocationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default driverLocationRoute;
