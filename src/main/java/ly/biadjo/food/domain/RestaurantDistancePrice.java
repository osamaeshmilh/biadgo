package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RestaurantDistancePrice.
 */
@Entity
@Table(name = "restaurant_distance_price")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestaurantDistancePrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "from_km")
    private Double fromKm;

    @Column(name = "to_km")
    private Double toKm;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user", "cuisine", "categories"}, allowSetters = true)
    private Restaurant restaurant;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RestaurantDistancePrice id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return this.price;
    }

    public RestaurantDistancePrice price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getFromKm() {
        return this.fromKm;
    }

    public RestaurantDistancePrice fromKm(Double fromKm) {
        this.setFromKm(fromKm);
        return this;
    }

    public void setFromKm(Double fromKm) {
        this.fromKm = fromKm;
    }

    public Double getToKm() {
        return this.toKm;
    }

    public RestaurantDistancePrice toKm(Double toKm) {
        this.setToKm(toKm);
        return this;
    }

    public void setToKm(Double toKm) {
        this.toKm = toKm;
    }

    public Boolean getIsAvailable() {
        return this.isAvailable;
    }

    public RestaurantDistancePrice isAvailable(Boolean isAvailable) {
        this.setIsAvailable(isAvailable);
        return this;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantDistancePrice restaurant(Restaurant restaurant) {
        this.setRestaurant(restaurant);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RestaurantDistancePrice)) {
            return false;
        }
        return id != null && id.equals(((RestaurantDistancePrice) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantDistancePrice{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", fromKm=" + getFromKm() +
            ", toKm=" + getToKm() +
            ", isAvailable='" + getIsAvailable() + "'" +
            "}";
    }
}
