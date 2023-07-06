import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../slider.test-samples';

import {SliderFormService} from './slider-form.service';

describe('Slider Form Service', () => {
  let service: SliderFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SliderFormService);
  });

  describe('Service methods', () => {
    describe('createSliderFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSliderFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            details: expect.any(Object),
            detailsAr: expect.any(Object),
            detailsEn: expect.any(Object),
            menuOrder: expect.any(Object),
            imageUrl: expect.any(Object),
            image: expect.any(Object),
            url: expect.any(Object),
            restaurantId: expect.any(Object),
            categoryId: expect.any(Object),
            foodId: expect.any(Object),
            notes: expect.any(Object),
          })
        );
      });

      it('passing ISlider should create a new form with FormGroup', () => {
        const formGroup = service.createSliderFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            details: expect.any(Object),
            detailsAr: expect.any(Object),
            detailsEn: expect.any(Object),
            menuOrder: expect.any(Object),
            imageUrl: expect.any(Object),
            image: expect.any(Object),
            url: expect.any(Object),
            restaurantId: expect.any(Object),
            categoryId: expect.any(Object),
            foodId: expect.any(Object),
            notes: expect.any(Object),
          })
        );
      });
    });

    describe('getSlider', () => {
      it('should return NewSlider for default Slider initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSliderFormGroup(sampleWithNewData);

        const slider = service.getSlider(formGroup) as any;

        expect(slider).toMatchObject(sampleWithNewData);
      });

      it('should return NewSlider for empty Slider initial value', () => {
        const formGroup = service.createSliderFormGroup();

        const slider = service.getSlider(formGroup) as any;

        expect(slider).toMatchObject({});
      });

      it('should return ISlider', () => {
        const formGroup = service.createSliderFormGroup(sampleWithRequiredData);

        const slider = service.getSlider(formGroup) as any;

        expect(slider).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISlider should not enable id FormControl', () => {
        const formGroup = service.createSliderFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSlider should disable id FormControl', () => {
        const formGroup = service.createSliderFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
