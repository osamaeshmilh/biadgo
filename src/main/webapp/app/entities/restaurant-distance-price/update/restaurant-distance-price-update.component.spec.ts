import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {RestaurantDistancePriceFormService} from './restaurant-distance-price-form.service';
import {RestaurantDistancePriceService} from '../service/restaurant-distance-price.service';
import {IRestaurantDistancePrice} from '../restaurant-distance-price.model';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';

import {RestaurantDistancePriceUpdateComponent} from './restaurant-distance-price-update.component';

describe('RestaurantDistancePrice Management Update Component', () => {
  let comp: RestaurantDistancePriceUpdateComponent;
  let fixture: ComponentFixture<RestaurantDistancePriceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let restaurantDistancePriceFormService: RestaurantDistancePriceFormService;
  let restaurantDistancePriceService: RestaurantDistancePriceService;
  let restaurantService: RestaurantService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), RestaurantDistancePriceUpdateComponent],
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
      .overrideTemplate(RestaurantDistancePriceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RestaurantDistancePriceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    restaurantDistancePriceFormService = TestBed.inject(RestaurantDistancePriceFormService);
    restaurantDistancePriceService = TestBed.inject(RestaurantDistancePriceService);
    restaurantService = TestBed.inject(RestaurantService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Restaurant query and add missing value', () => {
      const restaurantDistancePrice: IRestaurantDistancePrice = {id: 456};
      const restaurant: IRestaurant = {id: 11423};
      restaurantDistancePrice.restaurant = restaurant;

      const restaurantCollection: IRestaurant[] = [{id: 7487}];
      jest.spyOn(restaurantService, 'query').mockReturnValue(of(new HttpResponse({body: restaurantCollection})));
      const additionalRestaurants = [restaurant];
      const expectedCollection: IRestaurant[] = [...additionalRestaurants, ...restaurantCollection];
      jest.spyOn(restaurantService, 'addRestaurantToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({restaurantDistancePrice});
      comp.ngOnInit();

      expect(restaurantService.query).toHaveBeenCalled();
      expect(restaurantService.addRestaurantToCollectionIfMissing).toHaveBeenCalledWith(
        restaurantCollection,
        ...additionalRestaurants.map(expect.objectContaining)
      );
      expect(comp.restaurantsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const restaurantDistancePrice: IRestaurantDistancePrice = {id: 456};
      const restaurant: IRestaurant = {id: 77545};
      restaurantDistancePrice.restaurant = restaurant;

      activatedRoute.data = of({restaurantDistancePrice});
      comp.ngOnInit();

      expect(comp.restaurantsSharedCollection).toContain(restaurant);
      expect(comp.restaurantDistancePrice).toEqual(restaurantDistancePrice);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantDistancePrice>>();
      const restaurantDistancePrice = {id: 123};
      jest.spyOn(restaurantDistancePriceFormService, 'getRestaurantDistancePrice').mockReturnValue(restaurantDistancePrice);
      jest.spyOn(restaurantDistancePriceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantDistancePrice});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurantDistancePrice}));
      saveSubject.complete();

      // THEN
      expect(restaurantDistancePriceFormService.getRestaurantDistancePrice).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(restaurantDistancePriceService.update).toHaveBeenCalledWith(expect.objectContaining(restaurantDistancePrice));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantDistancePrice>>();
      const restaurantDistancePrice = {id: 123};
      jest.spyOn(restaurantDistancePriceFormService, 'getRestaurantDistancePrice').mockReturnValue({id: null});
      jest.spyOn(restaurantDistancePriceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantDistancePrice: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurantDistancePrice}));
      saveSubject.complete();

      // THEN
      expect(restaurantDistancePriceFormService.getRestaurantDistancePrice).toHaveBeenCalled();
      expect(restaurantDistancePriceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantDistancePrice>>();
      const restaurantDistancePrice = {id: 123};
      jest.spyOn(restaurantDistancePriceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantDistancePrice});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(restaurantDistancePriceService.update).toHaveBeenCalled();
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
  });
});
