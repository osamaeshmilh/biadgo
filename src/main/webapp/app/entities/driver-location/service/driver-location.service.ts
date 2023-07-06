import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {map} from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IDriverLocation, NewDriverLocation} from '../driver-location.model';

export type PartialUpdateDriverLocation = Partial<IDriverLocation> & Pick<IDriverLocation, 'id'>;

type RestOf<T extends IDriverLocation | NewDriverLocation> = Omit<T, 'locationDateTime'> & {
  locationDateTime?: string | null;
};

export type RestDriverLocation = RestOf<IDriverLocation>;

export type NewRestDriverLocation = RestOf<NewDriverLocation>;

export type PartialUpdateRestDriverLocation = RestOf<PartialUpdateDriverLocation>;

export type EntityResponseType = HttpResponse<IDriverLocation>;
export type EntityArrayResponseType = HttpResponse<IDriverLocation[]>;

@Injectable({providedIn: 'root'})
export class DriverLocationService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/driver-locations');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(driverLocation: NewDriverLocation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(driverLocation);
    return this.http
      .post<RestDriverLocation>(this.resourceUrl, copy, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(driverLocation: IDriverLocation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(driverLocation);
    return this.http
      .put<RestDriverLocation>(`${this.resourceUrl}/${this.getDriverLocationIdentifier(driverLocation)}`, copy, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(driverLocation: PartialUpdateDriverLocation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(driverLocation);
    return this.http
      .patch<RestDriverLocation>(`${this.resourceUrl}/${this.getDriverLocationIdentifier(driverLocation)}`, copy, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestDriverLocation>(`${this.resourceUrl}/${id}`, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestDriverLocation[]>(this.resourceUrl, {params: options, observe: 'response'})
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getDriverLocationIdentifier(driverLocation: Pick<IDriverLocation, 'id'>): number {
    return driverLocation.id;
  }

  compareDriverLocation(o1: Pick<IDriverLocation, 'id'> | null, o2: Pick<IDriverLocation, 'id'> | null): boolean {
    return o1 && o2 ? this.getDriverLocationIdentifier(o1) === this.getDriverLocationIdentifier(o2) : o1 === o2;
  }

  addDriverLocationToCollectionIfMissing<Type extends Pick<IDriverLocation, 'id'>>(
    driverLocationCollection: Type[],
    ...driverLocationsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const driverLocations: Type[] = driverLocationsToCheck.filter(isPresent);
    if (driverLocations.length > 0) {
      const driverLocationCollectionIdentifiers = driverLocationCollection.map(
        driverLocationItem => this.getDriverLocationIdentifier(driverLocationItem)!
      );
      const driverLocationsToAdd = driverLocations.filter(driverLocationItem => {
        const driverLocationIdentifier = this.getDriverLocationIdentifier(driverLocationItem);
        if (driverLocationCollectionIdentifiers.includes(driverLocationIdentifier)) {
          return false;
        }
        driverLocationCollectionIdentifiers.push(driverLocationIdentifier);
        return true;
      });
      return [...driverLocationsToAdd, ...driverLocationCollection];
    }
    return driverLocationCollection;
  }

  protected convertDateFromClient<T extends IDriverLocation | NewDriverLocation | PartialUpdateDriverLocation>(
    driverLocation: T
  ): RestOf<T> {
    return {
      ...driverLocation,
      locationDateTime: driverLocation.locationDateTime?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restDriverLocation: RestDriverLocation): IDriverLocation {
    return {
      ...restDriverLocation,
      locationDateTime: restDriverLocation.locationDateTime ? dayjs(restDriverLocation.locationDateTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestDriverLocation>): HttpResponse<IDriverLocation> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestDriverLocation[]>): HttpResponse<IDriverLocation[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
