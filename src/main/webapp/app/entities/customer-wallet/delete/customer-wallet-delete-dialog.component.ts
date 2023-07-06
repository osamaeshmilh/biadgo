import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {ICustomerWallet} from '../customer-wallet.model';
import {CustomerWalletService} from '../service/customer-wallet.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './customer-wallet-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CustomerWalletDeleteDialogComponent {
  customerWallet?: ICustomerWallet;

  constructor(protected customerWalletService: CustomerWalletService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.customerWalletService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
