package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import ly.biadjo.food.domain.FoodIngredient;
import ly.biadjo.food.domain.FoodOrder;
import ly.biadjo.food.domain.enumeration.OrderStatus;
import ly.biadjo.food.domain.enumeration.OrderType;
import ly.biadjo.food.domain.enumeration.PaymentType;

/**
 * A DTO for the {@link ly.biadjo.food.domain.Order} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private String orderNo;

    private Double companyAmount;

    private Double restaurantAmount;

    private Double deliveryFee;

    private Double itemsPrice;

    private Double discount;

    private Double total;

    private Double latitude;

    private Double longitude;

    private String plusCode;

    private PaymentType paymentType;

    private Boolean isApprovedByDriver;

    private Boolean isApprovedByRestaurant;

    private Boolean isPayed;

    private Double amountRecived;

    private Double amountRemaining;

    private String customerNotes;

    private String restaurantNotes;

    private String driverNotes;

    private OrderStatus orderStatus;

    private OrderType orderType;

    private Instant deliveredAt;

    private String notes;

    private CustomerDTO customer;

    private CouponDTO coupon;

    private DriverDTO driver;

    private DeliveryAddressDTO deliveryAddress;

    private RestaurantDTO restaurant;

    private Set<FoodOrder> foodOrders = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getCompanyAmount() {
        return companyAmount;
    }

    public void setCompanyAmount(Double companyAmount) {
        this.companyAmount = companyAmount;
    }

    public Double getRestaurantAmount() {
        return restaurantAmount;
    }

    public void setRestaurantAmount(Double restaurantAmount) {
        this.restaurantAmount = restaurantAmount;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getItemsPrice() {
        return itemsPrice;
    }

    public void setItemsPrice(Double itemsPrice) {
        this.itemsPrice = itemsPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Boolean getIsApprovedByDriver() {
        return isApprovedByDriver;
    }

    public void setIsApprovedByDriver(Boolean isApprovedByDriver) {
        this.isApprovedByDriver = isApprovedByDriver;
    }

    public Boolean getIsApprovedByRestaurant() {
        return isApprovedByRestaurant;
    }

    public void setIsApprovedByRestaurant(Boolean isApprovedByRestaurant) {
        this.isApprovedByRestaurant = isApprovedByRestaurant;
    }

    public Boolean getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Boolean isPayed) {
        this.isPayed = isPayed;
    }

    public Double getAmountRecived() {
        return amountRecived;
    }

    public void setAmountRecived(Double amountRecived) {
        this.amountRecived = amountRecived;
    }

    public Double getAmountRemaining() {
        return amountRemaining;
    }

    public void setAmountRemaining(Double amountRemaining) {
        this.amountRemaining = amountRemaining;
    }

    public String getCustomerNotes() {
        return customerNotes;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    public String getRestaurantNotes() {
        return restaurantNotes;
    }

    public void setRestaurantNotes(String restaurantNotes) {
        this.restaurantNotes = restaurantNotes;
    }

    public String getDriverNotes() {
        return driverNotes;
    }

    public void setDriverNotes(String driverNotes) {
        this.driverNotes = driverNotes;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Instant getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Instant deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public CouponDTO getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponDTO coupon) {
        this.coupon = coupon;
    }

    public DriverDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverDTO driver) {
        this.driver = driver;
    }

    public DeliveryAddressDTO getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddressDTO deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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
        if (!(o instanceof OrderDTO)) {
            return false;
        }

        OrderDTO orderDTO = (OrderDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderDTO{" +
            "id=" + getId() +
            ", orderNo='" + getOrderNo() + "'" +
            ", companyAmount=" + getCompanyAmount() +
            ", restaurantAmount=" + getRestaurantAmount() +
            ", deliveryFee=" + getDeliveryFee() +
            ", itemsPrice=" + getItemsPrice() +
            ", discount=" + getDiscount() +
            ", total=" + getTotal() +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", plusCode='" + getPlusCode() + "'" +
            ", paymentType='" + getPaymentType() + "'" +
            ", isApprovedByDriver='" + getIsApprovedByDriver() + "'" +
            ", isApprovedByRestaurant='" + getIsApprovedByRestaurant() + "'" +
            ", isPayed='" + getIsPayed() + "'" +
            ", amountRecived=" + getAmountRecived() +
            ", amountRemaining=" + getAmountRemaining() +
            ", customerNotes='" + getCustomerNotes() + "'" +
            ", restaurantNotes='" + getRestaurantNotes() + "'" +
            ", driverNotes='" + getDriverNotes() + "'" +
            ", orderStatus='" + getOrderStatus() + "'" +
            ", orderType='" + getOrderType() + "'" +
            ", deliveredAt='" + getDeliveredAt() + "'" +
            ", notes='" + getNotes() + "'" +
            ", customer=" + getCustomer() +
            ", coupon=" + getCoupon() +
            ", driver=" + getDriver() +
            ", deliveryAddress=" + getDeliveryAddress() +
            ", restaurant=" + getRestaurant() +
            "}";
    }

    public Set<FoodOrder> getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(Set<FoodOrder> foodOrders) {
        this.foodOrders = foodOrders;
    }
}
