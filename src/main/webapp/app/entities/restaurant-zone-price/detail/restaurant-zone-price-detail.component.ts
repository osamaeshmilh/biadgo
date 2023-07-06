import {Component, Input} from '@angular/core';
import {ActivatedRoute, RouterModule} from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import {DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe} from 'app/shared/date';
import {IRestaurantZonePrice} from '../restaurant-zone-price.model';

@Component({
  standalone: true,
  selector: 'jhi-restaurant-zone-price-detail',
  templateUrl: './restaurant-zone-price-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class RestaurantZonePriceDetailComponent {
  @Input() restaurantZonePrice: IRestaurantZonePrice | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {
  }

  previousState(): void {
    window.history.back();
  }
}
