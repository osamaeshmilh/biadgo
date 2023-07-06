import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {
  RestaurantDistancePriceFormService,
  RestaurantDistancePriceFormGroup
} from './restaurant-distance-price-form.service';
import {IRestaurantDistancePrice} from '../restaurant-distance-price.model';
import {RestaurantDistancePriceService} from '../service/restaurant-distance-price.service';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';

@Component({
  standalone: true,
  selector: 'jhi-restaurant-distance-price-update',
  templateUrl: './restaurant-distance-price-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class RestaurantDistancePriceUpdateComponent implements OnInit {
  isSaving = false;
  restaurantDistancePrice: IRestaurantDistancePrice | null = null;

  restaurantsSharedCollection: IRestaurant[] = [];

  editForm: RestaurantDistancePriceFormGroup = this.restaurantDistancePriceFormService.createRestaurantDistancePriceFormGroup();

  constructor(
    protected restaurantDistancePriceService: RestaurantDistancePriceService,
    protected restaurantDistancePriceFormService: RestaurantDistancePriceFormService,
    protected restaurantService: RestaurantService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareRestaurant = (o1: IRestaurant | null, o2: IRestaurant | null): boolean => this.restaurantService.compareRestaurant(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({restaurantDistancePrice}) => {
      this.restaurantDistancePrice = restaurantDistancePrice;
      if (restaurantDistancePrice) {
        this.updateForm(restaurantDistancePrice);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const restaurantDistancePrice = this.restaurantDistancePriceFormService.getRestaurantDistancePrice(this.editForm);
    if (restaurantDistancePrice.id !== null) {
      this.subscribeToSaveResponse(this.restaurantDistancePriceService.update(restaurantDistancePrice));
    } else {
      this.subscribeToSaveResponse(this.restaurantDistancePriceService.create(restaurantDistancePrice));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRestaurantDistancePrice>>): void {
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

  protected updateForm(restaurantDistancePrice: IRestaurantDistancePrice): void {
    this.restaurantDistancePrice = restaurantDistancePrice;
    this.restaurantDistancePriceFormService.resetForm(this.editForm, restaurantDistancePrice);

    this.restaurantsSharedCollection = this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(
      this.restaurantsSharedCollection,
      restaurantDistancePrice.restaurant
    );
  }

  protected loadRelationshipsOptions(): void {
    this.restaurantService
      .query()
      .pipe(map((res: HttpResponse<IRestaurant[]>) => res.body ?? []))
      .pipe(
        map((restaurants: IRestaurant[]) =>
          this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(restaurants, this.restaurantDistancePrice?.restaurant)
        )
      )
      .subscribe((restaurants: IRestaurant[]) => (this.restaurantsSharedCollection = restaurants));
  }
}
