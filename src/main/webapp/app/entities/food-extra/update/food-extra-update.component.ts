import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {FoodExtraFormService, FoodExtraFormGroup} from './food-extra-form.service';
import {IFoodExtra} from '../food-extra.model';
import {FoodExtraService} from '../service/food-extra.service';
import {IFood} from 'app/entities/food/food.model';
import {FoodService} from 'app/entities/food/service/food.service';

@Component({
  standalone: true,
  selector: 'jhi-food-extra-update',
  templateUrl: './food-extra-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FoodExtraUpdateComponent implements OnInit {
  isSaving = false;
  foodExtra: IFoodExtra | null = null;

  foodsSharedCollection: IFood[] = [];

  editForm: FoodExtraFormGroup = this.foodExtraFormService.createFoodExtraFormGroup();

  constructor(
    protected foodExtraService: FoodExtraService,
    protected foodExtraFormService: FoodExtraFormService,
    protected foodService: FoodService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareFood = (o1: IFood | null, o2: IFood | null): boolean => this.foodService.compareFood(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({foodExtra}) => {
      this.foodExtra = foodExtra;
      if (foodExtra) {
        this.updateForm(foodExtra);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const foodExtra = this.foodExtraFormService.getFoodExtra(this.editForm);
    if (foodExtra.id !== null) {
      this.subscribeToSaveResponse(this.foodExtraService.update(foodExtra));
    } else {
      this.subscribeToSaveResponse(this.foodExtraService.create(foodExtra));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFoodExtra>>): void {
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

  protected updateForm(foodExtra: IFoodExtra): void {
    this.foodExtra = foodExtra;
    this.foodExtraFormService.resetForm(this.editForm, foodExtra);

    this.foodsSharedCollection = this.foodService.addFoodToCollectionIfMissing<IFood>(this.foodsSharedCollection, foodExtra.food);
  }

  protected loadRelationshipsOptions(): void {
    this.foodService
      .query()
      .pipe(map((res: HttpResponse<IFood[]>) => res.body ?? []))
      .pipe(map((foods: IFood[]) => this.foodService.addFoodToCollectionIfMissing<IFood>(foods, this.foodExtra?.food)))
      .subscribe((foods: IFood[]) => (this.foodsSharedCollection = foods));
  }
}
