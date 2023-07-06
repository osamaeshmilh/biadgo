import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {DriverReviewFormService} from './driver-review-form.service';
import {DriverReviewService} from '../service/driver-review.service';
import {IDriverReview} from '../driver-review.model';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';
import {IDriver} from 'app/entities/driver/driver.model';
import {DriverService} from 'app/entities/driver/service/driver.service';

import {DriverReviewUpdateComponent} from './driver-review-update.component';

describe('DriverReview Management Update Component', () => {
  let comp: DriverReviewUpdateComponent;
  let fixture: ComponentFixture<DriverReviewUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let driverReviewFormService: DriverReviewFormService;
  let driverReviewService: DriverReviewService;
  let customerService: CustomerService;
  let driverService: DriverService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), DriverReviewUpdateComponent],
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
      .overrideTemplate(DriverReviewUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DriverReviewUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    driverReviewFormService = TestBed.inject(DriverReviewFormService);
    driverReviewService = TestBed.inject(DriverReviewService);
    customerService = TestBed.inject(CustomerService);
    driverService = TestBed.inject(DriverService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Customer query and add missing value', () => {
      const driverReview: IDriverReview = {id: 456};
      const customer: ICustomer = {id: 30291};
      driverReview.customer = customer;

      const customerCollection: ICustomer[] = [{id: 58615}];
      jest.spyOn(customerService, 'query').mockReturnValue(of(new HttpResponse({body: customerCollection})));
      const additionalCustomers = [customer];
      const expectedCollection: ICustomer[] = [...additionalCustomers, ...customerCollection];
      jest.spyOn(customerService, 'addCustomerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({driverReview});
      comp.ngOnInit();

      expect(customerService.query).toHaveBeenCalled();
      expect(customerService.addCustomerToCollectionIfMissing).toHaveBeenCalledWith(
        customerCollection,
        ...additionalCustomers.map(expect.objectContaining)
      );
      expect(comp.customersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Driver query and add missing value', () => {
      const driverReview: IDriverReview = {id: 456};
      const driver: IDriver = {id: 87027};
      driverReview.driver = driver;

      const driverCollection: IDriver[] = [{id: 93674}];
      jest.spyOn(driverService, 'query').mockReturnValue(of(new HttpResponse({body: driverCollection})));
      const additionalDrivers = [driver];
      const expectedCollection: IDriver[] = [...additionalDrivers, ...driverCollection];
      jest.spyOn(driverService, 'addDriverToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({driverReview});
      comp.ngOnInit();

      expect(driverService.query).toHaveBeenCalled();
      expect(driverService.addDriverToCollectionIfMissing).toHaveBeenCalledWith(
        driverCollection,
        ...additionalDrivers.map(expect.objectContaining)
      );
      expect(comp.driversSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const driverReview: IDriverReview = {id: 456};
      const customer: ICustomer = {id: 8187};
      driverReview.customer = customer;
      const driver: IDriver = {id: 55966};
      driverReview.driver = driver;

      activatedRoute.data = of({driverReview});
      comp.ngOnInit();

      expect(comp.customersSharedCollection).toContain(customer);
      expect(comp.driversSharedCollection).toContain(driver);
      expect(comp.driverReview).toEqual(driverReview);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDriverReview>>();
      const driverReview = {id: 123};
      jest.spyOn(driverReviewFormService, 'getDriverReview').mockReturnValue(driverReview);
      jest.spyOn(driverReviewService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({driverReview});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: driverReview}));
      saveSubject.complete();

      // THEN
      expect(driverReviewFormService.getDriverReview).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(driverReviewService.update).toHaveBeenCalledWith(expect.objectContaining(driverReview));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDriverReview>>();
      const driverReview = {id: 123};
      jest.spyOn(driverReviewFormService, 'getDriverReview').mockReturnValue({id: null});
      jest.spyOn(driverReviewService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({driverReview: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: driverReview}));
      saveSubject.complete();

      // THEN
      expect(driverReviewFormService.getDriverReview).toHaveBeenCalled();
      expect(driverReviewService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDriverReview>>();
      const driverReview = {id: 123};
      jest.spyOn(driverReviewService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({driverReview});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(driverReviewService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareCustomer', () => {
      it('Should forward to customerService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(customerService, 'compareCustomer');
        comp.compareCustomer(entity, entity2);
        expect(customerService.compareCustomer).toHaveBeenCalledWith(entity, entity2);
      });
    });

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
