import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {ICoupon} from '../coupon.model';
import {CouponService} from '../service/coupon.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './coupon-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CouponDeleteDialogComponent {
  coupon?: ICoupon;

  constructor(protected couponService: CouponService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.couponService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
