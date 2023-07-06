import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {FavoriteRestaurantFormService, FavoriteRestaurantFormGroup} from './favorite-restaurant-form.service';
import {IFavoriteRestaurant} from '../favorite-restaurant.model';
import {FavoriteRestaurantService} from '../service/favorite-restaurant.service';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';

@Component({
  standalone: true,
  selector: 'jhi-favorite-restaurant-update',
  templateUrl: './favorite-restaurant-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FavoriteRestaurantUpdateComponent implements OnInit {
  isSaving = false;
  favoriteRestaurant: IFavoriteRestaurant | null = null;

  customersSharedCollection: ICustomer[] = [];
  restaurantsSharedCollection: IRestaurant[] = [];

  editForm: FavoriteRestaurantFormGroup = this.favoriteRestaurantFormService.createFavoriteRestaurantFormGroup();

  constructor(
    protected favoriteRestaurantService: FavoriteRestaurantService,
    protected favoriteRestaurantFormService: FavoriteRestaurantFormService,
    protected customerService: CustomerService,
    protected restaurantService: RestaurantService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareCustomer = (o1: ICustomer | null, o2: ICustomer | null): boolean => this.customerService.compareCustomer(o1, o2);

  compareRestaurant = (o1: IRestaurant | null, o2: IRestaurant | null): boolean => this.restaurantService.compareRestaurant(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({favoriteRestaurant}) => {
      this.favoriteRestaurant = favoriteRestaurant;
      if (favoriteRestaurant) {
        this.updateForm(favoriteRestaurant);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const favoriteRestaurant = this.favoriteRestaurantFormService.getFavoriteRestaurant(this.editForm);
    if (favoriteRestaurant.id !== null) {
      this.subscribeToSaveResponse(this.favoriteRestaurantService.update(favoriteRestaurant));
    } else {
      this.subscribeToSaveResponse(this.favoriteRestaurantService.create(favoriteRestaurant));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFavoriteRestaurant>>): void {
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

  protected updateForm(favoriteRestaurant: IFavoriteRestaurant): void {
    this.favoriteRestaurant = favoriteRestaurant;
    this.favoriteRestaurantFormService.resetForm(this.editForm, favoriteRestaurant);

    this.customersSharedCollection = this.customerService.addCustomerToCollectionIfMissing<ICustomer>(
      this.customersSharedCollection,
      favoriteRestaurant.customer
    );
    this.restaurantsSharedCollection = this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(
      this.restaurantsSharedCollection,
      favoriteRestaurant.restaurant
    );
  }

  protected loadRelationshipsOptions(): void {
    this.customerService
      .query()
      .pipe(map((res: HttpResponse<ICustomer[]>) => res.body ?? []))
      .pipe(
        map((customers: ICustomer[]) =>
          this.customerService.addCustomerToCollectionIfMissing<ICustomer>(customers, this.favoriteRestaurant?.customer)
        )
      )
      .subscribe((customers: ICustomer[]) => (this.customersSharedCollection = customers));

    this.restaurantService
      .query()
      .pipe(map((res: HttpResponse<IRestaurant[]>) => res.body ?? []))
      .pipe(
        map((restaurants: IRestaurant[]) =>
          this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(restaurants, this.favoriteRestaurant?.restaurant)
        )
      )
      .subscribe((restaurants: IRestaurant[]) => (this.restaurantsSharedCollection = restaurants));
  }
}
