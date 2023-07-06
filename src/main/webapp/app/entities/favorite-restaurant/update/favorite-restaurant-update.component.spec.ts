import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {FavoriteRestaurantFormService} from './favorite-restaurant-form.service';
import {FavoriteRestaurantService} from '../service/favorite-restaurant.service';
import {IFavoriteRestaurant} from '../favorite-restaurant.model';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';

import {FavoriteRestaurantUpdateComponent} from './favorite-restaurant-update.component';

describe('FavoriteRestaurant Management Update Component', () => {
  let comp: FavoriteRestaurantUpdateComponent;
  let fixture: ComponentFixture<FavoriteRestaurantUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let favoriteRestaurantFormService: FavoriteRestaurantFormService;
  let favoriteRestaurantService: FavoriteRestaurantService;
  let customerService: CustomerService;
  let restaurantService: RestaurantService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FavoriteRestaurantUpdateComponent],
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
      .overrideTemplate(FavoriteRestaurantUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FavoriteRestaurantUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    favoriteRestaurantFormService = TestBed.inject(FavoriteRestaurantFormService);
    favoriteRestaurantService = TestBed.inject(FavoriteRestaurantService);
    customerService = TestBed.inject(CustomerService);
    restaurantService = TestBed.inject(RestaurantService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Customer query and add missing value', () => {
      const favoriteRestaurant: IFavoriteRestaurant = {id: 456};
      const customer: ICustomer = {id: 13996};
      favoriteRestaurant.customer = customer;

      const customerCollection: ICustomer[] = [{id: 26070}];
      jest.spyOn(customerService, 'query').mockReturnValue(of(new HttpResponse({body: customerCollection})));
      const additionalCustomers = [customer];
      const expectedCollection: ICustomer[] = [...additionalCustomers, ...customerCollection];
      jest.spyOn(customerService, 'addCustomerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({favoriteRestaurant});
      comp.ngOnInit();

      expect(customerService.query).toHaveBeenCalled();
      expect(customerService.addCustomerToCollectionIfMissing).toHaveBeenCalledWith(
        customerCollection,
        ...additionalCustomers.map(expect.objectContaining)
      );
      expect(comp.customersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Restaurant query and add missing value', () => {
      const favoriteRestaurant: IFavoriteRestaurant = {id: 456};
      const restaurant: IRestaurant = {id: 70754};
      favoriteRestaurant.restaurant = restaurant;

      const restaurantCollection: IRestaurant[] = [{id: 81292}];
      jest.spyOn(restaurantService, 'query').mockReturnValue(of(new HttpResponse({body: restaurantCollection})));
      const additionalRestaurants = [restaurant];
      const expectedCollection: IRestaurant[] = [...additionalRestaurants, ...restaurantCollection];
      jest.spyOn(restaurantService, 'addRestaurantToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({favoriteRestaurant});
      comp.ngOnInit();

      expect(restaurantService.query).toHaveBeenCalled();
      expect(restaurantService.addRestaurantToCollectionIfMissing).toHaveBeenCalledWith(
        restaurantCollection,
        ...additionalRestaurants.map(expect.objectContaining)
      );
      expect(comp.restaurantsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const favoriteRestaurant: IFavoriteRestaurant = {id: 456};
      const customer: ICustomer = {id: 45323};
      favoriteRestaurant.customer = customer;
      const restaurant: IRestaurant = {id: 67212};
      favoriteRestaurant.restaurant = restaurant;

      activatedRoute.data = of({favoriteRestaurant});
      comp.ngOnInit();

      expect(comp.customersSharedCollection).toContain(customer);
      expect(comp.restaurantsSharedCollection).toContain(restaurant);
      expect(comp.favoriteRestaurant).toEqual(favoriteRestaurant);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFavoriteRestaurant>>();
      const favoriteRestaurant = {id: 123};
      jest.spyOn(favoriteRestaurantFormService, 'getFavoriteRestaurant').mockReturnValue(favoriteRestaurant);
      jest.spyOn(favoriteRestaurantService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({favoriteRestaurant});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: favoriteRestaurant}));
      saveSubject.complete();

      // THEN
      expect(favoriteRestaurantFormService.getFavoriteRestaurant).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(favoriteRestaurantService.update).toHaveBeenCalledWith(expect.objectContaining(favoriteRestaurant));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFavoriteRestaurant>>();
      const favoriteRestaurant = {id: 123};
      jest.spyOn(favoriteRestaurantFormService, 'getFavoriteRestaurant').mockReturnValue({id: null});
      jest.spyOn(favoriteRestaurantService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({favoriteRestaurant: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: favoriteRestaurant}));
      saveSubject.complete();

      // THEN
      expect(favoriteRestaurantFormService.getFavoriteRestaurant).toHaveBeenCalled();
      expect(favoriteRestaurantService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFavoriteRestaurant>>();
      const favoriteRestaurant = {id: 123};
      jest.spyOn(favoriteRestaurantService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({favoriteRestaurant});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(favoriteRestaurantService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareCustomer', () => {
      it('Should forward to customerService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(customerService, 'compareCustomer');
        comp.compareCustomer(entity, entity2);
        expect(customerService.compareCustomer).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareRestaurant', () => {
      it('Should forward to restaurantService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(restaurantService, 'compareRestaurant');
        comp.compareRestaurant(entity, entity2);
        expect(restaurantService.compareRestaurant).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
