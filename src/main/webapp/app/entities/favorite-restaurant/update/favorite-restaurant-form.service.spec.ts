import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../favorite-restaurant.test-samples';

import {FavoriteRestaurantFormService} from './favorite-restaurant-form.service';

describe('FavoriteRestaurant Form Service', () => {
  let service: FavoriteRestaurantFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FavoriteRestaurantFormService);
  });

  describe('Service methods', () => {
    describe('createFavoriteRestaurantFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFavoriteRestaurantFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            customer: expect.any(Object),
            restaurant: expect.any(Object),
          })
        );
      });

      it('passing IFavoriteRestaurant should create a new form with FormGroup', () => {
        const formGroup = service.createFavoriteRestaurantFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            customer: expect.any(Object),
            restaurant: expect.any(Object),
          })
        );
      });
    });

    describe('getFavoriteRestaurant', () => {
      it('should return NewFavoriteRestaurant for default FavoriteRestaurant initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createFavoriteRestaurantFormGroup(sampleWithNewData);

        const favoriteRestaurant = service.getFavoriteRestaurant(formGroup) as any;

        expect(favoriteRestaurant).toMatchObject(sampleWithNewData);
      });

      it('should return NewFavoriteRestaurant for empty FavoriteRestaurant initial value', () => {
        const formGroup = service.createFavoriteRestaurantFormGroup();

        const favoriteRestaurant = service.getFavoriteRestaurant(formGroup) as any;

        expect(favoriteRestaurant).toMatchObject({});
      });

      it('should return IFavoriteRestaurant', () => {
        const formGroup = service.createFavoriteRestaurantFormGroup(sampleWithRequiredData);

        const favoriteRestaurant = service.getFavoriteRestaurant(formGroup) as any;

        expect(favoriteRestaurant).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFavoriteRestaurant should not enable id FormControl', () => {
        const formGroup = service.createFavoriteRestaurantFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFavoriteRestaurant should disable id FormControl', () => {
        const formGroup = service.createFavoriteRestaurantFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
