jest.mock('@ng-bootstrap/ng-bootstrap');

import {ComponentFixture, TestBed, inject, fakeAsync, tick} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {of} from 'rxjs';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import {FoodImageService} from '../service/food-image.service';

import {FoodImageDeleteDialogComponent} from './food-image-delete-dialog.component';

describe('FoodImage Management Delete Component', () => {
  let comp: FoodImageDeleteDialogComponent;
  let fixture: ComponentFixture<FoodImageDeleteDialogComponent>;
  let service: FoodImageService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, FoodImageDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(FoodImageDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FoodImageDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(FoodImageService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({body: {}})));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      })
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
