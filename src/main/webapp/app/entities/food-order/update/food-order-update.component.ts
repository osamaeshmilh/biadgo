import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {FoodOrderFormService, FoodOrderFormGroup} from './food-order-form.service';
import {IFoodOrder} from '../food-order.model';
import {FoodOrderService} from '../service/food-order.service';
import {IOrder} from 'app/entities/order/order.model';
import {OrderService} from 'app/entities/order/service/order.service';
import {IFood} from 'app/entities/food/food.model';
import {FoodService} from 'app/entities/food/service/food.service';

@Component({
  standalone: true,
  selector: 'jhi-food-order-update',
  templateUrl: './food-order-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FoodOrderUpdateComponent implements OnInit {
  isSaving = false;
  foodOrder: IFoodOrder | null = null;

  ordersSharedCollection: IOrder[] = [];
  foodsSharedCollection: IFood[] = [];

  editForm: FoodOrderFormGroup = this.foodOrderFormService.createFoodOrderFormGroup();

  constructor(
    protected foodOrderService: FoodOrderService,
    protected foodOrderFormService: FoodOrderFormService,
    protected orderService: OrderService,
    protected foodService: FoodService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareOrder = (o1: IOrder | null, o2: IOrder | null): boolean => this.orderService.compareOrder(o1, o2);

  compareFood = (o1: IFood | null, o2: IFood | null): boolean => this.foodService.compareFood(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({foodOrder}) => {
      this.foodOrder = foodOrder;
      if (foodOrder) {
        this.updateForm(foodOrder);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const foodOrder = this.foodOrderFormService.getFoodOrder(this.editForm);
    if (foodOrder.id !== null) {
      this.subscribeToSaveResponse(this.foodOrderService.update(foodOrder));
    } else {
      this.subscribeToSaveResponse(this.foodOrderService.create(foodOrder));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFoodOrder>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(foodOrder: IFoodOrder): void {
    this.foodOrder = foodOrder;
    this.foodOrderFormService.resetForm(this.editForm, foodOrder);

    this.ordersSharedCollection = this.orderService.addOrderToCollectionIfMissing<IOrder>(this.ordersSharedCollection, foodOrder.order);
    this.foodsSharedCollection = this.foodService.addFoodToCollectionIfMissing<IFood>(this.foodsSharedCollection, foodOrder.food);
  }

  protected loadRelationshipsOptions(): void {
    this.orderService
      .query()
      .pipe(map((res: HttpResponse<IOrder[]>) => res.body ?? []))
      .pipe(map((orders: IOrder[]) => this.orderService.addOrderToCollectionIfMissing<IOrder>(orders, this.foodOrder?.order)))
      .subscribe((orders: IOrder[]) => (this.ordersSharedCollection = orders));

    this.foodService
      .query()
      .pipe(map((res: HttpResponse<IFood[]>) => res.body ?? []))
      .pipe(map((foods: IFood[]) => this.foodService.addFoodToCollectionIfMissing<IFood>(foods, this.foodOrder?.food)))
      .subscribe((foods: IFood[]) => (this.foodsSharedCollection = foods));
  }
}
