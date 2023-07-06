import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IFoodIngredient} from '../food-ingredient.model';
import {FoodIngredientService} from '../service/food-ingredient.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './food-ingredient-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FoodIngredientDeleteDialogComponent {
  foodIngredient?: IFoodIngredient;

  constructor(protected foodIngredientService: FoodIngredientService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.foodIngredientService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
