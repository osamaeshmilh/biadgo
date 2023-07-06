import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../food-image.test-samples';

import {FoodImageFormService} from './food-image-form.service';

describe('FoodImage Form Service', () => {
  let service: FoodImageFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoodImageFormService);
  });

  describe('Service methods', () => {
    describe('createFoodImageFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFoodImageFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            description: expect.any(Object),
            descriptionAr: expect.any(Object),
            descriptionEn: expect.any(Object),
            menuOrder: expect.any(Object),
            imageUrl: expect.any(Object),
            image: expect.any(Object),
            food: expect.any(Object),
          })
        );
      });

      it('passing IFoodImage should create a new form with FormGroup', () => {
        const formGroup = service.createFoodImageFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            description: expect.any(Object),
            descriptionAr: expect.any(Object),
            descriptionEn: expect.any(Object),
            menuOrder: expect.any(Object),
            imageUrl: expect.any(Object),
            image: expect.any(Object),
            food: expect.any(Object),
          })
        );
      });
    });

    describe('getFoodImage', () => {
      it('should return NewFoodImage for default FoodImage initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createFoodImageFormGroup(sampleWithNewData);

        const foodImage = service.getFoodImage(formGroup) as any;

        expect(foodImage).toMatchObject(sampleWithNewData);
      });

      it('should return NewFoodImage for empty FoodImage initial value', () => {
        const formGroup = service.createFoodImageFormGroup();

        const foodImage = service.getFoodImage(formGroup) as any;

        expect(foodImage).toMatchObject({});
      });

      it('should return IFoodImage', () => {
        const formGroup = service.createFoodImageFormGroup(sampleWithRequiredData);

        const foodImage = service.getFoodImage(formGroup) as any;

        expect(foodImage).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFoodImage should not enable id FormControl', () => {
        const formGroup = service.createFoodImageFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFoodImage should disable id FormControl', () => {
        const formGroup = service.createFoodImageFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
