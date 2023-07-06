import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../restaurant-review.test-samples';

import {RestaurantReviewFormService} from './restaurant-review-form.service';

describe('RestaurantReview Form Service', () => {
  let service: RestaurantReviewFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestaurantReviewFormService);
  });

  describe('Service methods', () => {
    describe('createRestaurantReviewFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRestaurantReviewFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            review: expect.any(Object),
            rate: expect.any(Object),
            isEdited: expect.any(Object),
            customer: expect.any(Object),
            restaurant: expect.any(Object),
          })
        );
      });

      it('passing IRestaurantReview should create a new form with FormGroup', () => {
        const formGroup = service.createRestaurantReviewFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            review: expect.any(Object),
            rate: expect.any(Object),
            isEdited: expect.any(Object),
            customer: expect.any(Object),
            restaurant: expect.any(Object),
          })
        );
      });
    });

    describe('getRestaurantReview', () => {
      it('should return NewRestaurantReview for default RestaurantReview initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createRestaurantReviewFormGroup(sampleWithNewData);

        const restaurantReview = service.getRestaurantReview(formGroup) as any;

        expect(restaurantReview).toMatchObject(sampleWithNewData);
      });

      it('should return NewRestaurantReview for empty RestaurantReview initial value', () => {
        const formGroup = service.createRestaurantReviewFormGroup();

        const restaurantReview = service.getRestaurantReview(formGroup) as any;

        expect(restaurantReview).toMatchObject({});
      });

      it('should return IRestaurantReview', () => {
        const formGroup = service.createRestaurantReviewFormGroup(sampleWithRequiredData);

        const restaurantReview = service.getRestaurantReview(formGroup) as any;

        expect(restaurantReview).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRestaurantReview should not enable id FormControl', () => {
        const formGroup = service.createRestaurantReviewFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRestaurantReview should disable id FormControl', () => {
        const formGroup = service.createRestaurantReviewFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
