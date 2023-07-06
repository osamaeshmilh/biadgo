import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IRestaurantZonePrice} from '../restaurant-zone-price.model';
import {RestaurantZonePriceService} from '../service/restaurant-zone-price.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './restaurant-zone-price-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class RestaurantZonePriceDeleteDialogComponent {
  restaurantZonePrice?: IRestaurantZonePrice;

  constructor(protected restaurantZonePriceService: RestaurantZonePriceService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.restaurantZonePriceService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
