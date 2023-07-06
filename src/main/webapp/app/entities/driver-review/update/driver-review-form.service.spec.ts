import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../driver-review.test-samples';

import {DriverReviewFormService} from './driver-review-form.service';

describe('DriverReview Form Service', () => {
  let service: DriverReviewFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DriverReviewFormService);
  });

  describe('Service methods', () => {
    describe('createDriverReviewFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDriverReviewFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            details: expect.any(Object),
            rate: expect.any(Object),
            customer: expect.any(Object),
            driver: expect.any(Object),
          })
        );
      });

      it('passing IDriverReview should create a new form with FormGroup', () => {
        const formGroup = service.createDriverReviewFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            details: expect.any(Object),
            rate: expect.any(Object),
            customer: expect.any(Object),
            driver: expect.any(Object),
          })
        );
      });
    });

    describe('getDriverReview', () => {
      it('should return NewDriverReview for default DriverReview initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDriverReviewFormGroup(sampleWithNewData);

        const driverReview = service.getDriverReview(formGroup) as any;

        expect(driverReview).toMatchObject(sampleWithNewData);
      });

      it('should return NewDriverReview for empty DriverReview initial value', () => {
        const formGroup = service.createDriverReviewFormGroup();

        const driverReview = service.getDriverReview(formGroup) as any;

        expect(driverReview).toMatchObject({});
      });

      it('should return IDriverReview', () => {
        const formGroup = service.createDriverReviewFormGroup(sampleWithRequiredData);

        const driverReview = service.getDriverReview(formGroup) as any;

        expect(driverReview).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDriverReview should not enable id FormControl', () => {
        const formGroup = service.createDriverReviewFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDriverReview should disable id FormControl', () => {
        const formGroup = service.createDriverReviewFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
