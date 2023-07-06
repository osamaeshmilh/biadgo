import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IRestaurantWallet, NewRestaurantWallet} from '../restaurant-wallet.model';

export type PartialUpdateRestaurantWallet = Partial<IRestaurantWallet> & Pick<IRestaurantWallet, 'id'>;

export type EntityResponseType = HttpResponse<IRestaurantWallet>;
export type EntityArrayResponseType = HttpResponse<IRestaurantWallet[]>;

@Injectable({providedIn: 'root'})
export class RestaurantWalletService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/restaurant-wallets');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(restaurantWallet: NewRestaurantWallet): Observable<EntityResponseType> {
    return this.http.post<IRestaurantWallet>(this.resourceUrl, restaurantWallet, {observe: 'response'});
  }

  update(restaurantWallet: IRestaurantWallet): Observable<EntityResponseType> {
    return this.http.put<IRestaurantWallet>(
      `${this.resourceUrl}/${this.getRestaurantWalletIdentifier(restaurantWallet)}`,
      restaurantWallet,
      {observe: 'response'}
    );
  }

  partialUpdate(restaurantWallet: PartialUpdateRestaurantWallet): Observable<EntityResponseType> {
    return this.http.patch<IRestaurantWallet>(
      `${this.resourceUrl}/${this.getRestaurantWalletIdentifier(restaurantWallet)}`,
      restaurantWallet,
      {observe: 'response'}
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRestaurantWallet>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRestaurantWallet[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getRestaurantWalletIdentifier(restaurantWallet: Pick<IRestaurantWallet, 'id'>): number {
    return restaurantWallet.id;
  }

  compareRestaurantWallet(o1: Pick<IRestaurantWallet, 'id'> | null, o2: Pick<IRestaurantWallet, 'id'> | null): boolean {
    return o1 && o2 ? this.getRestaurantWalletIdentifier(o1) === this.getRestaurantWalletIdentifier(o2) : o1 === o2;
  }

  addRestaurantWalletToCollectionIfMissing<Type extends Pick<IRestaurantWallet, 'id'>>(
    restaurantWalletCollection: Type[],
    ...restaurantWalletsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const restaurantWallets: Type[] = restaurantWalletsToCheck.filter(isPresent);
    if (restaurantWallets.length > 0) {
      const restaurantWalletCollectionIdentifiers = restaurantWalletCollection.map(
        restaurantWalletItem => this.getRestaurantWalletIdentifier(restaurantWalletItem)!
      );
      const restaurantWalletsToAdd = restaurantWallets.filter(restaurantWalletItem => {
        const restaurantWalletIdentifier = this.getRestaurantWalletIdentifier(restaurantWalletItem);
        if (restaurantWalletCollectionIdentifiers.includes(restaurantWalletIdentifier)) {
          return false;
        }
        restaurantWalletCollectionIdentifiers.push(restaurantWalletIdentifier);
        return true;
      });
      return [...restaurantWalletsToAdd, ...restaurantWalletCollection];
    }
    return restaurantWalletCollection;
  }
}
