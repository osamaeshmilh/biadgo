import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {INotification, NewNotification} from '../notification.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts INotification for edit and NewNotificationFormGroupInput for create.
 */
type NotificationFormGroupInput = INotification | PartialWithRequiredKeyOf<NewNotification>;

type NotificationFormDefaults = Pick<NewNotification, 'id' | 'isRead'>;

type NotificationFormGroupContent = {
  id: FormControl<INotification['id'] | NewNotification['id']>;
  title: FormControl<INotification['title']>;
  details: FormControl<INotification['details']>;
  isRead: FormControl<INotification['isRead']>;
  customerId: FormControl<INotification['customerId']>;
};

export type NotificationFormGroup = FormGroup<NotificationFormGroupContent>;

@Injectable({providedIn: 'root'})
export class NotificationFormService {
  createNotificationFormGroup(notification: NotificationFormGroupInput = {id: null}): NotificationFormGroup {
    const notificationRawValue = {
      ...this.getFormDefaults(),
      ...notification,
    };
    return new FormGroup<NotificationFormGroupContent>({
      id: new FormControl(
        {value: notificationRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      title: new FormControl(notificationRawValue.title),
      details: new FormControl(notificationRawValue.details),
      isRead: new FormControl(notificationRawValue.isRead),
      customerId: new FormControl(notificationRawValue.customerId),
    });
  }

  getNotification(form: NotificationFormGroup): INotification | NewNotification {
    return form.getRawValue() as INotification | NewNotification;
  }

  resetForm(form: NotificationFormGroup, notification: NotificationFormGroupInput): void {
    const notificationRawValue = {...this.getFormDefaults(), ...notification};
    form.reset(
      {
        ...notificationRawValue,
        id: {value: notificationRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): NotificationFormDefaults {
    return {
      id: null,
      isRead: false,
    };
  }
}
