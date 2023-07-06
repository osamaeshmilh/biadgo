import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {RestaurantScheduleFormService, RestaurantScheduleFormGroup} from './restaurant-schedule-form.service';
import {IRestaurantSchedule} from '../restaurant-schedule.model';
import {RestaurantScheduleService} from '../service/restaurant-schedule.service';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';
import {DayOfWeek} from 'app/entities/enumerations/day-of-week.model';

@Component({
  standalone: true,
  selector: 'jhi-restaurant-schedule-update',
  templateUrl: './restaurant-schedule-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class RestaurantScheduleUpdateComponent implements OnInit {
  isSaving = false;
  restaurantSchedule: IRestaurantSchedule | null = null;
  dayOfWeekValues = Object.keys(DayOfWeek);

  restaurantsSharedCollection: IRestaurant[] = [];

  editForm: RestaurantScheduleFormGroup = this.restaurantScheduleFormService.createRestaurantScheduleFormGroup();

  constructor(
    protected restaurantScheduleService: RestaurantScheduleService,
    protected restaurantScheduleFormService: RestaurantScheduleFormService,
    protected restaurantService: RestaurantService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareRestaurant = (o1: IRestaurant | null, o2: IRestaurant | null): boolean => this.restaurantService.compareRestaurant(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({restaurantSchedule}) => {
      this.restaurantSchedule = restaurantSchedule;
      if (restaurantSchedule) {
        this.updateForm(restaurantSchedule);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const restaurantSchedule = this.restaurantScheduleFormService.getRestaurantSchedule(this.editForm);
    if (restaurantSchedule.id !== null) {
      this.subscribeToSaveResponse(this.restaurantScheduleService.update(restaurantSchedule));
    } else {
      this.subscribeToSaveResponse(this.restaurantScheduleService.create(restaurantSchedule));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRestaurantSchedule>>): void {
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

  protected updateForm(restaurantSchedule: IRestaurantSchedule): void {
    this.restaurantSchedule = restaurantSchedule;
    this.restaurantScheduleFormService.resetForm(this.editForm, restaurantSchedule);

    this.restaurantsSharedCollection = this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(
      this.restaurantsSharedCollection,
      restaurantSchedule.restaurant
    );
  }

  protected loadRelationshipsOptions(): void {
    this.restaurantService
      .query()
      .pipe(map((res: HttpResponse<IRestaurant[]>) => res.body ?? []))
      .pipe(
        map((restaurants: IRestaurant[]) =>
          this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(restaurants, this.restaurantSchedule?.restaurant)
        )
      )
      .subscribe((restaurants: IRestaurant[]) => (this.restaurantsSharedCollection = restaurants));
  }
}
