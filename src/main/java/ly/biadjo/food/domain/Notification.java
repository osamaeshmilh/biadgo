package ly.biadjo.food.domain;

import jakarta.persistence.*;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Notification.
 */
@Entity
@Table(name = "notification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Notification extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "details")
    private String details;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "customer_id")
    private Long customerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Notification id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Notification title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return this.details;
    }

    public Notification details(String details) {
        this.setDetails(details);
        return this;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getIsRead() {
        return this.isRead;
    }

    public Notification isRead(Boolean isRead) {
        this.setIsRead(isRead);
        return this;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public Notification customerId(Long customerId) {
        this.setCustomerId(customerId);
        return this;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Notification)) {
            return false;
        }
        return id != null && id.equals(((Notification) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Notification{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", details='" + getDetails() + "'" +
            ", isRead='" + getIsRead() + "'" +
            ", customerId=" + getCustomerId() +
            "}";
    }
}
