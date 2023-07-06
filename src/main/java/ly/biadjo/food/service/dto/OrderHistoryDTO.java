package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.OrderStatus;

/**
 * A DTO for the {@link ly.biadjo.food.domain.OrderHistory} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderHistoryDTO implements Serializable {

    private Long id;

    private OrderStatus orderStatusFrom;

    private OrderStatus orderStatusTo;

    private String notes;

    private OrderDTO order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatusFrom() {
        return orderStatusFrom;
    }

    public void setOrderStatusFrom(OrderStatus orderStatusFrom) {
        this.orderStatusFrom = orderStatusFrom;
    }

    public OrderStatus getOrderStatusTo() {
        return orderStatusTo;
    }

    public void setOrderStatusTo(OrderStatus orderStatusTo) {
        this.orderStatusTo = orderStatusTo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderHistoryDTO)) {
            return false;
        }

        OrderHistoryDTO orderHistoryDTO = (OrderHistoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderHistoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderHistoryDTO{" +
            "id=" + getId() +
            ", orderStatusFrom='" + getOrderStatusFrom() + "'" +
            ", orderStatusTo='" + getOrderStatusTo() + "'" +
            ", notes='" + getNotes() + "'" +
            ", order=" + getOrder() +
            "}";
    }
}
