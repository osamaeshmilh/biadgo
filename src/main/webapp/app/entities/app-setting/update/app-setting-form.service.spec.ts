import {TestBed} from '@angular/core/testing';

import {sampleWithRequiredData, sampleWithNewData} from '../app-setting.test-samples';

import {AppSettingFormService} from './app-setting-form.service';

describe('AppSetting Form Service', () => {
  let service: AppSettingFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppSettingFormService);
  });

  describe('Service methods', () => {
    describe('createAppSettingFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAppSettingFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            key: expect.any(Object),
            type: expect.any(Object),
            value: expect.any(Object),
          })
        );
      });

      it('passing IAppSetting should create a new form with FormGroup', () => {
        const formGroup = service.createAppSettingFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            key: expect.any(Object),
            type: expect.any(Object),
            value: expect.any(Object),
          })
        );
      });
    });

    describe('getAppSetting', () => {
      it('should return NewAppSetting for default AppSetting initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createAppSettingFormGroup(sampleWithNewData);

        const appSetting = service.getAppSetting(formGroup) as any;

        expect(appSetting).toMatchObject(sampleWithNewData);
      });

      it('should return NewAppSetting for empty AppSetting initial value', () => {
        const formGroup = service.createAppSettingFormGroup();

        const appSetting = service.getAppSetting(formGroup) as any;

        expect(appSetting).toMatchObject({});
      });

      it('should return IAppSetting', () => {
        const formGroup = service.createAppSettingFormGroup(sampleWithRequiredData);

        const appSetting = service.getAppSetting(formGroup) as any;

        expect(appSetting).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAppSetting should not enable id FormControl', () => {
        const formGroup = service.createAppSettingFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAppSetting should disable id FormControl', () => {
        const formGroup = service.createAppSettingFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, {id: null});

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
