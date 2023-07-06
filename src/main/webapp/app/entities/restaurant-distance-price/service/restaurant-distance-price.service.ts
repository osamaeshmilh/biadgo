import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IRestaurantDistancePrice, NewRestaurantDistancePrice} from '../restaurant-distance-price.model';

export type PartialUpdateRestaurantDistancePrice =
  Partial<IRestaurantDistancePrice>
  & Pick<IRestaurantDistancePrice, 'id'>;

export type EntityResponseType = HttpResponse<IRestaurantDistancePrice>;
export type EntityArrayResponseType = HttpResponse<IRestaurantDistancePrice[]>;

@Injectable({providedIn: 'root'})
export class RestaurantDistancePriceService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/restaurant-distance-prices');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(restaurantDistancePrice: NewRestaurantDistancePrice): Observable<EntityResponseType> {
    return this.http.post<IRestaurantDistancePrice>(this.resourceUrl, restaurantDistancePrice, {observe: 'response'});
  }

  update(restaurantDistancePrice: IRestaurantDistancePrice): Observable<EntityResponseType> {
    return this.http.put<IRestaurantDistancePrice>(
      `${this.resourceUrl}/${this.getRestaurantDistancePriceIdentifier(restaurantDistancePrice)}`,
      restaurantDistancePrice,
      {observe: 'response'}
    );
  }

  partialUpdate(restaurantDistancePrice: PartialUpdateRestaurantDistancePrice): Observable<EntityResponseType> {
    return this.http.patch<IRestaurantDistancePrice>(
      `${this.resourceUrl}/${this.getRestaurantDistancePriceIdentifier(restaurantDistancePrice)}`,
      restaurantDistancePrice,
      {observe: 'response'}
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRestaurantDistancePrice>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRestaurantDistancePrice[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getRestaurantDistancePriceIdentifier(restaurantDistancePrice: Pick<IRestaurantDistancePrice, 'id'>): number {
    return restaurantDistancePrice.id;
  }

  compareRestaurantDistancePrice(
    o1: Pick<IRestaurantDistancePrice, 'id'> | null,
    o2: Pick<IRestaurantDistancePrice, 'id'> | null
  ): boolean {
    return o1 && o2 ? this.getRestaurantDistancePriceIdentifier(o1) === this.getRestaurantDistancePriceIdentifier(o2) : o1 === o2;
  }

  addRestaurantDistancePriceToCollectionIfMissing<Type extends Pick<IRestaurantDistancePrice, 'id'>>(
    restaurantDistancePriceCollection: Type[],
    ...restaurantDistancePricesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const restaurantDistancePrices: Type[] = restaurantDistancePricesToCheck.filter(isPresent);
    if (restaurantDistancePrices.length > 0) {
      const restaurantDistancePriceCollectionIdentifiers = restaurantDistancePriceCollection.map(
        restaurantDistancePriceItem => this.getRestaurantDistancePriceIdentifier(restaurantDistancePriceItem)!
      );
      const restaurantDistancePricesToAdd = restaurantDistancePrices.filter(restaurantDistancePriceItem => {
        const restaurantDistancePriceIdentifier = this.getRestaurantDistancePriceIdentifier(restaurantDistancePriceItem);
        if (restaurantDistancePriceCollectionIdentifiers.includes(restaurantDistancePriceIdentifier)) {
          return false;
        }
        restaurantDistancePriceCollectionIdentifiers.push(restaurantDistancePriceIdentifier);
        return true;
      });
      return [...restaurantDistancePricesToAdd, ...restaurantDistancePriceCollection];
    }
    return restaurantDistancePriceCollection;
  }
}
