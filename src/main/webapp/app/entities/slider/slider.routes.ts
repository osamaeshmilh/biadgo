import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {SliderComponent} from './list/slider.component';
import {SliderDetailComponent} from './detail/slider-detail.component';
import {SliderUpdateComponent} from './update/slider-update.component';
import SliderResolve from './route/slider-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const sliderRoute: Routes = [
  {
    path: '',
    component: SliderComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SliderDetailComponent,
    resolve: {
      slider: SliderResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SliderUpdateComponent,
    resolve: {
      slider: SliderResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SliderUpdateComponent,
    resolve: {
      slider: SliderResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default sliderRoute;
