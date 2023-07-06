package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.FoodOrder} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.FoodOrderResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /food-orders?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FoodOrderCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private DoubleFilter price;

    private IntegerFilter quantity;

    private DoubleFilter total;

    private StringFilter specialNotes;

    private StringFilter foodExtraIdsList;

    private StringFilter foodIngredientIds;

    private StringFilter foodIngredientRemovedIds;

    private LongFilter orderId;

    private Boolean distinct;

    public FoodOrderCriteria() {
    }

    public FoodOrderCriteria(FoodOrderCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.quantity = other.quantity == null ? null : other.quantity.copy();
        this.total = other.total == null ? null : other.total.copy();
        this.specialNotes = other.specialNotes == null ? null : other.specialNotes.copy();
        this.foodExtraIdsList = other.foodExtraIdsList == null ? null : other.foodExtraIdsList.copy();
        this.foodIngredientIds = other.foodIngredientIds == null ? null : other.foodIngredientIds.copy();
        this.foodIngredientRemovedIds = other.foodIngredientRemovedIds == null ? null : other.foodIngredientRemovedIds.copy();
        this.orderId = other.orderId == null ? null : other.orderId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public FoodOrderCriteria copy() {
        return new FoodOrderCriteria(this);
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

    public IntegerFilter getQuantity() {
        return quantity;
    }

    public IntegerFilter quantity() {
        if (quantity == null) {
            quantity = new IntegerFilter();
        }
        return quantity;
    }

    public void setQuantity(IntegerFilter quantity) {
        this.quantity = quantity;
    }

    public DoubleFilter getTotal() {
        return total;
    }

    public DoubleFilter total() {
        if (total == null) {
            total = new DoubleFilter();
        }
        return total;
    }

    public void setTotal(DoubleFilter total) {
        this.total = total;
    }

    public StringFilter getSpecialNotes() {
        return specialNotes;
    }

    public StringFilter specialNotes() {
        if (specialNotes == null) {
            specialNotes = new StringFilter();
        }
        return specialNotes;
    }

    public void setSpecialNotes(StringFilter specialNotes) {
        this.specialNotes = specialNotes;
    }

    public StringFilter getFoodExtraIdsList() {
        return foodExtraIdsList;
    }

    public StringFilter foodExtraIdsList() {
        if (foodExtraIdsList == null) {
            foodExtraIdsList = new StringFilter();
        }
        return foodExtraIdsList;
    }

    public void setFoodExtraIdsList(StringFilter foodExtraIdsList) {
        this.foodExtraIdsList = foodExtraIdsList;
    }

    public StringFilter getFoodIngredientIds() {
        return foodIngredientIds;
    }

    public StringFilter foodIngredientIds() {
        if (foodIngredientIds == null) {
            foodIngredientIds = new StringFilter();
        }
        return foodIngredientIds;
    }

    public void setFoodIngredientIds(StringFilter foodIngredientIds) {
        this.foodIngredientIds = foodIngredientIds;
    }

    public StringFilter getFoodIngredientRemovedIds() {
        return foodIngredientRemovedIds;
    }

    public StringFilter foodIngredientRemovedIds() {
        if (foodIngredientRemovedIds == null) {
            foodIngredientRemovedIds = new StringFilter();
        }
        return foodIngredientRemovedIds;
    }

    public void setFoodIngredientRemovedIds(StringFilter foodIngredientRemovedIds) {
        this.foodIngredientRemovedIds = foodIngredientRemovedIds;
    }

    public LongFilter getOrderId() {
        return orderId;
    }

    public LongFilter orderId() {
        if (orderId == null) {
            orderId = new LongFilter();
        }
        return orderId;
    }

    public void setOrderId(LongFilter orderId) {
        this.orderId = orderId;
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
        final FoodOrderCriteria that = (FoodOrderCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(price, that.price) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(total, that.total) &&
                Objects.equals(specialNotes, that.specialNotes) &&
                Objects.equals(foodExtraIdsList, that.foodExtraIdsList) &&
                Objects.equals(foodIngredientIds, that.foodIngredientIds) &&
                Objects.equals(foodIngredientRemovedIds, that.foodIngredientRemovedIds) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            price,
            quantity,
            total,
            specialNotes,
            foodExtraIdsList,
            foodIngredientIds,
            foodIngredientRemovedIds,
            orderId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodOrderCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (price != null ? "price=" + price + ", " : "") +
            (quantity != null ? "quantity=" + quantity + ", " : "") +
            (total != null ? "total=" + total + ", " : "") +
            (specialNotes != null ? "specialNotes=" + specialNotes + ", " : "") +
            (foodExtraIdsList != null ? "foodExtraIdsList=" + foodExtraIdsList + ", " : "") +
            (foodIngredientIds != null ? "foodIngredientIds=" + foodIngredientIds + ", " : "") +
            (foodIngredientRemovedIds != null ? "foodIngredientRemovedIds=" + foodIngredientRemovedIds + ", " : "") +
            (orderId != null ? "orderId=" + orderId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
