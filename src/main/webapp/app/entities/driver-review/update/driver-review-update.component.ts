import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {DriverReviewFormService, DriverReviewFormGroup} from './driver-review-form.service';
import {IDriverReview} from '../driver-review.model';
import {DriverReviewService} from '../service/driver-review.service';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';
import {IDriver} from 'app/entities/driver/driver.model';
import {DriverService} from 'app/entities/driver/service/driver.service';

@Component({
  standalone: true,
  selector: 'jhi-driver-review-update',
  templateUrl: './driver-review-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DriverReviewUpdateComponent implements OnInit {
  isSaving = false;
  driverReview: IDriverReview | null = null;

  customersSharedCollection: ICustomer[] = [];
  driversSharedCollection: IDriver[] = [];

  editForm: DriverReviewFormGroup = this.driverReviewFormService.createDriverReviewFormGroup();

  constructor(
    protected driverReviewService: DriverReviewService,
    protected driverReviewFormService: DriverReviewFormService,
    protected customerService: CustomerService,
    protected driverService: DriverService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareCustomer = (o1: ICustomer | null, o2: ICustomer | null): boolean => this.customerService.compareCustomer(o1, o2);

  compareDriver = (o1: IDriver | null, o2: IDriver | null): boolean => this.driverService.compareDriver(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({driverReview}) => {
      this.driverReview = driverReview;
      if (driverReview) {
        this.updateForm(driverReview);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const driverReview = this.driverReviewFormService.getDriverReview(this.editForm);
    if (driverReview.id !== null) {
      this.subscribeToSaveResponse(this.driverReviewService.update(driverReview));
    } else {
      this.subscribeToSaveResponse(this.driverReviewService.create(driverReview));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDriverReview>>): void {
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

  protected updateForm(driverReview: IDriverReview): void {
    this.driverReview = driverReview;
    this.driverReviewFormService.resetForm(this.editForm, driverReview);

    this.customersSharedCollection = this.customerService.addCustomerToCollectionIfMissing<ICustomer>(
      this.customersSharedCollection,
      driverReview.customer
    );
    this.driversSharedCollection = this.driverService.addDriverToCollectionIfMissing<IDriver>(
      this.driversSharedCollection,
      driverReview.driver
    );
  }

  protected loadRelationshipsOptions(): void {
    this.customerService
      .query()
      .pipe(map((res: HttpResponse<ICustomer[]>) => res.body ?? []))
      .pipe(
        map((customers: ICustomer[]) =>
          this.customerService.addCustomerToCollectionIfMissing<ICustomer>(customers, this.driverReview?.customer)
        )
      )
      .subscribe((customers: ICustomer[]) => (this.customersSharedCollection = customers));

    this.driverService
      .query()
      .pipe(map((res: HttpResponse<IDriver[]>) => res.body ?? []))
      .pipe(map((drivers: IDriver[]) => this.driverService.addDriverToCollectionIfMissing<IDriver>(drivers, this.driverReview?.driver)))
      .subscribe((drivers: IDriver[]) => (this.driversSharedCollection = drivers));
  }
}
