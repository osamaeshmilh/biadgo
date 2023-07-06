package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.RestaurantZonePrice} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.RestaurantZonePriceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /restaurant-zone-prices?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestaurantZonePriceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DoubleFilter price;

    private BooleanFilter isAvailable;

    private LongFilter restaurantId;

    private LongFilter zoneId;

    private Boolean distinct;

    public RestaurantZonePriceCriteria() {
    }

    public RestaurantZonePriceCriteria(RestaurantZonePriceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.isAvailable = other.isAvailable == null ? null : other.isAvailable.copy();
        this.restaurantId = other.restaurantId == null ? null : other.restaurantId.copy();
        this.zoneId = other.zoneId == null ? null : other.zoneId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public RestaurantZonePriceCriteria copy() {
        return new RestaurantZonePriceCriteria(this);
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
        final RestaurantZonePriceCriteria that = (RestaurantZonePriceCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(price, that.price) &&
                Objects.equals(isAvailable, that.isAvailable) &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(zoneId, that.zoneId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, isAvailable, restaurantId, zoneId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantZonePriceCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (price != null ? "price=" + price + ", " : "") +
            (isAvailable != null ? "isAvailable=" + isAvailable + ", " : "") +
            (restaurantId != null ? "restaurantId=" + restaurantId + ", " : "") +
            (zoneId != null ? "zoneId=" + zoneId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
