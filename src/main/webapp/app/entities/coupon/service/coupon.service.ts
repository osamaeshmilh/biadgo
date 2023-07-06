import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {map} from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import {isPresent} from 'app/core/util/operators';
import {DATE_FORMAT} from 'app/config/input.constants';
import {ApplicationConfigService} from 'app/core/config/application-config.service';
import {createRequestOption} from 'app/core/request/request-util';
import {ICoupon, NewCoupon} from '../coupon.model';

export type PartialUpdateCoupon = Partial<ICoupon> & Pick<ICoupon, 'id'>;

type RestOf<T extends ICoupon | NewCoupon> = Omit<T, 'expiryDate'> & {
  expiryDate?: string | null;
};

export type RestCoupon = RestOf<ICoupon>;

export type NewRestCoupon = RestOf<NewCoupon>;

export type PartialUpdateRestCoupon = RestOf<PartialUpdateCoupon>;

export type EntityResponseType = HttpResponse<ICoupon>;
export type EntityArrayResponseType = HttpResponse<ICoupon[]>;

@Injectable({providedIn: 'root'})
export class CouponService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/coupons');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
  }

  create(coupon: NewCoupon): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(coupon);
    return this.http
      .post<RestCoupon>(this.resourceUrl, copy, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(coupon: ICoupon): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(coupon);
    return this.http
      .put<RestCoupon>(`${this.resourceUrl}/${this.getCouponIdentifier(coupon)}`, copy, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(coupon: PartialUpdateCoupon): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(coupon);
    return this.http
      .patch<RestCoupon>(`${this.resourceUrl}/${this.getCouponIdentifier(coupon)}`, copy, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestCoupon>(`${this.resourceUrl}/${id}`, {observe: 'response'})
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCoupon[]>(this.resourceUrl, {params: options, observe: 'response'})
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {observe: 'response'});
  }

  getCouponIdentifier(coupon: Pick<ICoupon, 'id'>): number {
    return coupon.id;
  }

  compareCoupon(o1: Pick<ICoupon, 'id'> | null, o2: Pick<ICoupon, 'id'> | null): boolean {
    return o1 && o2 ? this.getCouponIdentifier(o1) === this.getCouponIdentifier(o2) : o1 === o2;
  }

  addCouponToCollectionIfMissing<Type extends Pick<ICoupon, 'id'>>(
    couponCollection: Type[],
    ...couponsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const coupons: Type[] = couponsToCheck.filter(isPresent);
    if (coupons.length > 0) {
      const couponCollectionIdentifiers = couponCollection.map(couponItem => this.getCouponIdentifier(couponItem)!);
      const couponsToAdd = coupons.filter(couponItem => {
        const couponIdentifier = this.getCouponIdentifier(couponItem);
        if (couponCollectionIdentifiers.includes(couponIdentifier)) {
          return false;
        }
        couponCollectionIdentifiers.push(couponIdentifier);
        return true;
      });
      return [...couponsToAdd, ...couponCollection];
    }
    return couponCollection;
  }

  protected convertDateFromClient<T extends ICoupon | NewCoupon | PartialUpdateCoupon>(coupon: T): RestOf<T> {
    return {
      ...coupon,
      expiryDate: coupon.expiryDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restCoupon: RestCoupon): ICoupon {
    return {
      ...restCoupon,
      expiryDate: restCoupon.expiryDate ? dayjs(restCoupon.expiryDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCoupon>): HttpResponse<ICoupon> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCoupon[]>): HttpResponse<ICoupon[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
