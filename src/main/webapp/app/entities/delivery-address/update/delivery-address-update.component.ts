import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {DeliveryAddressFormService, DeliveryAddressFormGroup} from './delivery-address-form.service';
import {IDeliveryAddress} from '../delivery-address.model';
import {DeliveryAddressService} from '../service/delivery-address.service';
import {ICustomer} from 'app/entities/customer/customer.model';
import {CustomerService} from 'app/entities/customer/service/customer.service';
import {IZone} from 'app/entities/zone/zone.model';
import {ZoneService} from 'app/entities/zone/service/zone.service';

@Component({
  standalone: true,
  selector: 'jhi-delivery-address-update',
  templateUrl: './delivery-address-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DeliveryAddressUpdateComponent implements OnInit {
  isSaving = false;
  deliveryAddress: IDeliveryAddress | null = null;

  customersSharedCollection: ICustomer[] = [];
  zonesSharedCollection: IZone[] = [];

  editForm: DeliveryAddressFormGroup = this.deliveryAddressFormService.createDeliveryAddressFormGroup();

  constructor(
    protected deliveryAddressService: DeliveryAddressService,
    protected deliveryAddressFormService: DeliveryAddressFormService,
    protected customerService: CustomerService,
    protected zoneService: ZoneService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareCustomer = (o1: ICustomer | null, o2: ICustomer | null): boolean => this.customerService.compareCustomer(o1, o2);

  compareZone = (o1: IZone | null, o2: IZone | null): boolean => this.zoneService.compareZone(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({deliveryAddress}) => {
      this.deliveryAddress = deliveryAddress;
      if (deliveryAddress) {
        this.updateForm(deliveryAddress);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const deliveryAddress = this.deliveryAddressFormService.getDeliveryAddress(this.editForm);
    if (deliveryAddress.id !== null) {
      this.subscribeToSaveResponse(this.deliveryAddressService.update(deliveryAddress));
    } else {
      this.subscribeToSaveResponse(this.deliveryAddressService.create(deliveryAddress));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeliveryAddress>>): void {
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

  protected updateForm(deliveryAddress: IDeliveryAddress): void {
    this.deliveryAddress = deliveryAddress;
    this.deliveryAddressFormService.resetForm(this.editForm, deliveryAddress);

    this.customersSharedCollection = this.customerService.addCustomerToCollectionIfMissing<ICustomer>(
      this.customersSharedCollection,
      deliveryAddress.customer
    );
    this.zonesSharedCollection = this.zoneService.addZoneToCollectionIfMissing<IZone>(this.zonesSharedCollection, deliveryAddress.zone);
  }

  protected loadRelationshipsOptions(): void {
    this.customerService
      .query()
      .pipe(map((res: HttpResponse<ICustomer[]>) => res.body ?? []))
      .pipe(
        map((customers: ICustomer[]) =>
          this.customerService.addCustomerToCollectionIfMissing<ICustomer>(customers, this.deliveryAddress?.customer)
        )
      )
      .subscribe((customers: ICustomer[]) => (this.customersSharedCollection = customers));

    this.zoneService
      .query()
      .pipe(map((res: HttpResponse<IZone[]>) => res.body ?? []))
      .pipe(map((zones: IZone[]) => this.zoneService.addZoneToCollectionIfMissing<IZone>(zones, this.deliveryAddress?.zone)))
      .subscribe((zones: IZone[]) => (this.zonesSharedCollection = zones));
  }
}
