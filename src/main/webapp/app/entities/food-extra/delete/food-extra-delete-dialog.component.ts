import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IFoodExtra} from '../food-extra.model';
import {FoodExtraService} from '../service/food-extra.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './food-extra-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FoodExtraDeleteDialogComponent {
  foodExtra?: IFoodExtra;

  constructor(protected foodExtraService: FoodExtraService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.foodExtraService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
