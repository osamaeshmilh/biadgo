import {Component, Input} from '@angular/core';
import {ActivatedRoute, RouterModule} from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import {DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe} from 'app/shared/date';
import {IFoodIngredient} from '../food-ingredient.model';

@Component({
  standalone: true,
  selector: 'jhi-food-ingredient-detail',
  templateUrl: './food-ingredient-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FoodIngredientDetailComponent {
  @Input() foodIngredient: IFoodIngredient | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {
  }

  previousState(): void {
    window.history.back();
  }
}
