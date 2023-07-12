import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../restaurant-image.test-samples';

import {RestaurantImageFormService} from './restaurant-image-form.service';

describe('RestaurantImage Form Service', () => {
  let service: RestaurantImageFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestaurantImageFormService);
  });

  describe('Service methods', () => {
    describe('createRestaurantImageFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRestaurantImageFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            description: expect.any(Object),
            descriptionAr: expect.any(Object),
            descriptionEn: expect.any(Object),
            imageType: expect.any(Object),
            menuOrder: expect.any(Object),
            imageUrl: expect.any(Object),
            image: expect.any(Object),
            restaurant: expect.any(Object),
          })
        );
      });

      it('passing IRestaurantImage should create a new form with FormGroup', () => {
        const formGroup = service.createRestaurantImageFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            description: expect.any(Object),
            descriptionAr: expect.any(Object),
            descriptionEn: expect.any(Object),
            imageType: expect.any(Object),
            menuOrder: expect.any(Object),
            imageUrl: expect.any(Object),
            image: expect.any(Object),
            restaurant: expect.any(Object),
          })
        );
      });
    });

    describe('getRestaurantImage', () => {
      it('should return NewRestaurantImage for default RestaurantImage initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createRestaurantImageFormGroup(sampleWithNewData);

        const restaurantImage = service.getRestaurantImage(formGroup) as any;

        expect(restaurantImage).toMatchObject(sampleWithNewData);
      });

      it('should return NewRestaurantImage for empty RestaurantImage initial value', () => {
        const formGroup = service.createRestaurantImageFormGroup();

        const restaurantImage = service.getRestaurantImage(formGroup) as any;

        expect(restaurantImage).toMatchObject({});
      });

      it('should return IRestaurantImage', () => {
        const formGroup = service.createRestaurantImageFormGroup(sampleWithRequiredData);

        const restaurantImage = service.getRestaurantImage(formGroup) as any;

        expect(restaurantImage).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRestaurantImage should not enable id FormControl', () => {
        const formGroup = service.createRestaurantImageFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRestaurantImage should disable id FormControl', () => {
        const formGroup = service.createRestaurantImageFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
