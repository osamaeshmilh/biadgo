import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IRestaurantZonePrice, NewRestaurantZonePrice} from '../restaurant-zone-price.model';

export type PartialUpdateRestaurantZonePrice = Partial<IRestaurantZonePrice> & Pick<IRestaurantZonePrice, 'id'>;

export type EntityResponseType = HttpResponse<IRestaurantZonePrice>;
export type EntityArrayResponseType = HttpResponse<IRestaurantZonePrice[]>;

@Injectable({providedIn: 'root'})
export class RestaurantZonePriceService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/restaurant-zone-prices');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(restaurantZonePrice: NewRestaurantZonePrice): Observable<EntityResponseType> {
    return this.http.post<IRestaurantZonePrice>(this.resourceUrl, restaurantZonePrice, {observe: 'response'});
  }

  update(restaurantZonePrice: IRestaurantZonePrice): Observable<EntityResponseType> {
    return this.http.put<IRestaurantZonePrice>(
      `${this.resourceUrl}/${this.getRestaurantZonePriceIdentifier(restaurantZonePrice)}`,
      restaurantZonePrice,
      {observe: 'response'}
    );
  }

  partialUpdate(restaurantZonePrice: PartialUpdateRestaurantZonePrice): Observable<EntityResponseType> {
    return this.http.patch<IRestaurantZonePrice>(
      `${this.resourceUrl}/${this.getRestaurantZonePriceIdentifier(restaurantZonePrice)}`,
      restaurantZonePrice,
      {observe: 'response'}
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRestaurantZonePrice>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRestaurantZonePrice[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getRestaurantZonePriceIdentifier(restaurantZonePrice: Pick<IRestaurantZonePrice, 'id'>): number {
    return restaurantZonePrice.id;
  }

  compareRestaurantZonePrice(o1: Pick<IRestaurantZonePrice, 'id'> | null, o2: Pick<IRestaurantZonePrice, 'id'> | null): boolean {
    return o1 && o2 ? this.getRestaurantZonePriceIdentifier(o1) === this.getRestaurantZonePriceIdentifier(o2) : o1 === o2;
  }

  addRestaurantZonePriceToCollectionIfMissing<Type extends Pick<IRestaurantZonePrice, 'id'>>(
    restaurantZonePriceCollection: Type[],
    ...restaurantZonePricesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const restaurantZonePrices: Type[] = restaurantZonePricesToCheck.filter(isPresent);
    if (restaurantZonePrices.length > 0) {
      const restaurantZonePriceCollectionIdentifiers = restaurantZonePriceCollection.map(
        restaurantZonePriceItem => this.getRestaurantZonePriceIdentifier(restaurantZonePriceItem)!
      );
      const restaurantZonePricesToAdd = restaurantZonePrices.filter(restaurantZonePriceItem => {
        const restaurantZonePriceIdentifier = this.getRestaurantZonePriceIdentifier(restaurantZonePriceItem);
        if (restaurantZonePriceCollectionIdentifiers.includes(restaurantZonePriceIdentifier)) {
          return false;
        }
        restaurantZonePriceCollectionIdentifiers.push(restaurantZonePriceIdentifier);
        return true;
      });
      return [...restaurantZonePricesToAdd, ...restaurantZonePriceCollection];
    }
    return restaurantZonePriceCollection;
  }
}
