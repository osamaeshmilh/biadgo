import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../driver-wallet.test-samples';

import {DriverWalletFormService} from './driver-wallet-form.service';

describe('DriverWallet Form Service', () => {
  let service: DriverWalletFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DriverWalletFormService);
  });

  describe('Service methods', () => {
    describe('createDriverWalletFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDriverWalletFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            transactionNo: expect.any(Object),
            amount: expect.any(Object),
            walletAction: expect.any(Object),
            totalBeforeAction: expect.any(Object),
            totalAfterAction: expect.any(Object),
            paymentType: expect.any(Object),
            paymentReference: expect.any(Object),
            orderId: expect.any(Object),
            notes: expect.any(Object),
            driver: expect.any(Object),
          })
        );
      });

      it('passing IDriverWallet should create a new form with FormGroup', () => {
        const formGroup = service.createDriverWalletFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            transactionNo: expect.any(Object),
            amount: expect.any(Object),
            walletAction: expect.any(Object),
            totalBeforeAction: expect.any(Object),
            totalAfterAction: expect.any(Object),
            paymentType: expect.any(Object),
            paymentReference: expect.any(Object),
            orderId: expect.any(Object),
            notes: expect.any(Object),
            driver: expect.any(Object),
          })
        );
      });
    });

    describe('getDriverWallet', () => {
      it('should return NewDriverWallet for default DriverWallet initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDriverWalletFormGroup(sampleWithNewData);

        const driverWallet = service.getDriverWallet(formGroup) as any;

        expect(driverWallet).toMatchObject(sampleWithNewData);
      });

      it('should return NewDriverWallet for empty DriverWallet initial value', () => {
        const formGroup = service.createDriverWalletFormGroup();

        const driverWallet = service.getDriverWallet(formGroup) as any;

        expect(driverWallet).toMatchObject({});
      });

      it('should return IDriverWallet', () => {
        const formGroup = service.createDriverWalletFormGroup(sampleWithRequiredData);

        const driverWallet = service.getDriverWallet(formGroup) as any;

        expect(driverWallet).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDriverWallet should not enable id FormControl', () => {
        const formGroup = service.createDriverWalletFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDriverWallet should disable id FormControl', () => {
        const formGroup = service.createDriverWalletFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
