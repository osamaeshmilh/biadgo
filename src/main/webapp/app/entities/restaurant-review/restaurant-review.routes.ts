import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {RestaurantReviewComponent} from './list/restaurant-review.component';
import {RestaurantReviewDetailComponent} from './detail/restaurant-review-detail.component';
import {RestaurantReviewUpdateComponent} from './update/restaurant-review-update.component';
import RestaurantReviewResolve from './route/restaurant-review-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const restaurantReviewRoute: Routes = [
  {
    path: '',
    component: RestaurantReviewComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RestaurantReviewDetailComponent,
    resolve: {
      restaurantReview: RestaurantReviewResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RestaurantReviewUpdateComponent,
    resolve: {
      restaurantReview: RestaurantReviewResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RestaurantReviewUpdateComponent,
    resolve: {
      restaurantReview: RestaurantReviewResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default restaurantReviewRoute;
