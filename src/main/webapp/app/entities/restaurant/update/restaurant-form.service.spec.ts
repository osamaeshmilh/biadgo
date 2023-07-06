import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../restaurant.test-samples';

import {RestaurantFormService} from './restaurant-form.service';

describe('Restaurant Form Service', () => {
  let service: RestaurantFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestaurantFormService);
  });

  describe('Service methods', () => {
    describe('createRestaurantFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRestaurantFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            nameAr: expect.any(Object),
            nameEn: expect.any(Object),
            description: expect.any(Object),
            address: expect.any(Object),
            latitude: expect.any(Object),
            longitude: expect.any(Object),
            plusCode: expect.any(Object),
            mobile: expect.any(Object),
            estimitedTimeMins: expect.any(Object),
            deliveryFee: expect.any(Object),
            deliveryRange: expect.any(Object),
            availableForDelivery: expect.any(Object),
            availableForPickup: expect.any(Object),
            minimumOrderTotalForDelivery: expect.any(Object),
            deliveryPriceType: expect.any(Object),
            isOpen: expect.any(Object),
            isFeatured: expect.any(Object),
            isListedInOffers: expect.any(Object),
            isActive: expect.any(Object),
            offerBanner: expect.any(Object),
            priority: expect.any(Object),
            viewCounter: expect.any(Object),
            scheduledClosingTime: expect.any(Object),
            facebookPageUrl: expect.any(Object),
            notes: expect.any(Object),
            user: expect.any(Object),
            cuisine: expect.any(Object),
            categories: expect.any(Object),
          })
        );
      });

      it('passing IRestaurant should create a new form with FormGroup', () => {
        const formGroup = service.createRestaurantFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            nameAr: expect.any(Object),
            nameEn: expect.any(Object),
            description: expect.any(Object),
            address: expect.any(Object),
            latitude: expect.any(Object),
            longitude: expect.any(Object),
            plusCode: expect.any(Object),
            mobile: expect.any(Object),
            estimitedTimeMins: expect.any(Object),
            deliveryFee: expect.any(Object),
            deliveryRange: expect.any(Object),
            availableForDelivery: expect.any(Object),
            availableForPickup: expect.any(Object),
            minimumOrderTotalForDelivery: expect.any(Object),
            deliveryPriceType: expect.any(Object),
            isOpen: expect.any(Object),
            isFeatured: expect.any(Object),
            isListedInOffers: expect.any(Object),
            isActive: expect.any(Object),
            offerBanner: expect.any(Object),
            priority: expect.any(Object),
            viewCounter: expect.any(Object),
            scheduledClosingTime: expect.any(Object),
            facebookPageUrl: expect.any(Object),
            notes: expect.any(Object),
            user: expect.any(Object),
            cuisine: expect.any(Object),
            categories: expect.any(Object),
          })
        );
      });
    });

    describe('getRestaurant', () => {
      it('should return NewRestaurant for default Restaurant initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createRestaurantFormGroup(sampleWithNewData);

        const restaurant = service.getRestaurant(formGroup) as any;

        expect(restaurant).toMatchObject(sampleWithNewData);
      });

      it('should return NewRestaurant for empty Restaurant initial value', () => {
        const formGroup = service.createRestaurantFormGroup();

        const restaurant = service.getRestaurant(formGroup) as any;

        expect(restaurant).toMatchObject({});
      });

      it('should return IRestaurant', () => {
        const formGroup = service.createRestaurantFormGroup(sampleWithRequiredData);

        const restaurant = service.getRestaurant(formGroup) as any;

        expect(restaurant).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRestaurant should not enable id FormControl', () => {
        const formGroup = service.createRestaurantFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRestaurant should disable id FormControl', () => {
        const formGroup = service.createRestaurantFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
