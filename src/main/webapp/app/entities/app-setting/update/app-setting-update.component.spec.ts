import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of, Subject, from} from 'rxjs';

import {AppSettingFormService} from './app-setting-form.service';
import {AppSettingService} from '../service/app-setting.service';
import {IAppSetting} from '../app-setting.model';

import {AppSettingUpdateComponent} from './app-setting-update.component';

describe('AppSetting Management Update Component', () => {
  let comp: AppSettingUpdateComponent;
  let fixture: ComponentFixture<AppSettingUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let appSettingFormService: AppSettingFormService;
  let appSettingService: AppSettingService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), AppSettingUpdateComponent],
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
      .overrideTemplate(AppSettingUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AppSettingUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    appSettingFormService = TestBed.inject(AppSettingFormService);
    appSettingService = TestBed.inject(AppSettingService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const appSetting: IAppSetting = {id: 456};

      activatedRoute.data = of({appSetting});
      comp.ngOnInit();

      expect(comp.appSetting).toEqual(appSetting);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAppSetting>>();
      const appSetting = {id: 123};
      jest.spyOn(appSettingFormService, 'getAppSetting').mockReturnValue(appSetting);
      jest.spyOn(appSettingService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({appSetting});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: appSetting}));
      saveSubject.complete();

      // THEN
      expect(appSettingFormService.getAppSetting).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(appSettingService.update).toHaveBeenCalledWith(expect.objectContaining(appSetting));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAppSetting>>();
      const appSetting = {id: 123};
      jest.spyOn(appSettingFormService, 'getAppSetting').mockReturnValue({id: null});
      jest.spyOn(appSettingService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({appSetting: null});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({body: appSetting}));
      saveSubject.complete();

      // THEN
      expect(appSettingFormService.getAppSetting).toHaveBeenCalled();
      expect(appSettingService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAppSetting>>();
      const appSetting = {id: 123};
      jest.spyOn(appSettingService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({appSetting});
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(appSettingService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
