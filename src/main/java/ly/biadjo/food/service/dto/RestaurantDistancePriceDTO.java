package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.RestaurantDistancePrice} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestaurantDistancePriceDTO implements Serializable {

    private Long id;

    private Double price;

    private Double fromKm;

    private Double toKm;

    private Boolean isAvailable;

    private RestaurantDTO restaurant;

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

    public Double getFromKm() {
        return fromKm;
    }

    public void setFromKm(Double fromKm) {
        this.fromKm = fromKm;
    }

    public Double getToKm() {
        return toKm;
    }

    public void setToKm(Double toKm) {
        this.toKm = toKm;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
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
        if (!(o instanceof RestaurantDistancePriceDTO)) {
            return false;
        }

        RestaurantDistancePriceDTO restaurantDistancePriceDTO = (RestaurantDistancePriceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, restaurantDistancePriceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantDistancePriceDTO{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", fromKm=" + getFromKm() +
            ", toKm=" + getToKm() +
            ", isAvailable='" + getIsAvailable() + "'" +
            ", restaurant=" + getRestaurant() +
            "}";
    }
}
