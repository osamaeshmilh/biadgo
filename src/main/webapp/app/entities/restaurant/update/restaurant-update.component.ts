import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {RestaurantFormService, RestaurantFormGroup} from './restaurant-form.service';
import {IRestaurant} from '../restaurant.model';
import {RestaurantService} from '../service/restaurant.service';
import {IUser} from 'app/entities/user/user.model';
import {UserService} from 'app/entities/user/user.service';
import {ICuisine} from 'app/entities/cuisine/cuisine.model';
import {CuisineService} from 'app/entities/cuisine/service/cuisine.service';
import {ICategory} from 'app/entities/category/category.model';
import {CategoryService} from 'app/entities/category/service/category.service';
import {DeliveryPriceType} from 'app/entities/enumerations/delivery-price-type.model';

@Component({
  standalone: true,
  selector: 'jhi-restaurant-update',
  templateUrl: './restaurant-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class RestaurantUpdateComponent implements OnInit {
  isSaving = false;
  restaurant: IRestaurant | null = null;
  deliveryPriceTypeValues = Object.keys(DeliveryPriceType);

  usersSharedCollection: IUser[] = [];
  cuisinesSharedCollection: ICuisine[] = [];
  categoriesSharedCollection: ICategory[] = [];

  editForm: RestaurantFormGroup = this.restaurantFormService.createRestaurantFormGroup();

  constructor(
    protected restaurantService: RestaurantService,
    protected restaurantFormService: RestaurantFormService,
    protected userService: UserService,
    protected cuisineService: CuisineService,
    protected categoryService: CategoryService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareUser = (o1: IUser | null, o2: IUser | null): boolean => this.userService.compareUser(o1, o2);

  compareCuisine = (o1: ICuisine | null, o2: ICuisine | null): boolean => this.cuisineService.compareCuisine(o1, o2);

  compareCategory = (o1: ICategory | null, o2: ICategory | null): boolean => this.categoryService.compareCategory(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({restaurant}) => {
      this.restaurant = restaurant;
      if (restaurant) {
        this.updateForm(restaurant);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const restaurant = this.restaurantFormService.getRestaurant(this.editForm);
    if (restaurant.id !== null) {
      this.subscribeToSaveResponse(this.restaurantService.update(restaurant));
    } else {
      this.subscribeToSaveResponse(this.restaurantService.create(restaurant));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRestaurant>>): void {
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

  protected updateForm(restaurant: IRestaurant): void {
    this.restaurant = restaurant;
    this.restaurantFormService.resetForm(this.editForm, restaurant);

    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing<IUser>(this.usersSharedCollection, restaurant.user);
    this.cuisinesSharedCollection = this.cuisineService.addCuisineToCollectionIfMissing<ICuisine>(
      this.cuisinesSharedCollection,
      restaurant.cuisine
    );
    this.categoriesSharedCollection = this.categoryService.addCategoryToCollectionIfMissing<ICategory>(
      this.categoriesSharedCollection,
      ...(restaurant.categories ?? [])
    );
  }

  protected loadRelationshipsOptions(): void {
    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(map((users: IUser[]) => this.userService.addUserToCollectionIfMissing<IUser>(users, this.restaurant?.user)))
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));

    this.cuisineService
      .query()
      .pipe(map((res: HttpResponse<ICuisine[]>) => res.body ?? []))
      .pipe(
        map((cuisines: ICuisine[]) => this.cuisineService.addCuisineToCollectionIfMissing<ICuisine>(cuisines, this.restaurant?.cuisine))
      )
      .subscribe((cuisines: ICuisine[]) => (this.cuisinesSharedCollection = cuisines));

    this.categoryService
      .query()
      .pipe(map((res: HttpResponse<ICategory[]>) => res.body ?? []))
      .pipe(
        map((categories: ICategory[]) =>
          this.categoryService.addCategoryToCollectionIfMissing<ICategory>(categories, ...(this.restaurant?.categories ?? []))
        )
      )
      .subscribe((categories: ICategory[]) => (this.categoriesSharedCollection = categories));
  }
}
