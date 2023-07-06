import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IFoodImage, NewFoodImage} from '../food-image.model';

export type PartialUpdateFoodImage = Partial<IFoodImage> & Pick<IFoodImage, 'id'>;

export type EntityResponseType = HttpResponse<IFoodImage>;
export type EntityArrayResponseType = HttpResponse<IFoodImage[]>;

@Injectable({providedIn: 'root'})
export class FoodImageService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/food-images');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(foodImage: NewFoodImage): Observable<EntityResponseType> {
    return this.http.post<IFoodImage>(this.resourceUrl, foodImage, {observe: 'response'});
  }

  update(foodImage: IFoodImage): Observable<EntityResponseType> {
    return this.http.put<IFoodImage>(`${this.resourceUrl}/${this.getFoodImageIdentifier(foodImage)}`, foodImage, {observe: 'response'});
  }

  partialUpdate(foodImage: PartialUpdateFoodImage): Observable<EntityResponseType> {
    return this.http.patch<IFoodImage>(`${this.resourceUrl}/${this.getFoodImageIdentifier(foodImage)}`, foodImage, {observe: 'response'});
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFoodImage>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFoodImage[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getFoodImageIdentifier(foodImage: Pick<IFoodImage, 'id'>): number {
    return foodImage.id;
  }

  compareFoodImage(o1: Pick<IFoodImage, 'id'> | null, o2: Pick<IFoodImage, 'id'> | null): boolean {
    return o1 && o2 ? this.getFoodImageIdentifier(o1) === this.getFoodImageIdentifier(o2) : o1 === o2;
  }

  addFoodImageToCollectionIfMissing<Type extends Pick<IFoodImage, 'id'>>(
    foodImageCollection: Type[],
    ...foodImagesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const foodImages: Type[] = foodImagesToCheck.filter(isPresent);
    if (foodImages.length > 0) {
      const foodImageCollectionIdentifiers = foodImageCollection.map(foodImageItem => this.getFoodImageIdentifier(foodImageItem)!);
      const foodImagesToAdd = foodImages.filter(foodImageItem => {
        const foodImageIdentifier = this.getFoodImageIdentifier(foodImageItem);
        if (foodImageCollectionIdentifiers.includes(foodImageIdentifier)) {
          return false;
        }
        foodImageCollectionIdentifiers.push(foodImageIdentifier);
        return true;
      });
      return [...foodImagesToAdd, ...foodImageCollection];
    }
    return foodImageCollection;
  }
}
