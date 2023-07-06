import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {ICustomerWallet, NewCustomerWallet} from '../customer-wallet.model';

export type PartialUpdateCustomerWallet = Partial<ICustomerWallet> & Pick<ICustomerWallet, 'id'>;

export type EntityResponseType = HttpResponse<ICustomerWallet>;
export type EntityArrayResponseType = HttpResponse<ICustomerWallet[]>;

@Injectable({providedIn: 'root'})
export class CustomerWalletService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/customer-wallets');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(customerWallet: NewCustomerWallet): Observable<EntityResponseType> {
    return this.http.post<ICustomerWallet>(this.resourceUrl, customerWallet, {observe: 'response'});
  }

  update(customerWallet: ICustomerWallet): Observable<EntityResponseType> {
    return this.http.put<ICustomerWallet>(`${this.resourceUrl}/${this.getCustomerWalletIdentifier(customerWallet)}`, customerWallet, {
      observe: 'response',
    });
  }

  partialUpdate(customerWallet: PartialUpdateCustomerWallet): Observable<EntityResponseType> {
    return this.http.patch<ICustomerWallet>(`${this.resourceUrl}/${this.getCustomerWalletIdentifier(customerWallet)}`, customerWallet, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICustomerWallet>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICustomerWallet[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getCustomerWalletIdentifier(customerWallet: Pick<ICustomerWallet, 'id'>): number {
    return customerWallet.id;
  }

  compareCustomerWallet(o1: Pick<ICustomerWallet, 'id'> | null, o2: Pick<ICustomerWallet, 'id'> | null): boolean {
    return o1 && o2 ? this.getCustomerWalletIdentifier(o1) === this.getCustomerWalletIdentifier(o2) : o1 === o2;
  }

  addCustomerWalletToCollectionIfMissing<Type extends Pick<ICustomerWallet, 'id'>>(
    customerWalletCollection: Type[],
    ...customerWalletsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const customerWallets: Type[] = customerWalletsToCheck.filter(isPresent);
    if (customerWallets.length > 0) {
      const customerWalletCollectionIdentifiers = customerWalletCollection.map(
        customerWalletItem => this.getCustomerWalletIdentifier(customerWalletItem)!
      );
      const customerWalletsToAdd = customerWallets.filter(customerWalletItem => {
        const customerWalletIdentifier = this.getCustomerWalletIdentifier(customerWalletItem);
        if (customerWalletCollectionIdentifiers.includes(customerWalletIdentifier)) {
          return false;
        }
        customerWalletCollectionIdentifiers.push(customerWalletIdentifier);
        return true;
      });
      return [...customerWalletsToAdd, ...customerWalletCollection];
    }
    return customerWalletCollection;
  }
}
