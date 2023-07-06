package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.Cart} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.CartResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /carts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CartCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter quantity;

    private StringFilter customerNotes;

    private StringFilter foodExtraIdsList;

    private StringFilter foodIngredientIds;

    private StringFilter foodIngredientRemovedIds;

    private LongFilter customerId;

    private LongFilter foodId;

    private Boolean distinct;

    public CartCriteria() {
    }

    public CartCriteria(CartCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.quantity = other.quantity == null ? null : other.quantity.copy();
        this.customerNotes = other.customerNotes == null ? null : other.customerNotes.copy();
        this.foodExtraIdsList = other.foodExtraIdsList == null ? null : other.foodExtraIdsList.copy();
        this.foodIngredientIds = other.foodIngredientIds == null ? null : other.foodIngredientIds.copy();
        this.foodIngredientRemovedIds = other.foodIngredientRemovedIds == null ? null : other.foodIngredientRemovedIds.copy();
        this.customerId = other.customerId == null ? null : other.customerId.copy();
        this.foodId = other.foodId == null ? null : other.foodId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CartCriteria copy() {
        return new CartCriteria(this);
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

    public StringFilter getCustomerNotes() {
        return customerNotes;
    }

    public StringFilter customerNotes() {
        if (customerNotes == null) {
            customerNotes = new StringFilter();
        }
        return customerNotes;
    }

    public void setCustomerNotes(StringFilter customerNotes) {
        this.customerNotes = customerNotes;
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

    public LongFilter getFoodId() {
        return foodId;
    }

    public LongFilter foodId() {
        if (foodId == null) {
            foodId = new LongFilter();
        }
        return foodId;
    }

    public void setFoodId(LongFilter foodId) {
        this.foodId = foodId;
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
        final CartCriteria that = (CartCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(customerNotes, that.customerNotes) &&
                Objects.equals(foodExtraIdsList, that.foodExtraIdsList) &&
                Objects.equals(foodIngredientIds, that.foodIngredientIds) &&
                Objects.equals(foodIngredientRemovedIds, that.foodIngredientRemovedIds) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(foodId, that.foodId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            quantity,
            customerNotes,
            foodExtraIdsList,
            foodIngredientIds,
            foodIngredientRemovedIds,
            customerId,
            foodId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (quantity != null ? "quantity=" + quantity + ", " : "") +
            (customerNotes != null ? "customerNotes=" + customerNotes + ", " : "") +
            (foodExtraIdsList != null ? "foodExtraIdsList=" + foodExtraIdsList + ", " : "") +
            (foodIngredientIds != null ? "foodIngredientIds=" + foodIngredientIds + ", " : "") +
            (foodIngredientRemovedIds != null ? "foodIngredientRemovedIds=" + foodIngredientRemovedIds + ", " : "") +
            (customerId != null ? "customerId=" + customerId + ", " : "") +
            (foodId != null ? "foodId=" + foodId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
