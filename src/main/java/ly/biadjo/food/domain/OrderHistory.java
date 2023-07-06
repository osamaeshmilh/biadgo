package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

import ly.biadjo.food.domain.enumeration.OrderStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OrderHistory.
 */
@Entity
@Table(name = "order_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderHistory extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status_from")
    private OrderStatus orderStatusFrom;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status_to")
    private OrderStatus orderStatusTo;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"customer", "coupon", "driver", "deliveryAddress", "restaurant"}, allowSetters = true)
    private Order order;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OrderHistory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatusFrom() {
        return this.orderStatusFrom;
    }

    public OrderHistory orderStatusFrom(OrderStatus orderStatusFrom) {
        this.setOrderStatusFrom(orderStatusFrom);
        return this;
    }

    public void setOrderStatusFrom(OrderStatus orderStatusFrom) {
        this.orderStatusFrom = orderStatusFrom;
    }

    public OrderStatus getOrderStatusTo() {
        return this.orderStatusTo;
    }

    public OrderHistory orderStatusTo(OrderStatus orderStatusTo) {
        this.setOrderStatusTo(orderStatusTo);
        return this;
    }

    public void setOrderStatusTo(OrderStatus orderStatusTo) {
        this.orderStatusTo = orderStatusTo;
    }

    public String getNotes() {
        return this.notes;
    }

    public OrderHistory notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderHistory order(Order order) {
        this.setOrder(order);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderHistory)) {
            return false;
        }
        return id != null && id.equals(((OrderHistory) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderHistory{" +
            "id=" + getId() +
            ", orderStatusFrom='" + getOrderStatusFrom() + "'" +
            ", orderStatusTo='" + getOrderStatusTo() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
