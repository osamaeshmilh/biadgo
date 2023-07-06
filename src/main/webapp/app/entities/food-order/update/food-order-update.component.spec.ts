import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {FoodOrderFormService} from './food-order-form.service';
import {FoodOrderService} from '../service/food-order.service';
import {IFoodOrder} from '../food-order.model';
import {IOrder} from 'app/entities/order/order.model';
import {OrderService} from 'app/entities/order/service/order.service';

import {FoodOrderUpdateComponent} from './food-order-update.component';

describe('FoodOrder Management Update Component', () => {
  let comp: FoodOrderUpdateComponent;
  let fixture: ComponentFixture<FoodOrderUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let foodOrderFormService: FoodOrderFormService;
  let foodOrderService: FoodOrderService;
  let orderService: OrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), FoodOrderUpdateComponent],
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
      .overrideTemplate(FoodOrderUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FoodOrderUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    foodOrderFormService = TestBed.inject(FoodOrderFormService);
    foodOrderService = TestBed.inject(FoodOrderService);
    orderService = TestBed.inject(OrderService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Order query and add missing value', () => {
      const foodOrder: IFoodOrder = {id: 456};
      const order: IOrder = {id: 60689};
      foodOrder.order = order;

      const orderCollection: IOrder[] = [{id: 41082}];
      jest.spyOn(orderService, 'query').mockReturnValue(of(new HttpResponse({body: orderCollection})));
      const additionalOrders = [order];
      const expectedCollection: IOrder[] = [...additionalOrders, ...orderCollection];
      jest.spyOn(orderService, 'addOrderToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({foodOrder});
      comp.ngOnInit();

      expect(orderService.query).toHaveBeenCalled();
      expect(orderService.addOrderToCollectionIfMissing).toHaveBeenCalledWith(
        orderCollection,
        ...additionalOrders.map(expect.objectContaining)
      );
      expect(comp.ordersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const foodOrder: IFoodOrder = {id: 456};
      const order: IOrder = {id: 61274};
      foodOrder.order = order;

      activatedRoute.data = of({foodOrder});
      comp.ngOnInit();

      expect(comp.ordersSharedCollection).toContain(order);
      expect(comp.foodOrder).toEqual(foodOrder);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoodOrder>>();
      const foodOrder = {id: 123};
      jest.spyOn(foodOrderFormService, 'getFoodOrder').mockReturnValue(foodOrder);
      jest.spyOn(foodOrderService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({foodOrder});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: foodOrder}));
      saveSubject.complete();

      // THEN
      expect(foodOrderFormService.getFoodOrder).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(foodOrderService.update).toHaveBeenCalledWith(expect.objectContaining(foodOrder));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoodOrder>>();
      const foodOrder = {id: 123};
      jest.spyOn(foodOrderFormService, 'getFoodOrder').mockReturnValue({id: null});
      jest.spyOn(foodOrderService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({foodOrder: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: foodOrder}));
      saveSubject.complete();

      // THEN
      expect(foodOrderFormService.getFoodOrder).toHaveBeenCalled();
      expect(foodOrderService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoodOrder>>();
      const foodOrder = {id: 123};
      jest.spyOn(foodOrderService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({foodOrder});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(foodOrderService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareOrder', () => {
      it('Should forward to orderService', () => {
        const entity = {id: 123};
        const entity2 = {id: 456};
        jest.spyOn(orderService, 'compareOrder');
        comp.compareOrder(entity, entity2);
        expect(orderService.compareOrder).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
