import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {ActivationComponent} from './list/activation.component';
import {ActivationDetailComponent} from './detail/activation-detail.component';
import {ActivationUpdateComponent} from './update/activation-update.component';
import ActivationResolve from './route/activation-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const activationRoute: Routes = [
  {
    path: '',
    component: ActivationComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ActivationDetailComponent,
    resolve: {
      activation: ActivationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ActivationUpdateComponent,
    resolve: {
      activation: ActivationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ActivationUpdateComponent,
    resolve: {
      activation: ActivationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default activationRoute;
