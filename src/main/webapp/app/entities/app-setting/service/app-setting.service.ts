import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IAppSetting, NewAppSetting} from '../app-setting.model';

export type PartialUpdateAppSetting = Partial<IAppSetting> & Pick<IAppSetting, 'id'>;

export type EntityResponseType = HttpResponse<IAppSetting>;
export type EntityArrayResponseType = HttpResponse<IAppSetting[]>;

@Injectable({providedIn: 'root'})
export class AppSettingService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/app-settings');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(appSetting: NewAppSetting): Observable<EntityResponseType> {
    return this.http.post<IAppSetting>(this.resourceUrl, appSetting, {observe: 'response'});
  }

  update(appSetting: IAppSetting): Observable<EntityResponseType> {
    return this.http.put<IAppSetting>(`${this.resourceUrl}/${this.getAppSettingIdentifier(appSetting)}`, appSetting, {
      observe: 'response',
    });
  }

  partialUpdate(appSetting: PartialUpdateAppSetting): Observable<EntityResponseType> {
    return this.http.patch<IAppSetting>(`${this.resourceUrl}/${this.getAppSettingIdentifier(appSetting)}`, appSetting, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAppSetting>(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAppSetting[]>(this.resourceUrl, {params: options, observe: 'response'});
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getAppSettingIdentifier(appSetting: Pick<IAppSetting, 'id'>): number {
    return appSetting.id;
  }

  compareAppSetting(o1: Pick<IAppSetting, 'id'> | null, o2: Pick<IAppSetting, 'id'> | null): boolean {
    return o1 && o2 ? this.getAppSettingIdentifier(o1) === this.getAppSettingIdentifier(o2) : o1 === o2;
  }

  addAppSettingToCollectionIfMissing<Type extends Pick<IAppSetting, 'id'>>(
    appSettingCollection: Type[],
    ...appSettingsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const appSettings: Type[] = appSettingsToCheck.filter(isPresent);
    if (appSettings.length > 0) {
      const appSettingCollectionIdentifiers = appSettingCollection.map(appSettingItem => this.getAppSettingIdentifier(appSettingItem)!);
      const appSettingsToAdd = appSettings.filter(appSettingItem => {
        const appSettingIdentifier = this.getAppSettingIdentifier(appSettingItem);
        if (appSettingCollectionIdentifiers.includes(appSettingIdentifier)) {
          return false;
        }
        appSettingCollectionIdentifiers.push(appSettingIdentifier);
        return true;
      });
      return [...appSettingsToAdd, ...appSettingCollection];
    }
    return appSettingCollection;
  }
}
