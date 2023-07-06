import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {FoodIngredientFormService, FoodIngredientFormGroup} from './food-ingredient-form.service';
import {IFoodIngredient} from '../food-ingredient.model';
import {FoodIngredientService} from '../service/food-ingredient.service';
import {IFood} from 'app/entities/food/food.model';
import {FoodService} from 'app/entities/food/service/food.service';

@Component({
  standalone: true,
  selector: 'jhi-food-ingredient-update',
  templateUrl: './food-ingredient-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FoodIngredientUpdateComponent implements OnInit {
  isSaving = false;
  foodIngredient: IFoodIngredient | null = null;

  foodsSharedCollection: IFood[] = [];

  editForm: FoodIngredientFormGroup = this.foodIngredientFormService.createFoodIngredientFormGroup();

  constructor(
    protected foodIngredientService: FoodIngredientService,
    protected foodIngredientFormService: FoodIngredientFormService,
    protected foodService: FoodService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareFood = (o1: IFood | null, o2: IFood | null): boolean => this.foodService.compareFood(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({foodIngredient}) => {
      this.foodIngredient = foodIngredient;
      if (foodIngredient) {
        this.updateForm(foodIngredient);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const foodIngredient = this.foodIngredientFormService.getFoodIngredient(this.editForm);
    if (foodIngredient.id !== null) {
      this.subscribeToSaveResponse(this.foodIngredientService.update(foodIngredient));
    } else {
      this.subscribeToSaveResponse(this.foodIngredientService.create(foodIngredient));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFoodIngredient>>): void {
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

  protected updateForm(foodIngredient: IFoodIngredient): void {
    this.foodIngredient = foodIngredient;
    this.foodIngredientFormService.resetForm(this.editForm, foodIngredient);

    this.foodsSharedCollection = this.foodService.addFoodToCollectionIfMissing<IFood>(this.foodsSharedCollection, foodIngredient.food);
  }

  protected loadRelationshipsOptions(): void {
    this.foodService
      .query()
      .pipe(map((res: HttpResponse<IFood[]>) => res.body ?? []))
      .pipe(map((foods: IFood[]) => this.foodService.addFoodToCollectionIfMissing<IFood>(foods, this.foodIngredient?.food)))
      .subscribe((foods: IFood[]) => (this.foodsSharedCollection = foods));
  }
}
