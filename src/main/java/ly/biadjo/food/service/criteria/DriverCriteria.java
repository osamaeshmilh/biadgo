package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.DriverPaymentType;
import ly.biadjo.food.domain.enumeration.DriverStatus;
import ly.biadjo.food.domain.enumeration.DriverType;
import ly.biadjo.food.domain.enumeration.VehicleType;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.Driver} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.DriverResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /drivers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DriverCriteria implements Serializable, Criteria {

    /**
     * Class for filtering DriverType
     */
    public static class DriverTypeFilter extends Filter<DriverType> {

        public DriverTypeFilter() {
        }

        public DriverTypeFilter(DriverTypeFilter filter) {
            super(filter);
        }

        @Override
        public DriverTypeFilter copy() {
            return new DriverTypeFilter(this);
        }
    }

    /**
     * Class for filtering DriverPaymentType
     */
    public static class DriverPaymentTypeFilter extends Filter<DriverPaymentType> {

        public DriverPaymentTypeFilter() {
        }

        public DriverPaymentTypeFilter(DriverPaymentTypeFilter filter) {
            super(filter);
        }

        @Override
        public DriverPaymentTypeFilter copy() {
            return new DriverPaymentTypeFilter(this);
        }
    }

    /**
     * Class for filtering VehicleType
     */
    public static class VehicleTypeFilter extends Filter<VehicleType> {

        public VehicleTypeFilter() {
        }

        public VehicleTypeFilter(VehicleTypeFilter filter) {
            super(filter);
        }

        @Override
        public VehicleTypeFilter copy() {
            return new VehicleTypeFilter(this);
        }
    }

    /**
     * Class for filtering DriverStatus
     */
    public static class DriverStatusFilter extends Filter<DriverStatus> {

        public DriverStatusFilter() {
        }

        public DriverStatusFilter(DriverStatusFilter filter) {
            super(filter);
        }

        @Override
        public DriverStatusFilter copy() {
            return new DriverStatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter nameAr;

    private StringFilter nameEn;

    private StringFilter mobileNo;

    private StringFilter email;

    private StringFilter imageUrl;

    private DriverTypeFilter driverType;

    private DriverPaymentTypeFilter driverPaymentType;

    private VehicleTypeFilter vehicleType;

    private DriverStatusFilter driverStatus;

    private DoubleFilter commissionAmount;

    private DoubleFilter salaryAmount;

    private DoubleFilter latitude;

    private DoubleFilter longitude;

    private StringFilter plusCode;

    private InstantFilter lastLocationDateTime;

    private BooleanFilter isAvailable;

    private StringFilter notes;

    private LongFilter userId;

    private LongFilter zoneId;

    private Boolean distinct;

    public DriverCriteria() {
    }

    public DriverCriteria(DriverCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.nameAr = other.nameAr == null ? null : other.nameAr.copy();
        this.nameEn = other.nameEn == null ? null : other.nameEn.copy();
        this.mobileNo = other.mobileNo == null ? null : other.mobileNo.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.imageUrl = other.imageUrl == null ? null : other.imageUrl.copy();
        this.driverType = other.driverType == null ? null : other.driverType.copy();
        this.driverPaymentType = other.driverPaymentType == null ? null : other.driverPaymentType.copy();
        this.vehicleType = other.vehicleType == null ? null : other.vehicleType.copy();
        this.driverStatus = other.driverStatus == null ? null : other.driverStatus.copy();
        this.commissionAmount = other.commissionAmount == null ? null : other.commissionAmount.copy();
        this.salaryAmount = other.salaryAmount == null ? null : other.salaryAmount.copy();
        this.latitude = other.latitude == null ? null : other.latitude.copy();
        this.longitude = other.longitude == null ? null : other.longitude.copy();
        this.plusCode = other.plusCode == null ? null : other.plusCode.copy();
        this.lastLocationDateTime = other.lastLocationDateTime == null ? null : other.lastLocationDateTime.copy();
        this.isAvailable = other.isAvailable == null ? null : other.isAvailable.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.zoneId = other.zoneId == null ? null : other.zoneId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public DriverCriteria copy() {
        return new DriverCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getNameAr() {
        return nameAr;
    }

    public StringFilter nameAr() {
        if (nameAr == null) {
            nameAr = new StringFilter();
        }
        return nameAr;
    }

    public void setNameAr(StringFilter nameAr) {
        this.nameAr = nameAr;
    }

    public StringFilter getNameEn() {
        return nameEn;
    }

    public StringFilter nameEn() {
        if (nameEn == null) {
            nameEn = new StringFilter();
        }
        return nameEn;
    }

    public void setNameEn(StringFilter nameEn) {
        this.nameEn = nameEn;
    }

    public StringFilter getMobileNo() {
        return mobileNo;
    }

    public StringFilter mobileNo() {
        if (mobileNo == null) {
            mobileNo = new StringFilter();
        }
        return mobileNo;
    }

    public void setMobileNo(StringFilter mobileNo) {
        this.mobileNo = mobileNo;
    }

    public StringFilter getEmail() {
        return email;
    }

    public StringFilter email() {
        if (email == null) {
            email = new StringFilter();
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getImageUrl() {
        return imageUrl;
    }

    public StringFilter imageUrl() {
        if (imageUrl == null) {
            imageUrl = new StringFilter();
        }
        return imageUrl;
    }

    public void setImageUrl(StringFilter imageUrl) {
        this.imageUrl = imageUrl;
    }

    public DriverTypeFilter getDriverType() {
        return driverType;
    }

    public DriverTypeFilter driverType() {
        if (driverType == null) {
            driverType = new DriverTypeFilter();
        }
        return driverType;
    }

    public void setDriverType(DriverTypeFilter driverType) {
        this.driverType = driverType;
    }

    public DriverPaymentTypeFilter getDriverPaymentType() {
        return driverPaymentType;
    }

    public DriverPaymentTypeFilter driverPaymentType() {
        if (driverPaymentType == null) {
            driverPaymentType = new DriverPaymentTypeFilter();
        }
        return driverPaymentType;
    }

    public void setDriverPaymentType(DriverPaymentTypeFilter driverPaymentType) {
        this.driverPaymentType = driverPaymentType;
    }

    public VehicleTypeFilter getVehicleType() {
        return vehicleType;
    }

    public VehicleTypeFilter vehicleType() {
        if (vehicleType == null) {
            vehicleType = new VehicleTypeFilter();
        }
        return vehicleType;
    }

    public void setVehicleType(VehicleTypeFilter vehicleType) {
        this.vehicleType = vehicleType;
    }

    public DriverStatusFilter getDriverStatus() {
        return driverStatus;
    }

    public DriverStatusFilter driverStatus() {
        if (driverStatus == null) {
            driverStatus = new DriverStatusFilter();
        }
        return driverStatus;
    }

    public void setDriverStatus(DriverStatusFilter driverStatus) {
        this.driverStatus = driverStatus;
    }

    public DoubleFilter getCommissionAmount() {
        return commissionAmount;
    }

    public DoubleFilter commissionAmount() {
        if (commissionAmount == null) {
            commissionAmount = new DoubleFilter();
        }
        return commissionAmount;
    }

    public void setCommissionAmount(DoubleFilter commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public DoubleFilter getSalaryAmount() {
        return salaryAmount;
    }

    public DoubleFilter salaryAmount() {
        if (salaryAmount == null) {
            salaryAmount = new DoubleFilter();
        }
        return salaryAmount;
    }

    public void setSalaryAmount(DoubleFilter salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public DoubleFilter getLatitude() {
        return latitude;
    }

    public DoubleFilter latitude() {
        if (latitude == null) {
            latitude = new DoubleFilter();
        }
        return latitude;
    }

    public void setLatitude(DoubleFilter latitude) {
        this.latitude = latitude;
    }

    public DoubleFilter getLongitude() {
        return longitude;
    }

    public DoubleFilter longitude() {
        if (longitude == null) {
            longitude = new DoubleFilter();
        }
        return longitude;
    }

    public void setLongitude(DoubleFilter longitude) {
        this.longitude = longitude;
    }

    public StringFilter getPlusCode() {
        return plusCode;
    }

    public StringFilter plusCode() {
        if (plusCode == null) {
            plusCode = new StringFilter();
        }
        return plusCode;
    }

    public void setPlusCode(StringFilter plusCode) {
        this.plusCode = plusCode;
    }

    public InstantFilter getLastLocationDateTime() {
        return lastLocationDateTime;
    }

    public InstantFilter lastLocationDateTime() {
        if (lastLocationDateTime == null) {
            lastLocationDateTime = new InstantFilter();
        }
        return lastLocationDateTime;
    }

    public void setLastLocationDateTime(InstantFilter lastLocationDateTime) {
        this.lastLocationDateTime = lastLocationDateTime;
    }

    public BooleanFilter getIsAvailable() {
        return isAvailable;
    }

    public BooleanFilter isAvailable() {
        if (isAvailable == null) {
            isAvailable = new BooleanFilter();
        }
        return isAvailable;
    }

    public void setIsAvailable(BooleanFilter isAvailable) {
        this.isAvailable = isAvailable;
    }

    public StringFilter getNotes() {
        return notes;
    }

    public StringFilter notes() {
        if (notes == null) {
            notes = new StringFilter();
        }
        return notes;
    }

    public void setNotes(StringFilter notes) {
        this.notes = notes;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public LongFilter userId() {
        if (userId == null) {
            userId = new LongFilter();
        }
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getZoneId() {
        return zoneId;
    }

    public LongFilter zoneId() {
        if (zoneId == null) {
            zoneId = new LongFilter();
        }
        return zoneId;
    }

    public void setZoneId(LongFilter zoneId) {
        this.zoneId = zoneId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DriverCriteria that = (DriverCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(nameAr, that.nameAr) &&
                Objects.equals(nameEn, that.nameEn) &&
                Objects.equals(mobileNo, that.mobileNo) &&
                Objects.equals(email, that.email) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(driverType, that.driverType) &&
                Objects.equals(driverPaymentType, that.driverPaymentType) &&
                Objects.equals(vehicleType, that.vehicleType) &&
                Objects.equals(driverStatus, that.driverStatus) &&
                Objects.equals(commissionAmount, that.commissionAmount) &&
                Objects.equals(salaryAmount, that.salaryAmount) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(plusCode, that.plusCode) &&
                Objects.equals(lastLocationDateTime, that.lastLocationDateTime) &&
                Objects.equals(isAvailable, that.isAvailable) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(zoneId, that.zoneId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            nameAr,
            nameEn,
            mobileNo,
            email,
            imageUrl,
            driverType,
            driverPaymentType,
            vehicleType,
            driverStatus,
            commissionAmount,
            salaryAmount,
            latitude,
            longitude,
            plusCode,
            lastLocationDateTime,
            isAvailable,
            notes,
            userId,
            zoneId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DriverCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (nameAr != null ? "nameAr=" + nameAr + ", " : "") +
            (nameEn != null ? "nameEn=" + nameEn + ", " : "") +
            (mobileNo != null ? "mobileNo=" + mobileNo + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (imageUrl != null ? "imageUrl=" + imageUrl + ", " : "") +
            (driverType != null ? "driverType=" + driverType + ", " : "") +
            (driverPaymentType != null ? "driverPaymentType=" + driverPaymentType + ", " : "") +
            (vehicleType != null ? "vehicleType=" + vehicleType + ", " : "") +
            (driverStatus != null ? "driverStatus=" + driverStatus + ", " : "") +
            (commissionAmount != null ? "commissionAmount=" + commissionAmount + ", " : "") +
            (salaryAmount != null ? "salaryAmount=" + salaryAmount + ", " : "") +
            (latitude != null ? "latitude=" + latitude + ", " : "") +
            (longitude != null ? "longitude=" + longitude + ", " : "") +
            (plusCode != null ? "plusCode=" + plusCode + ", " : "") +
            (lastLocationDateTime != null ? "lastLocationDateTime=" + lastLocationDateTime + ", " : "") +
            (isAvailable != null ? "isAvailable=" + isAvailable + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (zoneId != null ? "zoneId=" + zoneId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
