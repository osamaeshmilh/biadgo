import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IRestaurantWallet} from '../restaurant-wallet.model';
import {RestaurantWalletService} from '../service/restaurant-wallet.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './restaurant-wallet-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class RestaurantWalletDeleteDialogComponent {
  restaurantWallet?: IRestaurantWallet;

  constructor(protected restaurantWalletService: RestaurantWalletService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.restaurantWalletService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
