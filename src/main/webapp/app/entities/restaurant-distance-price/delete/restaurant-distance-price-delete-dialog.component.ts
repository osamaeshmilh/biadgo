import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IRestaurantDistancePrice} from '../restaurant-distance-price.model';
import {RestaurantDistancePriceService} from '../service/restaurant-distance-price.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './restaurant-distance-price-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class RestaurantDistancePriceDeleteDialogComponent {
  restaurantDistancePrice?: IRestaurantDistancePrice;

  constructor(protected restaurantDistancePriceService: RestaurantDistancePriceService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.restaurantDistancePriceService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
