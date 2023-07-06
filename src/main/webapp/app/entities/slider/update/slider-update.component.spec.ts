import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {SliderFormService} from './slider-form.service';
import {SliderService} from '../service/slider.service';
import {ISlider} from '../slider.model';

import {SliderUpdateComponent} from './slider-update.component';

describe('Slider Management Update Component', () => {
  let comp: SliderUpdateComponent;
  let fixture: ComponentFixture<SliderUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sliderFormService: SliderFormService;
  let sliderService: SliderService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), SliderUpdateComponent],
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
      .overrideTemplate(SliderUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SliderUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sliderFormService = TestBed.inject(SliderFormService);
    sliderService = TestBed.inject(SliderService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const slider: ISlider = {id: 456};

      activatedRoute.data = of({slider});
      comp.ngOnInit();

      expect(comp.slider).toEqual(slider);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISlider>>();
      const slider = {id: 123};
      jest.spyOn(sliderFormService, 'getSlider').mockReturnValue(slider);
      jest.spyOn(sliderService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({slider});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: slider}));
      saveSubject.complete();

      // THEN
      expect(sliderFormService.getSlider).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sliderService.update).toHaveBeenCalledWith(expect.objectContaining(slider));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISlider>>();
      const slider = {id: 123};
      jest.spyOn(sliderFormService, 'getSlider').mockReturnValue({id: null});
      jest.spyOn(sliderService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({slider: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: slider}));
      saveSubject.complete();

      // THEN
      expect(sliderFormService.getSlider).toHaveBeenCalled();
      expect(sliderService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISlider>>();
      const slider = {id: 123};
      jest.spyOn(sliderService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({slider});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sliderService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
