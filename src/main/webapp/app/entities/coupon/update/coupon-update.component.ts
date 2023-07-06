import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {CouponFormService, CouponFormGroup} from './coupon-form.service';
import {ICoupon} from '../coupon.model';
import {CouponService} from '../service/coupon.service';
import {CouponType} from 'app/entities/enumerations/coupon-type.model';

@Component({
  standalone: true,
  selector: 'jhi-coupon-update',
  templateUrl: './coupon-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CouponUpdateComponent implements OnInit {
  isSaving = false;
  coupon: ICoupon | null = null;
  couponTypeValues = Object.keys(CouponType);

  editForm: CouponFormGroup = this.couponFormService.createCouponFormGroup();

  constructor(
    protected couponService: CouponService,
    protected couponFormService: CouponFormService,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({coupon}) => {
      this.coupon = coupon;
      if (coupon) {
        this.updateForm(coupon);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const coupon = this.couponFormService.getCoupon(this.editForm);
    if (coupon.id !== null) {
      this.subscribeToSaveResponse(this.couponService.update(coupon));
    } else {
      this.subscribeToSaveResponse(this.couponService.create(coupon));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICoupon>>): void {
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

  protected updateForm(coupon: ICoupon): void {
    this.coupon = coupon;
    this.couponFormService.resetForm(this.editForm, coupon);
  }
}
