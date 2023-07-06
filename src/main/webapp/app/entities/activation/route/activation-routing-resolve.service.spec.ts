import {TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap, RouterStateSnapshot} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {IActivation} from '../activation.model';
import {ActivationService} from '../service/activation.service';

import activationResolve from './activation-routing-resolve.service';

describe('Activation routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: ActivationService;
  let resultActivation: IActivation | null | undefined;

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
    service = TestBed.inject(ActivationService);
    resultActivation = undefined;
  });

  describe('resolve', () => {
    it('should return IActivation returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({body: {id}})));
      mockActivatedRouteSnapshot.params = {id: 123};

      // WHEN
      TestBed.runInInjectionContext(() => {
        activationResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultActivation = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultActivation).toEqual({id: 123});
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        activationResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultActivation = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultActivation).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IActivation>({body: null})));
      mockActivatedRouteSnapshot.params = {id: 123};

      // WHEN
      TestBed.runInInjectionContext(() => {
        activationResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultActivation = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultActivation).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
