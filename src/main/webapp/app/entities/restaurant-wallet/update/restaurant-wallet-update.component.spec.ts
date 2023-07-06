import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {RestaurantWalletFormService} from './restaurant-wallet-form.service';
import {RestaurantWalletService} from '../service/restaurant-wallet.service';
import {IRestaurantWallet} from '../restaurant-wallet.model';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';

import {RestaurantWalletUpdateComponent} from './restaurant-wallet-update.component';

describe('RestaurantWallet Management Update Component', () => {
  let comp: RestaurantWalletUpdateComponent;
  let fixture: ComponentFixture<RestaurantWalletUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let restaurantWalletFormService: RestaurantWalletFormService;
  let restaurantWalletService: RestaurantWalletService;
  let restaurantService: RestaurantService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), RestaurantWalletUpdateComponent],
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
      .overrideTemplate(RestaurantWalletUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RestaurantWalletUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    restaurantWalletFormService = TestBed.inject(RestaurantWalletFormService);
    restaurantWalletService = TestBed.inject(RestaurantWalletService);
    restaurantService = TestBed.inject(RestaurantService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Restaurant query and add missing value', () => {
      const restaurantWallet: IRestaurantWallet = {id: 456};
      const restaurant: IRestaurant = {id: 53010};
      restaurantWallet.restaurant = restaurant;

      const restaurantCollection: IRestaurant[] = [{id: 21778}];
      jest.spyOn(restaurantService, 'query').mockReturnValue(of(new HttpResponse({body: restaurantCollection})));
      const additionalRestaurants = [restaurant];
      const expectedCollection: IRestaurant[] = [...additionalRestaurants, ...restaurantCollection];
      jest.spyOn(restaurantService, 'addRestaurantToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({restaurantWallet});
      comp.ngOnInit();

      expect(restaurantService.query).toHaveBeenCalled();
      expect(restaurantService.addRestaurantToCollectionIfMissing).toHaveBeenCalledWith(
        restaurantCollection,
        ...additionalRestaurants.map(expect.objectContaining)
      );
      expect(comp.restaurantsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const restaurantWallet: IRestaurantWallet = {id: 456};
      const restaurant: IRestaurant = {id: 10493};
      restaurantWallet.restaurant = restaurant;

      activatedRoute.data = of({restaurantWallet});
      comp.ngOnInit();

      expect(comp.restaurantsSharedCollection).toContain(restaurant);
      expect(comp.restaurantWallet).toEqual(restaurantWallet);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantWallet>>();
      const restaurantWallet = {id: 123};
      jest.spyOn(restaurantWalletFormService, 'getRestaurantWallet').mockReturnValue(restaurantWallet);
      jest.spyOn(restaurantWalletService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantWallet});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurantWallet}));
      saveSubject.complete();

      // THEN
      expect(restaurantWalletFormService.getRestaurantWallet).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(restaurantWalletService.update).toHaveBeenCalledWith(expect.objectContaining(restaurantWallet));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantWallet>>();
      const restaurantWallet = {id: 123};
      jest.spyOn(restaurantWalletFormService, 'getRestaurantWallet').mockReturnValue({id: null});
      jest.spyOn(restaurantWalletService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantWallet: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: restaurantWallet}));
      saveSubject.complete();

      // THEN
      expect(restaurantWalletFormService.getRestaurantWallet).toHaveBeenCalled();
      expect(restaurantWalletService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRestaurantWallet>>();
      const restaurantWallet = {id: 123};
      jest.spyOn(restaurantWalletService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({restaurantWallet});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(restaurantWalletService.update).toHaveBeenCalled();
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
