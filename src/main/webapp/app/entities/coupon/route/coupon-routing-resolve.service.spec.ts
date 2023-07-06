import {TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap, RouterStateSnapshot} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {ICoupon} from '../coupon.model';
import {CouponService} from '../service/coupon.service';

import couponResolve from './coupon-routing-resolve.service';

describe('Coupon routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: CouponService;
  let resultCoupon: ICoupon | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    service = TestBed.inject(CouponService);
    resultCoupon = undefined;
  });

  describe('resolve', () => {
    it('should return ICoupon returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({body: {id}})));
      mockActivatedRouteSnapshot.params = {id: 123};

      // WHEN
      TestBed.runInInjectionContext(() => {
        couponResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultCoupon = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCoupon).toEqual({id: 123});
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        couponResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultCoupon = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultCoupon).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<ICoupon>({body: null})));
      mockActivatedRouteSnapshot.params = {id: 123};

      // WHEN
      TestBed.runInInjectionContext(() => {
        couponResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultCoupon = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultCoupon).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
