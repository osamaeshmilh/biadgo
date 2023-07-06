import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {CartFormService} from './cart-form.service';
import {CartService} from '../service/cart.service';
import {ICart} from '../cart.model';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';
import {IFood} from 'app/entities/food/food.model';
import {FoodService} from 'app/entities/food/service/food.service';

import {CartUpdateComponent} from './cart-update.component';

describe('Cart Management Update Component', () => {
  let comp: CartUpdateComponent;
  let fixture: ComponentFixture<CartUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let cartFormService: CartFormService;
  let cartService: CartService;
  let customerService: CustomerService;
  let foodService: FoodService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), CartUpdateComponent],
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
      .overrideTemplate(CartUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CartUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    cartFormService = TestBed.inject(CartFormService);
    cartService = TestBed.inject(CartService);
    customerService = TestBed.inject(CustomerService);
    foodService = TestBed.inject(FoodService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Customer query and add missing value', () => {
      const cart: ICart = {id: 456};
      const customer: ICustomer = {id: 95842};
      cart.customer = customer;

      const customerCollection: ICustomer[] = [{id: 27353}];
      jest.spyOn(customerService, 'query').mockReturnValue(of(new HttpResponse({body: customerCollection})));
      const additionalCustomers = [customer];
      const expectedCollection: ICustomer[] = [...additionalCustomers, ...customerCollection];
      jest.spyOn(customerService, 'addCustomerToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({cart});
      comp.ngOnInit();

      expect(customerService.query).toHaveBeenCalled();
      expect(customerService.addCustomerToCollectionIfMissing).toHaveBeenCalledWith(
        customerCollection,
        ...additionalCustomers.map(expect.objectContaining)
      );
      expect(comp.customersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Food query and add missing value', () => {
      const cart: ICart = {id: 456};
      const food: IFood = {id: 64901};
      cart.food = food;

      const foodCollection: IFood[] = [{id: 29615}];
      jest.spyOn(foodService, 'query').mockReturnValue(of(new HttpResponse({body: foodCollection})));
      const additionalFoods = [food];
      const expectedCollection: IFood[] = [...additionalFoods, ...foodCollection];
      jest.spyOn(foodService, 'addFoodToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({cart});
      comp.ngOnInit();

      expect(foodService.query).toHaveBeenCalled();
      expect(foodService.addFoodToCollectionIfMissing).toHaveBeenCalledWith(
        foodCollection,
        ...additionalFoods.map(expect.objectContaining)
      );
      expect(comp.foodsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const cart: ICart = {id: 456};
      const customer: ICustomer = {id: 44235};
      cart.customer = customer;
      const food: IFood = {id: 76847};
      cart.food = food;

      activatedRoute.data = of({cart});
      comp.ngOnInit();

      expect(comp.customersSharedCollection).toContain(customer);
      expect(comp.foodsSharedCollection).toContain(food);
      expect(comp.cart).toEqual(cart);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICart>>();
      const cart = {id: 123};
      jest.spyOn(cartFormService, 'getCart').mockReturnValue(cart);
      jest.spyOn(cartService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({cart});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: cart}));
      saveSubject.complete();

      // THEN
      expect(cartFormService.getCart).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(cartService.update).toHaveBeenCalledWith(expect.objectContaining(cart));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICart>>();
      const cart = {id: 123};
      jest.spyOn(cartFormService, 'getCart').mockReturnValue({id: null});
      jest.spyOn(cartService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({cart: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: cart}));
      saveSubject.complete();

      // THEN
      expect(cartFormService.getCart).toHaveBeenCalled();
      expect(cartService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICart>>();
      const cart = {id: 123};
      jest.spyOn(cartService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({cart});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(cartService.update).toHaveBeenCalled();
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
