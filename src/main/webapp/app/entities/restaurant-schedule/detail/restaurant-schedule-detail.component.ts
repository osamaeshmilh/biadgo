import {Component, Input} from '@angular/core';
import {ActivatedRoute, RouterModule} from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import {DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe} from 'app/shared/date';
import {IRestaurantSchedule} from '../restaurant-schedule.model';

@Component({
  standalone: true,
  selector: 'jhi-restaurant-schedule-detail',
  templateUrl: './restaurant-schedule-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class RestaurantScheduleDetailComponent {
  @Input() restaurantSchedule: IRestaurantSchedule | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {
  }

  previousState(): void {
    window.history.back();
  }
}
