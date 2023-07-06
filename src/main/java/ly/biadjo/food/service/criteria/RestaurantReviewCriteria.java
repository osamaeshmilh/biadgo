package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.RestaurantReview} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.RestaurantReviewResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /restaurant-reviews?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestaurantReviewCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter review;

    private IntegerFilter rate;

    private BooleanFilter isEdited;

    private LongFilter customerId;

    private LongFilter restaurantId;

    private Boolean distinct;

    public RestaurantReviewCriteria() {
    }

    public RestaurantReviewCriteria(RestaurantReviewCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.review = other.review == null ? null : other.review.copy();
        this.rate = other.rate == null ? null : other.rate.copy();
        this.isEdited = other.isEdited == null ? null : other.isEdited.copy();
        this.customerId = other.customerId == null ? null : other.customerId.copy();
        this.restaurantId = other.restaurantId == null ? null : other.restaurantId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public RestaurantReviewCriteria copy() {
        return new RestaurantReviewCriteria(this);
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

    public StringFilter getReview() {
        return review;
    }

    public StringFilter review() {
        if (review == null) {
            review = new StringFilter();
        }
        return review;
    }

    public void setReview(StringFilter review) {
        this.review = review;
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

    public BooleanFilter getIsEdited() {
        return isEdited;
    }

    public BooleanFilter isEdited() {
        if (isEdited == null) {
            isEdited = new BooleanFilter();
        }
        return isEdited;
    }

    public void setIsEdited(BooleanFilter isEdited) {
        this.isEdited = isEdited;
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

    public LongFilter getRestaurantId() {
        return restaurantId;
    }

    public LongFilter restaurantId() {
        if (restaurantId == null) {
            restaurantId = new LongFilter();
        }
        return restaurantId;
    }

    public void setRestaurantId(LongFilter restaurantId) {
        this.restaurantId = restaurantId;
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
        final RestaurantReviewCriteria that = (RestaurantReviewCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(review, that.review) &&
                Objects.equals(rate, that.rate) &&
                Objects.equals(isEdited, that.isEdited) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, review, rate, isEdited, customerId, restaurantId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantReviewCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (review != null ? "review=" + review + ", " : "") +
            (rate != null ? "rate=" + rate + ", " : "") +
            (isEdited != null ? "isEdited=" + isEdited + ", " : "") +
            (customerId != null ? "customerId=" + customerId + ", " : "") +
            (restaurantId != null ? "restaurantId=" + restaurantId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
