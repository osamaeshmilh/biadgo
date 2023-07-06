import {TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap, RouterStateSnapshot} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {IFavoriteRestaurant} from '../favorite-restaurant.model';
import {FavoriteRestaurantService} from '../service/favorite-restaurant.service';

import favoriteRestaurantResolve from './favorite-restaurant-routing-resolve.service';

describe('FavoriteRestaurant routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: FavoriteRestaurantService;
  let resultFavoriteRestaurant: IFavoriteRestaurant | null | undefined;

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
    service = TestBed.inject(FavoriteRestaurantService);
    resultFavoriteRestaurant = undefined;
  });

  describe('resolve', () => {
    it('should return IFavoriteRestaurant returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({body: {id}})));
      mockActivatedRouteSnapshot.params = {id: 123};

      // WHEN
      TestBed.runInInjectionContext(() => {
        favoriteRestaurantResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFavoriteRestaurant = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultFavoriteRestaurant).toEqual({id: 123});
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        favoriteRestaurantResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFavoriteRestaurant = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultFavoriteRestaurant).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IFavoriteRestaurant>({body: null})));
      mockActivatedRouteSnapshot.params = {id: 123};

      // WHEN
      TestBed.runInInjectionContext(() => {
        favoriteRestaurantResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFavoriteRestaurant = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultFavoriteRestaurant).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
