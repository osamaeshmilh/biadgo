import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {DashboardComponent} from "./dashboard.component";

const dashboardRoutes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'الرئيسية',
    },
    canActivate: [UserRouteAccessService],
  },
];

export default dashboardRoutes;
