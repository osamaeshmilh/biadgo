import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {RestaurantImageFormService} from './restaurant-image-form.service';
import {RestaurantImageService} from '../service/restaurant-image.service';
import {IRestaurantImage} from '../restaurant-image.model';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';

import {RestaurantImageUpdateComponent} from './restaurant-image-update.component';

describe('RestaurantImage Management Update Component', () => {
  let comp: RestaurantImageUpdateComponent;
  let fixture: ComponentFixture<RestaurantImageUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let restaurantImageFormService: RestaurantImageFormService;
  let restaurantImageService: RestaurantImageService;
  let restaurantService: RestaurantService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), RestaurantImageUpdateComponent],
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
      .overrideTemplate(RestaurantImageUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RestaurantImageUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    restaurantImageFormService = TestBed.inject(RestaurantImageFormService);
    restaurantImageService = TestBed.inject(RestaurantImageService);
    restaurantService = TestBed.inject(RestaurantService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Restaurant query and add missing value', () => {
      const restaurantImage: IRestaurantImage = {id: 456};
      const restaurant: IRestaurant = {id: 67204};
      restaurantImage.restaurant = restaurant;

      const restaurantCollection: IRestaurant[] = [{id: 47512}];
      jest.spyOn(restaurantService, 'query').mockReturnValue(of(new HttpResponse({body: restaurantCollection})));
      const additionalRestaurants = [restaurant];
      const expectedCollection: IRestaurant[] = [...additionalRestaurants, ...restaurantCollection];
      jest.spyOn(restaurantService, 'addRestaurantToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({restaurantImage});
      comp.ngOnInit();

      expect(restaurantService.query).toHaveBeenCalled();
      expect(restaurantService.addRestaurantToCollectionIfMissing).toHaveBeenCalledWith(
        restaurantCollection,
        ...additionalRestaurants.map(expect.objectContaining)
      );
      expect(comp.restaurantsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const restaurantImage: IRestaurantImage = {id: 456};
      const restaurant: IRestaurant = {id: 14602};
      restaurantImage.restaurant = restaurant;

      activatedRoute.data = of({restaurantImage});
      comp.ngOnInit();

      expect(comp.restaurantsSharedCollection).toContain(restaurant);
      expect(comp.restaurantImage).toEqual(restaurantImage);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantImage>>();
      const restaurantImage = {id: 123};
      jest.spyOn(restaurantImageFormService, 'getRestaurantImage').mockReturnValue(restaurantImage);
      jest.spyOn(restaurantImageService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantImage});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurantImage}));
      saveSubject.complete();

      // THEN
      expect(restaurantImageFormService.getRestaurantImage).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(restaurantImageService.update).toHaveBeenCalledWith(expect.objectContaining(restaurantImage));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantImage>>();
      const restaurantImage = {id: 123};
      jest.spyOn(restaurantImageFormService, 'getRestaurantImage').mockReturnValue({id: null});
      jest.spyOn(restaurantImageService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantImage: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurantImage}));
      saveSubject.complete();

      // THEN
      expect(restaurantImageFormService.getRestaurantImage).toHaveBeenCalled();
      expect(restaurantImageService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantImage>>();
      const restaurantImage = {id: 123};
      jest.spyOn(restaurantImageService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantImage});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(restaurantImageService.update).toHaveBeenCalled();
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
