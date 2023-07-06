import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IAppSetting} from '../app-setting.model';
import {AppSettingService} from '../service/app-setting.service';

export const appSettingResolve = (route: ActivatedRouteSnapshot): Observable<null | IAppSetting> => {
  const id = route.params['id'];
  if (id) {
    return inject(AppSettingService)
      .find(id)
      .pipe(
        mergeMap((appSetting: HttpResponse<IAppSetting>) => {
          if (appSetting.body) {
            return of(appSetting.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default appSettingResolve;
