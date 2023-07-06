import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IFavoriteRestaurant, NewFavoriteRestaurant} from '../favorite-restaurant.model';

export type PartialUpdateFavoriteRestaurant = Partial<IFavoriteRestaurant> & Pick<IFavoriteRestaurant, 'id'>;

export type EntityResponseType = HttpResponse<IFavoriteRestaurant>;
export type EntityArrayResponseType = HttpResponse<IFavoriteRestaurant[]>;

@Injectable({providedIn: 'root'})
export class FavoriteRestaurantService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/favorite-restaurants');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(favoriteRestaurant: NewFavoriteRestaurant): Observable<EntityResponseType> {
    return this.http.post<IFavoriteRestaurant>(this.resourceUrl, favoriteRestaurant, {observe: 'response'});
  }

  update(favoriteRestaurant: IFavoriteRestaurant): Observable<EntityResponseType> {
    return this.http.put<IFavoriteRestaurant>(
      `${this.resourceUrl}/${this.getFavoriteRestaurantIdentifier(favoriteRestaurant)}`,
      favoriteRestaurant,
      {observe: 'response'}
    );
  }

  partialUpdate(favoriteRestaurant: PartialUpdateFavoriteRestaurant): Observable<EntityResponseType> {
    return this.http.patch<IFavoriteRestaurant>(
      `${this.resourceUrl}/${this.getFavoriteRestaurantIdentifier(favoriteRestaurant)}`,
      favoriteRestaurant,
      {observe: 'response'}
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFavoriteRestaurant>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFavoriteRestaurant[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getFavoriteRestaurantIdentifier(favoriteRestaurant: Pick<IFavoriteRestaurant, 'id'>): number {
    return favoriteRestaurant.id;
  }

  compareFavoriteRestaurant(o1: Pick<IFavoriteRestaurant, 'id'> | null, o2: Pick<IFavoriteRestaurant, 'id'> | null): boolean {
    return o1 && o2 ? this.getFavoriteRestaurantIdentifier(o1) === this.getFavoriteRestaurantIdentifier(o2) : o1 === o2;
  }

  addFavoriteRestaurantToCollectionIfMissing<Type extends Pick<IFavoriteRestaurant, 'id'>>(
    favoriteRestaurantCollection: Type[],
    ...favoriteRestaurantsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const favoriteRestaurants: Type[] = favoriteRestaurantsToCheck.filter(isPresent);
    if (favoriteRestaurants.length > 0) {
      const favoriteRestaurantCollectionIdentifiers = favoriteRestaurantCollection.map(
        favoriteRestaurantItem => this.getFavoriteRestaurantIdentifier(favoriteRestaurantItem)!
      );
      const favoriteRestaurantsToAdd = favoriteRestaurants.filter(favoriteRestaurantItem => {
        const favoriteRestaurantIdentifier = this.getFavoriteRestaurantIdentifier(favoriteRestaurantItem);
        if (favoriteRestaurantCollectionIdentifiers.includes(favoriteRestaurantIdentifier)) {
          return false;
        }
        favoriteRestaurantCollectionIdentifiers.push(favoriteRestaurantIdentifier);
        return true;
      });
      return [...favoriteRestaurantsToAdd, ...favoriteRestaurantCollection];
    }
    return favoriteRestaurantCollection;
  }
}
