import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IRestaurantSchedule} from '../restaurant-schedule.model';
import {RestaurantScheduleService} from '../service/restaurant-schedule.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './restaurant-schedule-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class RestaurantScheduleDeleteDialogComponent {
  restaurantSchedule?: IRestaurantSchedule;

  constructor(protected restaurantScheduleService: RestaurantScheduleService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.restaurantScheduleService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
