import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {CouponComponent} from './list/coupon.component';
import {CouponDetailComponent} from './detail/coupon-detail.component';
import {CouponUpdateComponent} from './update/coupon-update.component';
import CouponResolve from './route/coupon-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const couponRoute: Routes = [
  {
    path: '',
    component: CouponComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CouponDetailComponent,
    resolve: {
      coupon: CouponResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CouponUpdateComponent,
    resolve: {
      coupon: CouponResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CouponUpdateComponent,
    resolve: {
      coupon: CouponResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default couponRoute;
