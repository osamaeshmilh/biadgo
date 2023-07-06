import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {ActivationFormService} from './activation-form.service';
import {ActivationService} from '../service/activation.service';
import {IActivation} from '../activation.model';

import {ActivationUpdateComponent} from './activation-update.component';

describe('Activation Management Update Component', () => {
  let comp: ActivationUpdateComponent;
  let fixture: ComponentFixture<ActivationUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let activationFormService: ActivationFormService;
  let activationService: ActivationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ActivationUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ActivationUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ActivationUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    activationFormService = TestBed.inject(ActivationFormService);
    activationService = TestBed.inject(ActivationService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const activation: IActivation = {id: 456};

      activatedRoute.data = of({activation});
      comp.ngOnInit();

      expect(comp.activation).toEqual(activation);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IActivation>>();
      const activation = {id: 123};
      jest.spyOn(activationFormService, 'getActivation').mockReturnValue(activation);
      jest.spyOn(activationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({activation});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: activation}));
      saveSubject.complete();

      // THEN
      expect(activationFormService.getActivation).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(activationService.update).toHaveBeenCalledWith(expect.objectContaining(activation));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IActivation>>();
      const activation = {id: 123};
      jest.spyOn(activationFormService, 'getActivation').mockReturnValue({id: null});
      jest.spyOn(activationService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({activation: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: activation}));
      saveSubject.complete();

      // THEN
      expect(activationFormService.getActivation).toHaveBeenCalled();
      expect(activationService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IActivation>>();
      const activation = {id: 123};
      jest.spyOn(activationService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({activation});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(activationService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
