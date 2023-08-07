package ly.biadjo.food.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.DriverPaymentType;
import ly.biadjo.food.domain.enumeration.DriverStatus;
import ly.biadjo.food.domain.enumeration.DriverType;
import ly.biadjo.food.domain.enumeration.VehicleType;

/**
 * A DTO for the {@link ly.biadjo.food.domain.Driver} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DriverDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String nameAr;

    private String nameEn;

    private String mobileNo;

    private String email;

    private String imageUrl;

    @Lob
    private byte[] image;

    private String imageContentType;
    private DriverType driverType;

    private DriverPaymentType driverPaymentType;

    private VehicleType vehicleType;

    private DriverStatus driverStatus;

    private Double commissionAmount;

    private Double salaryAmount;

    private Double latitude;

    private Double longitude;

    private String plusCode;

    private Instant lastLocationDateTime;

    private Boolean isAvailable;

    private String notes;

    private long reviewsCount;

    private float rating;

    private UserDTO user;

    private ZoneDTO zone;

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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public DriverPaymentType getDriverPaymentType() {
        return driverPaymentType;
    }

    public void setDriverPaymentType(DriverPaymentType driverPaymentType) {
        this.driverPaymentType = driverPaymentType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public Double getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(Double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Double getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(Double salaryAmount) {
        this.salaryAmount = salaryAmount;
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

    public Instant getLastLocationDateTime() {
        return lastLocationDateTime;
    }

    public void setLastLocationDateTime(Instant lastLocationDateTime) {
        this.lastLocationDateTime = lastLocationDateTime;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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
        if (!(o instanceof DriverDTO)) {
            return false;
        }

        DriverDTO driverDTO = (DriverDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, driverDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DriverDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", email='" + getEmail() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", image='" + getImage() + "'" +
            ", driverType='" + getDriverType() + "'" +
            ", driverPaymentType='" + getDriverPaymentType() + "'" +
            ", vehicleType='" + getVehicleType() + "'" +
            ", driverStatus='" + getDriverStatus() + "'" +
            ", commissionAmount=" + getCommissionAmount() +
            ", salaryAmount=" + getSalaryAmount() +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", plusCode='" + getPlusCode() + "'" +
            ", lastLocationDateTime='" + getLastLocationDateTime() + "'" +
            ", isAvailable='" + getIsAvailable() + "'" +
            ", notes='" + getNotes() + "'" +
            ", user=" + getUser() +
            ", zone=" + getZone() +
            "}";
    }

    public long getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(long reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
