import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IDriverWallet, NewDriverWallet} from '../driver-wallet.model';

export type PartialUpdateDriverWallet = Partial<IDriverWallet> & Pick<IDriverWallet, 'id'>;

export type EntityResponseType = HttpResponse<IDriverWallet>;
export type EntityArrayResponseType = HttpResponse<IDriverWallet[]>;

@Injectable({providedIn: 'root'})
export class DriverWalletService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/driver-wallets');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(driverWallet: NewDriverWallet): Observable<EntityResponseType> {
    return this.http.post<IDriverWallet>(this.resourceUrl, driverWallet, {observe: 'response'});
  }

  update(driverWallet: IDriverWallet): Observable<EntityResponseType> {
    return this.http.put<IDriverWallet>(`${this.resourceUrl}/${this.getDriverWalletIdentifier(driverWallet)}`, driverWallet, {
      observe: 'response',
    });
  }

  partialUpdate(driverWallet: PartialUpdateDriverWallet): Observable<EntityResponseType> {
    return this.http.patch<IDriverWallet>(`${this.resourceUrl}/${this.getDriverWalletIdentifier(driverWallet)}`, driverWallet, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDriverWallet>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDriverWallet[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getDriverWalletIdentifier(driverWallet: Pick<IDriverWallet, 'id'>): number {
    return driverWallet.id;
  }

  compareDriverWallet(o1: Pick<IDriverWallet, 'id'> | null, o2: Pick<IDriverWallet, 'id'> | null): boolean {
    return o1 && o2 ? this.getDriverWalletIdentifier(o1) === this.getDriverWalletIdentifier(o2) : o1 === o2;
  }

  addDriverWalletToCollectionIfMissing<Type extends Pick<IDriverWallet, 'id'>>(
    driverWalletCollection: Type[],
    ...driverWalletsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const driverWallets: Type[] = driverWalletsToCheck.filter(isPresent);
    if (driverWallets.length > 0) {
      const driverWalletCollectionIdentifiers = driverWalletCollection.map(
        driverWalletItem => this.getDriverWalletIdentifier(driverWalletItem)!
      );
      const driverWalletsToAdd = driverWallets.filter(driverWalletItem => {
        const driverWalletIdentifier = this.getDriverWalletIdentifier(driverWalletItem);
        if (driverWalletCollectionIdentifiers.includes(driverWalletIdentifier)) {
          return false;
        }
        driverWalletCollectionIdentifiers.push(driverWalletIdentifier);
        return true;
      });
      return [...driverWalletsToAdd, ...driverWalletCollection];
    }
    return driverWalletCollection;
  }
}
