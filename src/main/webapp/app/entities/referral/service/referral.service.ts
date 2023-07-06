import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {map} from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import {isPresent} from 'app/core/util/operators';
import {DATE_FORMAT} from 'app/config/input.constants';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {IReferral, NewReferral} from '../referral.model';

export type PartialUpdateReferral = Partial<IReferral> & Pick<IReferral, 'id'>;

type RestOf<T extends IReferral | NewReferral> = Omit<T, 'expiryDate' | 'usedDateTime'> & {
  expiryDate?: string | null;
  usedDateTime?: string | null;
};

export type RestReferral = RestOf<IReferral>;

export type NewRestReferral = RestOf<NewReferral>;

export type PartialUpdateRestReferral = RestOf<PartialUpdateReferral>;

export type EntityResponseType = HttpResponse<IReferral>;
export type EntityArrayResponseType = HttpResponse<IReferral[]>;

@Injectable({providedIn: 'root'})
export class ReferralService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/referrals');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(referral: NewReferral): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(referral);
    return this.http
      .post<RestReferral>(this.resourceUrl, copy, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(referral: IReferral): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(referral);
    return this.http
      .put<RestReferral>(`${this.resourceUrl}/${this.getReferralIdentifier(referral)}`, copy, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(referral: PartialUpdateReferral): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(referral);
    return this.http
      .patch<RestReferral>(`${this.resourceUrl}/${this.getReferralIdentifier(referral)}`, copy, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestReferral>(`${this.resourceUrl}/${id}`, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestReferral[]>(this.resourceUrl, {params: options, observe: 'response'})
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getReferralIdentifier(referral: Pick<IReferral, 'id'>): number {
    return referral.id;
  }

  compareReferral(o1: Pick<IReferral, 'id'> | null, o2: Pick<IReferral, 'id'> | null): boolean {
    return o1 && o2 ? this.getReferralIdentifier(o1) === this.getReferralIdentifier(o2) : o1 === o2;
  }

  addReferralToCollectionIfMissing<Type extends Pick<IReferral, 'id'>>(
    referralCollection: Type[],
    ...referralsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const referrals: Type[] = referralsToCheck.filter(isPresent);
    if (referrals.length > 0) {
      const referralCollectionIdentifiers = referralCollection.map(referralItem => this.getReferralIdentifier(referralItem)!);
      const referralsToAdd = referrals.filter(referralItem => {
        const referralIdentifier = this.getReferralIdentifier(referralItem);
        if (referralCollectionIdentifiers.includes(referralIdentifier)) {
          return false;
        }
        referralCollectionIdentifiers.push(referralIdentifier);
        return true;
      });
      return [...referralsToAdd, ...referralCollection];
    }
    return referralCollection;
  }

  protected convertDateFromClient<T extends IReferral | NewReferral | PartialUpdateReferral>(referral: T): RestOf<T> {
    return {
      ...referral,
      expiryDate: referral.expiryDate?.format(DATE_FORMAT) ?? null,
      usedDateTime: referral.usedDateTime?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restReferral: RestReferral): IReferral {
    return {
      ...restReferral,
      expiryDate: restReferral.expiryDate ? dayjs(restReferral.expiryDate) : undefined,
      usedDateTime: restReferral.usedDateTime ? dayjs(restReferral.usedDateTime) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestReferral>): HttpResponse<IReferral> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestReferral[]>): HttpResponse<IReferral[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
