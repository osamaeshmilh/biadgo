import {Component, Input} from '@angular/core';
import {ActivatedRoute, RouterModule} from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import {DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe} from 'app/shared/date';
import {IRestaurantReview} from '../restaurant-review.model';

@Component({
  standalone: true,
  selector: 'jhi-restaurant-review-detail',
  templateUrl: './restaurant-review-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class RestaurantReviewDetailComponent {
  @Input() restaurantReview: IRestaurantReview | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {
  }

  previousState(): void {
    window.history.back();
  }
}
