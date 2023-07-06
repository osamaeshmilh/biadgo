import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IFoodImage} from '../food-image.model';
import {FoodImageService} from '../service/food-image.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './food-image-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FoodImageDeleteDialogComponent {
  foodImage?: IFoodImage;

  constructor(protected foodImageService: FoodImageService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.foodImageService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
