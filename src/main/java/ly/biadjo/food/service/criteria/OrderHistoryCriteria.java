package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.OrderStatus;
import ly.biadjo.food.domain.enumeration.OrderStatus;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.OrderHistory} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.OrderHistoryResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /order-histories?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderHistoryCriteria implements Serializable, Criteria {

    /**
     * Class for filtering OrderStatus
     */
    public static class OrderStatusFilter extends Filter<OrderStatus> {

        public OrderStatusFilter() {
        }

        public OrderStatusFilter(OrderStatusFilter filter) {
            super(filter);
        }

        @Override
        public OrderStatusFilter copy() {
            return new OrderStatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private OrderStatusFilter orderStatusFrom;

    private OrderStatusFilter orderStatusTo;

    private StringFilter notes;

    private LongFilter orderId;

    private Boolean distinct;

    public OrderHistoryCriteria() {
    }

    public OrderHistoryCriteria(OrderHistoryCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.orderStatusFrom = other.orderStatusFrom == null ? null : other.orderStatusFrom.copy();
        this.orderStatusTo = other.orderStatusTo == null ? null : other.orderStatusTo.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.orderId = other.orderId == null ? null : other.orderId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public OrderHistoryCriteria copy() {
        return new OrderHistoryCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public OrderStatusFilter getOrderStatusFrom() {
        return orderStatusFrom;
    }

    public OrderStatusFilter orderStatusFrom() {
        if (orderStatusFrom == null) {
            orderStatusFrom = new OrderStatusFilter();
        }
        return orderStatusFrom;
    }

    public void setOrderStatusFrom(OrderStatusFilter orderStatusFrom) {
        this.orderStatusFrom = orderStatusFrom;
    }

    public OrderStatusFilter getOrderStatusTo() {
        return orderStatusTo;
    }

    public OrderStatusFilter orderStatusTo() {
        if (orderStatusTo == null) {
            orderStatusTo = new OrderStatusFilter();
        }
        return orderStatusTo;
    }

    public void setOrderStatusTo(OrderStatusFilter orderStatusTo) {
        this.orderStatusTo = orderStatusTo;
    }

    public StringFilter getNotes() {
        return notes;
    }

    public StringFilter notes() {
        if (notes == null) {
            notes = new StringFilter();
        }
        return notes;
    }

    public void setNotes(StringFilter notes) {
        this.notes = notes;
    }

    public LongFilter getOrderId() {
        return orderId;
    }

    public LongFilter orderId() {
        if (orderId == null) {
            orderId = new LongFilter();
        }
        return orderId;
    }

    public void setOrderId(LongFilter orderId) {
        this.orderId = orderId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrderHistoryCriteria that = (OrderHistoryCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(orderStatusFrom, that.orderStatusFrom) &&
                Objects.equals(orderStatusTo, that.orderStatusTo) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderStatusFrom, orderStatusTo, notes, orderId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderHistoryCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (orderStatusFrom != null ? "orderStatusFrom=" + orderStatusFrom + ", " : "") +
            (orderStatusTo != null ? "orderStatusTo=" + orderStatusTo + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (orderId != null ? "orderId=" + orderId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
