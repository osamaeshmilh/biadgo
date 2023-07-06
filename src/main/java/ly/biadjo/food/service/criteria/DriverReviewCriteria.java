package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.DriverReview} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.DriverReviewResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /driver-reviews?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DriverReviewCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter details;

    private IntegerFilter rate;

    private LongFilter customerId;

    private LongFilter driverId;

    private Boolean distinct;

    public DriverReviewCriteria() {
    }

    public DriverReviewCriteria(DriverReviewCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.details = other.details == null ? null : other.details.copy();
        this.rate = other.rate == null ? null : other.rate.copy();
        this.customerId = other.customerId == null ? null : other.customerId.copy();
        this.driverId = other.driverId == null ? null : other.driverId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public DriverReviewCriteria copy() {
        return new DriverReviewCriteria(this);
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

    public IntegerFilter getRate() {
        return rate;
    }

    public IntegerFilter rate() {
        if (rate == null) {
            rate = new IntegerFilter();
        }
        return rate;
    }

    public void setRate(IntegerFilter rate) {
        this.rate = rate;
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
        final DriverReviewCriteria that = (DriverReviewCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(details, that.details) &&
                Objects.equals(rate, that.rate) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(driverId, that.driverId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, details, rate, customerId, driverId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DriverReviewCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (details != null ? "details=" + details + ", " : "") +
            (rate != null ? "rate=" + rate + ", " : "") +
            (customerId != null ? "customerId=" + customerId + ", " : "") +
            (driverId != null ? "driverId=" + driverId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
