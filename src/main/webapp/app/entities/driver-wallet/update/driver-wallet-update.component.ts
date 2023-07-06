import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {DriverWalletFormService, DriverWalletFormGroup} from './driver-wallet-form.service';
import {IDriverWallet} from '../driver-wallet.model';
import {DriverWalletService} from '../service/driver-wallet.service';
import {IDriver} from 'app/entities/driver/driver.model';
import {DriverService} from 'app/entities/driver/service/driver.service';
import {WalletAction} from 'app/entities/enumerations/wallet-action.model';
import {PaymentType} from 'app/entities/enumerations/payment-type.model';

@Component({
  standalone: true,
  selector: 'jhi-driver-wallet-update',
  templateUrl: './driver-wallet-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DriverWalletUpdateComponent implements OnInit {
  isSaving = false;
  driverWallet: IDriverWallet | null = null;
  walletActionValues = Object.keys(WalletAction);
  paymentTypeValues = Object.keys(PaymentType);

  driversSharedCollection: IDriver[] = [];

  editForm: DriverWalletFormGroup = this.driverWalletFormService.createDriverWalletFormGroup();

  constructor(
    protected driverWalletService: DriverWalletService,
    protected driverWalletFormService: DriverWalletFormService,
    protected driverService: DriverService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareDriver = (o1: IDriver | null, o2: IDriver | null): boolean => this.driverService.compareDriver(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({driverWallet}) => {
      this.driverWallet = driverWallet;
      if (driverWallet) {
        this.updateForm(driverWallet);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const driverWallet = this.driverWalletFormService.getDriverWallet(this.editForm);
    if (driverWallet.id !== null) {
      this.subscribeToSaveResponse(this.driverWalletService.update(driverWallet));
    } else {
      this.subscribeToSaveResponse(this.driverWalletService.create(driverWallet));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDriverWallet>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(driverWallet: IDriverWallet): void {
    this.driverWallet = driverWallet;
    this.driverWalletFormService.resetForm(this.editForm, driverWallet);

    this.driversSharedCollection = this.driverService.addDriverToCollectionIfMissing<IDriver>(
      this.driversSharedCollection,
      driverWallet.driver
    );
  }

  protected loadRelationshipsOptions(): void {
    this.driverService
      .query()
      .pipe(map((res: HttpResponse<IDriver[]>) => res.body ?? []))
      .pipe(map((drivers: IDriver[]) => this.driverService.addDriverToCollectionIfMissing<IDriver>(drivers, this.driverWallet?.driver)))
      .subscribe((drivers: IDriver[]) => (this.driversSharedCollection = drivers));
  }
}
