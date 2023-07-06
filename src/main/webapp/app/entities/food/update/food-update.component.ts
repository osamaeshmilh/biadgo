import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {FoodFormService, FoodFormGroup} from './food-form.service';
import {IFood} from '../food.model';
import {FoodService} from '../service/food.service';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';
import {ICategory} from 'app/entities/category/category.model';
import {CategoryService} from 'app/entities/category/service/category.service';

@Component({
  standalone: true,
  selector: 'jhi-food-update',
  templateUrl: './food-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FoodUpdateComponent implements OnInit {
  isSaving = false;
  food: IFood | null = null;

  restaurantsSharedCollection: IRestaurant[] = [];
  categoriesSharedCollection: ICategory[] = [];

  editForm: FoodFormGroup = this.foodFormService.createFoodFormGroup();

  constructor(
    protected foodService: FoodService,
    protected foodFormService: FoodFormService,
    protected restaurantService: RestaurantService,
    protected categoryService: CategoryService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareRestaurant = (o1: IRestaurant | null, o2: IRestaurant | null): boolean => this.restaurantService.compareRestaurant(o1, o2);

  compareCategory = (o1: ICategory | null, o2: ICategory | null): boolean => this.categoryService.compareCategory(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({food}) => {
      this.food = food;
      if (food) {
        this.updateForm(food);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const food = this.foodFormService.getFood(this.editForm);
    if (food.id !== null) {
      this.subscribeToSaveResponse(this.foodService.update(food));
    } else {
      this.subscribeToSaveResponse(this.foodService.create(food));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFood>>): void {
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

  protected updateForm(food: IFood): void {
    this.food = food;
    this.foodFormService.resetForm(this.editForm, food);

    this.restaurantsSharedCollection = this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(
      this.restaurantsSharedCollection,
      food.restaurant
    );
    this.categoriesSharedCollection = this.categoryService.addCategoryToCollectionIfMissing<ICategory>(
      this.categoriesSharedCollection,
      food.category
    );
  }

  protected loadRelationshipsOptions(): void {
    this.restaurantService
      .query()
      .pipe(map((res: HttpResponse<IRestaurant[]>) => res.body ?? []))
      .pipe(
        map((restaurants: IRestaurant[]) =>
          this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(restaurants, this.food?.restaurant)
        )
      )
      .subscribe((restaurants: IRestaurant[]) => (this.restaurantsSharedCollection = restaurants));

    this.categoryService
      .query()
      .pipe(map((res: HttpResponse<ICategory[]>) => res.body ?? []))
      .pipe(
        map((categories: ICategory[]) => this.categoryService.addCategoryToCollectionIfMissing<ICategory>(categories, this.food?.category))
      )
      .subscribe((categories: ICategory[]) => (this.categoriesSharedCollection = categories));
  }
}
