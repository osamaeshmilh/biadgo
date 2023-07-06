package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Cart.
 */
@Entity
@Table(name = "cart")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Cart extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "customer_notes")
    private String customerNotes;

    @Column(name = "food_extra_ids_list")
    private String foodExtraIdsList;

    @Column(name = "food_ingredient_ids")
    private String foodIngredientIds;

    @Column(name = "food_ingredient_removed_ids")
    private String foodIngredientRemovedIds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"restaurant", "category"}, allowSetters = true)
    private Food food;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Cart id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Cart quantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCustomerNotes() {
        return this.customerNotes;
    }

    public Cart customerNotes(String customerNotes) {
        this.setCustomerNotes(customerNotes);
        return this;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    public String getFoodExtraIdsList() {
        return this.foodExtraIdsList;
    }

    public Cart foodExtraIdsList(String foodExtraIdsList) {
        this.setFoodExtraIdsList(foodExtraIdsList);
        return this;
    }

    public void setFoodExtraIdsList(String foodExtraIdsList) {
        this.foodExtraIdsList = foodExtraIdsList;
    }

    public String getFoodIngredientIds() {
        return this.foodIngredientIds;
    }

    public Cart foodIngredientIds(String foodIngredientIds) {
        this.setFoodIngredientIds(foodIngredientIds);
        return this;
    }

    public void setFoodIngredientIds(String foodIngredientIds) {
        this.foodIngredientIds = foodIngredientIds;
    }

    public String getFoodIngredientRemovedIds() {
        return this.foodIngredientRemovedIds;
    }

    public Cart foodIngredientRemovedIds(String foodIngredientRemovedIds) {
        this.setFoodIngredientRemovedIds(foodIngredientRemovedIds);
        return this;
    }

    public void setFoodIngredientRemovedIds(String foodIngredientRemovedIds) {
        this.foodIngredientRemovedIds = foodIngredientRemovedIds;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    public Food getFood() {
        return this.food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Cart food(Food food) {
        this.setFood(food);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cart)) {
            return false;
        }
        return id != null && id.equals(((Cart) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cart{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", customerNotes='" + getCustomerNotes() + "'" +
            ", foodExtraIdsList='" + getFoodExtraIdsList() + "'" +
            ", foodIngredientIds='" + getFoodIngredientIds() + "'" +
            ", foodIngredientRemovedIds='" + getFoodIngredientRemovedIds() + "'" +
            "}";
    }
}
