import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../restaurant-wallet.test-samples';

import {RestaurantWalletFormService} from './restaurant-wallet-form.service';

describe('RestaurantWallet Form Service', () => {
  let service: RestaurantWalletFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestaurantWalletFormService);
  });

  describe('Service methods', () => {
    describe('createRestaurantWalletFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRestaurantWalletFormGroup();

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
            notes: expect.any(Object),
            restaurant: expect.any(Object),
          })
        );
      });

      it('passing IRestaurantWallet should create a new form with FormGroup', () => {
        const formGroup = service.createRestaurantWalletFormGroup(sampleWithRequiredData);

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
            notes: expect.any(Object),
            restaurant: expect.any(Object),
          })
        );
      });
    });

    describe('getRestaurantWallet', () => {
      it('should return NewRestaurantWallet for default RestaurantWallet initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createRestaurantWalletFormGroup(sampleWithNewData);

        const restaurantWallet = service.getRestaurantWallet(formGroup) as any;

        expect(restaurantWallet).toMatchObject(sampleWithNewData);
      });

      it('should return NewRestaurantWallet for empty RestaurantWallet initial value', () => {
        const formGroup = service.createRestaurantWalletFormGroup();

        const restaurantWallet = service.getRestaurantWallet(formGroup) as any;

        expect(restaurantWallet).toMatchObject({});
      });

      it('should return IRestaurantWallet', () => {
        const formGroup = service.createRestaurantWalletFormGroup(sampleWithRequiredData);

        const restaurantWallet = service.getRestaurantWallet(formGroup) as any;

        expect(restaurantWallet).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRestaurantWallet should not enable id FormControl', () => {
        const formGroup = service.createRestaurantWalletFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRestaurantWallet should disable id FormControl', () => {
        const formGroup = service.createRestaurantWalletFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
