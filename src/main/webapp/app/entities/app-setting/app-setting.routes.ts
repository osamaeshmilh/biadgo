import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {AppSettingComponent} from './list/app-setting.component';
import {AppSettingDetailComponent} from './detail/app-setting-detail.component';
import {AppSettingUpdateComponent} from './update/app-setting-update.component';
import AppSettingResolve from './route/app-setting-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const appSettingRoute: Routes = [
  {
    path: '',
    component: AppSettingComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AppSettingDetailComponent,
    resolve: {
      appSetting: AppSettingResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AppSettingUpdateComponent,
    resolve: {
      appSetting: AppSettingResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AppSettingUpdateComponent,
    resolve: {
      appSetting: AppSettingResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default appSettingRoute;
