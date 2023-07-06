import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {FoodImageFormService} from './food-image-form.service';
import {FoodImageService} from '../service/food-image.service';
import {IFoodImage} from '../food-image.model';
import {IFood} from 'app/entities/food/food.model';
import {FoodService} from 'app/entities/food/service/food.service';

import {FoodImageUpdateComponent} from './food-image-update.component';

describe('FoodImage Management Update Component', () => {
  let comp: FoodImageUpdateComponent;
  let fixture: ComponentFixture<FoodImageUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let foodImageFormService: FoodImageFormService;
  let foodImageService: FoodImageService;
  let foodService: FoodService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FoodImageUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(FoodImageUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FoodImageUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    foodImageFormService = TestBed.inject(FoodImageFormService);
    foodImageService = TestBed.inject(FoodImageService);
    foodService = TestBed.inject(FoodService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Food query and add missing value', () => {
      const foodImage: IFoodImage = {id: 456};
      const food: IFood = {id: 22025};
      foodImage.food = food;

      const foodCollection: IFood[] = [{id: 66572}];
      jest.spyOn(foodService, 'query').mockReturnValue(of(new HttpResponse({body: foodCollection})));
      const additionalFoods = [food];
      const expectedCollection: IFood[] = [...additionalFoods, ...foodCollection];
      jest.spyOn(foodService, 'addFoodToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({foodImage});
      comp.ngOnInit();

      expect(foodService.query).toHaveBeenCalled();
      expect(foodService.addFoodToCollectionIfMissing).toHaveBeenCalledWith(
        foodCollection,
        ...additionalFoods.map(expect.objectContaining)
      );
      expect(comp.foodsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const foodImage: IFoodImage = {id: 456};
      const food: IFood = {id: 59881};
      foodImage.food = food;

      activatedRoute.data = of({foodImage});
      comp.ngOnInit();

      expect(comp.foodsSharedCollection).toContain(food);
      expect(comp.foodImage).toEqual(foodImage);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoodImage>>();
      const foodImage = {id: 123};
      jest.spyOn(foodImageFormService, 'getFoodImage').mockReturnValue(foodImage);
      jest.spyOn(foodImageService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({foodImage});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: foodImage}));
      saveSubject.complete();

      // THEN
      expect(foodImageFormService.getFoodImage).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(foodImageService.update).toHaveBeenCalledWith(expect.objectContaining(foodImage));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoodImage>>();
      const foodImage = {id: 123};
      jest.spyOn(foodImageFormService, 'getFoodImage').mockReturnValue({id: null});
      jest.spyOn(foodImageService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({foodImage: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: foodImage}));
      saveSubject.complete();

      // THEN
      expect(foodImageFormService.getFoodImage).toHaveBeenCalled();
      expect(foodImageService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoodImage>>();
      const foodImage = {id: 123};
      jest.spyOn(foodImageService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({foodImage});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(foodImageService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareFood', () => {
      it('Should forward to foodService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(foodService, 'compareFood');
        comp.compareFood(entity, entity2);
        expect(foodService.compareFood).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
