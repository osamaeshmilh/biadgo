package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import ly.biadjo.food.domain.enumeration.DriverPaymentType;
import ly.biadjo.food.domain.enumeration.DriverStatus;
import ly.biadjo.food.domain.enumeration.DriverType;
import ly.biadjo.food.domain.enumeration.VehicleType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Driver.
 */
@Entity
@Table(name = "driver")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "name_ar")
    private String nameAr;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "email")
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "driver_type")
    private DriverType driverType;

    @Enumerated(EnumType.STRING)
    @Column(name = "driver_payment_type")
    private DriverPaymentType driverPaymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "driver_status")
    private DriverStatus driverStatus;

    @Column(name = "commission_amount")
    private Double commissionAmount;

    @Column(name = "salary_amount")
    private Double salaryAmount;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "plus_code")
    private String plusCode;

    @Column(name = "last_location_date_time")
    private Instant lastLocationDateTime;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name = "notes")
    private String notes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"city"}, allowSetters = true)
    private Zone zone;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Driver id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Driver name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAr() {
        return this.nameAr;
    }

    public Driver nameAr(String nameAr) {
        this.setNameAr(nameAr);
        return this;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public Driver nameEn(String nameEn) {
        this.setNameEn(nameEn);
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public Driver mobileNo(String mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return this.email;
    }

    public Driver email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Driver imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public byte[] getImage() {
        return this.image;
    }

    public Driver image(byte[] image) {
        this.setImage(image);
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return this.imageContentType;
    }

    public Driver imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public DriverType getDriverType() {
        return this.driverType;
    }

    public Driver driverType(DriverType driverType) {
        this.setDriverType(driverType);
        return this;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public DriverPaymentType getDriverPaymentType() {
        return this.driverPaymentType;
    }

    public Driver driverPaymentType(DriverPaymentType driverPaymentType) {
        this.setDriverPaymentType(driverPaymentType);
        return this;
    }

    public void setDriverPaymentType(DriverPaymentType driverPaymentType) {
        this.driverPaymentType = driverPaymentType;
    }

    public VehicleType getVehicleType() {
        return this.vehicleType;
    }

    public Driver vehicleType(VehicleType vehicleType) {
        this.setVehicleType(vehicleType);
        return this;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public DriverStatus getDriverStatus() {
        return this.driverStatus;
    }

    public Driver driverStatus(DriverStatus driverStatus) {
        this.setDriverStatus(driverStatus);
        return this;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public Double getCommissionAmount() {
        return this.commissionAmount;
    }

    public Driver commissionAmount(Double commissionAmount) {
        this.setCommissionAmount(commissionAmount);
        return this;
    }

    public void setCommissionAmount(Double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Double getSalaryAmount() {
        return this.salaryAmount;
    }

    public Driver salaryAmount(Double salaryAmount) {
        this.setSalaryAmount(salaryAmount);
        return this;
    }

    public void setSalaryAmount(Double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Driver latitude(Double latitude) {
        this.setLatitude(latitude);
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public Driver longitude(Double longitude) {
        this.setLongitude(longitude);
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPlusCode() {
        return this.plusCode;
    }

    public Driver plusCode(String plusCode) {
        this.setPlusCode(plusCode);
        return this;
    }

    public void setPlusCode(String plusCode) {
        this.plusCode = plusCode;
    }

    public Instant getLastLocationDateTime() {
        return this.lastLocationDateTime;
    }

    public Driver lastLocationDateTime(Instant lastLocationDateTime) {
        this.setLastLocationDateTime(lastLocationDateTime);
        return this;
    }

    public void setLastLocationDateTime(Instant lastLocationDateTime) {
        this.lastLocationDateTime = lastLocationDateTime;
    }

    public Boolean getIsAvailable() {
        return this.isAvailable;
    }

    public Driver isAvailable(Boolean isAvailable) {
        this.setIsAvailable(isAvailable);
        return this;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getNotes() {
        return this.notes;
    }

    public Driver notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver user(User user) {
        this.setUser(user);
        return this;
    }

    public Zone getZone() {
        return this.zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Driver zone(Zone zone) {
        this.setZone(zone);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Driver)) {
            return false;
        }
        return id != null && id.equals(((Driver) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Driver{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", email='" + getEmail() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
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
            "}";
    }
}
