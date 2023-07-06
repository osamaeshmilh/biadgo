package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.DriverLocation} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DriverLocationDTO implements Serializable {

    private Long id;

    private Double latitude;

    private Double longitude;

    private String plusCode;

    private Instant locationDateTime;

    private DriverDTO driver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPlusCode() {
        return plusCode;
    }

    public void setPlusCode(String plusCode) {
        this.plusCode = plusCode;
    }

    public Instant getLocationDateTime() {
        return locationDateTime;
    }

    public void setLocationDateTime(Instant locationDateTime) {
        this.locationDateTime = locationDateTime;
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
        if (!(o instanceof DriverLocationDTO)) {
            return false;
        }

        DriverLocationDTO driverLocationDTO = (DriverLocationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, driverLocationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DriverLocationDTO{" +
            "id=" + getId() +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", plusCode='" + getPlusCode() + "'" +
            ", locationDateTime='" + getLocationDateTime() + "'" +
            ", driver=" + getDriver() +
            "}";
    }
}
