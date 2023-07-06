package ly.biadjo.food.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import ly.biadjo.food.domain.enumeration.CouponType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Coupon.
 */
@Entity
@Table(name = "coupon")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Coupon extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_type")
    private CouponType couponType;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "minimum_amount")
    private Integer minimumAmount;

    @Column(name = "use_limit")
    private Integer useLimit;

    @Column(name = "use_count")
    private Integer useCount;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "notes")
    private String notes;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Coupon id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Coupon code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CouponType getCouponType() {
        return this.couponType;
    }

    public Coupon couponType(CouponType couponType) {
        this.setCouponType(couponType);
        return this;
    }

    public void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public Coupon amount(Integer amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMinimumAmount() {
        return this.minimumAmount;
    }

    public Coupon minimumAmount(Integer minimumAmount) {
        this.setMinimumAmount(minimumAmount);
        return this;
    }

    public void setMinimumAmount(Integer minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public Integer getUseLimit() {
        return this.useLimit;
    }

    public Coupon useLimit(Integer useLimit) {
        this.setUseLimit(useLimit);
        return this;
    }

    public void setUseLimit(Integer useLimit) {
        this.useLimit = useLimit;
    }

    public Integer getUseCount() {
        return this.useCount;
    }

    public Coupon useCount(Integer useCount) {
        this.setUseCount(useCount);
        return this;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }

    public Coupon expiryDate(LocalDate expiryDate) {
        this.setExpiryDate(expiryDate);
        return this;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Coupon isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getNotes() {
        return this.notes;
    }

    public Coupon notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coupon)) {
            return false;
        }
        return id != null && id.equals(((Coupon) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Coupon{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", couponType='" + getCouponType() + "'" +
            ", amount=" + getAmount() +
            ", minimumAmount=" + getMinimumAmount() +
            ", useLimit=" + getUseLimit() +
            ", useCount=" + getUseCount() +
            ", expiryDate='" + getExpiryDate() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
