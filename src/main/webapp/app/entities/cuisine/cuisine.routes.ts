import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {CuisineComponent} from './list/cuisine.component';
import {CuisineDetailComponent} from './detail/cuisine-detail.component';
import {CuisineUpdateComponent} from './update/cuisine-update.component';
import CuisineResolve from './route/cuisine-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const cuisineRoute: Routes = [
  {
    path: '',
    component: CuisineComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CuisineDetailComponent,
    resolve: {
      cuisine: CuisineResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CuisineUpdateComponent,
    resolve: {
      cuisine: CuisineResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CuisineUpdateComponent,
    resolve: {
      cuisine: CuisineResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default cuisineRoute;
