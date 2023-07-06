package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.DriverLocation} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.DriverLocationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /driver-locations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DriverLocationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DoubleFilter latitude;

    private DoubleFilter longitude;

    private StringFilter plusCode;

    private InstantFilter locationDateTime;

    private LongFilter driverId;

    private Boolean distinct;

    public DriverLocationCriteria() {
    }

    public DriverLocationCriteria(DriverLocationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.latitude = other.latitude == null ? null : other.latitude.copy();
        this.longitude = other.longitude == null ? null : other.longitude.copy();
        this.plusCode = other.plusCode == null ? null : other.plusCode.copy();
        this.locationDateTime = other.locationDateTime == null ? null : other.locationDateTime.copy();
        this.driverId = other.driverId == null ? null : other.driverId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public DriverLocationCriteria copy() {
        return new DriverLocationCriteria(this);
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

    public InstantFilter getLocationDateTime() {
        return locationDateTime;
    }

    public InstantFilter locationDateTime() {
        if (locationDateTime == null) {
            locationDateTime = new InstantFilter();
        }
        return locationDateTime;
    }

    public void setLocationDateTime(InstantFilter locationDateTime) {
        this.locationDateTime = locationDateTime;
    }

    public LongFilter getDriverId() {
        return driverId;
    }

    public LongFilter driverId() {
        if (driverId == null) {
            driverId = new LongFilter();
        }
        return driverId;
    }

    public void setDriverId(LongFilter driverId) {
        this.driverId = driverId;
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
        final DriverLocationCriteria that = (DriverLocationCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(plusCode, that.plusCode) &&
                Objects.equals(locationDateTime, that.locationDateTime) &&
                Objects.equals(driverId, that.driverId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude, plusCode, locationDateTime, driverId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DriverLocationCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (latitude != null ? "latitude=" + latitude + ", " : "") +
            (longitude != null ? "longitude=" + longitude + ", " : "") +
            (plusCode != null ? "plusCode=" + plusCode + ", " : "") +
            (locationDateTime != null ? "locationDateTime=" + locationDateTime + ", " : "") +
            (driverId != null ? "driverId=" + driverId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
