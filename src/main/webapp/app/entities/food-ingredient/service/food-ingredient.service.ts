import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IFoodIngredient, NewFoodIngredient} from '../food-ingredient.model';

export type PartialUpdateFoodIngredient = Partial<IFoodIngredient> & Pick<IFoodIngredient, 'id'>;

export type EntityResponseType = HttpResponse<IFoodIngredient>;
export type EntityArrayResponseType = HttpResponse<IFoodIngredient[]>;

@Injectable({providedIn: 'root'})
export class FoodIngredientService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/food-ingredients');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(foodIngredient: NewFoodIngredient): Observable<EntityResponseType> {
    return this.http.post<IFoodIngredient>(this.resourceUrl, foodIngredient, {observe: 'response'});
  }

  update(foodIngredient: IFoodIngredient): Observable<EntityResponseType> {
    return this.http.put<IFoodIngredient>(`${this.resourceUrl}/${this.getFoodIngredientIdentifier(foodIngredient)}`, foodIngredient, {
      observe: 'response',
    });
  }

  partialUpdate(foodIngredient: PartialUpdateFoodIngredient): Observable<EntityResponseType> {
    return this.http.patch<IFoodIngredient>(`${this.resourceUrl}/${this.getFoodIngredientIdentifier(foodIngredient)}`, foodIngredient, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFoodIngredient>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFoodIngredient[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getFoodIngredientIdentifier(foodIngredient: Pick<IFoodIngredient, 'id'>): number {
    return foodIngredient.id;
  }

  compareFoodIngredient(o1: Pick<IFoodIngredient, 'id'> | null, o2: Pick<IFoodIngredient, 'id'> | null): boolean {
    return o1 && o2 ? this.getFoodIngredientIdentifier(o1) === this.getFoodIngredientIdentifier(o2) : o1 === o2;
  }

  addFoodIngredientToCollectionIfMissing<Type extends Pick<IFoodIngredient, 'id'>>(
    foodIngredientCollection: Type[],
    ...foodIngredientsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const foodIngredients: Type[] = foodIngredientsToCheck.filter(isPresent);
    if (foodIngredients.length > 0) {
      const foodIngredientCollectionIdentifiers = foodIngredientCollection.map(
        foodIngredientItem => this.getFoodIngredientIdentifier(foodIngredientItem)!
      );
      const foodIngredientsToAdd = foodIngredients.filter(foodIngredientItem => {
        const foodIngredientIdentifier = this.getFoodIngredientIdentifier(foodIngredientItem);
        if (foodIngredientCollectionIdentifiers.includes(foodIngredientIdentifier)) {
          return false;
        }
        foodIngredientCollectionIdentifiers.push(foodIngredientIdentifier);
        return true;
      });
      return [...foodIngredientsToAdd, ...foodIngredientCollection];
    }
    return foodIngredientCollection;
  }
}
