import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {FoodExtraFormService} from './food-extra-form.service';
import {FoodExtraService} from '../service/food-extra.service';
import {IFoodExtra} from '../food-extra.model';
import {IFood} from 'app/entities/food/food.model';
import {FoodService} from 'app/entities/food/service/food.service';

import {FoodExtraUpdateComponent} from './food-extra-update.component';

describe('FoodExtra Management Update Component', () => {
  let comp: FoodExtraUpdateComponent;
  let fixture: ComponentFixture<FoodExtraUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let foodExtraFormService: FoodExtraFormService;
  let foodExtraService: FoodExtraService;
  let foodService: FoodService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FoodExtraUpdateComponent],
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
      .overrideTemplate(FoodExtraUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FoodExtraUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    foodExtraFormService = TestBed.inject(FoodExtraFormService);
    foodExtraService = TestBed.inject(FoodExtraService);
    foodService = TestBed.inject(FoodService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Food query and add missing value', () => {
      const foodExtra: IFoodExtra = {id: 456};
      const food: IFood = {id: 18385};
      foodExtra.food = food;

      const foodCollection: IFood[] = [{id: 27847}];
      jest.spyOn(foodService, 'query').mockReturnValue(of(new HttpResponse({body: foodCollection})));
      const additionalFoods = [food];
      const expectedCollection: IFood[] = [...additionalFoods, ...foodCollection];
      jest.spyOn(foodService, 'addFoodToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({foodExtra});
      comp.ngOnInit();

      expect(foodService.query).toHaveBeenCalled();
      expect(foodService.addFoodToCollectionIfMissing).toHaveBeenCalledWith(
        foodCollection,
        ...additionalFoods.map(expect.objectContaining)
      );
      expect(comp.foodsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const foodExtra: IFoodExtra = {id: 456};
      const food: IFood = {id: 60245};
      foodExtra.food = food;

      activatedRoute.data = of({foodExtra});
      comp.ngOnInit();

      expect(comp.foodsSharedCollection).toContain(food);
      expect(comp.foodExtra).toEqual(foodExtra);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoodExtra>>();
      const foodExtra = {id: 123};
      jest.spyOn(foodExtraFormService, 'getFoodExtra').mockReturnValue(foodExtra);
      jest.spyOn(foodExtraService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({foodExtra});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: foodExtra}));
      saveSubject.complete();

      // THEN
      expect(foodExtraFormService.getFoodExtra).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(foodExtraService.update).toHaveBeenCalledWith(expect.objectContaining(foodExtra));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoodExtra>>();
      const foodExtra = {id: 123};
      jest.spyOn(foodExtraFormService, 'getFoodExtra').mockReturnValue({id: null});
      jest.spyOn(foodExtraService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({foodExtra: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: foodExtra}));
      saveSubject.complete();

      // THEN
      expect(foodExtraFormService.getFoodExtra).toHaveBeenCalled();
      expect(foodExtraService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoodExtra>>();
      const foodExtra = {id: 123};
      jest.spyOn(foodExtraService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({foodExtra});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(foodExtraService.update).toHaveBeenCalled();
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
