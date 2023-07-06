import {Component, Input} from '@angular/core';
import {ActivatedRoute, RouterModule} from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import {DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe} from 'app/shared/date';
import {IRestaurantDistancePrice} from '../restaurant-distance-price.model';

@Component({
  standalone: true,
  selector: 'jhi-restaurant-distance-price-detail',
  templateUrl: './restaurant-distance-price-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class RestaurantDistancePriceDetailComponent {
  @Input() restaurantDistancePrice: IRestaurantDistancePrice | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {
  }

  previousState(): void {
    window.history.back();
  }
}
