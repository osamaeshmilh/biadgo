import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {RestaurantReviewFormService} from './restaurant-review-form.service';
import {RestaurantReviewService} from '../service/restaurant-review.service';
import {IRestaurantReview} from '../restaurant-review.model';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';

import {RestaurantReviewUpdateComponent} from './restaurant-review-update.component';

describe('RestaurantReview Management Update Component', () => {
  let comp: RestaurantReviewUpdateComponent;
  let fixture: ComponentFixture<RestaurantReviewUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let restaurantReviewFormService: RestaurantReviewFormService;
  let restaurantReviewService: RestaurantReviewService;
  let customerService: CustomerService;
  let restaurantService: RestaurantService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), RestaurantReviewUpdateComponent],
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
      .overrideTemplate(RestaurantReviewUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RestaurantReviewUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    restaurantReviewFormService = TestBed.inject(RestaurantReviewFormService);
    restaurantReviewService = TestBed.inject(RestaurantReviewService);
    customerService = TestBed.inject(CustomerService);
    restaurantService = TestBed.inject(RestaurantService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Customer query and add missing value', () => {
      const restaurantReview: IRestaurantReview = {id: 456};
      const customer: ICustomer = {id: 44969};
      restaurantReview.customer = customer;

      const customerCollection: ICustomer[] = [{id: 52893}];
      jest.spyOn(customerService, 'query').mockReturnValue(of(new HttpResponse({body: customerCollection})));
      const additionalCustomers = [customer];
      const expectedCollection: ICustomer[] = [...additionalCustomers, ...customerCollection];
      jest.spyOn(customerService, 'addCustomerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({restaurantReview});
      comp.ngOnInit();

      expect(customerService.query).toHaveBeenCalled();
      expect(customerService.addCustomerToCollectionIfMissing).toHaveBeenCalledWith(
        customerCollection,
        ...additionalCustomers.map(expect.objectContaining)
      );
      expect(comp.customersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Restaurant query and add missing value', () => {
      const restaurantReview: IRestaurantReview = {id: 456};
      const restaurant: IRestaurant = {id: 3213};
      restaurantReview.restaurant = restaurant;

      const restaurantCollection: IRestaurant[] = [{id: 78249}];
      jest.spyOn(restaurantService, 'query').mockReturnValue(of(new HttpResponse({body: restaurantCollection})));
      const additionalRestaurants = [restaurant];
      const expectedCollection: IRestaurant[] = [...additionalRestaurants, ...restaurantCollection];
      jest.spyOn(restaurantService, 'addRestaurantToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({restaurantReview});
      comp.ngOnInit();

      expect(restaurantService.query).toHaveBeenCalled();
      expect(restaurantService.addRestaurantToCollectionIfMissing).toHaveBeenCalledWith(
        restaurantCollection,
        ...additionalRestaurants.map(expect.objectContaining)
      );
      expect(comp.restaurantsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const restaurantReview: IRestaurantReview = {id: 456};
      const customer: ICustomer = {id: 50922};
      restaurantReview.customer = customer;
      const restaurant: IRestaurant = {id: 45306};
      restaurantReview.restaurant = restaurant;

      activatedRoute.data = of({restaurantReview});
      comp.ngOnInit();

      expect(comp.customersSharedCollection).toContain(customer);
      expect(comp.restaurantsSharedCollection).toContain(restaurant);
      expect(comp.restaurantReview).toEqual(restaurantReview);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantReview>>();
      const restaurantReview = {id: 123};
      jest.spyOn(restaurantReviewFormService, 'getRestaurantReview').mockReturnValue(restaurantReview);
      jest.spyOn(restaurantReviewService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantReview});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurantReview}));
      saveSubject.complete();

      // THEN
      expect(restaurantReviewFormService.getRestaurantReview).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(restaurantReviewService.update).toHaveBeenCalledWith(expect.objectContaining(restaurantReview));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantReview>>();
      const restaurantReview = {id: 123};
      jest.spyOn(restaurantReviewFormService, 'getRestaurantReview').mockReturnValue({id: null});
      jest.spyOn(restaurantReviewService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantReview: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurantReview}));
      saveSubject.complete();

      // THEN
      expect(restaurantReviewFormService.getRestaurantReview).toHaveBeenCalled();
      expect(restaurantReviewService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantReview>>();
      const restaurantReview = {id: 123};
      jest.spyOn(restaurantReviewService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantReview});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(restaurantReviewService.update).toHaveBeenCalled();
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
