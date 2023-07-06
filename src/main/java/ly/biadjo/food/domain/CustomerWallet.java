package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

import ly.biadjo.food.domain.enumeration.PaymentType;
import ly.biadjo.food.domain.enumeration.WalletAction;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CustomerWallet.
 */
@Entity
@Table(name = "customer_wallet")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomerWallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "transaction_no")
    private String transactionNo;

    @Column(name = "amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "wallet_action")
    private WalletAction walletAction;

    @Column(name = "total_before_action")
    private Double totalBeforeAction;

    @Column(name = "total_after_action")
    private Double totalAfterAction;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "payment_reference")
    private String paymentReference;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CustomerWallet id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionNo() {
        return this.transactionNo;
    }

    public CustomerWallet transactionNo(String transactionNo) {
        this.setTransactionNo(transactionNo);
        return this;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Double getAmount() {
        return this.amount;
    }

    public CustomerWallet amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public WalletAction getWalletAction() {
        return this.walletAction;
    }

    public CustomerWallet walletAction(WalletAction walletAction) {
        this.setWalletAction(walletAction);
        return this;
    }

    public void setWalletAction(WalletAction walletAction) {
        this.walletAction = walletAction;
    }

    public Double getTotalBeforeAction() {
        return this.totalBeforeAction;
    }

    public CustomerWallet totalBeforeAction(Double totalBeforeAction) {
        this.setTotalBeforeAction(totalBeforeAction);
        return this;
    }

    public void setTotalBeforeAction(Double totalBeforeAction) {
        this.totalBeforeAction = totalBeforeAction;
    }

    public Double getTotalAfterAction() {
        return this.totalAfterAction;
    }

    public CustomerWallet totalAfterAction(Double totalAfterAction) {
        this.setTotalAfterAction(totalAfterAction);
        return this;
    }

    public void setTotalAfterAction(Double totalAfterAction) {
        this.totalAfterAction = totalAfterAction;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public CustomerWallet paymentType(PaymentType paymentType) {
        this.setPaymentType(paymentType);
        return this;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentReference() {
        return this.paymentReference;
    }

    public CustomerWallet paymentReference(String paymentReference) {
        this.setPaymentReference(paymentReference);
        return this;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public CustomerWallet orderId(String orderId) {
        this.setOrderId(orderId);
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNotes() {
        return this.notes;
    }

    public CustomerWallet notes(String notes) {
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

    public CustomerWallet customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerWallet)) {
            return false;
        }
        return id != null && id.equals(((CustomerWallet) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerWallet{" +
            "id=" + getId() +
            ", transactionNo='" + getTransactionNo() + "'" +
            ", amount=" + getAmount() +
            ", walletAction='" + getWalletAction() + "'" +
            ", totalBeforeAction=" + getTotalBeforeAction() +
            ", totalAfterAction=" + getTotalAfterAction() +
            ", paymentType='" + getPaymentType() + "'" +
            ", paymentReference='" + getPaymentReference() + "'" +
            ", orderId='" + getOrderId() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
