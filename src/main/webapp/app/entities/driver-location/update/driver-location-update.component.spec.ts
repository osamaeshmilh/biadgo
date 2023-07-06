import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {DriverLocationFormService} from './driver-location-form.service';
import {DriverLocationService} from '../service/driver-location.service';
import {IDriverLocation} from '../driver-location.model';
import {IDriver} from 'app/entities/driver/driver.model';
import {DriverService} from 'app/entities/driver/service/driver.service';

import {DriverLocationUpdateComponent} from './driver-location-update.component';

describe('DriverLocation Management Update Component', () => {
  let comp: DriverLocationUpdateComponent;
  let fixture: ComponentFixture<DriverLocationUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let driverLocationFormService: DriverLocationFormService;
  let driverLocationService: DriverLocationService;
  let driverService: DriverService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), DriverLocationUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(DriverLocationUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DriverLocationUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    driverLocationFormService = TestBed.inject(DriverLocationFormService);
    driverLocationService = TestBed.inject(DriverLocationService);
    driverService = TestBed.inject(DriverService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Driver query and add missing value', () => {
      const driverLocation: IDriverLocation = {id: 456};
      const driver: IDriver = {id: 69982};
      driverLocation.driver = driver;

      const driverCollection: IDriver[] = [{id: 66421}];
      jest.spyOn(driverService, 'query').mockReturnValue(of(new HttpResponse({body: driverCollection})));
      const additionalDrivers = [driver];
      const expectedCollection: IDriver[] = [...additionalDrivers, ...driverCollection];
      jest.spyOn(driverService, 'addDriverToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({driverLocation});
      comp.ngOnInit();

      expect(driverService.query).toHaveBeenCalled();
      expect(driverService.addDriverToCollectionIfMissing).toHaveBeenCalledWith(
        driverCollection,
        ...additionalDrivers.map(expect.objectContaining)
      );
      expect(comp.driversSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const driverLocation: IDriverLocation = {id: 456};
      const driver: IDriver = {id: 7002};
      driverLocation.driver = driver;

      activatedRoute.data = of({driverLocation});
      comp.ngOnInit();

      expect(comp.driversSharedCollection).toContain(driver);
      expect(comp.driverLocation).toEqual(driverLocation);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDriverLocation>>();
      const driverLocation = {id: 123};
      jest.spyOn(driverLocationFormService, 'getDriverLocation').mockReturnValue(driverLocation);
      jest.spyOn(driverLocationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({driverLocation});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: driverLocation}));
      saveSubject.complete();

      // THEN
      expect(driverLocationFormService.getDriverLocation).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(driverLocationService.update).toHaveBeenCalledWith(expect.objectContaining(driverLocation));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDriverLocation>>();
      const driverLocation = {id: 123};
      jest.spyOn(driverLocationFormService, 'getDriverLocation').mockReturnValue({id: null});
      jest.spyOn(driverLocationService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({driverLocation: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: driverLocation}));
      saveSubject.complete();

      // THEN
      expect(driverLocationFormService.getDriverLocation).toHaveBeenCalled();
      expect(driverLocationService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDriverLocation>>();
      const driverLocation = {id: 123};
      jest.spyOn(driverLocationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({driverLocation});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(driverLocationService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareDriver', () => {
      it('Should forward to driverService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(driverService, 'compareDriver');
        comp.compareDriver(entity, entity2);
        expect(driverService.compareDriver).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
