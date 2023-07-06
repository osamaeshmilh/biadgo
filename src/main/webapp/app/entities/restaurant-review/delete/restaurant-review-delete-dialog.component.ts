import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IRestaurantReview} from '../restaurant-review.model';
import {RestaurantReviewService} from '../service/restaurant-review.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './restaurant-review-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class RestaurantReviewDeleteDialogComponent {
  restaurantReview?: IRestaurantReview;

  constructor(protected restaurantReviewService: RestaurantReviewService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.restaurantReviewService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
