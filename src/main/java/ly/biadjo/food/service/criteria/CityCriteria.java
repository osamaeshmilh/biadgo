package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.City} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.CityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter nameAr;

    private StringFilter nameEn;

    private DoubleFilter latitude;

    private DoubleFilter longitude;

    private StringFilter plusCode;

    private DoubleFilter radius;

    private BooleanFilter isActive;

    private Boolean distinct;

    public CityCriteria() {
    }

    public CityCriteria(CityCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.nameAr = other.nameAr == null ? null : other.nameAr.copy();
        this.nameEn = other.nameEn == null ? null : other.nameEn.copy();
        this.latitude = other.latitude == null ? null : other.latitude.copy();
        this.longitude = other.longitude == null ? null : other.longitude.copy();
        this.plusCode = other.plusCode == null ? null : other.plusCode.copy();
        this.radius = other.radius == null ? null : other.radius.copy();
        this.isActive = other.isActive == null ? null : other.isActive.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CityCriteria copy() {
        return new CityCriteria(this);
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

    public DoubleFilter getRadius() {
        return radius;
    }

    public DoubleFilter radius() {
        if (radius == null) {
            radius = new DoubleFilter();
        }
        return radius;
    }

    public void setRadius(DoubleFilter radius) {
        this.radius = radius;
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
        final CityCriteria that = (CityCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(nameAr, that.nameAr) &&
                Objects.equals(nameEn, that.nameEn) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(plusCode, that.plusCode) &&
                Objects.equals(radius, that.radius) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nameAr, nameEn, latitude, longitude, plusCode, radius, isActive, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CityCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (nameAr != null ? "nameAr=" + nameAr + ", " : "") +
            (nameEn != null ? "nameEn=" + nameEn + ", " : "") +
            (latitude != null ? "latitude=" + latitude + ", " : "") +
            (longitude != null ? "longitude=" + longitude + ", " : "") +
            (plusCode != null ? "plusCode=" + plusCode + ", " : "") +
            (radius != null ? "radius=" + radius + ", " : "") +
            (isActive != null ? "isActive=" + isActive + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
