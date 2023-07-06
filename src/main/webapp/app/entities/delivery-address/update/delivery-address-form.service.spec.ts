import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../delivery-address.test-samples';

import {DeliveryAddressFormService} from './delivery-address-form.service';

describe('DeliveryAddress Form Service', () => {
  let service: DeliveryAddressFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeliveryAddressFormService);
  });

  describe('Service methods', () => {
    describe('createDeliveryAddressFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDeliveryAddressFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            title: expect.any(Object),
            address: expect.any(Object),
            details: expect.any(Object),
            phone: expect.any(Object),
            isDefault: expect.any(Object),
            latitude: expect.any(Object),
            longitude: expect.any(Object),
            plusCode: expect.any(Object),
            isActive: expect.any(Object),
            notes: expect.any(Object),
            customer: expect.any(Object),
            zone: expect.any(Object),
          })
        );
      });

      it('passing IDeliveryAddress should create a new form with FormGroup', () => {
        const formGroup = service.createDeliveryAddressFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            title: expect.any(Object),
            address: expect.any(Object),
            details: expect.any(Object),
            phone: expect.any(Object),
            isDefault: expect.any(Object),
            latitude: expect.any(Object),
            longitude: expect.any(Object),
            plusCode: expect.any(Object),
            isActive: expect.any(Object),
            notes: expect.any(Object),
            customer: expect.any(Object),
            zone: expect.any(Object),
          })
        );
      });
    });

    describe('getDeliveryAddress', () => {
      it('should return NewDeliveryAddress for default DeliveryAddress initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDeliveryAddressFormGroup(sampleWithNewData);

        const deliveryAddress = service.getDeliveryAddress(formGroup) as any;

        expect(deliveryAddress).toMatchObject(sampleWithNewData);
      });

      it('should return NewDeliveryAddress for empty DeliveryAddress initial value', () => {
        const formGroup = service.createDeliveryAddressFormGroup();

        const deliveryAddress = service.getDeliveryAddress(formGroup) as any;

        expect(deliveryAddress).toMatchObject({});
      });

      it('should return IDeliveryAddress', () => {
        const formGroup = service.createDeliveryAddressFormGroup(sampleWithRequiredData);

        const deliveryAddress = service.getDeliveryAddress(formGroup) as any;

        expect(deliveryAddress).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDeliveryAddress should not enable id FormControl', () => {
        const formGroup = service.createDeliveryAddressFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDeliveryAddress should disable id FormControl', () => {
        const formGroup = service.createDeliveryAddressFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
