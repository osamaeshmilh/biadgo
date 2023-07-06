package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.CouponType;

/**
 * A DTO for the {@link ly.biadjo.food.domain.Coupon} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CouponDTO implements Serializable {

    private Long id;

    private String code;

    private CouponType couponType;

    private Integer amount;

    private Integer minimumAmount;

    private Integer useLimit;

    private Integer useCount;

    private LocalDate expiryDate;

    private Boolean isActive;

    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CouponType getCouponType() {
        return couponType;
    }

    public void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(Integer minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public Integer getUseLimit() {
        return useLimit;
    }

    public void setUseLimit(Integer useLimit) {
        this.useLimit = useLimit;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CouponDTO)) {
            return false;
        }

        CouponDTO couponDTO = (CouponDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, couponDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CouponDTO{" +
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
