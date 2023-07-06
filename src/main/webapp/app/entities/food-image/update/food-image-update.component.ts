import {Component, OnInit, ElementRef} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {FoodImageFormService, FoodImageFormGroup} from './food-image-form.service';
import {IFoodImage} from '../food-image.model';
import {FoodImageService} from '../service/food-image.service';
import {AlertError} from 'app/shared/alert/alert-error.model';
import {EventManager, EventWithContent} from 'app/core/util/event-manager.service';
import {DataUtils, FileLoadError} from 'app/core/util/data-util.service';
import {IFood} from 'app/entities/food/food.model';
import {FoodService} from 'app/entities/food/service/food.service';

@Component({
  standalone: true,
  selector: 'jhi-food-image-update',
  templateUrl: './food-image-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FoodImageUpdateComponent implements OnInit {
  isSaving = false;
  foodImage: IFoodImage | null = null;

  foodsSharedCollection: IFood[] = [];

  editForm: FoodImageFormGroup = this.foodImageFormService.createFoodImageFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected foodImageService: FoodImageService,
    protected foodImageFormService: FoodImageFormService,
    protected foodService: FoodService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute
  ) {
  }

  compareFood = (o1: IFood | null, o2: IFood | null): boolean => this.foodService.compareFood(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({foodImage}) => {
      this.foodImage = foodImage;
      if (foodImage) {
        this.updateForm(foodImage);
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
    const foodImage = this.foodImageFormService.getFoodImage(this.editForm);
    if (foodImage.id !== null) {
      this.subscribeToSaveResponse(this.foodImageService.update(foodImage));
    } else {
      this.subscribeToSaveResponse(this.foodImageService.create(foodImage));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFoodImage>>): void {
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

  protected updateForm(foodImage: IFoodImage): void {
    this.foodImage = foodImage;
    this.foodImageFormService.resetForm(this.editForm, foodImage);

    this.foodsSharedCollection = this.foodService.addFoodToCollectionIfMissing<IFood>(this.foodsSharedCollection, foodImage.food);
  }

  protected loadRelationshipsOptions(): void {
    this.foodService
      .query()
      .pipe(map((res: HttpResponse<IFood[]>) => res.body ?? []))
      .pipe(map((foods: IFood[]) => this.foodService.addFoodToCollectionIfMissing<IFood>(foods, this.foodImage?.food)))
      .subscribe((foods: IFood[]) => (this.foodsSharedCollection = foods));
  }
}
