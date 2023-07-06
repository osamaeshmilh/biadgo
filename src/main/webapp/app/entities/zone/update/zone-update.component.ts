import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {ZoneFormService, ZoneFormGroup} from './zone-form.service';
import {IZone} from '../zone.model';
import {ZoneService} from '../service/zone.service';
import {ICity} from 'app/entities/city/city.model';
import {CityService} from 'app/entities/city/service/city.service';

@Component({
  standalone: true,
  selector: 'jhi-zone-update',
  templateUrl: './zone-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ZoneUpdateComponent implements OnInit {
  isSaving = false;
  zone: IZone | null = null;

  citiesSharedCollection: ICity[] = [];

  editForm: ZoneFormGroup = this.zoneFormService.createZoneFormGroup();

  constructor(
    protected zoneService: ZoneService,
    protected zoneFormService: ZoneFormService,
    protected cityService: CityService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareCity = (o1: ICity | null, o2: ICity | null): boolean => this.cityService.compareCity(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({zone}) => {
      this.zone = zone;
      if (zone) {
        this.updateForm(zone);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const zone = this.zoneFormService.getZone(this.editForm);
    if (zone.id !== null) {
      this.subscribeToSaveResponse(this.zoneService.update(zone));
    } else {
      this.subscribeToSaveResponse(this.zoneService.create(zone));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IZone>>): void {
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

  protected updateForm(zone: IZone): void {
    this.zone = zone;
    this.zoneFormService.resetForm(this.editForm, zone);

    this.citiesSharedCollection = this.cityService.addCityToCollectionIfMissing<ICity>(this.citiesSharedCollection, zone.city);
  }

  protected loadRelationshipsOptions(): void {
    this.cityService
      .query()
      .pipe(map((res: HttpResponse<ICity[]>) => res.body ?? []))
      .pipe(map((cities: ICity[]) => this.cityService.addCityToCollectionIfMissing<ICity>(cities, this.zone?.city)))
      .subscribe((cities: ICity[]) => (this.citiesSharedCollection = cities));
  }
}
