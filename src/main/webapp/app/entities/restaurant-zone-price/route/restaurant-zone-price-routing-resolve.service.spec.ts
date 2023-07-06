import {TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap, RouterStateSnapshot} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {IRestaurantZonePrice} from '../restaurant-zone-price.model';
import {RestaurantZonePriceService} from '../service/restaurant-zone-price.service';

import restaurantZonePriceResolve from './restaurant-zone-price-routing-resolve.service';

describe('RestaurantZonePrice routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: RestaurantZonePriceService;
  let resultRestaurantZonePrice: IRestaurantZonePrice | null | undefined;

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
    service = TestBed.inject(RestaurantZonePriceService);
    resultRestaurantZonePrice = undefined;
  });

  describe('resolve', () => {
    it('should return IRestaurantZonePrice returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({body: {id}})));
      mockActivatedRouteSnapshot.params = {id: 123};

      // WHEN
      TestBed.runInInjectionContext(() => {
        restaurantZonePriceResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultRestaurantZonePrice = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultRestaurantZonePrice).toEqual({id: 123});
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        restaurantZonePriceResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultRestaurantZonePrice = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultRestaurantZonePrice).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IRestaurantZonePrice>({body: null})));
      mockActivatedRouteSnapshot.params = {id: 123};

      // WHEN
      TestBed.runInInjectionContext(() => {
        restaurantZonePriceResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultRestaurantZonePrice = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultRestaurantZonePrice).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
