import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../restaurant-distance-price.test-samples';

import {RestaurantDistancePriceFormService} from './restaurant-distance-price-form.service';

describe('RestaurantDistancePrice Form Service', () => {
  let service: RestaurantDistancePriceFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestaurantDistancePriceFormService);
  });

  describe('Service methods', () => {
    describe('createRestaurantDistancePriceFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRestaurantDistancePriceFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            price: expect.any(Object),
            fromKm: expect.any(Object),
            toKm: expect.any(Object),
            isAvailable: expect.any(Object),
            restaurant: expect.any(Object),
          })
        );
      });

      it('passing IRestaurantDistancePrice should create a new form with FormGroup', () => {
        const formGroup = service.createRestaurantDistancePriceFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            price: expect.any(Object),
            fromKm: expect.any(Object),
            toKm: expect.any(Object),
            isAvailable: expect.any(Object),
            restaurant: expect.any(Object),
          })
        );
      });
    });

    describe('getRestaurantDistancePrice', () => {
      it('should return NewRestaurantDistancePrice for default RestaurantDistancePrice initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createRestaurantDistancePriceFormGroup(sampleWithNewData);

        const restaurantDistancePrice = service.getRestaurantDistancePrice(formGroup) as any;

        expect(restaurantDistancePrice).toMatchObject(sampleWithNewData);
      });

      it('should return NewRestaurantDistancePrice for empty RestaurantDistancePrice initial value', () => {
        const formGroup = service.createRestaurantDistancePriceFormGroup();

        const restaurantDistancePrice = service.getRestaurantDistancePrice(formGroup) as any;

        expect(restaurantDistancePrice).toMatchObject({});
      });

      it('should return IRestaurantDistancePrice', () => {
        const formGroup = service.createRestaurantDistancePriceFormGroup(sampleWithRequiredData);

        const restaurantDistancePrice = service.getRestaurantDistancePrice(formGroup) as any;

        expect(restaurantDistancePrice).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRestaurantDistancePrice should not enable id FormControl', () => {
        const formGroup = service.createRestaurantDistancePriceFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRestaurantDistancePrice should disable id FormControl', () => {
        const formGroup = service.createRestaurantDistancePriceFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
