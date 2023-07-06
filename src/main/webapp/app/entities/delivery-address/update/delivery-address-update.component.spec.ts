import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {DeliveryAddressFormService} from './delivery-address-form.service';
import {DeliveryAddressService} from '../service/delivery-address.service';
import {IDeliveryAddress} from '../delivery-address.model';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';
import {IZone} from 'app/entities/zone/zone.model';
import {ZoneService} from 'app/entities/zone/service/zone.service';

import {DeliveryAddressUpdateComponent} from './delivery-address-update.component';

describe('DeliveryAddress Management Update Component', () => {
  let comp: DeliveryAddressUpdateComponent;
  let fixture: ComponentFixture<DeliveryAddressUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let deliveryAddressFormService: DeliveryAddressFormService;
  let deliveryAddressService: DeliveryAddressService;
  let customerService: CustomerService;
  let zoneService: ZoneService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), DeliveryAddressUpdateComponent],
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
      .overrideTemplate(DeliveryAddressUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DeliveryAddressUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    deliveryAddressFormService = TestBed.inject(DeliveryAddressFormService);
    deliveryAddressService = TestBed.inject(DeliveryAddressService);
    customerService = TestBed.inject(CustomerService);
    zoneService = TestBed.inject(ZoneService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Customer query and add missing value', () => {
      const deliveryAddress: IDeliveryAddress = {id: 456};
      const customer: ICustomer = {id: 17593};
      deliveryAddress.customer = customer;

      const customerCollection: ICustomer[] = [{id: 47224}];
      jest.spyOn(customerService, 'query').mockReturnValue(of(new HttpResponse({body: customerCollection})));
      const additionalCustomers = [customer];
      const expectedCollection: ICustomer[] = [...additionalCustomers, ...customerCollection];
      jest.spyOn(customerService, 'addCustomerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({deliveryAddress});
      comp.ngOnInit();

      expect(customerService.query).toHaveBeenCalled();
      expect(customerService.addCustomerToCollectionIfMissing).toHaveBeenCalledWith(
        customerCollection,
        ...additionalCustomers.map(expect.objectContaining)
      );
      expect(comp.customersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Zone query and add missing value', () => {
      const deliveryAddress: IDeliveryAddress = {id: 456};
      const zone: IZone = {id: 39504};
      deliveryAddress.zone = zone;

      const zoneCollection: IZone[] = [{id: 11882}];
      jest.spyOn(zoneService, 'query').mockReturnValue(of(new HttpResponse({body: zoneCollection})));
      const additionalZones = [zone];
      const expectedCollection: IZone[] = [...additionalZones, ...zoneCollection];
      jest.spyOn(zoneService, 'addZoneToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({deliveryAddress});
      comp.ngOnInit();

      expect(zoneService.query).toHaveBeenCalled();
      expect(zoneService.addZoneToCollectionIfMissing).toHaveBeenCalledWith(
        zoneCollection,
        ...additionalZones.map(expect.objectContaining)
      );
      expect(comp.zonesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const deliveryAddress: IDeliveryAddress = {id: 456};
      const customer: ICustomer = {id: 63739};
      deliveryAddress.customer = customer;
      const zone: IZone = {id: 59228};
      deliveryAddress.zone = zone;

      activatedRoute.data = of({deliveryAddress});
      comp.ngOnInit();

      expect(comp.customersSharedCollection).toContain(customer);
      expect(comp.zonesSharedCollection).toContain(zone);
      expect(comp.deliveryAddress).toEqual(deliveryAddress);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDeliveryAddress>>();
      const deliveryAddress = {id: 123};
      jest.spyOn(deliveryAddressFormService, 'getDeliveryAddress').mockReturnValue(deliveryAddress);
      jest.spyOn(deliveryAddressService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({deliveryAddress});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: deliveryAddress}));
      saveSubject.complete();

      // THEN
      expect(deliveryAddressFormService.getDeliveryAddress).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(deliveryAddressService.update).toHaveBeenCalledWith(expect.objectContaining(deliveryAddress));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDeliveryAddress>>();
      const deliveryAddress = {id: 123};
      jest.spyOn(deliveryAddressFormService, 'getDeliveryAddress').mockReturnValue({id: null});
      jest.spyOn(deliveryAddressService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({deliveryAddress: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: deliveryAddress}));
      saveSubject.complete();

      // THEN
      expect(deliveryAddressFormService.getDeliveryAddress).toHaveBeenCalled();
      expect(deliveryAddressService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDeliveryAddress>>();
      const deliveryAddress = {id: 123};
      jest.spyOn(deliveryAddressService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({deliveryAddress});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(deliveryAddressService.update).toHaveBeenCalled();
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
