import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {RestaurantZonePriceFormService, RestaurantZonePriceFormGroup} from './restaurant-zone-price-form.service';
import {IRestaurantZonePrice} from '../restaurant-zone-price.model';
import {RestaurantZonePriceService} from '../service/restaurant-zone-price.service';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';
import {IZone} from 'app/entities/zone/zone.model';
import {ZoneService} from 'app/entities/zone/service/zone.service';

@Component({
  standalone: true,
  selector: 'jhi-restaurant-zone-price-update',
  templateUrl: './restaurant-zone-price-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class RestaurantZonePriceUpdateComponent implements OnInit {
  isSaving = false;
  restaurantZonePrice: IRestaurantZonePrice | null = null;

  restaurantsSharedCollection: IRestaurant[] = [];
  zonesSharedCollection: IZone[] = [];

  editForm: RestaurantZonePriceFormGroup = this.restaurantZonePriceFormService.createRestaurantZonePriceFormGroup();

  constructor(
    protected restaurantZonePriceService: RestaurantZonePriceService,
    protected restaurantZonePriceFormService: RestaurantZonePriceFormService,
    protected restaurantService: RestaurantService,
    protected zoneService: ZoneService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareRestaurant = (o1: IRestaurant | null, o2: IRestaurant | null): boolean => this.restaurantService.compareRestaurant(o1, o2);

  compareZone = (o1: IZone | null, o2: IZone | null): boolean => this.zoneService.compareZone(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({restaurantZonePrice}) => {
      this.restaurantZonePrice = restaurantZonePrice;
      if (restaurantZonePrice) {
        this.updateForm(restaurantZonePrice);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const restaurantZonePrice = this.restaurantZonePriceFormService.getRestaurantZonePrice(this.editForm);
    if (restaurantZonePrice.id !== null) {
      this.subscribeToSaveResponse(this.restaurantZonePriceService.update(restaurantZonePrice));
    } else {
      this.subscribeToSaveResponse(this.restaurantZonePriceService.create(restaurantZonePrice));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRestaurantZonePrice>>): void {
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

  protected updateForm(restaurantZonePrice: IRestaurantZonePrice): void {
    this.restaurantZonePrice = restaurantZonePrice;
    this.restaurantZonePriceFormService.resetForm(this.editForm, restaurantZonePrice);

    this.restaurantsSharedCollection = this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(
      this.restaurantsSharedCollection,
      restaurantZonePrice.restaurant
    );
    this.zonesSharedCollection = this.zoneService.addZoneToCollectionIfMissing<IZone>(this.zonesSharedCollection, restaurantZonePrice.zone);
  }

  protected loadRelationshipsOptions(): void {
    this.restaurantService
      .query()
      .pipe(map((res: HttpResponse<IRestaurant[]>) => res.body ?? []))
      .pipe(
        map((restaurants: IRestaurant[]) =>
          this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(restaurants, this.restaurantZonePrice?.restaurant)
        )
      )
      .subscribe((restaurants: IRestaurant[]) => (this.restaurantsSharedCollection = restaurants));

    this.zoneService
      .query()
      .pipe(map((res: HttpResponse<IZone[]>) => res.body ?? []))
      .pipe(map((zones: IZone[]) => this.zoneService.addZoneToCollectionIfMissing<IZone>(zones, this.restaurantZonePrice?.zone)))
      .subscribe((zones: IZone[]) => (this.zonesSharedCollection = zones));
  }
}
