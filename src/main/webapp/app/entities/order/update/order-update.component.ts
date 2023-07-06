import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {OrderFormService, OrderFormGroup} from './order-form.service';
import {IOrder} from '../order.model';
import {OrderService} from '../service/order.service';
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
import {PaymentType} from 'app/entities/enumerations/payment-type.model';
import {OrderStatus} from 'app/entities/enumerations/order-status.model';
import {OrderType} from 'app/entities/enumerations/order-type.model';

@Component({
  standalone: true,
  selector: 'jhi-order-update',
  templateUrl: './order-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OrderUpdateComponent implements OnInit {
  isSaving = false;
  order: IOrder | null = null;
  paymentTypeValues = Object.keys(PaymentType);
  orderStatusValues = Object.keys(OrderStatus);
  orderTypeValues = Object.keys(OrderType);

  customersSharedCollection: ICustomer[] = [];
  couponsSharedCollection: ICoupon[] = [];
  driversSharedCollection: IDriver[] = [];
  deliveryAddressesSharedCollection: IDeliveryAddress[] = [];
  restaurantsSharedCollection: IRestaurant[] = [];

  editForm: OrderFormGroup = this.orderFormService.createOrderFormGroup();

  constructor(
    protected orderService: OrderService,
    protected orderFormService: OrderFormService,
    protected customerService: CustomerService,
    protected couponService: CouponService,
    protected driverService: DriverService,
    protected deliveryAddressService: DeliveryAddressService,
    protected restaurantService: RestaurantService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareCustomer = (o1: ICustomer | null, o2: ICustomer | null): boolean => this.customerService.compareCustomer(o1, o2);

  compareCoupon = (o1: ICoupon | null, o2: ICoupon | null): boolean => this.couponService.compareCoupon(o1, o2);

  compareDriver = (o1: IDriver | null, o2: IDriver | null): boolean => this.driverService.compareDriver(o1, o2);

  compareDeliveryAddress = (o1: IDeliveryAddress | null, o2: IDeliveryAddress | null): boolean =>
    this.deliveryAddressService.compareDeliveryAddress(o1, o2);

  compareRestaurant = (o1: IRestaurant | null, o2: IRestaurant | null): boolean => this.restaurantService.compareRestaurant(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({order}) => {
      this.order = order;
      if (order) {
        this.updateForm(order);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const order = this.orderFormService.getOrder(this.editForm);
    if (order.id !== null) {
      this.subscribeToSaveResponse(this.orderService.update(order));
    } else {
      this.subscribeToSaveResponse(this.orderService.create(order));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrder>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(order: IOrder): void {
    this.order = order;
    this.orderFormService.resetForm(this.editForm, order);

    this.customersSharedCollection = this.customerService.addCustomerToCollectionIfMissing<ICustomer>(
      this.customersSharedCollection,
      order.customer
    );
    this.couponsSharedCollection = this.couponService.addCouponToCollectionIfMissing<ICoupon>(this.couponsSharedCollection, order.coupon);
    this.driversSharedCollection = this.driverService.addDriverToCollectionIfMissing<IDriver>(this.driversSharedCollection, order.driver);
    this.deliveryAddressesSharedCollection = this.deliveryAddressService.addDeliveryAddressToCollectionIfMissing<IDeliveryAddress>(
      this.deliveryAddressesSharedCollection,
      order.deliveryAddress
    );
    this.restaurantsSharedCollection = this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(
      this.restaurantsSharedCollection,
      order.restaurant
    );
  }

  protected loadRelationshipsOptions(): void {
    this.customerService
      .query()
      .pipe(map((res: HttpResponse<ICustomer[]>) => res.body ?? []))
      .pipe(
        map((customers: ICustomer[]) => this.customerService.addCustomerToCollectionIfMissing<ICustomer>(customers, this.order?.customer))
      )
      .subscribe((customers: ICustomer[]) => (this.customersSharedCollection = customers));

    this.couponService
      .query()
      .pipe(map((res: HttpResponse<ICoupon[]>) => res.body ?? []))
      .pipe(map((coupons: ICoupon[]) => this.couponService.addCouponToCollectionIfMissing<ICoupon>(coupons, this.order?.coupon)))
      .subscribe((coupons: ICoupon[]) => (this.couponsSharedCollection = coupons));

    this.driverService
      .query()
      .pipe(map((res: HttpResponse<IDriver[]>) => res.body ?? []))
      .pipe(map((drivers: IDriver[]) => this.driverService.addDriverToCollectionIfMissing<IDriver>(drivers, this.order?.driver)))
      .subscribe((drivers: IDriver[]) => (this.driversSharedCollection = drivers));

    this.deliveryAddressService
      .query()
      .pipe(map((res: HttpResponse<IDeliveryAddress[]>) => res.body ?? []))
      .pipe(
        map((deliveryAddresses: IDeliveryAddress[]) =>
          this.deliveryAddressService.addDeliveryAddressToCollectionIfMissing<IDeliveryAddress>(
            deliveryAddresses,
            this.order?.deliveryAddress
          )
        )
      )
      .subscribe((deliveryAddresses: IDeliveryAddress[]) => (this.deliveryAddressesSharedCollection = deliveryAddresses));

    this.restaurantService
      .query()
      .pipe(map((res: HttpResponse<IRestaurant[]>) => res.body ?? []))
      .pipe(
        map((restaurants: IRestaurant[]) =>
          this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(restaurants, this.order?.restaurant)
        )
      )
      .subscribe((restaurants: IRestaurant[]) => (this.restaurantsSharedCollection = restaurants));
  }
}
