import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../food-ingredient.test-samples';

import {FoodIngredientFormService} from './food-ingredient-form.service';

describe('FoodIngredient Form Service', () => {
  let service: FoodIngredientFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoodIngredientFormService);
  });

  describe('Service methods', () => {
    describe('createFoodIngredientFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFoodIngredientFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            nameAr: expect.any(Object),
            nameEn: expect.any(Object),
            notes: expect.any(Object),
            food: expect.any(Object),
          })
        );
      });

      it('passing IFoodIngredient should create a new form with FormGroup', () => {
        const formGroup = service.createFoodIngredientFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            nameAr: expect.any(Object),
            nameEn: expect.any(Object),
            notes: expect.any(Object),
            food: expect.any(Object),
          })
        );
      });
    });

    describe('getFoodIngredient', () => {
      it('should return NewFoodIngredient for default FoodIngredient initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createFoodIngredientFormGroup(sampleWithNewData);

        const foodIngredient = service.getFoodIngredient(formGroup) as any;

        expect(foodIngredient).toMatchObject(sampleWithNewData);
      });

      it('should return NewFoodIngredient for empty FoodIngredient initial value', () => {
        const formGroup = service.createFoodIngredientFormGroup();

        const foodIngredient = service.getFoodIngredient(formGroup) as any;

        expect(foodIngredient).toMatchObject({});
      });

      it('should return IFoodIngredient', () => {
        const formGroup = service.createFoodIngredientFormGroup(sampleWithRequiredData);

        const foodIngredient = service.getFoodIngredient(formGroup) as any;

        expect(foodIngredient).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFoodIngredient should not enable id FormControl', () => {
        const formGroup = service.createFoodIngredientFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFoodIngredient should disable id FormControl', () => {
        const formGroup = service.createFoodIngredientFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
