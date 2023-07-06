import {Component, Input} from '@angular/core';
import {ActivatedRoute, RouterModule} from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import {DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe} from 'app/shared/date';
import {ICuisine} from '../cuisine.model';

@Component({
  standalone: true,
  selector: 'jhi-cuisine-detail',
  templateUrl: './cuisine-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class CuisineDetailComponent {
  @Input() cuisine: ICuisine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {
  }

  previousState(): void {
    window.history.back();
  }
}
