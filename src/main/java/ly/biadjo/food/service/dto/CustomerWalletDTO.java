package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.PaymentType;
import ly.biadjo.food.domain.enumeration.WalletAction;

/**
 * A DTO for the {@link ly.biadjo.food.domain.CustomerWallet} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomerWalletDTO implements Serializable {

    private Long id;

    private String transactionNo;

    private Double amount;

    private WalletAction walletAction;

    private Double totalBeforeAction;

    private Double totalAfterAction;

    private PaymentType paymentType;

    private String paymentReference;

    private String orderId;

    private String notes;

    private CustomerDTO customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public WalletAction getWalletAction() {
        return walletAction;
    }

    public void setWalletAction(WalletAction walletAction) {
        this.walletAction = walletAction;
    }

    public Double getTotalBeforeAction() {
        return totalBeforeAction;
    }

    public void setTotalBeforeAction(Double totalBeforeAction) {
        this.totalBeforeAction = totalBeforeAction;
    }

    public Double getTotalAfterAction() {
        return totalAfterAction;
    }

    public void setTotalAfterAction(Double totalAfterAction) {
        this.totalAfterAction = totalAfterAction;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerWalletDTO)) {
            return false;
        }

        CustomerWalletDTO customerWalletDTO = (CustomerWalletDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, customerWalletDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerWalletDTO{" +
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
            ", customer=" + getCustomer() +
            "}";
    }
}
