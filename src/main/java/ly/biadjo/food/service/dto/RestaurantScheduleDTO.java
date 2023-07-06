package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.DayOfWeek;

/**
 * A DTO for the {@link ly.biadjo.food.domain.RestaurantSchedule} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestaurantScheduleDTO implements Serializable {

    private Long id;

    private DayOfWeek dayOfWeek;

    private String openingTime;

    private String closingTime;

    private RestaurantDTO restaurant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
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
        if (!(o instanceof RestaurantScheduleDTO)) {
            return false;
        }

        RestaurantScheduleDTO restaurantScheduleDTO = (RestaurantScheduleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, restaurantScheduleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantScheduleDTO{" +
            "id=" + getId() +
            ", dayOfWeek='" + getDayOfWeek() + "'" +
            ", openingTime='" + getOpeningTime() + "'" +
            ", closingTime='" + getClosingTime() + "'" +
            ", restaurant=" + getRestaurant() +
            "}";
    }
}
