import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IFoodExtra, NewFoodExtra} from '../food-extra.model';

export type PartialUpdateFoodExtra = Partial<IFoodExtra> & Pick<IFoodExtra, 'id'>;

export type EntityResponseType = HttpResponse<IFoodExtra>;
export type EntityArrayResponseType = HttpResponse<IFoodExtra[]>;

@Injectable({providedIn: 'root'})
export class FoodExtraService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/food-extras');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(foodExtra: NewFoodExtra): Observable<EntityResponseType> {
    return this.http.post<IFoodExtra>(this.resourceUrl, foodExtra, {observe: 'response'});
  }

  update(foodExtra: IFoodExtra): Observable<EntityResponseType> {
    return this.http.put<IFoodExtra>(`${this.resourceUrl}/${this.getFoodExtraIdentifier(foodExtra)}`, foodExtra, {observe: 'response'});
  }

  partialUpdate(foodExtra: PartialUpdateFoodExtra): Observable<EntityResponseType> {
    return this.http.patch<IFoodExtra>(`${this.resourceUrl}/${this.getFoodExtraIdentifier(foodExtra)}`, foodExtra, {observe: 'response'});
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFoodExtra>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFoodExtra[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getFoodExtraIdentifier(foodExtra: Pick<IFoodExtra, 'id'>): number {
    return foodExtra.id;
  }

  compareFoodExtra(o1: Pick<IFoodExtra, 'id'> | null, o2: Pick<IFoodExtra, 'id'> | null): boolean {
    return o1 && o2 ? this.getFoodExtraIdentifier(o1) === this.getFoodExtraIdentifier(o2) : o1 === o2;
  }

  addFoodExtraToCollectionIfMissing<Type extends Pick<IFoodExtra, 'id'>>(
    foodExtraCollection: Type[],
    ...foodExtrasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const foodExtras: Type[] = foodExtrasToCheck.filter(isPresent);
    if (foodExtras.length > 0) {
      const foodExtraCollectionIdentifiers = foodExtraCollection.map(foodExtraItem => this.getFoodExtraIdentifier(foodExtraItem)!);
      const foodExtrasToAdd = foodExtras.filter(foodExtraItem => {
        const foodExtraIdentifier = this.getFoodExtraIdentifier(foodExtraItem);
        if (foodExtraCollectionIdentifiers.includes(foodExtraIdentifier)) {
          return false;
        }
        foodExtraCollectionIdentifiers.push(foodExtraIdentifier);
        return true;
      });
      return [...foodExtrasToAdd, ...foodExtraCollection];
    }
    return foodExtraCollection;
  }
}
