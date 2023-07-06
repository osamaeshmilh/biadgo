import {Routes} from '@angular/router';

import {UserRouteAccessService} from 'app/core/auth/user-route-access.service';
import {NotificationComponent} from './list/notification.component';
import {NotificationDetailComponent} from './detail/notification-detail.component';
import {NotificationUpdateComponent} from './update/notification-update.component';
import NotificationResolve from './route/notification-routing-resolve.service';
import {ASC} from 'app/config/navigation.constants';

const notificationRoute: Routes = [
  {
    path: '',
    component: NotificationComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NotificationDetailComponent,
    resolve: {
      notification: NotificationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NotificationUpdateComponent,
    resolve: {
      notification: NotificationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NotificationUpdateComponent,
    resolve: {
      notification: NotificationResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default notificationRoute;
