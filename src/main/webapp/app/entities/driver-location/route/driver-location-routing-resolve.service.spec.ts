import {TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap, RouterStateSnapshot} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {IDriverLocation} from '../driver-location.model';
import {DriverLocationService} from '../service/driver-location.service';

import driverLocationResolve from './driver-location-routing-resolve.service';

describe('DriverLocation routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: DriverLocationService;
  let resultDriverLocation: IDriverLocation | null | undefined;

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
    service = TestBed.inject(DriverLocationService);
    resultDriverLocation = undefined;
  });

  describe('resolve', () => {
    it('should return IDriverLocation returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({body: {id}})));
      mockActivatedRouteSnapshot.params = {id: 123};

      // WHEN
      TestBed.runInInjectionContext(() => {
        driverLocationResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultDriverLocation = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDriverLocation).toEqual({id: 123});
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        driverLocationResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultDriverLocation = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultDriverLocation).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IDriverLocation>({body: null})));
      mockActivatedRouteSnapshot.params = {id: 123};

      // WHEN
      TestBed.runInInjectionContext(() => {
        driverLocationResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultDriverLocation = result;
          },
        });
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDriverLocation).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
