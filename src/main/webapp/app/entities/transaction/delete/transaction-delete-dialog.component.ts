import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {ITransaction} from '../transaction.model';
import {TransactionService} from '../service/transaction.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './transaction-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class TransactionDeleteDialogComponent {
  transaction?: ITransaction;

  constructor(protected transactionService: TransactionService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.transactionService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
