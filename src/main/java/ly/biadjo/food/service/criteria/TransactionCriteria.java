package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.PaymentType;
import ly.biadjo.food.domain.enumeration.TransactionStatus;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.Transaction} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.TransactionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /transactions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TransactionCriteria implements Serializable, Criteria {

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

    /**
     * Class for filtering TransactionStatus
     */
    public static class TransactionStatusFilter extends Filter<TransactionStatus> {

        public TransactionStatusFilter() {
        }

        public TransactionStatusFilter(TransactionStatusFilter filter) {
            super(filter);
        }

        @Override
        public TransactionStatusFilter copy() {
            return new TransactionStatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter transactionReference;

    private PaymentTypeFilter paymentType;

    private TransactionStatusFilter transactionStatus;

    private StringFilter vendorReference;

    private StringFilter vendorMessage;

    private DoubleFilter amount;

    private DoubleFilter fees;

    private DoubleFilter total;

    private StringFilter notes;

    private LongFilter customerId;

    private Boolean distinct;

    public TransactionCriteria() {
    }

    public TransactionCriteria(TransactionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.transactionReference = other.transactionReference == null ? null : other.transactionReference.copy();
        this.paymentType = other.paymentType == null ? null : other.paymentType.copy();
        this.transactionStatus = other.transactionStatus == null ? null : other.transactionStatus.copy();
        this.vendorReference = other.vendorReference == null ? null : other.vendorReference.copy();
        this.vendorMessage = other.vendorMessage == null ? null : other.vendorMessage.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.fees = other.fees == null ? null : other.fees.copy();
        this.total = other.total == null ? null : other.total.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.customerId = other.customerId == null ? null : other.customerId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public TransactionCriteria copy() {
        return new TransactionCriteria(this);
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

    public StringFilter getTransactionReference() {
        return transactionReference;
    }

    public StringFilter transactionReference() {
        if (transactionReference == null) {
            transactionReference = new StringFilter();
        }
        return transactionReference;
    }

    public void setTransactionReference(StringFilter transactionReference) {
        this.transactionReference = transactionReference;
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

    public TransactionStatusFilter getTransactionStatus() {
        return transactionStatus;
    }

    public TransactionStatusFilter transactionStatus() {
        if (transactionStatus == null) {
            transactionStatus = new TransactionStatusFilter();
        }
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatusFilter transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public StringFilter getVendorReference() {
        return vendorReference;
    }

    public StringFilter vendorReference() {
        if (vendorReference == null) {
            vendorReference = new StringFilter();
        }
        return vendorReference;
    }

    public void setVendorReference(StringFilter vendorReference) {
        this.vendorReference = vendorReference;
    }

    public StringFilter getVendorMessage() {
        return vendorMessage;
    }

    public StringFilter vendorMessage() {
        if (vendorMessage == null) {
            vendorMessage = new StringFilter();
        }
        return vendorMessage;
    }

    public void setVendorMessage(StringFilter vendorMessage) {
        this.vendorMessage = vendorMessage;
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

    public DoubleFilter getFees() {
        return fees;
    }

    public DoubleFilter fees() {
        if (fees == null) {
            fees = new DoubleFilter();
        }
        return fees;
    }

    public void setFees(DoubleFilter fees) {
        this.fees = fees;
    }

    public DoubleFilter getTotal() {
        return total;
    }

    public DoubleFilter total() {
        if (total == null) {
            total = new DoubleFilter();
        }
        return total;
    }

    public void setTotal(DoubleFilter total) {
        this.total = total;
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
        final TransactionCriteria that = (TransactionCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(transactionReference, that.transactionReference) &&
                Objects.equals(paymentType, that.paymentType) &&
                Objects.equals(transactionStatus, that.transactionStatus) &&
                Objects.equals(vendorReference, that.vendorReference) &&
                Objects.equals(vendorMessage, that.vendorMessage) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(fees, that.fees) &&
                Objects.equals(total, that.total) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            transactionReference,
            paymentType,
            transactionStatus,
            vendorReference,
            vendorMessage,
            amount,
            fees,
            total,
            notes,
            customerId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (transactionReference != null ? "transactionReference=" + transactionReference + ", " : "") +
            (paymentType != null ? "paymentType=" + paymentType + ", " : "") +
            (transactionStatus != null ? "transactionStatus=" + transactionStatus + ", " : "") +
            (vendorReference != null ? "vendorReference=" + vendorReference + ", " : "") +
            (vendorMessage != null ? "vendorMessage=" + vendorMessage + ", " : "") +
            (amount != null ? "amount=" + amount + ", " : "") +
            (fees != null ? "fees=" + fees + ", " : "") +
            (total != null ? "total=" + total + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (customerId != null ? "customerId=" + customerId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
