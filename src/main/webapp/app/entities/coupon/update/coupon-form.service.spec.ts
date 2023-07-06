import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../coupon.test-samples';

import {CouponFormService} from './coupon-form.service';

describe('Coupon Form Service', () => {
  let service: CouponFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CouponFormService);
  });

  describe('Service methods', () => {
    describe('createCouponFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCouponFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            couponType: expect.any(Object),
            amount: expect.any(Object),
            minimumAmount: expect.any(Object),
            useLimit: expect.any(Object),
            useCount: expect.any(Object),
            expiryDate: expect.any(Object),
            isActive: expect.any(Object),
            notes: expect.any(Object),
          })
        );
      });

      it('passing ICoupon should create a new form with FormGroup', () => {
        const formGroup = service.createCouponFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            couponType: expect.any(Object),
            amount: expect.any(Object),
            minimumAmount: expect.any(Object),
            useLimit: expect.any(Object),
            useCount: expect.any(Object),
            expiryDate: expect.any(Object),
            isActive: expect.any(Object),
            notes: expect.any(Object),
          })
        );
      });
    });

    describe('getCoupon', () => {
      it('should return NewCoupon for default Coupon initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCouponFormGroup(sampleWithNewData);

        const coupon = service.getCoupon(formGroup) as any;

        expect(coupon).toMatchObject(sampleWithNewData);
      });

      it('should return NewCoupon for empty Coupon initial value', () => {
        const formGroup = service.createCouponFormGroup();

        const coupon = service.getCoupon(formGroup) as any;

        expect(coupon).toMatchObject({});
      });

      it('should return ICoupon', () => {
        const formGroup = service.createCouponFormGroup(sampleWithRequiredData);

        const coupon = service.getCoupon(formGroup) as any;

        expect(coupon).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICoupon should not enable id FormControl', () => {
        const formGroup = service.createCouponFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCoupon should disable id FormControl', () => {
        const formGroup = service.createCouponFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
