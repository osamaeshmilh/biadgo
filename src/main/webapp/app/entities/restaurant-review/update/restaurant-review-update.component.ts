import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {RestaurantReviewFormService, RestaurantReviewFormGroup} from './restaurant-review-form.service';
import {IRestaurantReview} from '../restaurant-review.model';
import {RestaurantReviewService} from '../service/restaurant-review.service';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';

@Component({
  standalone: true,
  selector: 'jhi-restaurant-review-update',
  templateUrl: './restaurant-review-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class RestaurantReviewUpdateComponent implements OnInit {
  isSaving = false;
  restaurantReview: IRestaurantReview | null = null;

  customersSharedCollection: ICustomer[] = [];
  restaurantsSharedCollection: IRestaurant[] = [];

  editForm: RestaurantReviewFormGroup = this.restaurantReviewFormService.createRestaurantReviewFormGroup();

  constructor(
    protected restaurantReviewService: RestaurantReviewService,
    protected restaurantReviewFormService: RestaurantReviewFormService,
    protected customerService: CustomerService,
    protected restaurantService: RestaurantService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareCustomer = (o1: ICustomer | null, o2: ICustomer | null): boolean => this.customerService.compareCustomer(o1, o2);

  compareRestaurant = (o1: IRestaurant | null, o2: IRestaurant | null): boolean => this.restaurantService.compareRestaurant(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({restaurantReview}) => {
      this.restaurantReview = restaurantReview;
      if (restaurantReview) {
        this.updateForm(restaurantReview);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const restaurantReview = this.restaurantReviewFormService.getRestaurantReview(this.editForm);
    if (restaurantReview.id !== null) {
      this.subscribeToSaveResponse(this.restaurantReviewService.update(restaurantReview));
    } else {
      this.subscribeToSaveResponse(this.restaurantReviewService.create(restaurantReview));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRestaurantReview>>): void {
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

  protected updateForm(restaurantReview: IRestaurantReview): void {
    this.restaurantReview = restaurantReview;
    this.restaurantReviewFormService.resetForm(this.editForm, restaurantReview);

    this.customersSharedCollection = this.customerService.addCustomerToCollectionIfMissing<ICustomer>(
      this.customersSharedCollection,
      restaurantReview.customer
    );
    this.restaurantsSharedCollection = this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(
      this.restaurantsSharedCollection,
      restaurantReview.restaurant
    );
  }

  protected loadRelationshipsOptions(): void {
    this.customerService
      .query()
      .pipe(map((res: HttpResponse<ICustomer[]>) => res.body ?? []))
      .pipe(
        map((customers: ICustomer[]) =>
          this.customerService.addCustomerToCollectionIfMissing<ICustomer>(customers, this.restaurantReview?.customer)
        )
      )
      .subscribe((customers: ICustomer[]) => (this.customersSharedCollection = customers));

    this.restaurantService
      .query()
      .pipe(map((res: HttpResponse<IRestaurant[]>) => res.body ?? []))
      .pipe(
        map((restaurants: IRestaurant[]) =>
          this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(restaurants, this.restaurantReview?.restaurant)
        )
      )
      .subscribe((restaurants: IRestaurant[]) => (this.restaurantsSharedCollection = restaurants));
  }
}
