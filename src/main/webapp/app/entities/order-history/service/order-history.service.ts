import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IOrderHistory, NewOrderHistory} from '../order-history.model';

export type PartialUpdateOrderHistory = Partial<IOrderHistory> & Pick<IOrderHistory, 'id'>;

export type EntityResponseType = HttpResponse<IOrderHistory>;
export type EntityArrayResponseType = HttpResponse<IOrderHistory[]>;

@Injectable({providedIn: 'root'})
export class OrderHistoryService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/order-histories');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(orderHistory: NewOrderHistory): Observable<EntityResponseType> {
    return this.http.post<IOrderHistory>(this.resourceUrl, orderHistory, {observe: 'response'});
  }

  update(orderHistory: IOrderHistory): Observable<EntityResponseType> {
    return this.http.put<IOrderHistory>(`${this.resourceUrl}/${this.getOrderHistoryIdentifier(orderHistory)}`, orderHistory, {
      observe: 'response',
    });
  }

  partialUpdate(orderHistory: PartialUpdateOrderHistory): Observable<EntityResponseType> {
    return this.http.patch<IOrderHistory>(`${this.resourceUrl}/${this.getOrderHistoryIdentifier(orderHistory)}`, orderHistory, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrderHistory>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrderHistory[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getOrderHistoryIdentifier(orderHistory: Pick<IOrderHistory, 'id'>): number {
    return orderHistory.id;
  }

  compareOrderHistory(o1: Pick<IOrderHistory, 'id'> | null, o2: Pick<IOrderHistory, 'id'> | null): boolean {
    return o1 && o2 ? this.getOrderHistoryIdentifier(o1) === this.getOrderHistoryIdentifier(o2) : o1 === o2;
  }

  addOrderHistoryToCollectionIfMissing<Type extends Pick<IOrderHistory, 'id'>>(
    orderHistoryCollection: Type[],
    ...orderHistoriesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const orderHistories: Type[] = orderHistoriesToCheck.filter(isPresent);
    if (orderHistories.length > 0) {
      const orderHistoryCollectionIdentifiers = orderHistoryCollection.map(
        orderHistoryItem => this.getOrderHistoryIdentifier(orderHistoryItem)!
      );
      const orderHistoriesToAdd = orderHistories.filter(orderHistoryItem => {
        const orderHistoryIdentifier = this.getOrderHistoryIdentifier(orderHistoryItem);
        if (orderHistoryCollectionIdentifiers.includes(orderHistoryIdentifier)) {
          return false;
        }
        orderHistoryCollectionIdentifiers.push(orderHistoryIdentifier);
        return true;
      });
      return [...orderHistoriesToAdd, ...orderHistoryCollection];
    }
    return orderHistoryCollection;
  }
}
