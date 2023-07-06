package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.DeliveryAddress} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.DeliveryAddressResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /delivery-addresses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DeliveryAddressCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter address;

    private StringFilter details;

    private StringFilter phone;

    private BooleanFilter isDefault;

    private DoubleFilter latitude;

    private DoubleFilter longitude;

    private StringFilter plusCode;

    private BooleanFilter isActive;

    private StringFilter notes;

    private LongFilter customerId;

    private LongFilter zoneId;

    private Boolean distinct;

    public DeliveryAddressCriteria() {
    }

    public DeliveryAddressCriteria(DeliveryAddressCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.details = other.details == null ? null : other.details.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.isDefault = other.isDefault == null ? null : other.isDefault.copy();
        this.latitude = other.latitude == null ? null : other.latitude.copy();
        this.longitude = other.longitude == null ? null : other.longitude.copy();
        this.plusCode = other.plusCode == null ? null : other.plusCode.copy();
        this.isActive = other.isActive == null ? null : other.isActive.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.customerId = other.customerId == null ? null : other.customerId.copy();
        this.zoneId = other.zoneId == null ? null : other.zoneId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public DeliveryAddressCriteria copy() {
        return new DeliveryAddressCriteria(this);
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

    public StringFilter getTitle() {
        return title;
    }

    public StringFilter title() {
        if (title == null) {
            title = new StringFilter();
        }
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getAddress() {
        return address;
    }

    public StringFilter address() {
        if (address == null) {
            address = new StringFilter();
        }
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getDetails() {
        return details;
    }

    public StringFilter details() {
        if (details == null) {
            details = new StringFilter();
        }
        return details;
    }

    public void setDetails(StringFilter details) {
        this.details = details;
    }

    public StringFilter getPhone() {
        return phone;
    }

    public StringFilter phone() {
        if (phone == null) {
            phone = new StringFilter();
        }
        return phone;
    }

    public void setPhone(StringFilter phone) {
        this.phone = phone;
    }

    public BooleanFilter getIsDefault() {
        return isDefault;
    }

    public BooleanFilter isDefault() {
        if (isDefault == null) {
            isDefault = new BooleanFilter();
        }
        return isDefault;
    }

    public void setIsDefault(BooleanFilter isDefault) {
        this.isDefault = isDefault;
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

    public BooleanFilter getIsActive() {
        return isActive;
    }

    public BooleanFilter isActive() {
        if (isActive == null) {
            isActive = new BooleanFilter();
        }
        return isActive;
    }

    public void setIsActive(BooleanFilter isActive) {
        this.isActive = isActive;
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

    public LongFilter getCustomerId() {
        return customerId;
    }

    public LongFilter customerId() {
        if (customerId == null) {
            customerId = new LongFilter();
        }
        return customerId;
    }

    public void setCustomerId(LongFilter customerId) {
        this.customerId = customerId;
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
        final DeliveryAddressCriteria that = (DeliveryAddressCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(address, that.address) &&
                Objects.equals(details, that.details) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(isDefault, that.isDefault) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(plusCode, that.plusCode) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(zoneId, that.zoneId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            title,
            address,
            details,
            phone,
            isDefault,
            latitude,
            longitude,
            plusCode,
            isActive,
            notes,
            customerId,
            zoneId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryAddressCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (address != null ? "address=" + address + ", " : "") +
            (details != null ? "details=" + details + ", " : "") +
            (phone != null ? "phone=" + phone + ", " : "") +
            (isDefault != null ? "isDefault=" + isDefault + ", " : "") +
            (latitude != null ? "latitude=" + latitude + ", " : "") +
            (longitude != null ? "longitude=" + longitude + ", " : "") +
            (plusCode != null ? "plusCode=" + plusCode + ", " : "") +
            (isActive != null ? "isActive=" + isActive + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (customerId != null ? "customerId=" + customerId + ", " : "") +
            (zoneId != null ? "zoneId=" + zoneId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
