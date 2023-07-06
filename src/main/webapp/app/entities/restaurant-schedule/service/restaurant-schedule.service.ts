import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IRestaurantSchedule, NewRestaurantSchedule} from '../restaurant-schedule.model';

export type PartialUpdateRestaurantSchedule = Partial<IRestaurantSchedule> & Pick<IRestaurantSchedule, 'id'>;

export type EntityResponseType = HttpResponse<IRestaurantSchedule>;
export type EntityArrayResponseType = HttpResponse<IRestaurantSchedule[]>;

@Injectable({providedIn: 'root'})
export class RestaurantScheduleService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/restaurant-schedules');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(restaurantSchedule: NewRestaurantSchedule): Observable<EntityResponseType> {
    return this.http.post<IRestaurantSchedule>(this.resourceUrl, restaurantSchedule, {observe: 'response'});
  }

  update(restaurantSchedule: IRestaurantSchedule): Observable<EntityResponseType> {
    return this.http.put<IRestaurantSchedule>(
      `${this.resourceUrl}/${this.getRestaurantScheduleIdentifier(restaurantSchedule)}`,
      restaurantSchedule,
      {observe: 'response'}
    );
  }

  partialUpdate(restaurantSchedule: PartialUpdateRestaurantSchedule): Observable<EntityResponseType> {
    return this.http.patch<IRestaurantSchedule>(
      `${this.resourceUrl}/${this.getRestaurantScheduleIdentifier(restaurantSchedule)}`,
      restaurantSchedule,
      {observe: 'response'}
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRestaurantSchedule>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRestaurantSchedule[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getRestaurantScheduleIdentifier(restaurantSchedule: Pick<IRestaurantSchedule, 'id'>): number {
    return restaurantSchedule.id;
  }

  compareRestaurantSchedule(o1: Pick<IRestaurantSchedule, 'id'> | null, o2: Pick<IRestaurantSchedule, 'id'> | null): boolean {
    return o1 && o2 ? this.getRestaurantScheduleIdentifier(o1) === this.getRestaurantScheduleIdentifier(o2) : o1 === o2;
  }

  addRestaurantScheduleToCollectionIfMissing<Type extends Pick<IRestaurantSchedule, 'id'>>(
    restaurantScheduleCollection: Type[],
    ...restaurantSchedulesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const restaurantSchedules: Type[] = restaurantSchedulesToCheck.filter(isPresent);
    if (restaurantSchedules.length > 0) {
      const restaurantScheduleCollectionIdentifiers = restaurantScheduleCollection.map(
        restaurantScheduleItem => this.getRestaurantScheduleIdentifier(restaurantScheduleItem)!
      );
      const restaurantSchedulesToAdd = restaurantSchedules.filter(restaurantScheduleItem => {
        const restaurantScheduleIdentifier = this.getRestaurantScheduleIdentifier(restaurantScheduleItem);
        if (restaurantScheduleCollectionIdentifiers.includes(restaurantScheduleIdentifier)) {
          return false;
        }
        restaurantScheduleCollectionIdentifiers.push(restaurantScheduleIdentifier);
        return true;
      });
      return [...restaurantSchedulesToAdd, ...restaurantScheduleCollection];
    }
    return restaurantScheduleCollection;
  }
}
