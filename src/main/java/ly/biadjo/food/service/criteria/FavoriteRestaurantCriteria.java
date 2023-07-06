package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.FavoriteRestaurant} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.FavoriteRestaurantResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /favorite-restaurants?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FavoriteRestaurantCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter customerId;

    private LongFilter restaurantId;

    private Boolean distinct;

    public FavoriteRestaurantCriteria() {
    }

    public FavoriteRestaurantCriteria(FavoriteRestaurantCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.customerId = other.customerId == null ? null : other.customerId.copy();
        this.restaurantId = other.restaurantId == null ? null : other.restaurantId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public FavoriteRestaurantCriteria copy() {
        return new FavoriteRestaurantCriteria(this);
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
        final FavoriteRestaurantCriteria that = (FavoriteRestaurantCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, restaurantId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FavoriteRestaurantCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (customerId != null ? "customerId=" + customerId + ", " : "") +
            (restaurantId != null ? "restaurantId=" + restaurantId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
