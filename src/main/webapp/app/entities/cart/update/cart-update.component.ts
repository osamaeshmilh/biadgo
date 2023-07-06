import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {CartFormService, CartFormGroup} from './cart-form.service';
import {ICart} from '../cart.model';
import {CartService} from '../service/cart.service';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';
import {IFood} from 'app/entities/food/food.model';
import {FoodService} from 'app/entities/food/service/food.service';

@Component({
  standalone: true,
  selector: 'jhi-cart-update',
  templateUrl: './cart-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CartUpdateComponent implements OnInit {
  isSaving = false;
  cart: ICart | null = null;

  customersSharedCollection: ICustomer[] = [];
  foodsSharedCollection: IFood[] = [];

  editForm: CartFormGroup = this.cartFormService.createCartFormGroup();

  constructor(
    protected cartService: CartService,
    protected cartFormService: CartFormService,
    protected customerService: CustomerService,
    protected foodService: FoodService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareCustomer = (o1: ICustomer | null, o2: ICustomer | null): boolean => this.customerService.compareCustomer(o1, o2);

  compareFood = (o1: IFood | null, o2: IFood | null): boolean => this.foodService.compareFood(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({cart}) => {
      this.cart = cart;
      if (cart) {
        this.updateForm(cart);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cart = this.cartFormService.getCart(this.editForm);
    if (cart.id !== null) {
      this.subscribeToSaveResponse(this.cartService.update(cart));
    } else {
      this.subscribeToSaveResponse(this.cartService.create(cart));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICart>>): void {
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

  protected updateForm(cart: ICart): void {
    this.cart = cart;
    this.cartFormService.resetForm(this.editForm, cart);

    this.customersSharedCollection = this.customerService.addCustomerToCollectionIfMissing<ICustomer>(
      this.customersSharedCollection,
      cart.customer
    );
    this.foodsSharedCollection = this.foodService.addFoodToCollectionIfMissing<IFood>(this.foodsSharedCollection, cart.food);
  }

  protected loadRelationshipsOptions(): void {
    this.customerService
      .query()
      .pipe(map((res: HttpResponse<ICustomer[]>) => res.body ?? []))
      .pipe(
        map((customers: ICustomer[]) => this.customerService.addCustomerToCollectionIfMissing<ICustomer>(customers, this.cart?.customer))
      )
      .subscribe((customers: ICustomer[]) => (this.customersSharedCollection = customers));

    this.foodService
      .query()
      .pipe(map((res: HttpResponse<IFood[]>) => res.body ?? []))
      .pipe(map((foods: IFood[]) => this.foodService.addFoodToCollectionIfMissing<IFood>(foods, this.cart?.food)))
      .subscribe((foods: IFood[]) => (this.foodsSharedCollection = foods));
  }
}
