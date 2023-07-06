package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DriverLocation.
 */
@Entity
@Table(name = "driver_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DriverLocation extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "plus_code")
    private String plusCode;

    @Column(name = "location_date_time")
    private Instant locationDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user", "zone"}, allowSetters = true)
    private Driver driver;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DriverLocation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public DriverLocation latitude(Double latitude) {
        this.setLatitude(latitude);
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public DriverLocation longitude(Double longitude) {
        this.setLongitude(longitude);
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPlusCode() {
        return this.plusCode;
    }

    public DriverLocation plusCode(String plusCode) {
        this.setPlusCode(plusCode);
        return this;
    }

    public void setPlusCode(String plusCode) {
        this.plusCode = plusCode;
    }

    public Instant getLocationDateTime() {
        return this.locationDateTime;
    }

    public DriverLocation locationDateTime(Instant locationDateTime) {
        this.setLocationDateTime(locationDateTime);
        return this;
    }

    public void setLocationDateTime(Instant locationDateTime) {
        this.locationDateTime = locationDateTime;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public DriverLocation driver(Driver driver) {
        this.setDriver(driver);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DriverLocation)) {
            return false;
        }
        return id != null && id.equals(((DriverLocation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DriverLocation{" +
            "id=" + getId() +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", plusCode='" + getPlusCode() + "'" +
            ", locationDateTime='" + getLocationDateTime() + "'" +
            "}";
    }
}
