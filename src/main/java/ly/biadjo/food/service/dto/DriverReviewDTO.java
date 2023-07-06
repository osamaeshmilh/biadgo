package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.DriverReview} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DriverReviewDTO implements Serializable {

    private Long id;

    private String details;

    private Integer rate;

    private CustomerDTO customer;

    private DriverDTO driver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public DriverDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverDTO driver) {
        this.driver = driver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DriverReviewDTO)) {
            return false;
        }

        DriverReviewDTO driverReviewDTO = (DriverReviewDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, driverReviewDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DriverReviewDTO{" +
            "id=" + getId() +
            ", details='" + getDetails() + "'" +
            ", rate=" + getRate() +
            ", customer=" + getCustomer() +
            ", driver=" + getDriver() +
            "}";
    }
}
