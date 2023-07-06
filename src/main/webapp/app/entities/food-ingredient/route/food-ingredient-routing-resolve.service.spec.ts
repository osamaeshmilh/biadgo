import {TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap, RouterStateSnapshot} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {IFoodIngredient} from '../food-ingredient.model';
import {FoodIngredientService} from '../service/food-ingredient.service';

import foodIngredientResolve from './food-ingredient-routing-resolve.service';

describe('FoodIngredient routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: FoodIngredientService;
  let resultFoodIngredient: IFoodIngredient | null | undefined;

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
    service = TestBed.inject(FoodIngredientService);
    resultFoodIngredient = undefined;
  });

  describe('resolve', () => {
    it('should return IFoodIngredient returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({body: {id}})));
      mockActivatedRouteSnapshot.params = {id: 123};

      // WHEN
      TestBed.runInInjectionContext(() => {
        foodIngredientResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFoodIngredient = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultFoodIngredient).toEqual({id: 123});
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        foodIngredientResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFoodIngredient = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultFoodIngredient).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IFoodIngredient>({body: null})));
      mockActivatedRouteSnapshot.params = {id: 123};

      // WHEN
      TestBed.runInInjectionContext(() => {
        foodIngredientResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFoodIngredient = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultFoodIngredient).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
