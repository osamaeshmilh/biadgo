import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IRestaurantReview, NewRestaurantReview} from '../restaurant-review.model';

export type PartialUpdateRestaurantReview = Partial<IRestaurantReview> & Pick<IRestaurantReview, 'id'>;

export type EntityResponseType = HttpResponse<IRestaurantReview>;
export type EntityArrayResponseType = HttpResponse<IRestaurantReview[]>;

@Injectable({providedIn: 'root'})
export class RestaurantReviewService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/restaurant-reviews');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(restaurantReview: NewRestaurantReview): Observable<EntityResponseType> {
    return this.http.post<IRestaurantReview>(this.resourceUrl, restaurantReview, {observe: 'response'});
  }

  update(restaurantReview: IRestaurantReview): Observable<EntityResponseType> {
    return this.http.put<IRestaurantReview>(
      `${this.resourceUrl}/${this.getRestaurantReviewIdentifier(restaurantReview)}`,
      restaurantReview,
      {observe: 'response'}
    );
  }

  partialUpdate(restaurantReview: PartialUpdateRestaurantReview): Observable<EntityResponseType> {
    return this.http.patch<IRestaurantReview>(
      `${this.resourceUrl}/${this.getRestaurantReviewIdentifier(restaurantReview)}`,
      restaurantReview,
      {observe: 'response'}
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRestaurantReview>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRestaurantReview[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getRestaurantReviewIdentifier(restaurantReview: Pick<IRestaurantReview, 'id'>): number {
    return restaurantReview.id;
  }

  compareRestaurantReview(o1: Pick<IRestaurantReview, 'id'> | null, o2: Pick<IRestaurantReview, 'id'> | null): boolean {
    return o1 && o2 ? this.getRestaurantReviewIdentifier(o1) === this.getRestaurantReviewIdentifier(o2) : o1 === o2;
  }

  addRestaurantReviewToCollectionIfMissing<Type extends Pick<IRestaurantReview, 'id'>>(
    restaurantReviewCollection: Type[],
    ...restaurantReviewsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const restaurantReviews: Type[] = restaurantReviewsToCheck.filter(isPresent);
    if (restaurantReviews.length > 0) {
      const restaurantReviewCollectionIdentifiers = restaurantReviewCollection.map(
        restaurantReviewItem => this.getRestaurantReviewIdentifier(restaurantReviewItem)!
      );
      const restaurantReviewsToAdd = restaurantReviews.filter(restaurantReviewItem => {
        const restaurantReviewIdentifier = this.getRestaurantReviewIdentifier(restaurantReviewItem);
        if (restaurantReviewCollectionIdentifiers.includes(restaurantReviewIdentifier)) {
          return false;
        }
        restaurantReviewCollectionIdentifiers.push(restaurantReviewIdentifier);
        return true;
      });
      return [...restaurantReviewsToAdd, ...restaurantReviewCollection];
    }
    return restaurantReviewCollection;
  }
}
