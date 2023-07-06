import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IFoodOrder} from '../food-order.model';
import {FoodOrderService} from '../service/food-order.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './food-order-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FoodOrderDeleteDialogComponent {
  foodOrder?: IFoodOrder;

  constructor(protected foodOrderService: FoodOrderService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.foodOrderService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
