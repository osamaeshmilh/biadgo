import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {DriverReviewComponent} from './list/driver-review.component';
import {DriverReviewDetailComponent} from './detail/driver-review-detail.component';
import {DriverReviewUpdateComponent} from './update/driver-review-update.component';
import DriverReviewResolve from './route/driver-review-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const driverReviewRoute: Routes = [
  {
    path: '',
    component: DriverReviewComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DriverReviewDetailComponent,
    resolve: {
      driverReview: DriverReviewResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DriverReviewUpdateComponent,
    resolve: {
      driverReview: DriverReviewResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DriverReviewUpdateComponent,
    resolve: {
      driverReview: DriverReviewResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default driverReviewRoute;
