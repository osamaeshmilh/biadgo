import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IFoodOrder, NewFoodOrder} from '../food-order.model';

export type PartialUpdateFoodOrder = Partial<IFoodOrder> & Pick<IFoodOrder, 'id'>;

export type EntityResponseType = HttpResponse<IFoodOrder>;
export type EntityArrayResponseType = HttpResponse<IFoodOrder[]>;

@Injectable({providedIn: 'root'})
export class FoodOrderService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/food-orders');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(foodOrder: NewFoodOrder): Observable<EntityResponseType> {
    return this.http.post<IFoodOrder>(this.resourceUrl, foodOrder, {observe: 'response'});
  }

  update(foodOrder: IFoodOrder): Observable<EntityResponseType> {
    return this.http.put<IFoodOrder>(`${this.resourceUrl}/${this.getFoodOrderIdentifier(foodOrder)}`, foodOrder, {observe: 'response'});
  }

  partialUpdate(foodOrder: PartialUpdateFoodOrder): Observable<EntityResponseType> {
    return this.http.patch<IFoodOrder>(`${this.resourceUrl}/${this.getFoodOrderIdentifier(foodOrder)}`, foodOrder, {observe: 'response'});
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFoodOrder>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFoodOrder[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getFoodOrderIdentifier(foodOrder: Pick<IFoodOrder, 'id'>): number {
    return foodOrder.id;
  }

  compareFoodOrder(o1: Pick<IFoodOrder, 'id'> | null, o2: Pick<IFoodOrder, 'id'> | null): boolean {
    return o1 && o2 ? this.getFoodOrderIdentifier(o1) === this.getFoodOrderIdentifier(o2) : o1 === o2;
  }

  addFoodOrderToCollectionIfMissing<Type extends Pick<IFoodOrder, 'id'>>(
    foodOrderCollection: Type[],
    ...foodOrdersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const foodOrders: Type[] = foodOrdersToCheck.filter(isPresent);
    if (foodOrders.length > 0) {
      const foodOrderCollectionIdentifiers = foodOrderCollection.map(foodOrderItem => this.getFoodOrderIdentifier(foodOrderItem)!);
      const foodOrdersToAdd = foodOrders.filter(foodOrderItem => {
        const foodOrderIdentifier = this.getFoodOrderIdentifier(foodOrderItem);
        if (foodOrderCollectionIdentifiers.includes(foodOrderIdentifier)) {
          return false;
        }
        foodOrderCollectionIdentifiers.push(foodOrderIdentifier);
        return true;
      });
      return [...foodOrdersToAdd, ...foodOrderCollection];
    }
    return foodOrderCollection;
  }
}
