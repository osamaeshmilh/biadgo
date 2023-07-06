import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {RestaurantWalletFormService, RestaurantWalletFormGroup} from './restaurant-wallet-form.service';
import {IRestaurantWallet} from '../restaurant-wallet.model';
import {RestaurantWalletService} from '../service/restaurant-wallet.service';
import {IRestaurant} from 'app/entities/restaurant/restaurant.model';
import {RestaurantService} from 'app/entities/restaurant/service/restaurant.service';
import {WalletAction} from 'app/entities/enumerations/wallet-action.model';
import {PaymentType} from 'app/entities/enumerations/payment-type.model';

@Component({
  standalone: true,
  selector: 'jhi-restaurant-wallet-update',
  templateUrl: './restaurant-wallet-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class RestaurantWalletUpdateComponent implements OnInit {
  isSaving = false;
  restaurantWallet: IRestaurantWallet | null = null;
  walletActionValues = Object.keys(WalletAction);
  paymentTypeValues = Object.keys(PaymentType);

  restaurantsSharedCollection: IRestaurant[] = [];

  editForm: RestaurantWalletFormGroup = this.restaurantWalletFormService.createRestaurantWalletFormGroup();

  constructor(
    protected restaurantWalletService: RestaurantWalletService,
    protected restaurantWalletFormService: RestaurantWalletFormService,
    protected restaurantService: RestaurantService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareRestaurant = (o1: IRestaurant | null, o2: IRestaurant | null): boolean => this.restaurantService.compareRestaurant(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({restaurantWallet}) => {
      this.restaurantWallet = restaurantWallet;
      if (restaurantWallet) {
        this.updateForm(restaurantWallet);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const restaurantWallet = this.restaurantWalletFormService.getRestaurantWallet(this.editForm);
    if (restaurantWallet.id !== null) {
      this.subscribeToSaveResponse(this.restaurantWalletService.update(restaurantWallet));
    } else {
      this.subscribeToSaveResponse(this.restaurantWalletService.create(restaurantWallet));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRestaurantWallet>>): void {
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

  protected updateForm(restaurantWallet: IRestaurantWallet): void {
    this.restaurantWallet = restaurantWallet;
    this.restaurantWalletFormService.resetForm(this.editForm, restaurantWallet);

    this.restaurantsSharedCollection = this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(
      this.restaurantsSharedCollection,
      restaurantWallet.restaurant
    );
  }

  protected loadRelationshipsOptions(): void {
    this.restaurantService
      .query()
      .pipe(map((res: HttpResponse<IRestaurant[]>) => res.body ?? []))
      .pipe(
        map((restaurants: IRestaurant[]) =>
          this.restaurantService.addRestaurantToCollectionIfMissing<IRestaurant>(restaurants, this.restaurantWallet?.restaurant)
        )
      )
      .subscribe((restaurants: IRestaurant[]) => (this.restaurantsSharedCollection = restaurants));
  }
}
