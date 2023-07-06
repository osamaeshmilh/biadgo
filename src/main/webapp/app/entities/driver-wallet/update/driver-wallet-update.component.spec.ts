import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {DriverWalletFormService} from './driver-wallet-form.service';
import {DriverWalletService} from '../service/driver-wallet.service';
import {IDriverWallet} from '../driver-wallet.model';
import {IDriver} from 'app/entities/driver/driver.model';
import {DriverService} from 'app/entities/driver/service/driver.service';

import {DriverWalletUpdateComponent} from './driver-wallet-update.component';

describe('DriverWallet Management Update Component', () => {
  let comp: DriverWalletUpdateComponent;
  let fixture: ComponentFixture<DriverWalletUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let driverWalletFormService: DriverWalletFormService;
  let driverWalletService: DriverWalletService;
  let driverService: DriverService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), DriverWalletUpdateComponent],
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
      .overrideTemplate(DriverWalletUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DriverWalletUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    driverWalletFormService = TestBed.inject(DriverWalletFormService);
    driverWalletService = TestBed.inject(DriverWalletService);
    driverService = TestBed.inject(DriverService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Driver query and add missing value', () => {
      const driverWallet: IDriverWallet = {id: 456};
      const driver: IDriver = {id: 56897};
      driverWallet.driver = driver;

      const driverCollection: IDriver[] = [{id: 63331}];
      jest.spyOn(driverService, 'query').mockReturnValue(of(new HttpResponse({body: driverCollection})));
      const additionalDrivers = [driver];
      const expectedCollection: IDriver[] = [...additionalDrivers, ...driverCollection];
      jest.spyOn(driverService, 'addDriverToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({driverWallet});
      comp.ngOnInit();

      expect(driverService.query).toHaveBeenCalled();
      expect(driverService.addDriverToCollectionIfMissing).toHaveBeenCalledWith(
        driverCollection,
        ...additionalDrivers.map(expect.objectContaining)
      );
      expect(comp.driversSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const driverWallet: IDriverWallet = {id: 456};
      const driver: IDriver = {id: 7982};
      driverWallet.driver = driver;

      activatedRoute.data = of({driverWallet});
      comp.ngOnInit();

      expect(comp.driversSharedCollection).toContain(driver);
      expect(comp.driverWallet).toEqual(driverWallet);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDriverWallet>>();
      const driverWallet = {id: 123};
      jest.spyOn(driverWalletFormService, 'getDriverWallet').mockReturnValue(driverWallet);
      jest.spyOn(driverWalletService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({driverWallet});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: driverWallet}));
      saveSubject.complete();

      // THEN
      expect(driverWalletFormService.getDriverWallet).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(driverWalletService.update).toHaveBeenCalledWith(expect.objectContaining(driverWallet));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDriverWallet>>();
      const driverWallet = {id: 123};
      jest.spyOn(driverWalletFormService, 'getDriverWallet').mockReturnValue({id: null});
      jest.spyOn(driverWalletService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({driverWallet: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: driverWallet}));
      saveSubject.complete();

      // THEN
      expect(driverWalletFormService.getDriverWallet).toHaveBeenCalled();
      expect(driverWalletService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDriverWallet>>();
      const driverWallet = {id: 123};
      jest.spyOn(driverWalletService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({driverWallet});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(driverWalletService.update).toHaveBeenCalled();
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
