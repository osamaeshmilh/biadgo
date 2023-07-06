import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IDeliveryAddress, NewDeliveryAddress} from '../delivery-address.model';

export type PartialUpdateDeliveryAddress = Partial<IDeliveryAddress> & Pick<IDeliveryAddress, 'id'>;

export type EntityResponseType = HttpResponse<IDeliveryAddress>;
export type EntityArrayResponseType = HttpResponse<IDeliveryAddress[]>;

@Injectable({providedIn: 'root'})
export class DeliveryAddressService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/delivery-addresses');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(deliveryAddress: NewDeliveryAddress): Observable<EntityResponseType> {
    return this.http.post<IDeliveryAddress>(this.resourceUrl, deliveryAddress, {observe: 'response'});
  }

  update(deliveryAddress: IDeliveryAddress): Observable<EntityResponseType> {
    return this.http.put<IDeliveryAddress>(`${this.resourceUrl}/${this.getDeliveryAddressIdentifier(deliveryAddress)}`, deliveryAddress, {
      observe: 'response',
    });
  }

  partialUpdate(deliveryAddress: PartialUpdateDeliveryAddress): Observable<EntityResponseType> {
    return this.http.patch<IDeliveryAddress>(`${this.resourceUrl}/${this.getDeliveryAddressIdentifier(deliveryAddress)}`, deliveryAddress, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDeliveryAddress>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDeliveryAddress[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getDeliveryAddressIdentifier(deliveryAddress: Pick<IDeliveryAddress, 'id'>): number {
    return deliveryAddress.id;
  }

  compareDeliveryAddress(o1: Pick<IDeliveryAddress, 'id'> | null, o2: Pick<IDeliveryAddress, 'id'> | null): boolean {
    return o1 && o2 ? this.getDeliveryAddressIdentifier(o1) === this.getDeliveryAddressIdentifier(o2) : o1 === o2;
  }

  addDeliveryAddressToCollectionIfMissing<Type extends Pick<IDeliveryAddress, 'id'>>(
    deliveryAddressCollection: Type[],
    ...deliveryAddressesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const deliveryAddresses: Type[] = deliveryAddressesToCheck.filter(isPresent);
    if (deliveryAddresses.length > 0) {
      const deliveryAddressCollectionIdentifiers = deliveryAddressCollection.map(
        deliveryAddressItem => this.getDeliveryAddressIdentifier(deliveryAddressItem)!
      );
      const deliveryAddressesToAdd = deliveryAddresses.filter(deliveryAddressItem => {
        const deliveryAddressIdentifier = this.getDeliveryAddressIdentifier(deliveryAddressItem);
        if (deliveryAddressCollectionIdentifiers.includes(deliveryAddressIdentifier)) {
          return false;
        }
        deliveryAddressCollectionIdentifiers.push(deliveryAddressIdentifier);
        return true;
      });
      return [...deliveryAddressesToAdd, ...deliveryAddressCollection];
    }
    return deliveryAddressCollection;
  }
}
