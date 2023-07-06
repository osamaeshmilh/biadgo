import {Component, Input} from '@angular/core';
import {ActivatedRoute, RouterModule} from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import {DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe} from 'app/shared/date';
import {IFood} from '../food.model';

@Component({
  standalone: true,
  selector: 'jhi-food-detail',
  templateUrl: './food-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FoodDetailComponent {
  @Input() food: IFood | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {
  }

  previousState(): void {
    window.history.back();
  }
}
