import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../food-order.test-samples';

import {FoodOrderFormService} from './food-order-form.service';

describe('FoodOrder Form Service', () => {
  let service: FoodOrderFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoodOrderFormService);
  });

  describe('Service methods', () => {
    describe('createFoodOrderFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFoodOrderFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            price: expect.any(Object),
            quantity: expect.any(Object),
            total: expect.any(Object),
            specialNotes: expect.any(Object),
            foodExtraIdsList: expect.any(Object),
            foodIngredientIds: expect.any(Object),
            foodIngredientRemovedIds: expect.any(Object),
            order: expect.any(Object),
            food: expect.any(Object),
          })
        );
      });

      it('passing IFoodOrder should create a new form with FormGroup', () => {
        const formGroup = service.createFoodOrderFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            price: expect.any(Object),
            quantity: expect.any(Object),
            total: expect.any(Object),
            specialNotes: expect.any(Object),
            foodExtraIdsList: expect.any(Object),
            foodIngredientIds: expect.any(Object),
            foodIngredientRemovedIds: expect.any(Object),
            order: expect.any(Object),
            food: expect.any(Object),
          })
        );
      });
    });

    describe('getFoodOrder', () => {
      it('should return NewFoodOrder for default FoodOrder initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createFoodOrderFormGroup(sampleWithNewData);

        const foodOrder = service.getFoodOrder(formGroup) as any;

        expect(foodOrder).toMatchObject(sampleWithNewData);
      });

      it('should return NewFoodOrder for empty FoodOrder initial value', () => {
        const formGroup = service.createFoodOrderFormGroup();

        const foodOrder = service.getFoodOrder(formGroup) as any;

        expect(foodOrder).toMatchObject({});
      });

      it('should return IFoodOrder', () => {
        const formGroup = service.createFoodOrderFormGroup(sampleWithRequiredData);

        const foodOrder = service.getFoodOrder(formGroup) as any;

        expect(foodOrder).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFoodOrder should not enable id FormControl', () => {
        const formGroup = service.createFoodOrderFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFoodOrder should disable id FormControl', () => {
        const formGroup = service.createFoodOrderFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
