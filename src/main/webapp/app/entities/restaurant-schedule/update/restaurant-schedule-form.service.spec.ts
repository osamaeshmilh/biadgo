import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../restaurant-schedule.test-samples';

import {RestaurantScheduleFormService} from './restaurant-schedule-form.service';

describe('RestaurantSchedule Form Service', () => {
  let service: RestaurantScheduleFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestaurantScheduleFormService);
  });

  describe('Service methods', () => {
    describe('createRestaurantScheduleFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRestaurantScheduleFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dayOfWeek: expect.any(Object),
            openingTime: expect.any(Object),
            closingTime: expect.any(Object),
            restaurant: expect.any(Object),
          })
        );
      });

      it('passing IRestaurantSchedule should create a new form with FormGroup', () => {
        const formGroup = service.createRestaurantScheduleFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dayOfWeek: expect.any(Object),
            openingTime: expect.any(Object),
            closingTime: expect.any(Object),
            restaurant: expect.any(Object),
          })
        );
      });
    });

    describe('getRestaurantSchedule', () => {
      it('should return NewRestaurantSchedule for default RestaurantSchedule initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createRestaurantScheduleFormGroup(sampleWithNewData);

        const restaurantSchedule = service.getRestaurantSchedule(formGroup) as any;

        expect(restaurantSchedule).toMatchObject(sampleWithNewData);
      });

      it('should return NewRestaurantSchedule for empty RestaurantSchedule initial value', () => {
        const formGroup = service.createRestaurantScheduleFormGroup();

        const restaurantSchedule = service.getRestaurantSchedule(formGroup) as any;

        expect(restaurantSchedule).toMatchObject({});
      });

      it('should return IRestaurantSchedule', () => {
        const formGroup = service.createRestaurantScheduleFormGroup(sampleWithRequiredData);

        const restaurantSchedule = service.getRestaurantSchedule(formGroup) as any;

        expect(restaurantSchedule).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRestaurantSchedule should not enable id FormControl', () => {
        const formGroup = service.createRestaurantScheduleFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRestaurantSchedule should disable id FormControl', () => {
        const formGroup = service.createRestaurantScheduleFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
