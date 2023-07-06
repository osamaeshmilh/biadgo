import {Component, Input} from '@angular/core';
import {ActivatedRoute, RouterModule} from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import {DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe} from 'app/shared/date';
import {IFoodOrder} from '../food-order.model';

@Component({
  standalone: true,
  selector: 'jhi-food-order-detail',
  templateUrl: './food-order-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FoodOrderDetailComponent {
  @Input() foodOrder: IFoodOrder | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {
  }

  previousState(): void {
    window.history.back();
  }
}
