import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IRestaurantImage, NewRestaurantImage} from '../restaurant-image.model';

export type PartialUpdateRestaurantImage = Partial<IRestaurantImage> & Pick<IRestaurantImage, 'id'>;

export type EntityResponseType = HttpResponse<IRestaurantImage>;
export type EntityArrayResponseType = HttpResponse<IRestaurantImage[]>;

@Injectable({providedIn: 'root'})
export class RestaurantImageService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/restaurant-images');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(restaurantImage: NewRestaurantImage): Observable<EntityResponseType> {
    return this.http.post<IRestaurantImage>(this.resourceUrl, restaurantImage, {observe: 'response'});
  }

  update(restaurantImage: IRestaurantImage): Observable<EntityResponseType> {
    return this.http.put<IRestaurantImage>(`${this.resourceUrl}/${this.getRestaurantImageIdentifier(restaurantImage)}`, restaurantImage, {
      observe: 'response',
    });
  }

  partialUpdate(restaurantImage: PartialUpdateRestaurantImage): Observable<EntityResponseType> {
    return this.http.patch<IRestaurantImage>(`${this.resourceUrl}/${this.getRestaurantImageIdentifier(restaurantImage)}`, restaurantImage, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRestaurantImage>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRestaurantImage[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getRestaurantImageIdentifier(restaurantImage: Pick<IRestaurantImage, 'id'>): number {
    return restaurantImage.id;
  }

  compareRestaurantImage(o1: Pick<IRestaurantImage, 'id'> | null, o2: Pick<IRestaurantImage, 'id'> | null): boolean {
    return o1 && o2 ? this.getRestaurantImageIdentifier(o1) === this.getRestaurantImageIdentifier(o2) : o1 === o2;
  }

  addRestaurantImageToCollectionIfMissing<Type extends Pick<IRestaurantImage, 'id'>>(
    restaurantImageCollection: Type[],
    ...restaurantImagesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const restaurantImages: Type[] = restaurantImagesToCheck.filter(isPresent);
    if (restaurantImages.length > 0) {
      const restaurantImageCollectionIdentifiers = restaurantImageCollection.map(
        restaurantImageItem => this.getRestaurantImageIdentifier(restaurantImageItem)!
      );
      const restaurantImagesToAdd = restaurantImages.filter(restaurantImageItem => {
        const restaurantImageIdentifier = this.getRestaurantImageIdentifier(restaurantImageItem);
        if (restaurantImageCollectionIdentifiers.includes(restaurantImageIdentifier)) {
          return false;
        }
        restaurantImageCollectionIdentifiers.push(restaurantImageIdentifier);
        return true;
      });
      return [...restaurantImagesToAdd, ...restaurantImageCollection];
    }
    return restaurantImageCollection;
  }
}
