import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IDriverReview} from '../driver-review.model';
import {DriverReviewService} from '../service/driver-review.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './driver-review-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class DriverReviewDeleteDialogComponent {
  driverReview?: IDriverReview;

  constructor(protected driverReviewService: DriverReviewService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.driverReviewService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
