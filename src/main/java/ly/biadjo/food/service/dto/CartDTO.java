package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.Cart} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CartDTO implements Serializable {

    private Long id;

    private Integer quantity;

    private String customerNotes;

    private String foodExtraIdsList;

    private String foodIngredientIds;

    private String foodIngredientRemovedIds;

    private CustomerDTO customer;

    private FoodDTO food;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCustomerNotes() {
        return customerNotes;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    public String getFoodExtraIdsList() {
        return foodExtraIdsList;
    }

    public void setFoodExtraIdsList(String foodExtraIdsList) {
        this.foodExtraIdsList = foodExtraIdsList;
    }

    public String getFoodIngredientIds() {
        return foodIngredientIds;
    }

    public void setFoodIngredientIds(String foodIngredientIds) {
        this.foodIngredientIds = foodIngredientIds;
    }

    public String getFoodIngredientRemovedIds() {
        return foodIngredientRemovedIds;
    }

    public void setFoodIngredientRemovedIds(String foodIngredientRemovedIds) {
        this.foodIngredientRemovedIds = foodIngredientRemovedIds;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public FoodDTO getFood() {
        return food;
    }

    public void setFood(FoodDTO food) {
        this.food = food;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartDTO)) {
            return false;
        }

        CartDTO cartDTO = (CartDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cartDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", customerNotes='" + getCustomerNotes() + "'" +
            ", foodExtraIdsList='" + getFoodExtraIdsList() + "'" +
            ", foodIngredientIds='" + getFoodIngredientIds() + "'" +
            ", foodIngredientRemovedIds='" + getFoodIngredientRemovedIds() + "'" +
            ", customer=" + getCustomer() +
            ", food=" + getFood() +
            "}";
    }
}
