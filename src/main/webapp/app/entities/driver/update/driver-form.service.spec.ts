import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../driver.test-samples';

import {DriverFormService} from './driver-form.service';

describe('Driver Form Service', () => {
  let service: DriverFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DriverFormService);
  });

  describe('Service methods', () => {
    describe('createDriverFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDriverFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            nameAr: expect.any(Object),
            nameEn: expect.any(Object),
            mobileNo: expect.any(Object),
            email: expect.any(Object),
            imageUrl: expect.any(Object),
            image: expect.any(Object),
            driverType: expect.any(Object),
            driverPaymentType: expect.any(Object),
            vehicleType: expect.any(Object),
            driverStatus: expect.any(Object),
            commissionAmount: expect.any(Object),
            salaryAmount: expect.any(Object),
            latitude: expect.any(Object),
            longitude: expect.any(Object),
            plusCode: expect.any(Object),
            lastLocationDateTime: expect.any(Object),
            isAvailable: expect.any(Object),
            notes: expect.any(Object),
            user: expect.any(Object),
            zone: expect.any(Object),
          })
        );
      });

      it('passing IDriver should create a new form with FormGroup', () => {
        const formGroup = service.createDriverFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            nameAr: expect.any(Object),
            nameEn: expect.any(Object),
            mobileNo: expect.any(Object),
            email: expect.any(Object),
            imageUrl: expect.any(Object),
            image: expect.any(Object),
            driverType: expect.any(Object),
            driverPaymentType: expect.any(Object),
            vehicleType: expect.any(Object),
            driverStatus: expect.any(Object),
            commissionAmount: expect.any(Object),
            salaryAmount: expect.any(Object),
            latitude: expect.any(Object),
            longitude: expect.any(Object),
            plusCode: expect.any(Object),
            lastLocationDateTime: expect.any(Object),
            isAvailable: expect.any(Object),
            notes: expect.any(Object),
            user: expect.any(Object),
            zone: expect.any(Object),
          })
        );
      });
    });

    describe('getDriver', () => {
      it('should return NewDriver for default Driver initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDriverFormGroup(sampleWithNewData);

        const driver = service.getDriver(formGroup) as any;

        expect(driver).toMatchObject(sampleWithNewData);
      });

      it('should return NewDriver for empty Driver initial value', () => {
        const formGroup = service.createDriverFormGroup();

        const driver = service.getDriver(formGroup) as any;

        expect(driver).toMatchObject({});
      });

      it('should return IDriver', () => {
        const formGroup = service.createDriverFormGroup(sampleWithRequiredData);

        const driver = service.getDriver(formGroup) as any;

        expect(driver).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDriver should not enable id FormControl', () => {
        const formGroup = service.createDriverFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDriver should disable id FormControl', () => {
        const formGroup = service.createDriverFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
