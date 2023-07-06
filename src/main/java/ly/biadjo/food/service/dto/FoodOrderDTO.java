package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.FoodOrder} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FoodOrderDTO implements Serializable {

    private Long id;

    private Double price;

    private Integer quantity;

    private Double total;

    private String specialNotes;

    private String foodExtraIdsList;

    private String foodIngredientIds;

    private String foodIngredientRemovedIds;

    private OrderDTO order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
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

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodOrderDTO)) {
            return false;
        }

        FoodOrderDTO foodOrderDTO = (FoodOrderDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, foodOrderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodOrderDTO{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", quantity=" + getQuantity() +
            ", total=" + getTotal() +
            ", specialNotes='" + getSpecialNotes() + "'" +
            ", foodExtraIdsList='" + getFoodExtraIdsList() + "'" +
            ", foodIngredientIds='" + getFoodIngredientIds() + "'" +
            ", foodIngredientRemovedIds='" + getFoodIngredientRemovedIds() + "'" +
            ", order=" + getOrder() +
            "}";
    }
}
