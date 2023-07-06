import {Component, Input} from '@angular/core';
import {ActivatedRoute, RouterModule} from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import {DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe} from 'app/shared/date';
import {IRestaurantWallet} from '../restaurant-wallet.model';

@Component({
  standalone: true,
  selector: 'jhi-restaurant-wallet-detail',
  templateUrl: './restaurant-wallet-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class RestaurantWalletDetailComponent {
  @Input() restaurantWallet: IRestaurantWallet | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {
  }

  previousState(): void {
    window.history.back();
  }
}
