import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {map} from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import {isPresent} from 'app/core/util/operators';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IActivation, NewActivation} from '../activation.model';

export type PartialUpdateActivation = Partial<IActivation> & Pick<IActivation, 'id'>;

type RestOf<T extends IActivation | NewActivation> = Omit<T, 'sentOn' | 'validUntil'> & {
  sentOn?: string | null;
  validUntil?: string | null;
};

export type RestActivation = RestOf<IActivation>;

export type NewRestActivation = RestOf<NewActivation>;

export type PartialUpdateRestActivation = RestOf<PartialUpdateActivation>;

export type EntityResponseType = HttpResponse<IActivation>;
export type EntityArrayResponseType = HttpResponse<IActivation[]>;

@Injectable({providedIn: 'root'})
export class ActivationService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/activations');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(activation: NewActivation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(activation);
    return this.http
      .post<RestActivation>(this.resourceUrl, copy, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(activation: IActivation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(activation);
    return this.http
      .put<RestActivation>(`${this.resourceUrl}/${this.getActivationIdentifier(activation)}`, copy, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(activation: PartialUpdateActivation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(activation);
    return this.http
      .patch<RestActivation>(`${this.resourceUrl}/${this.getActivationIdentifier(activation)}`, copy, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestActivation>(`${this.resourceUrl}/${id}`, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestActivation[]>(this.resourceUrl, {params: options, observe: 'response'})
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getActivationIdentifier(activation: Pick<IActivation, 'id'>): number {
    return activation.id;
  }

  compareActivation(o1: Pick<IActivation, 'id'> | null, o2: Pick<IActivation, 'id'> | null): boolean {
    return o1 && o2 ? this.getActivationIdentifier(o1) === this.getActivationIdentifier(o2) : o1 === o2;
  }

  addActivationToCollectionIfMissing<Type extends Pick<IActivation, 'id'>>(
    activationCollection: Type[],
    ...activationsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const activations: Type[] = activationsToCheck.filter(isPresent);
    if (activations.length > 0) {
      const activationCollectionIdentifiers = activationCollection.map(activationItem => this.getActivationIdentifier(activationItem)!);
      const activationsToAdd = activations.filter(activationItem => {
        const activationIdentifier = this.getActivationIdentifier(activationItem);
        if (activationCollectionIdentifiers.includes(activationIdentifier)) {
          return false;
        }
        activationCollectionIdentifiers.push(activationIdentifier);
        return true;
      });
      return [...activationsToAdd, ...activationCollection];
    }
    return activationCollection;
  }

  protected convertDateFromClient<T extends IActivation | NewActivation | PartialUpdateActivation>(activation: T): RestOf<T> {
    return {
      ...activation,
      sentOn: activation.sentOn?.toJSON() ?? null,
      validUntil: activation.validUntil?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restActivation: RestActivation): IActivation {
    return {
      ...restActivation,
      sentOn: restActivation.sentOn ? dayjs(restActivation.sentOn) : undefined,
      validUntil: restActivation.validUntil ? dayjs(restActivation.validUntil) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestActivation>): HttpResponse<IActivation> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestActivation[]>): HttpResponse<IActivation[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
