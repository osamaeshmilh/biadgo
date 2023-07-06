package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.PaymentType;
import ly.biadjo.food.domain.enumeration.WalletAction;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.CustomerWallet} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.CustomerWalletResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /customer-wallets?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomerWalletCriteria implements Serializable, Criteria {

    /**
     * Class for filtering WalletAction
     */
    public static class WalletActionFilter extends Filter<WalletAction> {

        public WalletActionFilter() {
        }

        public WalletActionFilter(WalletActionFilter filter) {
            super(filter);
        }

        @Override
        public WalletActionFilter copy() {
            return new WalletActionFilter(this);
        }
    }

    /**
     * Class for filtering PaymentType
     */
    public static class PaymentTypeFilter extends Filter<PaymentType> {

        public PaymentTypeFilter() {
        }

        public PaymentTypeFilter(PaymentTypeFilter filter) {
            super(filter);
        }

        @Override
        public PaymentTypeFilter copy() {
            return new PaymentTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter transactionNo;

    private DoubleFilter amount;

    private WalletActionFilter walletAction;

    private DoubleFilter totalBeforeAction;

    private DoubleFilter totalAfterAction;

    private PaymentTypeFilter paymentType;

    private StringFilter paymentReference;

    private StringFilter orderId;

    private StringFilter notes;

    private LongFilter customerId;

    private Boolean distinct;

    public CustomerWalletCriteria() {
    }

    public CustomerWalletCriteria(CustomerWalletCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.transactionNo = other.transactionNo == null ? null : other.transactionNo.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.walletAction = other.walletAction == null ? null : other.walletAction.copy();
        this.totalBeforeAction = other.totalBeforeAction == null ? null : other.totalBeforeAction.copy();
        this.totalAfterAction = other.totalAfterAction == null ? null : other.totalAfterAction.copy();
        this.paymentType = other.paymentType == null ? null : other.paymentType.copy();
        this.paymentReference = other.paymentReference == null ? null : other.paymentReference.copy();
        this.orderId = other.orderId == null ? null : other.orderId.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.customerId = other.customerId == null ? null : other.customerId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CustomerWalletCriteria copy() {
        return new CustomerWalletCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTransactionNo() {
        return transactionNo;
    }

    public StringFilter transactionNo() {
        if (transactionNo == null) {
            transactionNo = new StringFilter();
        }
        return transactionNo;
    }

    public void setTransactionNo(StringFilter transactionNo) {
        this.transactionNo = transactionNo;
    }

    public DoubleFilter getAmount() {
        return amount;
    }

    public DoubleFilter amount() {
        if (amount == null) {
            amount = new DoubleFilter();
        }
        return amount;
    }

    public void setAmount(DoubleFilter amount) {
        this.amount = amount;
    }

    public WalletActionFilter getWalletAction() {
        return walletAction;
    }

    public WalletActionFilter walletAction() {
        if (walletAction == null) {
            walletAction = new WalletActionFilter();
        }
        return walletAction;
    }

    public void setWalletAction(WalletActionFilter walletAction) {
        this.walletAction = walletAction;
    }

    public DoubleFilter getTotalBeforeAction() {
        return totalBeforeAction;
    }

    public DoubleFilter totalBeforeAction() {
        if (totalBeforeAction == null) {
            totalBeforeAction = new DoubleFilter();
        }
        return totalBeforeAction;
    }

    public void setTotalBeforeAction(DoubleFilter totalBeforeAction) {
        this.totalBeforeAction = totalBeforeAction;
    }

    public DoubleFilter getTotalAfterAction() {
        return totalAfterAction;
    }

    public DoubleFilter totalAfterAction() {
        if (totalAfterAction == null) {
            totalAfterAction = new DoubleFilter();
        }
        return totalAfterAction;
    }

    public void setTotalAfterAction(DoubleFilter totalAfterAction) {
        this.totalAfterAction = totalAfterAction;
    }

    public PaymentTypeFilter getPaymentType() {
        return paymentType;
    }

    public PaymentTypeFilter paymentType() {
        if (paymentType == null) {
            paymentType = new PaymentTypeFilter();
        }
        return paymentType;
    }

    public void setPaymentType(PaymentTypeFilter paymentType) {
        this.paymentType = paymentType;
    }

    public StringFilter getPaymentReference() {
        return paymentReference;
    }

    public StringFilter paymentReference() {
        if (paymentReference == null) {
            paymentReference = new StringFilter();
        }
        return paymentReference;
    }

    public void setPaymentReference(StringFilter paymentReference) {
        this.paymentReference = paymentReference;
    }

    public StringFilter getOrderId() {
        return orderId;
    }

    public StringFilter orderId() {
        if (orderId == null) {
            orderId = new StringFilter();
        }
        return orderId;
    }

    public void setOrderId(StringFilter orderId) {
        this.orderId = orderId;
    }

    public StringFilter getNotes() {
        return notes;
    }

    public StringFilter notes() {
        if (notes == null) {
            notes = new StringFilter();
        }
        return notes;
    }

    public void setNotes(StringFilter notes) {
        this.notes = notes;
    }

    public LongFilter getCustomerId() {
        return customerId;
    }

    public LongFilter customerId() {
        if (customerId == null) {
            customerId = new LongFilter();
        }
        return customerId;
    }

    public void setCustomerId(LongFilter customerId) {
        this.customerId = customerId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CustomerWalletCriteria that = (CustomerWalletCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(transactionNo, that.transactionNo) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(walletAction, that.walletAction) &&
                Objects.equals(totalBeforeAction, that.totalBeforeAction) &&
                Objects.equals(totalAfterAction, that.totalAfterAction) &&
                Objects.equals(paymentType, that.paymentType) &&
                Objects.equals(paymentReference, that.paymentReference) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            transactionNo,
            amount,
            walletAction,
            totalBeforeAction,
            totalAfterAction,
            paymentType,
            paymentReference,
            orderId,
            notes,
            customerId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerWalletCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (transactionNo != null ? "transactionNo=" + transactionNo + ", " : "") +
            (amount != null ? "amount=" + amount + ", " : "") +
            (walletAction != null ? "walletAction=" + walletAction + ", " : "") +
            (totalBeforeAction != null ? "totalBeforeAction=" + totalBeforeAction + ", " : "") +
            (totalAfterAction != null ? "totalAfterAction=" + totalAfterAction + ", " : "") +
            (paymentType != null ? "paymentType=" + paymentType + ", " : "") +
            (paymentReference != null ? "paymentReference=" + paymentReference + ", " : "") +
            (orderId != null ? "orderId=" + orderId + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (customerId != null ? "customerId=" + customerId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
