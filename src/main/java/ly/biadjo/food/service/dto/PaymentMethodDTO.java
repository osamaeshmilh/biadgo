package ly.biadjo.food.service.dto;

import jakarta.persistence.Lob;

import java.io.Serializable;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.PaymentType;

/**
 * A DTO for the {@link ly.biadjo.food.domain.PaymentMethod} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentMethodDTO implements Serializable {

    private Long id;

    private String name;

    private String nameAr;

    private String nameEn;

    private Integer menuOrder;

    private String imageUrl;

    @Lob
    private byte[] image;

    private String imageContentType;
    private String details;

    private Double feePercentage;

    private PaymentType paymentType;

    private Boolean isActive;

    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Double getFeePercentage() {
        return feePercentage;
    }

    public void setFeePercentage(Double feePercentage) {
        this.feePercentage = feePercentage;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
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
        if (!(o instanceof PaymentMethodDTO)) {
            return false;
        }

        PaymentMethodDTO paymentMethodDTO = (PaymentMethodDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paymentMethodDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentMethodDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", menuOrder=" + getMenuOrder() +
            ", imageUrl='" + getImageUrl() + "'" +
            ", image='" + getImage() + "'" +
            ", details='" + getDetails() + "'" +
            ", feePercentage=" + getFeePercentage() +
            ", paymentType='" + getPaymentType() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
