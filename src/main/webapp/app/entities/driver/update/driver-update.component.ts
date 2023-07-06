import {Component, OnInit, ElementRef} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {DriverFormService, DriverFormGroup} from './driver-form.service';
import {IDriver} from '../driver.model';
import {DriverService} from '../service/driver.service';
import {AlertError} from 'app/shared/alert/alert-error.model';
import {EventManager, EventWithContent} from 'app/core/util/event-manager.service';
import {DataUtils, FileLoadError} from 'app/core/util/data-util.service';
import {IUser} from 'app/entities/user/user.model';
import {UserService} from 'app/entities/user/user.service';
import {IZone} from 'app/entities/zone/zone.model';
import {ZoneService} from 'app/entities/zone/service/zone.service';
import {DriverType} from 'app/entities/enumerations/driver-type.model';
import {DriverPaymentType} from 'app/entities/enumerations/driver-payment-type.model';
import {VehicleType} from 'app/entities/enumerations/vehicle-type.model';
import {DriverStatus} from 'app/entities/enumerations/driver-status.model';

@Component({
  standalone: true,
  selector: 'jhi-driver-update',
  templateUrl: './driver-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DriverUpdateComponent implements OnInit {
  isSaving = false;
  driver: IDriver | null = null;
  driverTypeValues = Object.keys(DriverType);
  driverPaymentTypeValues = Object.keys(DriverPaymentType);
  vehicleTypeValues = Object.keys(VehicleType);
  driverStatusValues = Object.keys(DriverStatus);

  usersSharedCollection: IUser[] = [];
  zonesSharedCollection: IZone[] = [];

  editForm: DriverFormGroup = this.driverFormService.createDriverFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected driverService: DriverService,
    protected driverFormService: DriverFormService,
    protected userService: UserService,
    protected zoneService: ZoneService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareUser = (o1: IUser | null, o2: IUser | null): boolean => this.userService.compareUser(o1, o2);

  compareZone = (o1: IZone | null, o2: IZone | null): boolean => this.zoneService.compareZone(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({driver}) => {
      this.driver = driver;
      if (driver) {
        this.updateForm(driver);
      }

      this.loadRelationshipsOptions();
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('biadjoApp.error', {message: err.message})),
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const driver = this.driverFormService.getDriver(this.editForm);
    if (driver.id !== null) {
      this.subscribeToSaveResponse(this.driverService.update(driver));
    } else {
      this.subscribeToSaveResponse(this.driverService.create(driver));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDriver>>): void {
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

  protected updateForm(driver: IDriver): void {
    this.driver = driver;
    this.driverFormService.resetForm(this.editForm, driver);

    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing<IUser>(this.usersSharedCollection, driver.user);
    this.zonesSharedCollection = this.zoneService.addZoneToCollectionIfMissing<IZone>(this.zonesSharedCollection, driver.zone);
  }

  protected loadRelationshipsOptions(): void {
    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(map((users: IUser[]) => this.userService.addUserToCollectionIfMissing<IUser>(users, this.driver?.user)))
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));

    this.zoneService
      .query()
      .pipe(map((res: HttpResponse<IZone[]>) => res.body ?? []))
      .pipe(map((zones: IZone[]) => this.zoneService.addZoneToCollectionIfMissing<IZone>(zones, this.driver?.zone)))
      .subscribe((zones: IZone[]) => (this.zonesSharedCollection = zones));
  }
}
