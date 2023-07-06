import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IAppSetting, NewAppSetting} from '../app-setting.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAppSetting for edit and NewAppSettingFormGroupInput for create.
 */
type AppSettingFormGroupInput = IAppSetting | PartialWithRequiredKeyOf<NewAppSetting>;

type AppSettingFormDefaults = Pick<NewAppSetting, 'id'>;

type AppSettingFormGroupContent = {
  id: FormControl<IAppSetting['id'] | NewAppSetting['id']>;
  name: FormControl<IAppSetting['name']>;
  key: FormControl<IAppSetting['key']>;
  type: FormControl<IAppSetting['type']>;
  value: FormControl<IAppSetting['value']>;
};

export type AppSettingFormGroup = FormGroup<AppSettingFormGroupContent>;

@Injectable({providedIn: 'root'})
export class AppSettingFormService {
  createAppSettingFormGroup(appSetting: AppSettingFormGroupInput = {id: null}): AppSettingFormGroup {
    const appSettingRawValue = {
      ...this.getFormDefaults(),
      ...appSetting,
    };
    return new FormGroup<AppSettingFormGroupContent>({
      id: new FormControl(
        {value: appSettingRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(appSettingRawValue.name),
      key: new FormControl(appSettingRawValue.key),
      type: new FormControl(appSettingRawValue.type),
      value: new FormControl(appSettingRawValue.value),
    });
  }

  getAppSetting(form: AppSettingFormGroup): IAppSetting | NewAppSetting {
    return form.getRawValue() as IAppSetting | NewAppSetting;
  }

  resetForm(form: AppSettingFormGroup, appSetting: AppSettingFormGroupInput): void {
    const appSettingRawValue = {...this.getFormDefaults(), ...appSetting};
    form.reset(
      {
        ...appSettingRawValue,
        id: {value: appSettingRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): AppSettingFormDefaults {
    return {
      id: null,
    };
  }
}
