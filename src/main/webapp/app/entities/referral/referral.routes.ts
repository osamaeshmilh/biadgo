import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {ReferralComponent} from './list/referral.component';
import {ReferralDetailComponent} from './detail/referral-detail.component';
import {ReferralUpdateComponent} from './update/referral-update.component';
import ReferralResolve from './route/referral-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const referralRoute: Routes = [
  {
    path: '',
    component: ReferralComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReferralDetailComponent,
    resolve: {
      referral: ReferralResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReferralUpdateComponent,
    resolve: {
      referral: ReferralResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReferralUpdateComponent,
    resolve: {
      referral: ReferralResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default referralRoute;
