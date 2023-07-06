jest.mock('@ng-bootstrap/ng-bootstrap');

import {ComponentFixture, TestBed, inject, fakeAsync, tick} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {of} from 'rxjs';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

import {DriverLocationService} from '../service/driver-location.service';

import {DriverLocationDeleteDialogComponent} from './driver-location-delete-dialog.component';

describe('DriverLocation Management Delete Component', () => {
  let comp: DriverLocationDeleteDialogComponent;
  let fixture: ComponentFixture<DriverLocationDeleteDialogComponent>;
  let service: DriverLocationService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, DriverLocationDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(DriverLocationDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DriverLocationDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(DriverLocationService);
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
