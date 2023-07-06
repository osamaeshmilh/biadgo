import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {RestaurantZonePriceFormService} from './restaurant-zone-price-form.service';
import {RestaurantZonePriceService} from '../service/restaurant-zone-price.service';
import {IRestaurantZonePrice} from '../restaurant-zone-price.model';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';
import {IZone} from 'app/entities/zone/zone.model';
import {ZoneService} from 'app/entities/zone/service/zone.service';

import {RestaurantZonePriceUpdateComponent} from './restaurant-zone-price-update.component';

describe('RestaurantZonePrice Management Update Component', () => {
  let comp: RestaurantZonePriceUpdateComponent;
  let fixture: ComponentFixture<RestaurantZonePriceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let restaurantZonePriceFormService: RestaurantZonePriceFormService;
  let restaurantZonePriceService: RestaurantZonePriceService;
  let restaurantService: RestaurantService;
  let zoneService: ZoneService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), RestaurantZonePriceUpdateComponent],
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
      .overrideTemplate(RestaurantZonePriceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RestaurantZonePriceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    restaurantZonePriceFormService = TestBed.inject(RestaurantZonePriceFormService);
    restaurantZonePriceService = TestBed.inject(RestaurantZonePriceService);
    restaurantService = TestBed.inject(RestaurantService);
    zoneService = TestBed.inject(ZoneService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Restaurant query and add missing value', () => {
      const restaurantZonePrice: IRestaurantZonePrice = {id: 456};
      const restaurant: IRestaurant = {id: 63285};
      restaurantZonePrice.restaurant = restaurant;

      const restaurantCollection: IRestaurant[] = [{id: 7033}];
      jest.spyOn(restaurantService, 'query').mockReturnValue(of(new HttpResponse({body: restaurantCollection})));
      const additionalRestaurants = [restaurant];
      const expectedCollection: IRestaurant[] = [...additionalRestaurants, ...restaurantCollection];
      jest.spyOn(restaurantService, 'addRestaurantToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({restaurantZonePrice});
      comp.ngOnInit();

      expect(restaurantService.query).toHaveBeenCalled();
      expect(restaurantService.addRestaurantToCollectionIfMissing).toHaveBeenCalledWith(
        restaurantCollection,
        ...additionalRestaurants.map(expect.objectContaining)
      );
      expect(comp.restaurantsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Zone query and add missing value', () => {
      const restaurantZonePrice: IRestaurantZonePrice = {id: 456};
      const zone: IZone = {id: 6173};
      restaurantZonePrice.zone = zone;

      const zoneCollection: IZone[] = [{id: 66223}];
      jest.spyOn(zoneService, 'query').mockReturnValue(of(new HttpResponse({body: zoneCollection})));
      const additionalZones = [zone];
      const expectedCollection: IZone[] = [...additionalZones, ...zoneCollection];
      jest.spyOn(zoneService, 'addZoneToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({restaurantZonePrice});
      comp.ngOnInit();

      expect(zoneService.query).toHaveBeenCalled();
      expect(zoneService.addZoneToCollectionIfMissing).toHaveBeenCalledWith(
        zoneCollection,
        ...additionalZones.map(expect.objectContaining)
      );
      expect(comp.zonesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const restaurantZonePrice: IRestaurantZonePrice = {id: 456};
      const restaurant: IRestaurant = {id: 13823};
      restaurantZonePrice.restaurant = restaurant;
      const zone: IZone = {id: 13251};
      restaurantZonePrice.zone = zone;

      activatedRoute.data = of({restaurantZonePrice});
      comp.ngOnInit();

      expect(comp.restaurantsSharedCollection).toContain(restaurant);
      expect(comp.zonesSharedCollection).toContain(zone);
      expect(comp.restaurantZonePrice).toEqual(restaurantZonePrice);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantZonePrice>>();
      const restaurantZonePrice = {id: 123};
      jest.spyOn(restaurantZonePriceFormService, 'getRestaurantZonePrice').mockReturnValue(restaurantZonePrice);
      jest.spyOn(restaurantZonePriceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantZonePrice});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurantZonePrice}));
      saveSubject.complete();

      // THEN
      expect(restaurantZonePriceFormService.getRestaurantZonePrice).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(restaurantZonePriceService.update).toHaveBeenCalledWith(expect.objectContaining(restaurantZonePrice));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantZonePrice>>();
      const restaurantZonePrice = {id: 123};
      jest.spyOn(restaurantZonePriceFormService, 'getRestaurantZonePrice').mockReturnValue({id: null});
      jest.spyOn(restaurantZonePriceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantZonePrice: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurantZonePrice}));
      saveSubject.complete();

      // THEN
      expect(restaurantZonePriceFormService.getRestaurantZonePrice).toHaveBeenCalled();
      expect(restaurantZonePriceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantZonePrice>>();
      const restaurantZonePrice = {id: 123};
      jest.spyOn(restaurantZonePriceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantZonePrice});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(restaurantZonePriceService.update).toHaveBeenCalled();
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

    describe('compareZone', () => {
      it('Should forward to zoneService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(zoneService, 'compareZone');
        comp.compareZone(entity, entity2);
        expect(zoneService.compareZone).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
