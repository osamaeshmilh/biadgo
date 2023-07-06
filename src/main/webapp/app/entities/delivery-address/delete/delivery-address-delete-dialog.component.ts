import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IDeliveryAddress} from '../delivery-address.model';
import {DeliveryAddressService} from '../service/delivery-address.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './delivery-address-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class DeliveryAddressDeleteDialogComponent {
  deliveryAddress?: IDeliveryAddress;

  constructor(protected deliveryAddressService: DeliveryAddressService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.deliveryAddressService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
