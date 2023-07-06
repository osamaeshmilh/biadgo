import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {DriverFormService} from './driver-form.service';
import {DriverService} from '../service/driver.service';
import {IDriver} from '../driver.model';

import {IUser} from 'app/entities/user/user.model';
import {UserService} from 'app/entities/user/user.service';
import {IZone} from 'app/entities/zone/zone.model';
import {ZoneService} from 'app/entities/zone/service/zone.service';

import {DriverUpdateComponent} from './driver-update.component';

describe('Driver Management Update Component', () => {
  let comp: DriverUpdateComponent;
  let fixture: ComponentFixture<DriverUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let driverFormService: DriverFormService;
  let driverService: DriverService;
  let userService: UserService;
  let zoneService: ZoneService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), DriverUpdateComponent],
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
      .overrideTemplate(DriverUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DriverUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    driverFormService = TestBed.inject(DriverFormService);
    driverService = TestBed.inject(DriverService);
    userService = TestBed.inject(UserService);
    zoneService = TestBed.inject(ZoneService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call User query and add missing value', () => {
      const driver: IDriver = {id: 456};
      const user: IUser = {id: 86154};
      driver.user = user;

      const userCollection: IUser[] = [{id: 71049}];
      jest.spyOn(userService, 'query').mockReturnValue(of(new HttpResponse({body: userCollection})));
      const additionalUsers = [user];
      const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
      jest.spyOn(userService, 'addUserToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({driver});
      comp.ngOnInit();

      expect(userService.query).toHaveBeenCalled();
      expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(
        userCollection,
        ...additionalUsers.map(expect.objectContaining)
      );
      expect(comp.usersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Zone query and add missing value', () => {
      const driver: IDriver = {id: 456};
      const zone: IZone = {id: 19634};
      driver.zone = zone;

      const zoneCollection: IZone[] = [{id: 36482}];
      jest.spyOn(zoneService, 'query').mockReturnValue(of(new HttpResponse({body: zoneCollection})));
      const additionalZones = [zone];
      const expectedCollection: IZone[] = [...additionalZones, ...zoneCollection];
      jest.spyOn(zoneService, 'addZoneToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({driver});
      comp.ngOnInit();

      expect(zoneService.query).toHaveBeenCalled();
      expect(zoneService.addZoneToCollectionIfMissing).toHaveBeenCalledWith(
        zoneCollection,
        ...additionalZones.map(expect.objectContaining)
      );
      expect(comp.zonesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const driver: IDriver = {id: 456};
      const user: IUser = {id: 13410};
      driver.user = user;
      const zone: IZone = {id: 45233};
      driver.zone = zone;

      activatedRoute.data = of({driver});
      comp.ngOnInit();

      expect(comp.usersSharedCollection).toContain(user);
      expect(comp.zonesSharedCollection).toContain(zone);
      expect(comp.driver).toEqual(driver);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDriver>>();
      const driver = {id: 123};
      jest.spyOn(driverFormService, 'getDriver').mockReturnValue(driver);
      jest.spyOn(driverService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({driver});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: driver}));
      saveSubject.complete();

      // THEN
      expect(driverFormService.getDriver).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(driverService.update).toHaveBeenCalledWith(expect.objectContaining(driver));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDriver>>();
      const driver = {id: 123};
      jest.spyOn(driverFormService, 'getDriver').mockReturnValue({id: null});
      jest.spyOn(driverService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({driver: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: driver}));
      saveSubject.complete();

      // THEN
      expect(driverFormService.getDriver).toHaveBeenCalled();
      expect(driverService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDriver>>();
      const driver = {id: 123};
      jest.spyOn(driverService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({driver});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(driverService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareUser', () => {
      it('Should forward to userService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(userService, 'compareUser');
        comp.compareUser(entity, entity2);
        expect(userService.compareUser).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareZone', () => {
      it('Should forward to zoneService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(zoneService, 'compareZone');
        comp.compareZone(entity, entity2);
        expect(zoneService.compareZone).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
