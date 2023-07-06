import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {RestaurantFormService} from './restaurant-form.service';
import {RestaurantService} from '../service/restaurant.service';
import {IRestaurant} from '../restaurant.model';

import {IUser} from 'app/entities/user/user.model';
import {UserService} from 'app/entities/user/user.service';
import {ICuisine} from 'app/entities/cuisine/cuisine.model';
import {CuisineService} from 'app/entities/cuisine/service/cuisine.service';
import {ICategory} from 'app/entities/category/category.model';
import {CategoryService} from 'app/entities/category/service/category.service';

import {RestaurantUpdateComponent} from './restaurant-update.component';

describe('Restaurant Management Update Component', () => {
  let comp: RestaurantUpdateComponent;
  let fixture: ComponentFixture<RestaurantUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let restaurantFormService: RestaurantFormService;
  let restaurantService: RestaurantService;
  let userService: UserService;
  let cuisineService: CuisineService;
  let categoryService: CategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), RestaurantUpdateComponent],
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
      .overrideTemplate(RestaurantUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RestaurantUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    restaurantFormService = TestBed.inject(RestaurantFormService);
    restaurantService = TestBed.inject(RestaurantService);
    userService = TestBed.inject(UserService);
    cuisineService = TestBed.inject(CuisineService);
    categoryService = TestBed.inject(CategoryService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call User query and add missing value', () => {
      const restaurant: IRestaurant = {id: 456};
      const user: IUser = {id: 81629};
      restaurant.user = user;

      const userCollection: IUser[] = [{id: 24028}];
      jest.spyOn(userService, 'query').mockReturnValue(of(new HttpResponse({body: userCollection})));
      const additionalUsers = [user];
      const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
      jest.spyOn(userService, 'addUserToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({restaurant});
      comp.ngOnInit();

      expect(userService.query).toHaveBeenCalled();
      expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(
        userCollection,
        ...additionalUsers.map(expect.objectContaining)
      );
      expect(comp.usersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Cuisine query and add missing value', () => {
      const restaurant: IRestaurant = {id: 456};
      const cuisine: ICuisine = {id: 3059};
      restaurant.cuisine = cuisine;

      const cuisineCollection: ICuisine[] = [{id: 48484}];
      jest.spyOn(cuisineService, 'query').mockReturnValue(of(new HttpResponse({body: cuisineCollection})));
      const additionalCuisines = [cuisine];
      const expectedCollection: ICuisine[] = [...additionalCuisines, ...cuisineCollection];
      jest.spyOn(cuisineService, 'addCuisineToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({restaurant});
      comp.ngOnInit();

      expect(cuisineService.query).toHaveBeenCalled();
      expect(cuisineService.addCuisineToCollectionIfMissing).toHaveBeenCalledWith(
        cuisineCollection,
        ...additionalCuisines.map(expect.objectContaining)
      );
      expect(comp.cuisinesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Category query and add missing value', () => {
      const restaurant: IRestaurant = {id: 456};
      const categories: ICategory[] = [{id: 39212}];
      restaurant.categories = categories;

      const categoryCollection: ICategory[] = [{id: 29146}];
      jest.spyOn(categoryService, 'query').mockReturnValue(of(new HttpResponse({body: categoryCollection})));
      const additionalCategories = [...categories];
      const expectedCollection: ICategory[] = [...additionalCategories, ...categoryCollection];
      jest.spyOn(categoryService, 'addCategoryToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({restaurant});
      comp.ngOnInit();

      expect(categoryService.query).toHaveBeenCalled();
      expect(categoryService.addCategoryToCollectionIfMissing).toHaveBeenCalledWith(
        categoryCollection,
        ...additionalCategories.map(expect.objectContaining)
      );
      expect(comp.categoriesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const restaurant: IRestaurant = {id: 456};
      const user: IUser = {id: 98666};
      restaurant.user = user;
      const cuisine: ICuisine = {id: 98633};
      restaurant.cuisine = cuisine;
      const categories: ICategory = {id: 57113};
      restaurant.categories = [categories];

      activatedRoute.data = of({restaurant});
      comp.ngOnInit();

      expect(comp.usersSharedCollection).toContain(user);
      expect(comp.cuisinesSharedCollection).toContain(cuisine);
      expect(comp.categoriesSharedCollection).toContain(categories);
      expect(comp.restaurant).toEqual(restaurant);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurant>>();
      const restaurant = {id: 123};
      jest.spyOn(restaurantFormService, 'getRestaurant').mockReturnValue(restaurant);
      jest.spyOn(restaurantService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurant});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurant}));
      saveSubject.complete();

      // THEN
      expect(restaurantFormService.getRestaurant).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(restaurantService.update).toHaveBeenCalledWith(expect.objectContaining(restaurant));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurant>>();
      const restaurant = {id: 123};
      jest.spyOn(restaurantFormService, 'getRestaurant').mockReturnValue({id: null});
      jest.spyOn(restaurantService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurant: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurant}));
      saveSubject.complete();

      // THEN
      expect(restaurantFormService.getRestaurant).toHaveBeenCalled();
      expect(restaurantService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurant>>();
      const restaurant = {id: 123};
      jest.spyOn(restaurantService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurant});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(restaurantService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareUser', () => {
      it('Should forward to userService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(userService, 'compareUser');
        comp.compareUser(entity, entity2);
        expect(userService.compareUser).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareCuisine', () => {
      it('Should forward to cuisineService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(cuisineService, 'compareCuisine');
        comp.compareCuisine(entity, entity2);
        expect(cuisineService.compareCuisine).toHaveBeenCalledWith(entity, entity2);
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
