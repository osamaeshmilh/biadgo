import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {IOrderHistory, NewOrderHistory} from '../order-history.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOrderHistory for edit and NewOrderHistoryFormGroupInput for create.
 */
type OrderHistoryFormGroupInput = IOrderHistory | PartialWithRequiredKeyOf<NewOrderHistory>;

type OrderHistoryFormDefaults = Pick<NewOrderHistory, 'id'>;

type OrderHistoryFormGroupContent = {
  id: FormControl<IOrderHistory['id'] | NewOrderHistory['id']>;
  orderStatusFrom: FormControl<IOrderHistory['orderStatusFrom']>;
  orderStatusTo: FormControl<IOrderHistory['orderStatusTo']>;
  notes: FormControl<IOrderHistory['notes']>;
  order: FormControl<IOrderHistory['order']>;
};

export type OrderHistoryFormGroup = FormGroup<OrderHistoryFormGroupContent>;

@Injectable({providedIn: 'root'})
export class OrderHistoryFormService {
  createOrderHistoryFormGroup(orderHistory: OrderHistoryFormGroupInput = {id: null}): OrderHistoryFormGroup {
    const orderHistoryRawValue = {
      ...this.getFormDefaults(),
      ...orderHistory,
    };
    return new FormGroup<OrderHistoryFormGroupContent>({
      id: new FormControl(
        {value: orderHistoryRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      orderStatusFrom: new FormControl(orderHistoryRawValue.orderStatusFrom),
      orderStatusTo: new FormControl(orderHistoryRawValue.orderStatusTo),
      notes: new FormControl(orderHistoryRawValue.notes),
      order: new FormControl(orderHistoryRawValue.order),
    });
  }

  getOrderHistory(form: OrderHistoryFormGroup): IOrderHistory | NewOrderHistory {
    return form.getRawValue() as IOrderHistory | NewOrderHistory;
  }

  resetForm(form: OrderHistoryFormGroup, orderHistory: OrderHistoryFormGroupInput): void {
    const orderHistoryRawValue = {...this.getFormDefaults(), ...orderHistory};
    form.reset(
      {
        ...orderHistoryRawValue,
        id: {value: orderHistoryRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): OrderHistoryFormDefaults {
    return {
      id: null,
    };
  }
}
