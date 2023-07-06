package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RestaurantReview.
 */
@Entity
@Table(name = "restaurant_review")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestaurantReview extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "review")
    private String review;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "is_edited")
    private Boolean isEdited;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user", "cuisine", "categories"}, allowSetters = true)
    private Restaurant restaurant;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RestaurantReview id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return this.review;
    }

    public RestaurantReview review(String review) {
        this.setReview(review);
        return this;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRate() {
        return this.rate;
    }

    public RestaurantReview rate(Integer rate) {
        this.setRate(rate);
        return this;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Boolean getIsEdited() {
        return this.isEdited;
    }

    public RestaurantReview isEdited(Boolean isEdited) {
        this.setIsEdited(isEdited);
        return this;
    }

    public void setIsEdited(Boolean isEdited) {
        this.isEdited = isEdited;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public RestaurantReview customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantReview restaurant(Restaurant restaurant) {
        this.setRestaurant(restaurant);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RestaurantReview)) {
            return false;
        }
        return id != null && id.equals(((RestaurantReview) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantReview{" +
            "id=" + getId() +
            ", review='" + getReview() + "'" +
            ", rate=" + getRate() +
            ", isEdited='" + getIsEdited() + "'" +
            "}";
    }
}
