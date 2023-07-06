package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

import ly.biadjo.food.domain.enumeration.PaymentType;
import ly.biadjo.food.domain.enumeration.WalletAction;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DriverWallet.
 */
@Entity
@Table(name = "driver_wallet")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DriverWallet extends AbstractAuditingEntity<Long> implements Serializable {

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
    @JsonIgnoreProperties(value = {"user", "zone"}, allowSetters = true)
    private Driver driver;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DriverWallet id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionNo() {
        return this.transactionNo;
    }

    public DriverWallet transactionNo(String transactionNo) {
        this.setTransactionNo(transactionNo);
        return this;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Double getAmount() {
        return this.amount;
    }

    public DriverWallet amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public WalletAction getWalletAction() {
        return this.walletAction;
    }

    public DriverWallet walletAction(WalletAction walletAction) {
        this.setWalletAction(walletAction);
        return this;
    }

    public void setWalletAction(WalletAction walletAction) {
        this.walletAction = walletAction;
    }

    public Double getTotalBeforeAction() {
        return this.totalBeforeAction;
    }

    public DriverWallet totalBeforeAction(Double totalBeforeAction) {
        this.setTotalBeforeAction(totalBeforeAction);
        return this;
    }

    public void setTotalBeforeAction(Double totalBeforeAction) {
        this.totalBeforeAction = totalBeforeAction;
    }

    public Double getTotalAfterAction() {
        return this.totalAfterAction;
    }

    public DriverWallet totalAfterAction(Double totalAfterAction) {
        this.setTotalAfterAction(totalAfterAction);
        return this;
    }

    public void setTotalAfterAction(Double totalAfterAction) {
        this.totalAfterAction = totalAfterAction;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public DriverWallet paymentType(PaymentType paymentType) {
        this.setPaymentType(paymentType);
        return this;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentReference() {
        return this.paymentReference;
    }

    public DriverWallet paymentReference(String paymentReference) {
        this.setPaymentReference(paymentReference);
        return this;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public DriverWallet orderId(String orderId) {
        this.setOrderId(orderId);
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNotes() {
        return this.notes;
    }

    public DriverWallet notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public DriverWallet driver(Driver driver) {
        this.setDriver(driver);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DriverWallet)) {
            return false;
        }
        return id != null && id.equals(((DriverWallet) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DriverWallet{" +
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
