import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {CustomerWalletFormService} from './customer-wallet-form.service';
import {CustomerWalletService} from '../service/customer-wallet.service';
import {ICustomerWallet} from '../customer-wallet.model';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';

import {CustomerWalletUpdateComponent} from './customer-wallet-update.component';

describe('CustomerWallet Management Update Component', () => {
  let comp: CustomerWalletUpdateComponent;
  let fixture: ComponentFixture<CustomerWalletUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let customerWalletFormService: CustomerWalletFormService;
  let customerWalletService: CustomerWalletService;
  let customerService: CustomerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), CustomerWalletUpdateComponent],
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
      .overrideTemplate(CustomerWalletUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CustomerWalletUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    customerWalletFormService = TestBed.inject(CustomerWalletFormService);
    customerWalletService = TestBed.inject(CustomerWalletService);
    customerService = TestBed.inject(CustomerService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Customer query and add missing value', () => {
      const customerWallet: ICustomerWallet = {id: 456};
      const customer: ICustomer = {id: 17401};
      customerWallet.customer = customer;

      const customerCollection: ICustomer[] = [{id: 38286}];
      jest.spyOn(customerService, 'query').mockReturnValue(of(new HttpResponse({body: customerCollection})));
      const additionalCustomers = [customer];
      const expectedCollection: ICustomer[] = [...additionalCustomers, ...customerCollection];
      jest.spyOn(customerService, 'addCustomerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({customerWallet});
      comp.ngOnInit();

      expect(customerService.query).toHaveBeenCalled();
      expect(customerService.addCustomerToCollectionIfMissing).toHaveBeenCalledWith(
        customerCollection,
        ...additionalCustomers.map(expect.objectContaining)
      );
      expect(comp.customersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const customerWallet: ICustomerWallet = {id: 456};
      const customer: ICustomer = {id: 97317};
      customerWallet.customer = customer;

      activatedRoute.data = of({customerWallet});
      comp.ngOnInit();

      expect(comp.customersSharedCollection).toContain(customer);
      expect(comp.customerWallet).toEqual(customerWallet);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICustomerWallet>>();
      const customerWallet = {id: 123};
      jest.spyOn(customerWalletFormService, 'getCustomerWallet').mockReturnValue(customerWallet);
      jest.spyOn(customerWalletService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({customerWallet});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: customerWallet}));
      saveSubject.complete();

      // THEN
      expect(customerWalletFormService.getCustomerWallet).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(customerWalletService.update).toHaveBeenCalledWith(expect.objectContaining(customerWallet));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICustomerWallet>>();
      const customerWallet = {id: 123};
      jest.spyOn(customerWalletFormService, 'getCustomerWallet').mockReturnValue({id: null});
      jest.spyOn(customerWalletService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({customerWallet: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: customerWallet}));
      saveSubject.complete();

      // THEN
      expect(customerWalletFormService.getCustomerWallet).toHaveBeenCalled();
      expect(customerWalletService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICustomerWallet>>();
      const customerWallet = {id: 123};
      jest.spyOn(customerWalletService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({customerWallet});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(customerWalletService.update).toHaveBeenCalled();
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
