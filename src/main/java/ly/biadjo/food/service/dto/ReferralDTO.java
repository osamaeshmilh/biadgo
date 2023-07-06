package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.Referral} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReferralDTO implements Serializable {

    private Long id;

    private String referralCode;

    private Double referrerAmount;

    private Double referredCustomerAmount;

    private LocalDate expiryDate;

    private Boolean isUsed;

    private Instant usedDateTime;

    private String notes;

    private CustomerDTO referredCustomer;

    private CustomerDTO referrerCustomer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Double getReferrerAmount() {
        return referrerAmount;
    }

    public void setReferrerAmount(Double referrerAmount) {
        this.referrerAmount = referrerAmount;
    }

    public Double getReferredCustomerAmount() {
        return referredCustomerAmount;
    }

    public void setReferredCustomerAmount(Double referredCustomerAmount) {
        this.referredCustomerAmount = referredCustomerAmount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Instant getUsedDateTime() {
        return usedDateTime;
    }

    public void setUsedDateTime(Instant usedDateTime) {
        this.usedDateTime = usedDateTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public CustomerDTO getReferredCustomer() {
        return referredCustomer;
    }

    public void setReferredCustomer(CustomerDTO referredCustomer) {
        this.referredCustomer = referredCustomer;
    }

    public CustomerDTO getReferrerCustomer() {
        return referrerCustomer;
    }

    public void setReferrerCustomer(CustomerDTO referrerCustomer) {
        this.referrerCustomer = referrerCustomer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReferralDTO)) {
            return false;
        }

        ReferralDTO referralDTO = (ReferralDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, referralDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReferralDTO{" +
            "id=" + getId() +
            ", referralCode='" + getReferralCode() + "'" +
            ", referrerAmount=" + getReferrerAmount() +
            ", referredCustomerAmount=" + getReferredCustomerAmount() +
            ", expiryDate='" + getExpiryDate() + "'" +
            ", isUsed='" + getIsUsed() + "'" +
            ", usedDateTime='" + getUsedDateTime() + "'" +
            ", notes='" + getNotes() + "'" +
            ", referredCustomer=" + getReferredCustomer() +
            ", referrerCustomer=" + getReferrerCustomer() +
            "}";
    }
}
