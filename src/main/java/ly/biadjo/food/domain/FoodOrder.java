package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FoodOrder.
 */
@Entity
@Table(name = "food_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FoodOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private Double total;

    @Column(name = "special_notes")
    private String specialNotes;

    @Column(name = "food_extra_ids_list")
    private String foodExtraIdsList;

    @Column(name = "food_ingredient_ids")
    private String foodIngredientIds;

    @Column(name = "food_ingredient_removed_ids")
    private String foodIngredientRemovedIds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"customer", "coupon", "driver", "deliveryAddress", "restaurant"}, allowSetters = true)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"restaurant", "category"}, allowSetters = true)
    private Food food;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FoodOrder id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return this.price;
    }

    public FoodOrder price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public FoodOrder quantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return this.total;
    }

    public FoodOrder total(Double total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getSpecialNotes() {
        return this.specialNotes;
    }

    public FoodOrder specialNotes(String specialNotes) {
        this.setSpecialNotes(specialNotes);
        return this;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }

    public String getFoodExtraIdsList() {
        return this.foodExtraIdsList;
    }

    public FoodOrder foodExtraIdsList(String foodExtraIdsList) {
        this.setFoodExtraIdsList(foodExtraIdsList);
        return this;
    }

    public void setFoodExtraIdsList(String foodExtraIdsList) {
        this.foodExtraIdsList = foodExtraIdsList;
    }

    public String getFoodIngredientIds() {
        return this.foodIngredientIds;
    }

    public FoodOrder foodIngredientIds(String foodIngredientIds) {
        this.setFoodIngredientIds(foodIngredientIds);
        return this;
    }

    public void setFoodIngredientIds(String foodIngredientIds) {
        this.foodIngredientIds = foodIngredientIds;
    }

    public String getFoodIngredientRemovedIds() {
        return this.foodIngredientRemovedIds;
    }

    public FoodOrder foodIngredientRemovedIds(String foodIngredientRemovedIds) {
        this.setFoodIngredientRemovedIds(foodIngredientRemovedIds);
        return this;
    }

    public void setFoodIngredientRemovedIds(String foodIngredientRemovedIds) {
        this.foodIngredientRemovedIds = foodIngredientRemovedIds;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public FoodOrder order(Order order) {
        this.setOrder(order);
        return this;
    }

    public Food getFood() {
        return this.food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public FoodOrder food(Food food) {
        this.setFood(food);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodOrder)) {
            return false;
        }
        return id != null && id.equals(((FoodOrder) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodOrder{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", quantity=" + getQuantity() +
            ", total=" + getTotal() +
            ", specialNotes='" + getSpecialNotes() + "'" +
            ", foodExtraIdsList='" + getFoodExtraIdsList() + "'" +
            ", foodIngredientIds='" + getFoodIngredientIds() + "'" +
            ", foodIngredientRemovedIds='" + getFoodIngredientRemovedIds() + "'" +
            "}";
    }
}
