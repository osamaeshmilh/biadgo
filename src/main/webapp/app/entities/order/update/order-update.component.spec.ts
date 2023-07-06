import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {OrderFormService} from './order-form.service';
import {OrderService} from '../service/order.service';
import {IOrder} from '../order.model';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';
import {ICoupon} from 'app/entities/coupon/coupon.model';
import {CouponService} from 'app/entities/coupon/service/coupon.service';
import {IDriver} from 'app/entities/driver/driver.model';
import {DriverService} from 'app/entities/driver/service/driver.service';
import {IDeliveryAddress} from 'app/entities/delivery-address/delivery-address.model';
import {DeliveryAddressService} from 'app/entities/delivery-address/service/delivery-address.service';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';

import {OrderUpdateComponent} from './order-update.component';

describe('Order Management Update Component', () => {
  let comp: OrderUpdateComponent;
  let fixture: ComponentFixture<OrderUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let orderFormService: OrderFormService;
  let orderService: OrderService;
  let customerService: CustomerService;
  let couponService: CouponService;
  let driverService: DriverService;
  let deliveryAddressService: DeliveryAddressService;
  let restaurantService: RestaurantService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), OrderUpdateComponent],
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
      .overrideTemplate(OrderUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OrderUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    orderFormService = TestBed.inject(OrderFormService);
    orderService = TestBed.inject(OrderService);
    customerService = TestBed.inject(CustomerService);
    couponService = TestBed.inject(CouponService);
    driverService = TestBed.inject(DriverService);
    deliveryAddressService = TestBed.inject(DeliveryAddressService);
    restaurantService = TestBed.inject(RestaurantService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Customer query and add missing value', () => {
      const order: IOrder = {id: 456};
      const customer: ICustomer = {id: 22480};
      order.customer = customer;

      const customerCollection: ICustomer[] = [{id: 35023}];
      jest.spyOn(customerService, 'query').mockReturnValue(of(new HttpResponse({body: customerCollection})));
      const additionalCustomers = [customer];
      const expectedCollection: ICustomer[] = [...additionalCustomers, ...customerCollection];
      jest.spyOn(customerService, 'addCustomerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({order});
      comp.ngOnInit();

      expect(customerService.query).toHaveBeenCalled();
      expect(customerService.addCustomerToCollectionIfMissing).toHaveBeenCalledWith(
        customerCollection,
        ...additionalCustomers.map(expect.objectContaining)
      );
      expect(comp.customersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Coupon query and add missing value', () => {
      const order: IOrder = {id: 456};
      const coupon: ICoupon = {id: 47214};
      order.coupon = coupon;

      const couponCollection: ICoupon[] = [{id: 25635}];
      jest.spyOn(couponService, 'query').mockReturnValue(of(new HttpResponse({body: couponCollection})));
      const additionalCoupons = [coupon];
      const expectedCollection: ICoupon[] = [...additionalCoupons, ...couponCollection];
      jest.spyOn(couponService, 'addCouponToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({order});
      comp.ngOnInit();

      expect(couponService.query).toHaveBeenCalled();
      expect(couponService.addCouponToCollectionIfMissing).toHaveBeenCalledWith(
        couponCollection,
        ...additionalCoupons.map(expect.objectContaining)
      );
      expect(comp.couponsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Driver query and add missing value', () => {
      const order: IOrder = {id: 456};
      const driver: IDriver = {id: 70625};
      order.driver = driver;

      const driverCollection: IDriver[] = [{id: 22066}];
      jest.spyOn(driverService, 'query').mockReturnValue(of(new HttpResponse({body: driverCollection})));
      const additionalDrivers = [driver];
      const expectedCollection: IDriver[] = [...additionalDrivers, ...driverCollection];
      jest.spyOn(driverService, 'addDriverToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({order});
      comp.ngOnInit();

      expect(driverService.query).toHaveBeenCalled();
      expect(driverService.addDriverToCollectionIfMissing).toHaveBeenCalledWith(
        driverCollection,
        ...additionalDrivers.map(expect.objectContaining)
      );
      expect(comp.driversSharedCollection).toEqual(expectedCollection);
    });

    it('Should call DeliveryAddress query and add missing value', () => {
      const order: IOrder = {id: 456};
      const deliveryAddress: IDeliveryAddress = {id: 77955};
      order.deliveryAddress = deliveryAddress;

      const deliveryAddressCollection: IDeliveryAddress[] = [{id: 31623}];
      jest.spyOn(deliveryAddressService, 'query').mockReturnValue(of(new HttpResponse({body: deliveryAddressCollection})));
      const additionalDeliveryAddresses = [deliveryAddress];
      const expectedCollection: IDeliveryAddress[] = [...additionalDeliveryAddresses, ...deliveryAddressCollection];
      jest.spyOn(deliveryAddressService, 'addDeliveryAddressToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({order});
      comp.ngOnInit();

      expect(deliveryAddressService.query).toHaveBeenCalled();
      expect(deliveryAddressService.addDeliveryAddressToCollectionIfMissing).toHaveBeenCalledWith(
        deliveryAddressCollection,
        ...additionalDeliveryAddresses.map(expect.objectContaining)
      );
      expect(comp.deliveryAddressesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Restaurant query and add missing value', () => {
      const order: IOrder = {id: 456};
      const restaurant: IRestaurant = {id: 87421};
      order.restaurant = restaurant;

      const restaurantCollection: IRestaurant[] = [{id: 39290}];
      jest.spyOn(restaurantService, 'query').mockReturnValue(of(new HttpResponse({body: restaurantCollection})));
      const additionalRestaurants = [restaurant];
      const expectedCollection: IRestaurant[] = [...additionalRestaurants, ...restaurantCollection];
      jest.spyOn(restaurantService, 'addRestaurantToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({order});
      comp.ngOnInit();

      expect(restaurantService.query).toHaveBeenCalled();
      expect(restaurantService.addRestaurantToCollectionIfMissing).toHaveBeenCalledWith(
        restaurantCollection,
        ...additionalRestaurants.map(expect.objectContaining)
      );
      expect(comp.restaurantsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const order: IOrder = {id: 456};
      const customer: ICustomer = {id: 9495};
      order.customer = customer;
      const coupon: ICoupon = {id: 43148};
      order.coupon = coupon;
      const driver: IDriver = {id: 34944};
      order.driver = driver;
      const deliveryAddress: IDeliveryAddress = {id: 92871};
      order.deliveryAddress = deliveryAddress;
      const restaurant: IRestaurant = {id: 53868};
      order.restaurant = restaurant;

      activatedRoute.data = of({order});
      comp.ngOnInit();

      expect(comp.customersSharedCollection).toContain(customer);
      expect(comp.couponsSharedCollection).toContain(coupon);
      expect(comp.driversSharedCollection).toContain(driver);
      expect(comp.deliveryAddressesSharedCollection).toContain(deliveryAddress);
      expect(comp.restaurantsSharedCollection).toContain(restaurant);
      expect(comp.order).toEqual(order);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOrder>>();
      const order = {id: 123};
      jest.spyOn(orderFormService, 'getOrder').mockReturnValue(order);
      jest.spyOn(orderService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({order});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: order}));
      saveSubject.complete();

      // THEN
      expect(orderFormService.getOrder).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(orderService.update).toHaveBeenCalledWith(expect.objectContaining(order));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOrder>>();
      const order = {id: 123};
      jest.spyOn(orderFormService, 'getOrder').mockReturnValue({id: null});
      jest.spyOn(orderService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({order: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: order}));
      saveSubject.complete();

      // THEN
      expect(orderFormService.getOrder).toHaveBeenCalled();
      expect(orderService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IOrder>>();
      const order = {id: 123};
      jest.spyOn(orderService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({order});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(orderService.update).toHaveBeenCalled();
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

    describe('compareCoupon', () => {
      it('Should forward to couponService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(couponService, 'compareCoupon');
        comp.compareCoupon(entity, entity2);
        expect(couponService.compareCoupon).toHaveBeenCalledWith(entity, entity2);
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

    describe('compareDeliveryAddress', () => {
      it('Should forward to deliveryAddressService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(deliveryAddressService, 'compareDeliveryAddress');
        comp.compareDeliveryAddress(entity, entity2);
        expect(deliveryAddressService.compareDeliveryAddress).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareRestaurant', () => {
      it('Should forward to restaurantService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(restaurantService, 'compareRestaurant');
        comp.compareRestaurant(entity, entity2);
        expect(restaurantService.compareRestaurant).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
