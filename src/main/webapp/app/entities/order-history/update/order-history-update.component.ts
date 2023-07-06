import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {OrderHistoryFormService, OrderHistoryFormGroup} from './order-history-form.service';
import {IOrderHistory} from '../order-history.model';
import {OrderHistoryService} from '../service/order-history.service';
import {IOrder} from 'app/entities/order/order.model';
import {OrderService} from 'app/entities/order/service/order.service';
import {OrderStatus} from 'app/entities/enumerations/order-status.model';

@Component({
  standalone: true,
  selector: 'jhi-order-history-update',
  templateUrl: './order-history-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OrderHistoryUpdateComponent implements OnInit {
  isSaving = false;
  orderHistory: IOrderHistory | null = null;
  orderStatusValues = Object.keys(OrderStatus);

  ordersSharedCollection: IOrder[] = [];

  editForm: OrderHistoryFormGroup = this.orderHistoryFormService.createOrderHistoryFormGroup();

  constructor(
    protected orderHistoryService: OrderHistoryService,
    protected orderHistoryFormService: OrderHistoryFormService,
    protected orderService: OrderService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareOrder = (o1: IOrder | null, o2: IOrder | null): boolean => this.orderService.compareOrder(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({orderHistory}) => {
      this.orderHistory = orderHistory;
      if (orderHistory) {
        this.updateForm(orderHistory);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const orderHistory = this.orderHistoryFormService.getOrderHistory(this.editForm);
    if (orderHistory.id !== null) {
      this.subscribeToSaveResponse(this.orderHistoryService.update(orderHistory));
    } else {
      this.subscribeToSaveResponse(this.orderHistoryService.create(orderHistory));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderHistory>>): void {
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

  protected updateForm(orderHistory: IOrderHistory): void {
    this.orderHistory = orderHistory;
    this.orderHistoryFormService.resetForm(this.editForm, orderHistory);

    this.ordersSharedCollection = this.orderService.addOrderToCollectionIfMissing<IOrder>(this.ordersSharedCollection, orderHistory.order);
  }

  protected loadRelationshipsOptions(): void {
    this.orderService
      .query()
      .pipe(map((res: HttpResponse<IOrder[]>) => res.body ?? []))
      .pipe(map((orders: IOrder[]) => this.orderService.addOrderToCollectionIfMissing<IOrder>(orders, this.orderHistory?.order)))
      .subscribe((orders: IOrder[]) => (this.ordersSharedCollection = orders));
  }
}
