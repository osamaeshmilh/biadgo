package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import ly.biadjo.food.domain.enumeration.OrderStatus;
import ly.biadjo.food.domain.enumeration.OrderType;
import ly.biadjo.food.domain.enumeration.PaymentType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Order.
 */
@Entity
@Table(name = "jhi_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "company_amount")
    private Double companyAmount;

    @Column(name = "restaurant_amount")
    private Double restaurantAmount;

    @Column(name = "delivery_fee")
    private Double deliveryFee;

    @Column(name = "items_price")
    private Double itemsPrice;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "total")
    private Double total;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "plus_code")
    private String plusCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "is_approved_by_driver")
    private Boolean isApprovedByDriver;

    @Column(name = "is_approved_by_restaurant")
    private Boolean isApprovedByRestaurant;

    @Column(name = "is_payed")
    private Boolean isPayed;

    @Column(name = "amount_recived")
    private Double amountRecived;

    @Column(name = "amount_remaining")
    private Double amountRemaining;

    @Column(name = "customer_notes")
    private String customerNotes;

    @Column(name = "restaurant_notes")
    private String restaurantNotes;

    @Column(name = "driver_notes")
    private String driverNotes;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type")
    private OrderType orderType;

    @Column(name = "delivered_at")
    private Instant deliveredAt;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user", "zone"}, allowSetters = true)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"customer", "zone"}, allowSetters = true)
    private DeliveryAddress deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user", "cuisine", "categories"}, allowSetters = true)
    private Restaurant restaurant;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Order id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public Order orderNo(String orderNo) {
        this.setOrderNo(orderNo);
        return this;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getCompanyAmount() {
        return this.companyAmount;
    }

    public Order companyAmount(Double companyAmount) {
        this.setCompanyAmount(companyAmount);
        return this;
    }

    public void setCompanyAmount(Double companyAmount) {
        this.companyAmount = companyAmount;
    }

    public Double getRestaurantAmount() {
        return this.restaurantAmount;
    }

    public Order restaurantAmount(Double restaurantAmount) {
        this.setRestaurantAmount(restaurantAmount);
        return this;
    }

    public void setRestaurantAmount(Double restaurantAmount) {
        this.restaurantAmount = restaurantAmount;
    }

    public Double getDeliveryFee() {
        return this.deliveryFee;
    }

    public Order deliveryFee(Double deliveryFee) {
        this.setDeliveryFee(deliveryFee);
        return this;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getItemsPrice() {
        return this.itemsPrice;
    }

    public Order itemsPrice(Double itemsPrice) {
        this.setItemsPrice(itemsPrice);
        return this;
    }

    public void setItemsPrice(Double itemsPrice) {
        this.itemsPrice = itemsPrice;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public Order discount(Double discount) {
        this.setDiscount(discount);
        return this;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return this.total;
    }

    public Order total(Double total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Order latitude(Double latitude) {
        this.setLatitude(latitude);
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public Order longitude(Double longitude) {
        this.setLongitude(longitude);
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPlusCode() {
        return this.plusCode;
    }

    public Order plusCode(String plusCode) {
        this.setPlusCode(plusCode);
        return this;
    }

    public void setPlusCode(String plusCode) {
        this.plusCode = plusCode;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public Order paymentType(PaymentType paymentType) {
        this.setPaymentType(paymentType);
        return this;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Boolean getIsApprovedByDriver() {
        return this.isApprovedByDriver;
    }

    public Order isApprovedByDriver(Boolean isApprovedByDriver) {
        this.setIsApprovedByDriver(isApprovedByDriver);
        return this;
    }

    public void setIsApprovedByDriver(Boolean isApprovedByDriver) {
        this.isApprovedByDriver = isApprovedByDriver;
    }

    public Boolean getIsApprovedByRestaurant() {
        return this.isApprovedByRestaurant;
    }

    public Order isApprovedByRestaurant(Boolean isApprovedByRestaurant) {
        this.setIsApprovedByRestaurant(isApprovedByRestaurant);
        return this;
    }

    public void setIsApprovedByRestaurant(Boolean isApprovedByRestaurant) {
        this.isApprovedByRestaurant = isApprovedByRestaurant;
    }

    public Boolean getIsPayed() {
        return this.isPayed;
    }

    public Order isPayed(Boolean isPayed) {
        this.setIsPayed(isPayed);
        return this;
    }

    public void setIsPayed(Boolean isPayed) {
        this.isPayed = isPayed;
    }

    public Double getAmountRecived() {
        return this.amountRecived;
    }

    public Order amountRecived(Double amountRecived) {
        this.setAmountRecived(amountRecived);
        return this;
    }

    public void setAmountRecived(Double amountRecived) {
        this.amountRecived = amountRecived;
    }

    public Double getAmountRemaining() {
        return this.amountRemaining;
    }

    public Order amountRemaining(Double amountRemaining) {
        this.setAmountRemaining(amountRemaining);
        return this;
    }

    public void setAmountRemaining(Double amountRemaining) {
        this.amountRemaining = amountRemaining;
    }

    public String getCustomerNotes() {
        return this.customerNotes;
    }

    public Order customerNotes(String customerNotes) {
        this.setCustomerNotes(customerNotes);
        return this;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    public String getRestaurantNotes() {
        return this.restaurantNotes;
    }

    public Order restaurantNotes(String restaurantNotes) {
        this.setRestaurantNotes(restaurantNotes);
        return this;
    }

    public void setRestaurantNotes(String restaurantNotes) {
        this.restaurantNotes = restaurantNotes;
    }

    public String getDriverNotes() {
        return this.driverNotes;
    }

    public Order driverNotes(String driverNotes) {
        this.setDriverNotes(driverNotes);
        return this;
    }

    public void setDriverNotes(String driverNotes) {
        this.driverNotes = driverNotes;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public Order orderStatus(OrderStatus orderStatus) {
        this.setOrderStatus(orderStatus);
        return this;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderType getOrderType() {
        return this.orderType;
    }

    public Order orderType(OrderType orderType) {
        this.setOrderType(orderType);
        return this;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Instant getDeliveredAt() {
        return this.deliveredAt;
    }

    public Order deliveredAt(Instant deliveredAt) {
        this.setDeliveredAt(deliveredAt);
        return this;
    }

    public void setDeliveredAt(Instant deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public String getNotes() {
        return this.notes;
    }

    public Order notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    public Coupon getCoupon() {
        return this.coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Order coupon(Coupon coupon) {
        this.setCoupon(coupon);
        return this;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Order driver(Driver driver) {
        this.setDriver(driver);
        return this;
    }

    public DeliveryAddress getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Order deliveryAddress(DeliveryAddress deliveryAddress) {
        this.setDeliveryAddress(deliveryAddress);
        return this;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Order restaurant(Restaurant restaurant) {
        this.setRestaurant(restaurant);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Order{" +
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
            "}";
    }
}
