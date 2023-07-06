import {Component, Input} from '@angular/core';
import {ActivatedRoute, RouterModule} from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import {DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe} from 'app/shared/date';
import {ICustomerWallet} from '../customer-wallet.model';

@Component({
  standalone: true,
  selector: 'jhi-customer-wallet-detail',
  templateUrl: './customer-wallet-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class CustomerWalletDetailComponent {
  @Input() customerWallet: ICustomerWallet | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {
  }

  previousState(): void {
    window.history.back();
  }
}
