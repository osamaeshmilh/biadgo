package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

import ly.biadjo.food.domain.enumeration.PaymentType;
import ly.biadjo.food.domain.enumeration.TransactionStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Transaction extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "transaction_reference")
    private String transactionReference;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;

    @Column(name = "vendor_reference")
    private String vendorReference;

    @Column(name = "vendor_message")
    private String vendorMessage;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "fees")
    private Double fees;

    @Column(name = "total")
    private Double total;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Transaction id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionReference() {
        return this.transactionReference;
    }

    public Transaction transactionReference(String transactionReference) {
        this.setTransactionReference(transactionReference);
        return this;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public Transaction paymentType(PaymentType paymentType) {
        this.setPaymentType(paymentType);
        return this;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public TransactionStatus getTransactionStatus() {
        return this.transactionStatus;
    }

    public Transaction transactionStatus(TransactionStatus transactionStatus) {
        this.setTransactionStatus(transactionStatus);
        return this;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getVendorReference() {
        return this.vendorReference;
    }

    public Transaction vendorReference(String vendorReference) {
        this.setVendorReference(vendorReference);
        return this;
    }

    public void setVendorReference(String vendorReference) {
        this.vendorReference = vendorReference;
    }

    public String getVendorMessage() {
        return this.vendorMessage;
    }

    public Transaction vendorMessage(String vendorMessage) {
        this.setVendorMessage(vendorMessage);
        return this;
    }

    public void setVendorMessage(String vendorMessage) {
        this.vendorMessage = vendorMessage;
    }

    public Double getAmount() {
        return this.amount;
    }

    public Transaction amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getFees() {
        return this.fees;
    }

    public Transaction fees(Double fees) {
        this.setFees(fees);
        return this;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public Double getTotal() {
        return this.total;
    }

    public Transaction total(Double total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getNotes() {
        return this.notes;
    }

    public Transaction notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Transaction customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return id != null && id.equals(((Transaction) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transaction{" +
            "id=" + getId() +
            ", transactionReference='" + getTransactionReference() + "'" +
            ", paymentType='" + getPaymentType() + "'" +
            ", transactionStatus='" + getTransactionStatus() + "'" +
            ", vendorReference='" + getVendorReference() + "'" +
            ", vendorMessage='" + getVendorMessage() + "'" +
            ", amount=" + getAmount() +
            ", fees=" + getFees() +
            ", total=" + getTotal() +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
