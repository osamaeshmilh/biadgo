import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IDriverReview, NewDriverReview} from '../driver-review.model';

export type PartialUpdateDriverReview = Partial<IDriverReview> & Pick<IDriverReview, 'id'>;

export type EntityResponseType = HttpResponse<IDriverReview>;
export type EntityArrayResponseType = HttpResponse<IDriverReview[]>;

@Injectable({providedIn: 'root'})
export class DriverReviewService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/driver-reviews');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(driverReview: NewDriverReview): Observable<EntityResponseType> {
    return this.http.post<IDriverReview>(this.resourceUrl, driverReview, {observe: 'response'});
  }

  update(driverReview: IDriverReview): Observable<EntityResponseType> {
    return this.http.put<IDriverReview>(`${this.resourceUrl}/${this.getDriverReviewIdentifier(driverReview)}`, driverReview, {
      observe: 'response',
    });
  }

  partialUpdate(driverReview: PartialUpdateDriverReview): Observable<EntityResponseType> {
    return this.http.patch<IDriverReview>(`${this.resourceUrl}/${this.getDriverReviewIdentifier(driverReview)}`, driverReview, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDriverReview>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDriverReview[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getDriverReviewIdentifier(driverReview: Pick<IDriverReview, 'id'>): number {
    return driverReview.id;
  }

  compareDriverReview(o1: Pick<IDriverReview, 'id'> | null, o2: Pick<IDriverReview, 'id'> | null): boolean {
    return o1 && o2 ? this.getDriverReviewIdentifier(o1) === this.getDriverReviewIdentifier(o2) : o1 === o2;
  }

  addDriverReviewToCollectionIfMissing<Type extends Pick<IDriverReview, 'id'>>(
    driverReviewCollection: Type[],
    ...driverReviewsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const driverReviews: Type[] = driverReviewsToCheck.filter(isPresent);
    if (driverReviews.length > 0) {
      const driverReviewCollectionIdentifiers = driverReviewCollection.map(
        driverReviewItem => this.getDriverReviewIdentifier(driverReviewItem)!
      );
      const driverReviewsToAdd = driverReviews.filter(driverReviewItem => {
        const driverReviewIdentifier = this.getDriverReviewIdentifier(driverReviewItem);
        if (driverReviewCollectionIdentifiers.includes(driverReviewIdentifier)) {
          return false;
        }
        driverReviewCollectionIdentifiers.push(driverReviewIdentifier);
        return true;
      });
      return [...driverReviewsToAdd, ...driverReviewCollection];
    }
    return driverReviewCollection;
  }
}
