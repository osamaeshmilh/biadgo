import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../food-extra.test-samples';

import {FoodExtraFormService} from './food-extra-form.service';

describe('FoodExtra Form Service', () => {
  let service: FoodExtraFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoodExtraFormService);
  });

  describe('Service methods', () => {
    describe('createFoodExtraFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFoodExtraFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            nameAr: expect.any(Object),
            nameEn: expect.any(Object),
            price: expect.any(Object),
            notes: expect.any(Object),
            food: expect.any(Object),
          })
        );
      });

      it('passing IFoodExtra should create a new form with FormGroup', () => {
        const formGroup = service.createFoodExtraFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            nameAr: expect.any(Object),
            nameEn: expect.any(Object),
            price: expect.any(Object),
            notes: expect.any(Object),
            food: expect.any(Object),
          })
        );
      });
    });

    describe('getFoodExtra', () => {
      it('should return NewFoodExtra for default FoodExtra initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createFoodExtraFormGroup(sampleWithNewData);

        const foodExtra = service.getFoodExtra(formGroup) as any;

        expect(foodExtra).toMatchObject(sampleWithNewData);
      });

      it('should return NewFoodExtra for empty FoodExtra initial value', () => {
        const formGroup = service.createFoodExtraFormGroup();

        const foodExtra = service.getFoodExtra(formGroup) as any;

        expect(foodExtra).toMatchObject({});
      });

      it('should return IFoodExtra', () => {
        const formGroup = service.createFoodExtraFormGroup(sampleWithRequiredData);

        const foodExtra = service.getFoodExtra(formGroup) as any;

        expect(foodExtra).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFoodExtra should not enable id FormControl', () => {
        const formGroup = service.createFoodExtraFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFoodExtra should disable id FormControl', () => {
        const formGroup = service.createFoodExtraFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
