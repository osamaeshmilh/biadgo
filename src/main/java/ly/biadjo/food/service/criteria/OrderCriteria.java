package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.OrderStatus;
import ly.biadjo.food.domain.enumeration.OrderType;
import ly.biadjo.food.domain.enumeration.PaymentType;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.Order} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.OrderResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /orders?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderCriteria implements Serializable, Criteria {

    /**
     * Class for filtering PaymentType
     */
    public static class PaymentTypeFilter extends Filter<PaymentType> {

        public PaymentTypeFilter() {
        }

        public PaymentTypeFilter(PaymentTypeFilter filter) {
            super(filter);
        }

        @Override
        public PaymentTypeFilter copy() {
            return new PaymentTypeFilter(this);
        }
    }

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

    /**
     * Class for filtering OrderType
     */
    public static class OrderTypeFilter extends Filter<OrderType> {

        public OrderTypeFilter() {
        }

        public OrderTypeFilter(OrderTypeFilter filter) {
            super(filter);
        }

        @Override
        public OrderTypeFilter copy() {
            return new OrderTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter orderNo;

    private DoubleFilter companyAmount;

    private DoubleFilter restaurantAmount;

    private DoubleFilter deliveryFee;

    private DoubleFilter itemsPrice;

    private DoubleFilter discount;

    private DoubleFilter total;

    private DoubleFilter latitude;

    private DoubleFilter longitude;

    private StringFilter plusCode;

    private PaymentTypeFilter paymentType;

    private BooleanFilter isApprovedByDriver;

    private BooleanFilter isApprovedByRestaurant;

    private BooleanFilter isPayed;

    private DoubleFilter amountRecived;

    private DoubleFilter amountRemaining;

    private StringFilter customerNotes;

    private StringFilter restaurantNotes;

    private StringFilter driverNotes;

    private OrderStatusFilter orderStatus;

    private OrderTypeFilter orderType;

    private InstantFilter deliveredAt;

    private StringFilter notes;

    private LongFilter customerId;

    private LongFilter couponId;

    private LongFilter driverId;

    private LongFilter deliveryAddressId;

    private LongFilter restaurantId;

    private Boolean distinct;

    public OrderCriteria() {
    }

    public OrderCriteria(OrderCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.orderNo = other.orderNo == null ? null : other.orderNo.copy();
        this.companyAmount = other.companyAmount == null ? null : other.companyAmount.copy();
        this.restaurantAmount = other.restaurantAmount == null ? null : other.restaurantAmount.copy();
        this.deliveryFee = other.deliveryFee == null ? null : other.deliveryFee.copy();
        this.itemsPrice = other.itemsPrice == null ? null : other.itemsPrice.copy();
        this.discount = other.discount == null ? null : other.discount.copy();
        this.total = other.total == null ? null : other.total.copy();
        this.latitude = other.latitude == null ? null : other.latitude.copy();
        this.longitude = other.longitude == null ? null : other.longitude.copy();
        this.plusCode = other.plusCode == null ? null : other.plusCode.copy();
        this.paymentType = other.paymentType == null ? null : other.paymentType.copy();
        this.isApprovedByDriver = other.isApprovedByDriver == null ? null : other.isApprovedByDriver.copy();
        this.isApprovedByRestaurant = other.isApprovedByRestaurant == null ? null : other.isApprovedByRestaurant.copy();
        this.isPayed = other.isPayed == null ? null : other.isPayed.copy();
        this.amountRecived = other.amountRecived == null ? null : other.amountRecived.copy();
        this.amountRemaining = other.amountRemaining == null ? null : other.amountRemaining.copy();
        this.customerNotes = other.customerNotes == null ? null : other.customerNotes.copy();
        this.restaurantNotes = other.restaurantNotes == null ? null : other.restaurantNotes.copy();
        this.driverNotes = other.driverNotes == null ? null : other.driverNotes.copy();
        this.orderStatus = other.orderStatus == null ? null : other.orderStatus.copy();
        this.orderType = other.orderType == null ? null : other.orderType.copy();
        this.deliveredAt = other.deliveredAt == null ? null : other.deliveredAt.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.customerId = other.customerId == null ? null : other.customerId.copy();
        this.couponId = other.couponId == null ? null : other.couponId.copy();
        this.driverId = other.driverId == null ? null : other.driverId.copy();
        this.deliveryAddressId = other.deliveryAddressId == null ? null : other.deliveryAddressId.copy();
        this.restaurantId = other.restaurantId == null ? null : other.restaurantId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public OrderCriteria copy() {
        return new OrderCriteria(this);
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

    public StringFilter getOrderNo() {
        return orderNo;
    }

    public StringFilter orderNo() {
        if (orderNo == null) {
            orderNo = new StringFilter();
        }
        return orderNo;
    }

    public void setOrderNo(StringFilter orderNo) {
        this.orderNo = orderNo;
    }

    public DoubleFilter getCompanyAmount() {
        return companyAmount;
    }

    public DoubleFilter companyAmount() {
        if (companyAmount == null) {
            companyAmount = new DoubleFilter();
        }
        return companyAmount;
    }

    public void setCompanyAmount(DoubleFilter companyAmount) {
        this.companyAmount = companyAmount;
    }

    public DoubleFilter getRestaurantAmount() {
        return restaurantAmount;
    }

    public DoubleFilter restaurantAmount() {
        if (restaurantAmount == null) {
            restaurantAmount = new DoubleFilter();
        }
        return restaurantAmount;
    }

    public void setRestaurantAmount(DoubleFilter restaurantAmount) {
        this.restaurantAmount = restaurantAmount;
    }

    public DoubleFilter getDeliveryFee() {
        return deliveryFee;
    }

    public DoubleFilter deliveryFee() {
        if (deliveryFee == null) {
            deliveryFee = new DoubleFilter();
        }
        return deliveryFee;
    }

    public void setDeliveryFee(DoubleFilter deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public DoubleFilter getItemsPrice() {
        return itemsPrice;
    }

    public DoubleFilter itemsPrice() {
        if (itemsPrice == null) {
            itemsPrice = new DoubleFilter();
        }
        return itemsPrice;
    }

    public void setItemsPrice(DoubleFilter itemsPrice) {
        this.itemsPrice = itemsPrice;
    }

    public DoubleFilter getDiscount() {
        return discount;
    }

    public DoubleFilter discount() {
        if (discount == null) {
            discount = new DoubleFilter();
        }
        return discount;
    }

    public void setDiscount(DoubleFilter discount) {
        this.discount = discount;
    }

    public DoubleFilter getTotal() {
        return total;
    }

    public DoubleFilter total() {
        if (total == null) {
            total = new DoubleFilter();
        }
        return total;
    }

    public void setTotal(DoubleFilter total) {
        this.total = total;
    }

    public DoubleFilter getLatitude() {
        return latitude;
    }

    public DoubleFilter latitude() {
        if (latitude == null) {
            latitude = new DoubleFilter();
        }
        return latitude;
    }

    public void setLatitude(DoubleFilter latitude) {
        this.latitude = latitude;
    }

    public DoubleFilter getLongitude() {
        return longitude;
    }

    public DoubleFilter longitude() {
        if (longitude == null) {
            longitude = new DoubleFilter();
        }
        return longitude;
    }

    public void setLongitude(DoubleFilter longitude) {
        this.longitude = longitude;
    }

    public StringFilter getPlusCode() {
        return plusCode;
    }

    public StringFilter plusCode() {
        if (plusCode == null) {
            plusCode = new StringFilter();
        }
        return plusCode;
    }

    public void setPlusCode(StringFilter plusCode) {
        this.plusCode = plusCode;
    }

    public PaymentTypeFilter getPaymentType() {
        return paymentType;
    }

    public PaymentTypeFilter paymentType() {
        if (paymentType == null) {
            paymentType = new PaymentTypeFilter();
        }
        return paymentType;
    }

    public void setPaymentType(PaymentTypeFilter paymentType) {
        this.paymentType = paymentType;
    }

    public BooleanFilter getIsApprovedByDriver() {
        return isApprovedByDriver;
    }

    public BooleanFilter isApprovedByDriver() {
        if (isApprovedByDriver == null) {
            isApprovedByDriver = new BooleanFilter();
        }
        return isApprovedByDriver;
    }

    public void setIsApprovedByDriver(BooleanFilter isApprovedByDriver) {
        this.isApprovedByDriver = isApprovedByDriver;
    }

    public BooleanFilter getIsApprovedByRestaurant() {
        return isApprovedByRestaurant;
    }

    public BooleanFilter isApprovedByRestaurant() {
        if (isApprovedByRestaurant == null) {
            isApprovedByRestaurant = new BooleanFilter();
        }
        return isApprovedByRestaurant;
    }

    public void setIsApprovedByRestaurant(BooleanFilter isApprovedByRestaurant) {
        this.isApprovedByRestaurant = isApprovedByRestaurant;
    }

    public BooleanFilter getIsPayed() {
        return isPayed;
    }

    public BooleanFilter isPayed() {
        if (isPayed == null) {
            isPayed = new BooleanFilter();
        }
        return isPayed;
    }

    public void setIsPayed(BooleanFilter isPayed) {
        this.isPayed = isPayed;
    }

    public DoubleFilter getAmountRecived() {
        return amountRecived;
    }

    public DoubleFilter amountRecived() {
        if (amountRecived == null) {
            amountRecived = new DoubleFilter();
        }
        return amountRecived;
    }

    public void setAmountRecived(DoubleFilter amountRecived) {
        this.amountRecived = amountRecived;
    }

    public DoubleFilter getAmountRemaining() {
        return amountRemaining;
    }

    public DoubleFilter amountRemaining() {
        if (amountRemaining == null) {
            amountRemaining = new DoubleFilter();
        }
        return amountRemaining;
    }

    public void setAmountRemaining(DoubleFilter amountRemaining) {
        this.amountRemaining = amountRemaining;
    }

    public StringFilter getCustomerNotes() {
        return customerNotes;
    }

    public StringFilter customerNotes() {
        if (customerNotes == null) {
            customerNotes = new StringFilter();
        }
        return customerNotes;
    }

    public void setCustomerNotes(StringFilter customerNotes) {
        this.customerNotes = customerNotes;
    }

    public StringFilter getRestaurantNotes() {
        return restaurantNotes;
    }

    public StringFilter restaurantNotes() {
        if (restaurantNotes == null) {
            restaurantNotes = new StringFilter();
        }
        return restaurantNotes;
    }

    public void setRestaurantNotes(StringFilter restaurantNotes) {
        this.restaurantNotes = restaurantNotes;
    }

    public StringFilter getDriverNotes() {
        return driverNotes;
    }

    public StringFilter driverNotes() {
        if (driverNotes == null) {
            driverNotes = new StringFilter();
        }
        return driverNotes;
    }

    public void setDriverNotes(StringFilter driverNotes) {
        this.driverNotes = driverNotes;
    }

    public OrderStatusFilter getOrderStatus() {
        return orderStatus;
    }

    public OrderStatusFilter orderStatus() {
        if (orderStatus == null) {
            orderStatus = new OrderStatusFilter();
        }
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusFilter orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderTypeFilter getOrderType() {
        return orderType;
    }

    public OrderTypeFilter orderType() {
        if (orderType == null) {
            orderType = new OrderTypeFilter();
        }
        return orderType;
    }

    public void setOrderType(OrderTypeFilter orderType) {
        this.orderType = orderType;
    }

    public InstantFilter getDeliveredAt() {
        return deliveredAt;
    }

    public InstantFilter deliveredAt() {
        if (deliveredAt == null) {
            deliveredAt = new InstantFilter();
        }
        return deliveredAt;
    }

    public void setDeliveredAt(InstantFilter deliveredAt) {
        this.deliveredAt = deliveredAt;
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

    public LongFilter getCustomerId() {
        return customerId;
    }

    public LongFilter customerId() {
        if (customerId == null) {
            customerId = new LongFilter();
        }
        return customerId;
    }

    public void setCustomerId(LongFilter customerId) {
        this.customerId = customerId;
    }

    public LongFilter getCouponId() {
        return couponId;
    }

    public LongFilter couponId() {
        if (couponId == null) {
            couponId = new LongFilter();
        }
        return couponId;
    }

    public void setCouponId(LongFilter couponId) {
        this.couponId = couponId;
    }

    public LongFilter getDriverId() {
        return driverId;
    }

    public LongFilter driverId() {
        if (driverId == null) {
            driverId = new LongFilter();
        }
        return driverId;
    }

    public void setDriverId(LongFilter driverId) {
        this.driverId = driverId;
    }

    public LongFilter getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public LongFilter deliveryAddressId() {
        if (deliveryAddressId == null) {
            deliveryAddressId = new LongFilter();
        }
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(LongFilter deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public LongFilter getRestaurantId() {
        return restaurantId;
    }

    public LongFilter restaurantId() {
        if (restaurantId == null) {
            restaurantId = new LongFilter();
        }
        return restaurantId;
    }

    public void setRestaurantId(LongFilter restaurantId) {
        this.restaurantId = restaurantId;
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
        final OrderCriteria that = (OrderCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(orderNo, that.orderNo) &&
                Objects.equals(companyAmount, that.companyAmount) &&
                Objects.equals(restaurantAmount, that.restaurantAmount) &&
                Objects.equals(deliveryFee, that.deliveryFee) &&
                Objects.equals(itemsPrice, that.itemsPrice) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(total, that.total) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(plusCode, that.plusCode) &&
                Objects.equals(paymentType, that.paymentType) &&
                Objects.equals(isApprovedByDriver, that.isApprovedByDriver) &&
                Objects.equals(isApprovedByRestaurant, that.isApprovedByRestaurant) &&
                Objects.equals(isPayed, that.isPayed) &&
                Objects.equals(amountRecived, that.amountRecived) &&
                Objects.equals(amountRemaining, that.amountRemaining) &&
                Objects.equals(customerNotes, that.customerNotes) &&
                Objects.equals(restaurantNotes, that.restaurantNotes) &&
                Objects.equals(driverNotes, that.driverNotes) &&
                Objects.equals(orderStatus, that.orderStatus) &&
                Objects.equals(orderType, that.orderType) &&
                Objects.equals(deliveredAt, that.deliveredAt) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(couponId, that.couponId) &&
                Objects.equals(driverId, that.driverId) &&
                Objects.equals(deliveryAddressId, that.deliveryAddressId) &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            orderNo,
            companyAmount,
            restaurantAmount,
            deliveryFee,
            itemsPrice,
            discount,
            total,
            latitude,
            longitude,
            plusCode,
            paymentType,
            isApprovedByDriver,
            isApprovedByRestaurant,
            isPayed,
            amountRecived,
            amountRemaining,
            customerNotes,
            restaurantNotes,
            driverNotes,
            orderStatus,
            orderType,
            deliveredAt,
            notes,
            customerId,
            couponId,
            driverId,
            deliveryAddressId,
            restaurantId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (orderNo != null ? "orderNo=" + orderNo + ", " : "") +
            (companyAmount != null ? "companyAmount=" + companyAmount + ", " : "") +
            (restaurantAmount != null ? "restaurantAmount=" + restaurantAmount + ", " : "") +
            (deliveryFee != null ? "deliveryFee=" + deliveryFee + ", " : "") +
            (itemsPrice != null ? "itemsPrice=" + itemsPrice + ", " : "") +
            (discount != null ? "discount=" + discount + ", " : "") +
            (total != null ? "total=" + total + ", " : "") +
            (latitude != null ? "latitude=" + latitude + ", " : "") +
            (longitude != null ? "longitude=" + longitude + ", " : "") +
            (plusCode != null ? "plusCode=" + plusCode + ", " : "") +
            (paymentType != null ? "paymentType=" + paymentType + ", " : "") +
            (isApprovedByDriver != null ? "isApprovedByDriver=" + isApprovedByDriver + ", " : "") +
            (isApprovedByRestaurant != null ? "isApprovedByRestaurant=" + isApprovedByRestaurant + ", " : "") +
            (isPayed != null ? "isPayed=" + isPayed + ", " : "") +
            (amountRecived != null ? "amountRecived=" + amountRecived + ", " : "") +
            (amountRemaining != null ? "amountRemaining=" + amountRemaining + ", " : "") +
            (customerNotes != null ? "customerNotes=" + customerNotes + ", " : "") +
            (restaurantNotes != null ? "restaurantNotes=" + restaurantNotes + ", " : "") +
            (driverNotes != null ? "driverNotes=" + driverNotes + ", " : "") +
            (orderStatus != null ? "orderStatus=" + orderStatus + ", " : "") +
            (orderType != null ? "orderType=" + orderType + ", " : "") +
            (deliveredAt != null ? "deliveredAt=" + deliveredAt + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (customerId != null ? "customerId=" + customerId + ", " : "") +
            (couponId != null ? "couponId=" + couponId + ", " : "") +
            (driverId != null ? "driverId=" + driverId + ", " : "") +
            (deliveryAddressId != null ? "deliveryAddressId=" + deliveryAddressId + ", " : "") +
            (restaurantId != null ? "restaurantId=" + restaurantId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
