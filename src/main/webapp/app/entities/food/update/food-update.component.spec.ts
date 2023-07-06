import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {FoodFormService} from './food-form.service';
import {FoodService} from '../service/food.service';
import {IFood} from '../food.model';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';
import {ICategory} from 'app/entities/category/category.model';
import {CategoryService} from 'app/entities/category/service/category.service';

import {FoodUpdateComponent} from './food-update.component';

describe('Food Management Update Component', () => {
  let comp: FoodUpdateComponent;
  let fixture: ComponentFixture<FoodUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let foodFormService: FoodFormService;
  let foodService: FoodService;
  let restaurantService: RestaurantService;
  let categoryService: CategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FoodUpdateComponent],
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
      .overrideTemplate(FoodUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FoodUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    foodFormService = TestBed.inject(FoodFormService);
    foodService = TestBed.inject(FoodService);
    restaurantService = TestBed.inject(RestaurantService);
    categoryService = TestBed.inject(CategoryService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Restaurant query and add missing value', () => {
      const food: IFood = {id: 456};
      const restaurant: IRestaurant = {id: 94384};
      food.restaurant = restaurant;

      const restaurantCollection: IRestaurant[] = [{id: 4991}];
      jest.spyOn(restaurantService, 'query').mockReturnValue(of(new HttpResponse({body: restaurantCollection})));
      const additionalRestaurants = [restaurant];
      const expectedCollection: IRestaurant[] = [...additionalRestaurants, ...restaurantCollection];
      jest.spyOn(restaurantService, 'addRestaurantToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({food});
      comp.ngOnInit();

      expect(restaurantService.query).toHaveBeenCalled();
      expect(restaurantService.addRestaurantToCollectionIfMissing).toHaveBeenCalledWith(
        restaurantCollection,
        ...additionalRestaurants.map(expect.objectContaining)
      );
      expect(comp.restaurantsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Category query and add missing value', () => {
      const food: IFood = {id: 456};
      const category: ICategory = {id: 57707};
      food.category = category;

      const categoryCollection: ICategory[] = [{id: 63458}];
      jest.spyOn(categoryService, 'query').mockReturnValue(of(new HttpResponse({body: categoryCollection})));
      const additionalCategories = [category];
      const expectedCollection: ICategory[] = [...additionalCategories, ...categoryCollection];
      jest.spyOn(categoryService, 'addCategoryToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({food});
      comp.ngOnInit();

      expect(categoryService.query).toHaveBeenCalled();
      expect(categoryService.addCategoryToCollectionIfMissing).toHaveBeenCalledWith(
        categoryCollection,
        ...additionalCategories.map(expect.objectContaining)
      );
      expect(comp.categoriesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const food: IFood = {id: 456};
      const restaurant: IRestaurant = {id: 23777};
      food.restaurant = restaurant;
      const category: ICategory = {id: 26944};
      food.category = category;

      activatedRoute.data = of({food});
      comp.ngOnInit();

      expect(comp.restaurantsSharedCollection).toContain(restaurant);
      expect(comp.categoriesSharedCollection).toContain(category);
      expect(comp.food).toEqual(food);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFood>>();
      const food = {id: 123};
      jest.spyOn(foodFormService, 'getFood').mockReturnValue(food);
      jest.spyOn(foodService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({food});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: food}));
      saveSubject.complete();

      // THEN
      expect(foodFormService.getFood).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(foodService.update).toHaveBeenCalledWith(expect.objectContaining(food));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFood>>();
      const food = {id: 123};
      jest.spyOn(foodFormService, 'getFood').mockReturnValue({id: null});
      jest.spyOn(foodService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({food: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: food}));
      saveSubject.complete();

      // THEN
      expect(foodFormService.getFood).toHaveBeenCalled();
      expect(foodService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFood>>();
      const food = {id: 123};
      jest.spyOn(foodService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({food});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(foodService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareRestaurant', () => {
      it('Should forward to restaurantService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(restaurantService, 'compareRestaurant');
        comp.compareRestaurant(entity, entity2);
        expect(restaurantService.compareRestaurant).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareCategory', () => {
      it('Should forward to categoryService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(categoryService, 'compareCategory');
        comp.compareCategory(entity, entity2);
        expect(categoryService.compareCategory).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
