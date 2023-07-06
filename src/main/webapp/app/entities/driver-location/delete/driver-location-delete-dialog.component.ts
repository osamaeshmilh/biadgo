import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IDriverLocation} from '../driver-location.model';
import {DriverLocationService} from '../service/driver-location.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './driver-location-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class DriverLocationDeleteDialogComponent {
  driverLocation?: IDriverLocation;

  constructor(protected driverLocationService: DriverLocationService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.driverLocationService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
