import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../activation.test-samples';

import {ActivationFormService} from './activation-form.service';

describe('Activation Form Service', () => {
  let service: ActivationFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActivationFormService);
  });

  describe('Service methods', () => {
    describe('createActivationFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createActivationFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            mobileNo: expect.any(Object),
            email: expect.any(Object),
            code: expect.any(Object),
            sentOn: expect.any(Object),
            validUntil: expect.any(Object),
            isUsed: expect.any(Object),
          })
        );
      });

      it('passing IActivation should create a new form with FormGroup', () => {
        const formGroup = service.createActivationFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            mobileNo: expect.any(Object),
            email: expect.any(Object),
            code: expect.any(Object),
            sentOn: expect.any(Object),
            validUntil: expect.any(Object),
            isUsed: expect.any(Object),
          })
        );
      });
    });

    describe('getActivation', () => {
      it('should return NewActivation for default Activation initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createActivationFormGroup(sampleWithNewData);

        const activation = service.getActivation(formGroup) as any;

        expect(activation).toMatchObject(sampleWithNewData);
      });

      it('should return NewActivation for empty Activation initial value', () => {
        const formGroup = service.createActivationFormGroup();

        const activation = service.getActivation(formGroup) as any;

        expect(activation).toMatchObject({});
      });

      it('should return IActivation', () => {
        const formGroup = service.createActivationFormGroup(sampleWithRequiredData);

        const activation = service.getActivation(formGroup) as any;

        expect(activation).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IActivation should not enable id FormControl', () => {
        const formGroup = service.createActivationFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewActivation should disable id FormControl', () => {
        const formGroup = service.createActivationFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
