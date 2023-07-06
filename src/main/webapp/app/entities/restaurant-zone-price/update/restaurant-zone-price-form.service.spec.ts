import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../restaurant-zone-price.test-samples';

import {RestaurantZonePriceFormService} from './restaurant-zone-price-form.service';

describe('RestaurantZonePrice Form Service', () => {
  let service: RestaurantZonePriceFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestaurantZonePriceFormService);
  });

  describe('Service methods', () => {
    describe('createRestaurantZonePriceFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRestaurantZonePriceFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            price: expect.any(Object),
            isAvailable: expect.any(Object),
            restaurant: expect.any(Object),
            zone: expect.any(Object),
          })
        );
      });

      it('passing IRestaurantZonePrice should create a new form with FormGroup', () => {
        const formGroup = service.createRestaurantZonePriceFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            price: expect.any(Object),
            isAvailable: expect.any(Object),
            restaurant: expect.any(Object),
            zone: expect.any(Object),
          })
        );
      });
    });

    describe('getRestaurantZonePrice', () => {
      it('should return NewRestaurantZonePrice for default RestaurantZonePrice initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createRestaurantZonePriceFormGroup(sampleWithNewData);

        const restaurantZonePrice = service.getRestaurantZonePrice(formGroup) as any;

        expect(restaurantZonePrice).toMatchObject(sampleWithNewData);
      });

      it('should return NewRestaurantZonePrice for empty RestaurantZonePrice initial value', () => {
        const formGroup = service.createRestaurantZonePriceFormGroup();

        const restaurantZonePrice = service.getRestaurantZonePrice(formGroup) as any;

        expect(restaurantZonePrice).toMatchObject({});
      });

      it('should return IRestaurantZonePrice', () => {
        const formGroup = service.createRestaurantZonePriceFormGroup(sampleWithRequiredData);

        const restaurantZonePrice = service.getRestaurantZonePrice(formGroup) as any;

        expect(restaurantZonePrice).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRestaurantZonePrice should not enable id FormControl', () => {
        const formGroup = service.createRestaurantZonePriceFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRestaurantZonePrice should disable id FormControl', () => {
        const formGroup = service.createRestaurantZonePriceFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
