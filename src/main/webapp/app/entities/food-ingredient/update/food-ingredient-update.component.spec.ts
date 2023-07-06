import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {FoodIngredientFormService} from './food-ingredient-form.service';
import {FoodIngredientService} from '../service/food-ingredient.service';
import {IFoodIngredient} from '../food-ingredient.model';
import {IFood} from 'app/entities/food/food.model';
import {FoodService} from 'app/entities/food/service/food.service';

import {FoodIngredientUpdateComponent} from './food-ingredient-update.component';

describe('FoodIngredient Management Update Component', () => {
  let comp: FoodIngredientUpdateComponent;
  let fixture: ComponentFixture<FoodIngredientUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let foodIngredientFormService: FoodIngredientFormService;
  let foodIngredientService: FoodIngredientService;
  let foodService: FoodService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FoodIngredientUpdateComponent],
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
      .overrideTemplate(FoodIngredientUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FoodIngredientUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    foodIngredientFormService = TestBed.inject(FoodIngredientFormService);
    foodIngredientService = TestBed.inject(FoodIngredientService);
    foodService = TestBed.inject(FoodService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Food query and add missing value', () => {
      const foodIngredient: IFoodIngredient = {id: 456};
      const food: IFood = {id: 24682};
      foodIngredient.food = food;

      const foodCollection: IFood[] = [{id: 40732}];
      jest.spyOn(foodService, 'query').mockReturnValue(of(new HttpResponse({body: foodCollection})));
      const additionalFoods = [food];
      const expectedCollection: IFood[] = [...additionalFoods, ...foodCollection];
      jest.spyOn(foodService, 'addFoodToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({foodIngredient});
      comp.ngOnInit();

      expect(foodService.query).toHaveBeenCalled();
      expect(foodService.addFoodToCollectionIfMissing).toHaveBeenCalledWith(
        foodCollection,
        ...additionalFoods.map(expect.objectContaining)
      );
      expect(comp.foodsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const foodIngredient: IFoodIngredient = {id: 456};
      const food: IFood = {id: 77393};
      foodIngredient.food = food;

      activatedRoute.data = of({foodIngredient});
      comp.ngOnInit();

      expect(comp.foodsSharedCollection).toContain(food);
      expect(comp.foodIngredient).toEqual(foodIngredient);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoodIngredient>>();
      const foodIngredient = {id: 123};
      jest.spyOn(foodIngredientFormService, 'getFoodIngredient').mockReturnValue(foodIngredient);
      jest.spyOn(foodIngredientService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({foodIngredient});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: foodIngredient}));
      saveSubject.complete();

      // THEN
      expect(foodIngredientFormService.getFoodIngredient).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(foodIngredientService.update).toHaveBeenCalledWith(expect.objectContaining(foodIngredient));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoodIngredient>>();
      const foodIngredient = {id: 123};
      jest.spyOn(foodIngredientFormService, 'getFoodIngredient').mockReturnValue({id: null});
      jest.spyOn(foodIngredientService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({foodIngredient: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: foodIngredient}));
      saveSubject.complete();

      // THEN
      expect(foodIngredientFormService.getFoodIngredient).toHaveBeenCalled();
      expect(foodIngredientService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoodIngredient>>();
      const foodIngredient = {id: 123};
      jest.spyOn(foodIngredientService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({foodIngredient});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(foodIngredientService.update).toHaveBeenCalled();
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
