import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../customer-wallet.test-samples';

import {CustomerWalletFormService} from './customer-wallet-form.service';

describe('CustomerWallet Form Service', () => {
  let service: CustomerWalletFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomerWalletFormService);
  });

  describe('Service methods', () => {
    describe('createCustomerWalletFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCustomerWalletFormGroup();

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
            customer: expect.any(Object),
          })
        );
      });

      it('passing ICustomerWallet should create a new form with FormGroup', () => {
        const formGroup = service.createCustomerWalletFormGroup(sampleWithRequiredData);

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
            customer: expect.any(Object),
          })
        );
      });
    });

    describe('getCustomerWallet', () => {
      it('should return NewCustomerWallet for default CustomerWallet initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCustomerWalletFormGroup(sampleWithNewData);

        const customerWallet = service.getCustomerWallet(formGroup) as any;

        expect(customerWallet).toMatchObject(sampleWithNewData);
      });

      it('should return NewCustomerWallet for empty CustomerWallet initial value', () => {
        const formGroup = service.createCustomerWalletFormGroup();

        const customerWallet = service.getCustomerWallet(formGroup) as any;

        expect(customerWallet).toMatchObject({});
      });

      it('should return ICustomerWallet', () => {
        const formGroup = service.createCustomerWalletFormGroup(sampleWithRequiredData);

        const customerWallet = service.getCustomerWallet(formGroup) as any;

        expect(customerWallet).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICustomerWallet should not enable id FormControl', () => {
        const formGroup = service.createCustomerWalletFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCustomerWallet should disable id FormControl', () => {
        const formGroup = service.createCustomerWalletFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
