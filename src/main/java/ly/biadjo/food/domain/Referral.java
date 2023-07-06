package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Referral.
 */
@Entity
@Table(name = "referral")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Referral implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "referral_code")
    private String referralCode;

    @Column(name = "referrer_amount")
    private Double referrerAmount;

    @Column(name = "referred_customer_amount")
    private Double referredCustomerAmount;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "is_used")
    private Boolean isUsed;

    @Column(name = "used_date_time")
    private Instant usedDateTime;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
    private Customer referredCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
    private Customer referrerCustomer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Referral id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferralCode() {
        return this.referralCode;
    }

    public Referral referralCode(String referralCode) {
        this.setReferralCode(referralCode);
        return this;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Double getReferrerAmount() {
        return this.referrerAmount;
    }

    public Referral referrerAmount(Double referrerAmount) {
        this.setReferrerAmount(referrerAmount);
        return this;
    }

    public void setReferrerAmount(Double referrerAmount) {
        this.referrerAmount = referrerAmount;
    }

    public Double getReferredCustomerAmount() {
        return this.referredCustomerAmount;
    }

    public Referral referredCustomerAmount(Double referredCustomerAmount) {
        this.setReferredCustomerAmount(referredCustomerAmount);
        return this;
    }

    public void setReferredCustomerAmount(Double referredCustomerAmount) {
        this.referredCustomerAmount = referredCustomerAmount;
    }

    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }

    public Referral expiryDate(LocalDate expiryDate) {
        this.setExpiryDate(expiryDate);
        return this;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getIsUsed() {
        return this.isUsed;
    }

    public Referral isUsed(Boolean isUsed) {
        this.setIsUsed(isUsed);
        return this;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Instant getUsedDateTime() {
        return this.usedDateTime;
    }

    public Referral usedDateTime(Instant usedDateTime) {
        this.setUsedDateTime(usedDateTime);
        return this;
    }

    public void setUsedDateTime(Instant usedDateTime) {
        this.usedDateTime = usedDateTime;
    }

    public String getNotes() {
        return this.notes;
    }

    public Referral notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Customer getReferredCustomer() {
        return this.referredCustomer;
    }

    public void setReferredCustomer(Customer customer) {
        this.referredCustomer = customer;
    }

    public Referral referredCustomer(Customer customer) {
        this.setReferredCustomer(customer);
        return this;
    }

    public Customer getReferrerCustomer() {
        return this.referrerCustomer;
    }

    public void setReferrerCustomer(Customer customer) {
        this.referrerCustomer = customer;
    }

    public Referral referrerCustomer(Customer customer) {
        this.setReferrerCustomer(customer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Referral)) {
            return false;
        }
        return id != null && id.equals(((Referral) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Referral{" +
            "id=" + getId() +
            ", referralCode='" + getReferralCode() + "'" +
            ", referrerAmount=" + getReferrerAmount() +
            ", referredCustomerAmount=" + getReferredCustomerAmount() +
            ", expiryDate='" + getExpiryDate() + "'" +
            ", isUsed='" + getIsUsed() + "'" +
            ", usedDateTime='" + getUsedDateTime() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
