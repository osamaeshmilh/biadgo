import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import dayjs from 'dayjs/esm';
import {DATE_TIME_FORMAT} from 'app/config/input.constants';
import {IOrder, NewOrder} from '../order.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOrder for edit and NewOrderFormGroupInput for create.
 */
type OrderFormGroupInput = IOrder | PartialWithRequiredKeyOf<NewOrder>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IOrder | NewOrder> = Omit<T, 'deliveredAt'> & {
  deliveredAt?: string | null;
};

type OrderFormRawValue = FormValueOf<IOrder>;

type NewOrderFormRawValue = FormValueOf<NewOrder>;

type OrderFormDefaults = Pick<NewOrder, 'id' | 'isApprovedByDriver' | 'isApprovedByRestaurant' | 'isPayed' | 'deliveredAt'>;

type OrderFormGroupContent = {
  id: FormControl<OrderFormRawValue['id'] | NewOrder['id']>;
  orderNo: FormControl<OrderFormRawValue['orderNo']>;
  companyAmount: FormControl<OrderFormRawValue['companyAmount']>;
  restaurantAmount: FormControl<OrderFormRawValue['restaurantAmount']>;
  deliveryFee: FormControl<OrderFormRawValue['deliveryFee']>;
  itemsPrice: FormControl<OrderFormRawValue['itemsPrice']>;
  discount: FormControl<OrderFormRawValue['discount']>;
  total: FormControl<OrderFormRawValue['total']>;
  latitude: FormControl<OrderFormRawValue['latitude']>;
  longitude: FormControl<OrderFormRawValue['longitude']>;
  plusCode: FormControl<OrderFormRawValue['plusCode']>;
  paymentType: FormControl<OrderFormRawValue['paymentType']>;
  isApprovedByDriver: FormControl<OrderFormRawValue['isApprovedByDriver']>;
  isApprovedByRestaurant: FormControl<OrderFormRawValue['isApprovedByRestaurant']>;
  isPayed: FormControl<OrderFormRawValue['isPayed']>;
  amountRecived: FormControl<OrderFormRawValue['amountRecived']>;
  amountRemaining: FormControl<OrderFormRawValue['amountRemaining']>;
  customerNotes: FormControl<OrderFormRawValue['customerNotes']>;
  restaurantNotes: FormControl<OrderFormRawValue['restaurantNotes']>;
  driverNotes: FormControl<OrderFormRawValue['driverNotes']>;
  orderStatus: FormControl<OrderFormRawValue['orderStatus']>;
  orderType: FormControl<OrderFormRawValue['orderType']>;
  deliveredAt: FormControl<OrderFormRawValue['deliveredAt']>;
  notes: FormControl<OrderFormRawValue['notes']>;
  customer: FormControl<OrderFormRawValue['customer']>;
  coupon: FormControl<OrderFormRawValue['coupon']>;
  driver: FormControl<OrderFormRawValue['driver']>;
  deliveryAddress: FormControl<OrderFormRawValue['deliveryAddress']>;
  restaurant: FormControl<OrderFormRawValue['restaurant']>;
};

export type OrderFormGroup = FormGroup<OrderFormGroupContent>;

@Injectable({providedIn: 'root'})
export class OrderFormService {
  createOrderFormGroup(order: OrderFormGroupInput = {id: null}): OrderFormGroup {
    const orderRawValue = this.convertOrderToOrderRawValue({
      ...this.getFormDefaults(),
      ...order,
    });
    return new FormGroup<OrderFormGroupContent>({
      id: new FormControl(
        {value: orderRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      orderNo: new FormControl(orderRawValue.orderNo),
      companyAmount: new FormControl(orderRawValue.companyAmount),
      restaurantAmount: new FormControl(orderRawValue.restaurantAmount),
      deliveryFee: new FormControl(orderRawValue.deliveryFee),
      itemsPrice: new FormControl(orderRawValue.itemsPrice),
      discount: new FormControl(orderRawValue.discount),
      total: new FormControl(orderRawValue.total),
      latitude: new FormControl(orderRawValue.latitude),
      longitude: new FormControl(orderRawValue.longitude),
      plusCode: new FormControl(orderRawValue.plusCode),
      paymentType: new FormControl(orderRawValue.paymentType),
      isApprovedByDriver: new FormControl(orderRawValue.isApprovedByDriver),
      isApprovedByRestaurant: new FormControl(orderRawValue.isApprovedByRestaurant),
      isPayed: new FormControl(orderRawValue.isPayed),
      amountRecived: new FormControl(orderRawValue.amountRecived),
      amountRemaining: new FormControl(orderRawValue.amountRemaining),
      customerNotes: new FormControl(orderRawValue.customerNotes),
      restaurantNotes: new FormControl(orderRawValue.restaurantNotes),
      driverNotes: new FormControl(orderRawValue.driverNotes),
      orderStatus: new FormControl(orderRawValue.orderStatus),
      orderType: new FormControl(orderRawValue.orderType),
      deliveredAt: new FormControl(orderRawValue.deliveredAt),
      notes: new FormControl(orderRawValue.notes),
      customer: new FormControl(orderRawValue.customer),
      coupon: new FormControl(orderRawValue.coupon),
      driver: new FormControl(orderRawValue.driver),
      deliveryAddress: new FormControl(orderRawValue.deliveryAddress),
      restaurant: new FormControl(orderRawValue.restaurant),
    });
  }

  getOrder(form: OrderFormGroup): IOrder | NewOrder {
    return this.convertOrderRawValueToOrder(form.getRawValue() as OrderFormRawValue | NewOrderFormRawValue);
  }

  resetForm(form: OrderFormGroup, order: OrderFormGroupInput): void {
    const orderRawValue = this.convertOrderToOrderRawValue({...this.getFormDefaults(), ...order});
    form.reset(
      {
        ...orderRawValue,
        id: {value: orderRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): OrderFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      isApprovedByDriver: false,
      isApprovedByRestaurant: false,
      isPayed: false,
      deliveredAt: currentTime,
    };
  }

  private convertOrderRawValueToOrder(rawOrder: OrderFormRawValue | NewOrderFormRawValue): IOrder | NewOrder {
    return {
      ...rawOrder,
      deliveredAt: dayjs(rawOrder.deliveredAt, DATE_TIME_FORMAT),
    };
  }

  private convertOrderToOrderRawValue(
    order: IOrder | (Partial<NewOrder> & OrderFormDefaults)
  ): OrderFormRawValue | PartialWithRequiredKeyOf<NewOrderFormRawValue> {
    return {
      ...order,
      deliveredAt: order.deliveredAt ? order.deliveredAt.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
