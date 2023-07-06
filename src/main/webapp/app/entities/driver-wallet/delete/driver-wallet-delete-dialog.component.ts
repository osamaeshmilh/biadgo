import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IDriverWallet} from '../driver-wallet.model';
import {DriverWalletService} from '../service/driver-wallet.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './driver-wallet-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class DriverWalletDeleteDialogComponent {
  driverWallet?: IDriverWallet;

  constructor(protected driverWalletService: DriverWalletService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.driverWalletService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
