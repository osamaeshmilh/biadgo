import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {ISlider, NewSlider} from '../slider.model';

export type PartialUpdateSlider = Partial<ISlider> & Pick<ISlider, 'id'>;

export type EntityResponseType = HttpResponse<ISlider>;
export type EntityArrayResponseType = HttpResponse<ISlider[]>;

@Injectable({providedIn: 'root'})
export class SliderService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/sliders');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(slider: NewSlider): Observable<EntityResponseType> {
    return this.http.post<ISlider>(this.resourceUrl, slider, {observe: 'response'});
  }

  update(slider: ISlider): Observable<EntityResponseType> {
    return this.http.put<ISlider>(`${this.resourceUrl}/${this.getSliderIdentifier(slider)}`, slider, {observe: 'response'});
  }

  partialUpdate(slider: PartialUpdateSlider): Observable<EntityResponseType> {
    return this.http.patch<ISlider>(`${this.resourceUrl}/${this.getSliderIdentifier(slider)}`, slider, {observe: 'response'});
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISlider>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISlider[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getSliderIdentifier(slider: Pick<ISlider, 'id'>): number {
    return slider.id;
  }

  compareSlider(o1: Pick<ISlider, 'id'> | null, o2: Pick<ISlider, 'id'> | null): boolean {
    return o1 && o2 ? this.getSliderIdentifier(o1) === this.getSliderIdentifier(o2) : o1 === o2;
  }

  addSliderToCollectionIfMissing<Type extends Pick<ISlider, 'id'>>(
    sliderCollection: Type[],
    ...slidersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const sliders: Type[] = slidersToCheck.filter(isPresent);
    if (sliders.length > 0) {
      const sliderCollectionIdentifiers = sliderCollection.map(sliderItem => this.getSliderIdentifier(sliderItem)!);
      const slidersToAdd = sliders.filter(sliderItem => {
        const sliderIdentifier = this.getSliderIdentifier(sliderItem);
        if (sliderCollectionIdentifiers.includes(sliderIdentifier)) {
          return false;
        }
        sliderCollectionIdentifiers.push(sliderIdentifier);
        return true;
      });
      return [...slidersToAdd, ...sliderCollection];
    }
    return sliderCollection;
  }
}
