package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.FavoriteRestaurant} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FavoriteRestaurantDTO implements Serializable {

    private Long id;

    private CustomerDTO customer;

    private RestaurantDTO restaurant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FavoriteRestaurantDTO)) {
            return false;
        }

        FavoriteRestaurantDTO favoriteRestaurantDTO = (FavoriteRestaurantDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, favoriteRestaurantDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FavoriteRestaurantDTO{" +
            "id=" + getId() +
            ", customer=" + getCustomer() +
            ", restaurant=" + getRestaurant() +
            "}";
    }
}
