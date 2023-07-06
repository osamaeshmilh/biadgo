import {inject} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Router} from '@angular/router';
import {of, EMPTY, Observable} from 'rxjs';
import {mergeMap} from 'rxjs/operators';

import {ISlider} from '../slider.model';
import {SliderService} from '../service/slider.service';

export const sliderResolve = (route: ActivatedRouteSnapshot): Observable<null | ISlider> => {
  const id = route.params['id'];
  if (id) {
    return inject(SliderService)
      .find(id)
      .pipe(
        mergeMap((slider: HttpResponse<ISlider>) => {
          if (slider.body) {
            return of(slider.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default sliderResolve;
