import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {DriverLocationFormService, DriverLocationFormGroup} from './driver-location-form.service';
import {IDriverLocation} from '../driver-location.model';
import {DriverLocationService} from '../service/driver-location.service';
import {IDriver} from 'app/entities/driver/driver.model';
import {DriverService} from 'app/entities/driver/service/driver.service';

@Component({
  standalone: true,
  selector: 'jhi-driver-location-update',
  templateUrl: './driver-location-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DriverLocationUpdateComponent implements OnInit {
  isSaving = false;
  driverLocation: IDriverLocation | null = null;

  driversSharedCollection: IDriver[] = [];

  editForm: DriverLocationFormGroup = this.driverLocationFormService.createDriverLocationFormGroup();

  constructor(
    protected driverLocationService: DriverLocationService,
    protected driverLocationFormService: DriverLocationFormService,
    protected driverService: DriverService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareDriver = (o1: IDriver | null, o2: IDriver | null): boolean => this.driverService.compareDriver(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({driverLocation}) => {
      this.driverLocation = driverLocation;
      if (driverLocation) {
        this.updateForm(driverLocation);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const driverLocation = this.driverLocationFormService.getDriverLocation(this.editForm);
    if (driverLocation.id !== null) {
      this.subscribeToSaveResponse(this.driverLocationService.update(driverLocation));
    } else {
      this.subscribeToSaveResponse(this.driverLocationService.create(driverLocation));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDriverLocation>>): void {
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

  protected updateForm(driverLocation: IDriverLocation): void {
    this.driverLocation = driverLocation;
    this.driverLocationFormService.resetForm(this.editForm, driverLocation);

    this.driversSharedCollection = this.driverService.addDriverToCollectionIfMissing<IDriver>(
      this.driversSharedCollection,
      driverLocation.driver
    );
  }

  protected loadRelationshipsOptions(): void {
    this.driverService
      .query()
      .pipe(map((res: HttpResponse<IDriver[]>) => res.body ?? []))
      .pipe(map((drivers: IDriver[]) => this.driverService.addDriverToCollectionIfMissing<IDriver>(drivers, this.driverLocation?.driver)))
      .subscribe((drivers: IDriver[]) => (this.driversSharedCollection = drivers));
  }
}
