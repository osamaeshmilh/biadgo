import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {FoodImageComponent} from './list/food-image.component';
import {FoodImageDetailComponent} from './detail/food-image-detail.component';
import {FoodImageUpdateComponent} from './update/food-image-update.component';
import FoodImageResolve from './route/food-image-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const foodImageRoute: Routes = [
  {
    path: '',
    component: FoodImageComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FoodImageDetailComponent,
    resolve: {
      foodImage: FoodImageResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FoodImageUpdateComponent,
    resolve: {
      foodImage: FoodImageResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FoodImageUpdateComponent,
    resolve: {
      foodImage: FoodImageResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default foodImageRoute;
