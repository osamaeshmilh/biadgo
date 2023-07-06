import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {ReferralFormService} from './referral-form.service';
import {ReferralService} from '../service/referral.service';
import {IReferral} from '../referral.model';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';

import {ReferralUpdateComponent} from './referral-update.component';

describe('Referral Management Update Component', () => {
  let comp: ReferralUpdateComponent;
  let fixture: ComponentFixture<ReferralUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let referralFormService: ReferralFormService;
  let referralService: ReferralService;
  let customerService: CustomerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ReferralUpdateComponent],
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
      .overrideTemplate(ReferralUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ReferralUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    referralFormService = TestBed.inject(ReferralFormService);
    referralService = TestBed.inject(ReferralService);
    customerService = TestBed.inject(CustomerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Customer query and add missing value', () => {
      const referral: IReferral = {id: 456};
      const referredCustomer: ICustomer = {id: 34847};
      referral.referredCustomer = referredCustomer;
      const referrerCustomer: ICustomer = {id: 34003};
      referral.referrerCustomer = referrerCustomer;

      const customerCollection: ICustomer[] = [{id: 48759}];
      jest.spyOn(customerService, 'query').mockReturnValue(of(new HttpResponse({body: customerCollection})));
      const additionalCustomers = [referredCustomer, referrerCustomer];
      const expectedCollection: ICustomer[] = [...additionalCustomers, ...customerCollection];
      jest.spyOn(customerService, 'addCustomerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({referral});
      comp.ngOnInit();

      expect(customerService.query).toHaveBeenCalled();
      expect(customerService.addCustomerToCollectionIfMissing).toHaveBeenCalledWith(
        customerCollection,
        ...additionalCustomers.map(expect.objectContaining)
      );
      expect(comp.customersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const referral: IReferral = {id: 456};
      const referredCustomer: ICustomer = {id: 81746};
      referral.referredCustomer = referredCustomer;
      const referrerCustomer: ICustomer = {id: 77611};
      referral.referrerCustomer = referrerCustomer;

      activatedRoute.data = of({referral});
      comp.ngOnInit();

      expect(comp.customersSharedCollection).toContain(referredCustomer);
      expect(comp.customersSharedCollection).toContain(referrerCustomer);
      expect(comp.referral).toEqual(referral);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReferral>>();
      const referral = {id: 123};
      jest.spyOn(referralFormService, 'getReferral').mockReturnValue(referral);
      jest.spyOn(referralService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({referral});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: referral}));
      saveSubject.complete();

      // THEN
      expect(referralFormService.getReferral).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(referralService.update).toHaveBeenCalledWith(expect.objectContaining(referral));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReferral>>();
      const referral = {id: 123};
      jest.spyOn(referralFormService, 'getReferral').mockReturnValue({id: null});
      jest.spyOn(referralService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({referral: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: referral}));
      saveSubject.complete();

      // THEN
      expect(referralFormService.getReferral).toHaveBeenCalled();
      expect(referralService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReferral>>();
      const referral = {id: 123};
      jest.spyOn(referralService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({referral});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(referralService.update).toHaveBeenCalled();
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
  });
});
