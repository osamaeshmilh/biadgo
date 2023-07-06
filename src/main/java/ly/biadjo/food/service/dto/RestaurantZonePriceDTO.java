package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.RestaurantZonePrice} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestaurantZonePriceDTO implements Serializable {

    private Long id;

    private Double price;

    private Boolean isAvailable;

    private RestaurantDTO restaurant;

    private ZoneDTO zone;

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

    public ZoneDTO getZone() {
        return zone;
    }

    public void setZone(ZoneDTO zone) {
        this.zone = zone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RestaurantZonePriceDTO)) {
            return false;
        }

        RestaurantZonePriceDTO restaurantZonePriceDTO = (RestaurantZonePriceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, restaurantZonePriceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantZonePriceDTO{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", isAvailable='" + getIsAvailable() + "'" +
            ", restaurant=" + getRestaurant() +
            ", zone=" + getZone() +
            "}";
    }
}
