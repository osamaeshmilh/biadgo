package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.RestaurantDistancePrice} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.RestaurantDistancePriceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /restaurant-distance-prices?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestaurantDistancePriceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DoubleFilter price;

    private DoubleFilter fromKm;

    private DoubleFilter toKm;

    private BooleanFilter isAvailable;

    private LongFilter restaurantId;

    private Boolean distinct;

    public RestaurantDistancePriceCriteria() {
    }

    public RestaurantDistancePriceCriteria(RestaurantDistancePriceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.fromKm = other.fromKm == null ? null : other.fromKm.copy();
        this.toKm = other.toKm == null ? null : other.toKm.copy();
        this.isAvailable = other.isAvailable == null ? null : other.isAvailable.copy();
        this.restaurantId = other.restaurantId == null ? null : other.restaurantId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public RestaurantDistancePriceCriteria copy() {
        return new RestaurantDistancePriceCriteria(this);
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

    public DoubleFilter getPrice() {
        return price;
    }

    public DoubleFilter price() {
        if (price == null) {
            price = new DoubleFilter();
        }
        return price;
    }

    public void setPrice(DoubleFilter price) {
        this.price = price;
    }

    public DoubleFilter getFromKm() {
        return fromKm;
    }

    public DoubleFilter fromKm() {
        if (fromKm == null) {
            fromKm = new DoubleFilter();
        }
        return fromKm;
    }

    public void setFromKm(DoubleFilter fromKm) {
        this.fromKm = fromKm;
    }

    public DoubleFilter getToKm() {
        return toKm;
    }

    public DoubleFilter toKm() {
        if (toKm == null) {
            toKm = new DoubleFilter();
        }
        return toKm;
    }

    public void setToKm(DoubleFilter toKm) {
        this.toKm = toKm;
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
        final RestaurantDistancePriceCriteria that = (RestaurantDistancePriceCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(price, that.price) &&
                Objects.equals(fromKm, that.fromKm) &&
                Objects.equals(toKm, that.toKm) &&
                Objects.equals(isAvailable, that.isAvailable) &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, fromKm, toKm, isAvailable, restaurantId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantDistancePriceCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (price != null ? "price=" + price + ", " : "") +
            (fromKm != null ? "fromKm=" + fromKm + ", " : "") +
            (toKm != null ? "toKm=" + toKm + ", " : "") +
            (isAvailable != null ? "isAvailable=" + isAvailable + ", " : "") +
            (restaurantId != null ? "restaurantId=" + restaurantId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
