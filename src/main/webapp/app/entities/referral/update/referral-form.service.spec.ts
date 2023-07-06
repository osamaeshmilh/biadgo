import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../referral.test-samples';

import {ReferralFormService} from './referral-form.service';

describe('Referral Form Service', () => {
  let service: ReferralFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReferralFormService);
  });

  describe('Service methods', () => {
    describe('createReferralFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createReferralFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            referralCode: expect.any(Object),
            referrerAmount: expect.any(Object),
            referredCustomerAmount: expect.any(Object),
            expiryDate: expect.any(Object),
            isUsed: expect.any(Object),
            usedDateTime: expect.any(Object),
            notes: expect.any(Object),
            referredCustomer: expect.any(Object),
            referrerCustomer: expect.any(Object),
          })
        );
      });

      it('passing IReferral should create a new form with FormGroup', () => {
        const formGroup = service.createReferralFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            referralCode: expect.any(Object),
            referrerAmount: expect.any(Object),
            referredCustomerAmount: expect.any(Object),
            expiryDate: expect.any(Object),
            isUsed: expect.any(Object),
            usedDateTime: expect.any(Object),
            notes: expect.any(Object),
            referredCustomer: expect.any(Object),
            referrerCustomer: expect.any(Object),
          })
        );
      });
    });

    describe('getReferral', () => {
      it('should return NewReferral for default Referral initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createReferralFormGroup(sampleWithNewData);

        const referral = service.getReferral(formGroup) as any;

        expect(referral).toMatchObject(sampleWithNewData);
      });

      it('should return NewReferral for empty Referral initial value', () => {
        const formGroup = service.createReferralFormGroup();

        const referral = service.getReferral(formGroup) as any;

        expect(referral).toMatchObject({});
      });

      it('should return IReferral', () => {
        const formGroup = service.createReferralFormGroup(sampleWithRequiredData);

        const referral = service.getReferral(formGroup) as any;

        expect(referral).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IReferral should not enable id FormControl', () => {
        const formGroup = service.createReferralFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewReferral should disable id FormControl', () => {
        const formGroup = service.createReferralFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
