import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {RestaurantScheduleFormService} from './restaurant-schedule-form.service';
import {RestaurantScheduleService} from '../service/restaurant-schedule.service';
import {IRestaurantSchedule} from '../restaurant-schedule.model';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';

import {RestaurantScheduleUpdateComponent} from './restaurant-schedule-update.component';

describe('RestaurantSchedule Management Update Component', () => {
  let comp: RestaurantScheduleUpdateComponent;
  let fixture: ComponentFixture<RestaurantScheduleUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let restaurantScheduleFormService: RestaurantScheduleFormService;
  let restaurantScheduleService: RestaurantScheduleService;
  let restaurantService: RestaurantService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), RestaurantScheduleUpdateComponent],
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
      .overrideTemplate(RestaurantScheduleUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RestaurantScheduleUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    restaurantScheduleFormService = TestBed.inject(RestaurantScheduleFormService);
    restaurantScheduleService = TestBed.inject(RestaurantScheduleService);
    restaurantService = TestBed.inject(RestaurantService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Restaurant query and add missing value', () => {
      const restaurantSchedule: IRestaurantSchedule = {id: 456};
      const restaurant: IRestaurant = {id: 84187};
      restaurantSchedule.restaurant = restaurant;

      const restaurantCollection: IRestaurant[] = [{id: 54346}];
      jest.spyOn(restaurantService, 'query').mockReturnValue(of(new HttpResponse({body: restaurantCollection})));
      const additionalRestaurants = [restaurant];
      const expectedCollection: IRestaurant[] = [...additionalRestaurants, ...restaurantCollection];
      jest.spyOn(restaurantService, 'addRestaurantToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({restaurantSchedule});
      comp.ngOnInit();

      expect(restaurantService.query).toHaveBeenCalled();
      expect(restaurantService.addRestaurantToCollectionIfMissing).toHaveBeenCalledWith(
        restaurantCollection,
        ...additionalRestaurants.map(expect.objectContaining)
      );
      expect(comp.restaurantsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const restaurantSchedule: IRestaurantSchedule = {id: 456};
      const restaurant: IRestaurant = {id: 11710};
      restaurantSchedule.restaurant = restaurant;

      activatedRoute.data = of({restaurantSchedule});
      comp.ngOnInit();

      expect(comp.restaurantsSharedCollection).toContain(restaurant);
      expect(comp.restaurantSchedule).toEqual(restaurantSchedule);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantSchedule>>();
      const restaurantSchedule = {id: 123};
      jest.spyOn(restaurantScheduleFormService, 'getRestaurantSchedule').mockReturnValue(restaurantSchedule);
      jest.spyOn(restaurantScheduleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantSchedule});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurantSchedule}));
      saveSubject.complete();

      // THEN
      expect(restaurantScheduleFormService.getRestaurantSchedule).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(restaurantScheduleService.update).toHaveBeenCalledWith(expect.objectContaining(restaurantSchedule));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantSchedule>>();
      const restaurantSchedule = {id: 123};
      jest.spyOn(restaurantScheduleFormService, 'getRestaurantSchedule').mockReturnValue({id: null});
      jest.spyOn(restaurantScheduleService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantSchedule: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurantSchedule}));
      saveSubject.complete();

      // THEN
      expect(restaurantScheduleFormService.getRestaurantSchedule).toHaveBeenCalled();
      expect(restaurantScheduleService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantSchedule>>();
      const restaurantSchedule = {id: 123};
      jest.spyOn(restaurantScheduleService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantSchedule});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(restaurantScheduleService.update).toHaveBeenCalled();
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
