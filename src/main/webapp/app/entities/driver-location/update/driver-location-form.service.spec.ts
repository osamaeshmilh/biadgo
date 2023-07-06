import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../driver-location.test-samples';

import {DriverLocationFormService} from './driver-location-form.service';

describe('DriverLocation Form Service', () => {
  let service: DriverLocationFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DriverLocationFormService);
  });

  describe('Service methods', () => {
    describe('createDriverLocationFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDriverLocationFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            latitude: expect.any(Object),
            longitude: expect.any(Object),
            plusCode: expect.any(Object),
            locationDateTime: expect.any(Object),
            driver: expect.any(Object),
          })
        );
      });

      it('passing IDriverLocation should create a new form with FormGroup', () => {
        const formGroup = service.createDriverLocationFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            latitude: expect.any(Object),
            longitude: expect.any(Object),
            plusCode: expect.any(Object),
            locationDateTime: expect.any(Object),
            driver: expect.any(Object),
          })
        );
      });
    });

    describe('getDriverLocation', () => {
      it('should return NewDriverLocation for default DriverLocation initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDriverLocationFormGroup(sampleWithNewData);

        const driverLocation = service.getDriverLocation(formGroup) as any;

        expect(driverLocation).toMatchObject(sampleWithNewData);
      });

      it('should return NewDriverLocation for empty DriverLocation initial value', () => {
        const formGroup = service.createDriverLocationFormGroup();

        const driverLocation = service.getDriverLocation(formGroup) as any;

        expect(driverLocation).toMatchObject({});
      });

      it('should return IDriverLocation', () => {
        const formGroup = service.createDriverLocationFormGroup(sampleWithRequiredData);

        const driverLocation = service.getDriverLocation(formGroup) as any;

        expect(driverLocation).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDriverLocation should not enable id FormControl', () => {
        const formGroup = service.createDriverLocationFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDriverLocation should disable id FormControl', () => {
        const formGroup = service.createDriverLocationFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
