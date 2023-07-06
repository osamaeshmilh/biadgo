import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IPaymentMethod} from '../payment-method.model';
import {PaymentMethodService} from '../service/payment-method.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './payment-method-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class PaymentMethodDeleteDialogComponent {
  paymentMethod?: IPaymentMethod;

  constructor(protected paymentMethodService: PaymentMethodService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paymentMethodService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
