package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.RestaurantReview} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestaurantReviewDTO implements Serializable {

    private Long id;

    private String review;

    private Integer rate;

    private Boolean isEdited;

    private CustomerDTO customer;

    private RestaurantDTO restaurant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Boolean getIsEdited() {
        return isEdited;
    }

    public void setIsEdited(Boolean isEdited) {
        this.isEdited = isEdited;
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
        if (!(o instanceof RestaurantReviewDTO)) {
            return false;
        }

        RestaurantReviewDTO restaurantReviewDTO = (RestaurantReviewDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, restaurantReviewDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantReviewDTO{" +
            "id=" + getId() +
            ", review='" + getReview() + "'" +
            ", rate=" + getRate() +
            ", isEdited='" + getIsEdited() + "'" +
            ", customer=" + getCustomer() +
            ", restaurant=" + getRestaurant() +
            "}";
    }
}
