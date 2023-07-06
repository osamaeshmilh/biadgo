package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.DeliveryAddress} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DeliveryAddressDTO implements Serializable {

    private Long id;

    private String title;

    private String address;

    private String details;

    private String phone;

    private Boolean isDefault;

    private Double latitude;

    private Double longitude;

    private String plusCode;

    private Boolean isActive;

    private String notes;

    private CustomerDTO customer;

    private ZoneDTO zone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPlusCode() {
        return plusCode;
    }

    public void setPlusCode(String plusCode) {
        this.plusCode = plusCode;
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

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public ZoneDTO getZone() {
        return zone;
    }

    public void setZone(ZoneDTO zone) {
        this.zone = zone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryAddressDTO)) {
            return false;
        }

        DeliveryAddressDTO deliveryAddressDTO = (DeliveryAddressDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, deliveryAddressDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryAddressDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", address='" + getAddress() + "'" +
            ", details='" + getDetails() + "'" +
            ", phone='" + getPhone() + "'" +
            ", isDefault='" + getIsDefault() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", plusCode='" + getPlusCode() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", notes='" + getNotes() + "'" +
            ", customer=" + getCustomer() +
            ", zone=" + getZone() +
            "}";
    }
}
