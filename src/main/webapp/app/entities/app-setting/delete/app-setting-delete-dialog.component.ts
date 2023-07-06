import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import {IAppSetting} from '../app-setting.model';
import {AppSettingService} from '../service/app-setting.service';
import {ITEM_DELETED_EVENT} from 'app/config/navigation.constants';

@Component({
  standalone: true,
  templateUrl: './app-setting-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class AppSettingDeleteDialogComponent {
  appSetting?: IAppSetting;

  constructor(protected appSettingService: AppSettingService, protected activeModal: NgbActiveModal) {
  }

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.appSettingService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
