import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {IActivation} from '../activation.model';
import {ActivationService} from '../service/activation.service';

export const activationResolve = (route: ActivatedRouteSnapshot): Observable<null | IActivation> => {
  const id = route.params['id'];
  if (id) {
    return inject(ActivationService)
      .find(id)
      .pipe(
        mergeMap((activation: HttpResponse<IActivation>) => {
          if (activation.body) {
            return of(activation.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default activationResolve;
