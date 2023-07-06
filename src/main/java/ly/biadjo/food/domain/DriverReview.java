package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DriverReview.
 */
@Entity
@Table(name = "driver_review")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DriverReview extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "details")
    private String details;

    @Column(name = "rate")
    private Integer rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user", "zone"}, allowSetters = true)
    private Driver driver;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DriverReview id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return this.details;
    }

    public DriverReview details(String details) {
        this.setDetails(details);
        return this;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getRate() {
        return this.rate;
    }

    public DriverReview rate(Integer rate) {
        this.setRate(rate);
        return this;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DriverReview customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public DriverReview driver(Driver driver) {
        this.setDriver(driver);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DriverReview)) {
            return false;
        }
        return id != null && id.equals(((DriverReview) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DriverReview{" +
            "id=" + getId() +
            ", details='" + getDetails() + "'" +
            ", rate=" + getRate() +
            "}";
    }
}
