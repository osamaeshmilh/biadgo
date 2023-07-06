import {Injectable} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';

import {ITransaction, NewTransaction} from '../transaction.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITransaction for edit and NewTransactionFormGroupInput for create.
 */
type TransactionFormGroupInput = ITransaction | PartialWithRequiredKeyOf<NewTransaction>;

type TransactionFormDefaults = Pick<NewTransaction, 'id'>;

type TransactionFormGroupContent = {
  id: FormControl<ITransaction['id'] | NewTransaction['id']>;
  transactionReference: FormControl<ITransaction['transactionReference']>;
  paymentType: FormControl<ITransaction['paymentType']>;
  transactionStatus: FormControl<ITransaction['transactionStatus']>;
  vendorReference: FormControl<ITransaction['vendorReference']>;
  vendorMessage: FormControl<ITransaction['vendorMessage']>;
  amount: FormControl<ITransaction['amount']>;
  fees: FormControl<ITransaction['fees']>;
  total: FormControl<ITransaction['total']>;
  notes: FormControl<ITransaction['notes']>;
  customer: FormControl<ITransaction['customer']>;
};

export type TransactionFormGroup = FormGroup<TransactionFormGroupContent>;

@Injectable({providedIn: 'root'})
export class TransactionFormService {
  createTransactionFormGroup(transaction: TransactionFormGroupInput = {id: null}): TransactionFormGroup {
    const transactionRawValue = {
      ...this.getFormDefaults(),
      ...transaction,
    };
    return new FormGroup<TransactionFormGroupContent>({
      id: new FormControl(
        {value: transactionRawValue.id, disabled: true},
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      transactionReference: new FormControl(transactionRawValue.transactionReference),
      paymentType: new FormControl(transactionRawValue.paymentType),
      transactionStatus: new FormControl(transactionRawValue.transactionStatus),
      vendorReference: new FormControl(transactionRawValue.vendorReference),
      vendorMessage: new FormControl(transactionRawValue.vendorMessage),
      amount: new FormControl(transactionRawValue.amount),
      fees: new FormControl(transactionRawValue.fees),
      total: new FormControl(transactionRawValue.total),
      notes: new FormControl(transactionRawValue.notes),
      customer: new FormControl(transactionRawValue.customer),
    });
  }

  getTransaction(form: TransactionFormGroup): ITransaction | NewTransaction {
    return form.getRawValue() as ITransaction | NewTransaction;
  }

  resetForm(form: TransactionFormGroup, transaction: TransactionFormGroupInput): void {
    const transactionRawValue = {...this.getFormDefaults(), ...transaction};
    form.reset(
      {
        ...transactionRawValue,
        id: {value: transactionRawValue.id, disabled: true},
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): TransactionFormDefaults {
    return {
      id: null,
    };
  }
}
