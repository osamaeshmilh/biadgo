import {Component, Input} from '@angular/core';
import {ActivatedRoute, RouterModule} from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import {DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe} from 'app/shared/date';
import {IFavoriteRestaurant} from '../favorite-restaurant.model';

@Component({
  standalone: true,
  selector: 'jhi-favorite-restaurant-detail',
  templateUrl: './favorite-restaurant-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FavoriteRestaurantDetailComponent {
  @Input() favoriteRestaurant: IFavoriteRestaurant | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {
  }

  previousState(): void {
    window.history.back();
  }
}
