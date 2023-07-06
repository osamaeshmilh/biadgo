package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import ly.biadjo.food.domain.enumeration.DeliveryPriceType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Restaurant.
 */
@Entity
@Table(name = "restaurant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Restaurant extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_ar")
    private String nameAr;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "plus_code")
    private String plusCode;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "estimited_time_mins")
    private Integer estimitedTimeMins;

    @Column(name = "delivery_fee")
    private Double deliveryFee;

    @Column(name = "delivery_range")
    private Double deliveryRange;

    @Column(name = "available_for_delivery")
    private Boolean availableForDelivery;

    @Column(name = "available_for_pickup")
    private Boolean availableForPickup;

    @Column(name = "minimum_order_total_for_delivery")
    private Double minimumOrderTotalForDelivery;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_price_type")
    private DeliveryPriceType deliveryPriceType;

    @Column(name = "is_open")
    private Boolean isOpen;

    @Column(name = "is_featured")
    private Boolean isFeatured;

    @Column(name = "is_listed_in_offers")
    private Boolean isListedInOffers;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "offer_banner")
    private String offerBanner;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "view_counter")
    private Integer viewCounter;

    @Column(name = "scheduled_closing_time")
    private String scheduledClosingTime;

    @Column(name = "facebook_page_url")
    private String facebookPageUrl;

    @Column(name = "notes")
    private String notes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cuisine cuisine;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_restaurant__categories",
        joinColumns = @JoinColumn(name = "restaurant_id"),
        inverseJoinColumns = @JoinColumn(name = "categories_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"restaurants"}, allowSetters = true)
    private Set<Category> categories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Restaurant id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Restaurant name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAr() {
        return this.nameAr;
    }

    public Restaurant nameAr(String nameAr) {
        this.setNameAr(nameAr);
        return this;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public Restaurant nameEn(String nameEn) {
        this.setNameEn(nameEn);
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getDescription() {
        return this.description;
    }

    public Restaurant description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return this.address;
    }

    public Restaurant address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Restaurant latitude(Double latitude) {
        this.setLatitude(latitude);
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public Restaurant longitude(Double longitude) {
        this.setLongitude(longitude);
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPlusCode() {
        return this.plusCode;
    }

    public Restaurant plusCode(String plusCode) {
        this.setPlusCode(plusCode);
        return this;
    }

    public void setPlusCode(String plusCode) {
        this.plusCode = plusCode;
    }

    public String getMobile() {
        return this.mobile;
    }

    public Restaurant mobile(String mobile) {
        this.setMobile(mobile);
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getEstimitedTimeMins() {
        return this.estimitedTimeMins;
    }

    public Restaurant estimitedTimeMins(Integer estimitedTimeMins) {
        this.setEstimitedTimeMins(estimitedTimeMins);
        return this;
    }

    public void setEstimitedTimeMins(Integer estimitedTimeMins) {
        this.estimitedTimeMins = estimitedTimeMins;
    }

    public Double getDeliveryFee() {
        return this.deliveryFee;
    }

    public Restaurant deliveryFee(Double deliveryFee) {
        this.setDeliveryFee(deliveryFee);
        return this;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getDeliveryRange() {
        return this.deliveryRange;
    }

    public Restaurant deliveryRange(Double deliveryRange) {
        this.setDeliveryRange(deliveryRange);
        return this;
    }

    public void setDeliveryRange(Double deliveryRange) {
        this.deliveryRange = deliveryRange;
    }

    public Boolean getAvailableForDelivery() {
        return this.availableForDelivery;
    }

    public Restaurant availableForDelivery(Boolean availableForDelivery) {
        this.setAvailableForDelivery(availableForDelivery);
        return this;
    }

    public void setAvailableForDelivery(Boolean availableForDelivery) {
        this.availableForDelivery = availableForDelivery;
    }

    public Boolean getAvailableForPickup() {
        return this.availableForPickup;
    }

    public Restaurant availableForPickup(Boolean availableForPickup) {
        this.setAvailableForPickup(availableForPickup);
        return this;
    }

    public void setAvailableForPickup(Boolean availableForPickup) {
        this.availableForPickup = availableForPickup;
    }

    public Double getMinimumOrderTotalForDelivery() {
        return this.minimumOrderTotalForDelivery;
    }

    public Restaurant minimumOrderTotalForDelivery(Double minimumOrderTotalForDelivery) {
        this.setMinimumOrderTotalForDelivery(minimumOrderTotalForDelivery);
        return this;
    }

    public void setMinimumOrderTotalForDelivery(Double minimumOrderTotalForDelivery) {
        this.minimumOrderTotalForDelivery = minimumOrderTotalForDelivery;
    }

    public DeliveryPriceType getDeliveryPriceType() {
        return this.deliveryPriceType;
    }

    public Restaurant deliveryPriceType(DeliveryPriceType deliveryPriceType) {
        this.setDeliveryPriceType(deliveryPriceType);
        return this;
    }

    public void setDeliveryPriceType(DeliveryPriceType deliveryPriceType) {
        this.deliveryPriceType = deliveryPriceType;
    }

    public Boolean getIsOpen() {
        return this.isOpen;
    }

    public Restaurant isOpen(Boolean isOpen) {
        this.setIsOpen(isOpen);
        return this;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Boolean getIsFeatured() {
        return this.isFeatured;
    }

    public Restaurant isFeatured(Boolean isFeatured) {
        this.setIsFeatured(isFeatured);
        return this;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Boolean getIsListedInOffers() {
        return this.isListedInOffers;
    }

    public Restaurant isListedInOffers(Boolean isListedInOffers) {
        this.setIsListedInOffers(isListedInOffers);
        return this;
    }

    public void setIsListedInOffers(Boolean isListedInOffers) {
        this.isListedInOffers = isListedInOffers;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Restaurant isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getOfferBanner() {
        return this.offerBanner;
    }

    public Restaurant offerBanner(String offerBanner) {
        this.setOfferBanner(offerBanner);
        return this;
    }

    public void setOfferBanner(String offerBanner) {
        this.offerBanner = offerBanner;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public Restaurant priority(Integer priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getViewCounter() {
        return this.viewCounter;
    }

    public Restaurant viewCounter(Integer viewCounter) {
        this.setViewCounter(viewCounter);
        return this;
    }

    public void setViewCounter(Integer viewCounter) {
        this.viewCounter = viewCounter;
    }

    public String getScheduledClosingTime() {
        return this.scheduledClosingTime;
    }

    public Restaurant scheduledClosingTime(String scheduledClosingTime) {
        this.setScheduledClosingTime(scheduledClosingTime);
        return this;
    }

    public void setScheduledClosingTime(String scheduledClosingTime) {
        this.scheduledClosingTime = scheduledClosingTime;
    }

    public String getFacebookPageUrl() {
        return this.facebookPageUrl;
    }

    public Restaurant facebookPageUrl(String facebookPageUrl) {
        this.setFacebookPageUrl(facebookPageUrl);
        return this;
    }

    public void setFacebookPageUrl(String facebookPageUrl) {
        this.facebookPageUrl = facebookPageUrl;
    }

    public String getNotes() {
        return this.notes;
    }

    public Restaurant notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant user(User user) {
        this.setUser(user);
        return this;
    }

    public Cuisine getCuisine() {
        return this.cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public Restaurant cuisine(Cuisine cuisine) {
        this.setCuisine(cuisine);
        return this;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Restaurant categories(Set<Category> categories) {
        this.setCategories(categories);
        return this;
    }

    public Restaurant addCategories(Category category) {
        this.categories.add(category);
        category.getRestaurants().add(this);
        return this;
    }

    public Restaurant removeCategories(Category category) {
        this.categories.remove(category);
        category.getRestaurants().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Restaurant)) {
            return false;
        }
        return id != null && id.equals(((Restaurant) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Restaurant{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", description='" + getDescription() + "'" +
            ", address='" + getAddress() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", plusCode='" + getPlusCode() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", estimitedTimeMins=" + getEstimitedTimeMins() +
            ", deliveryFee=" + getDeliveryFee() +
            ", deliveryRange=" + getDeliveryRange() +
            ", availableForDelivery='" + getAvailableForDelivery() + "'" +
            ", availableForPickup='" + getAvailableForPickup() + "'" +
            ", minimumOrderTotalForDelivery=" + getMinimumOrderTotalForDelivery() +
            ", deliveryPriceType='" + getDeliveryPriceType() + "'" +
            ", isOpen='" + getIsOpen() + "'" +
            ", isFeatured='" + getIsFeatured() + "'" +
            ", isListedInOffers='" + getIsListedInOffers() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", offerBanner='" + getOfferBanner() + "'" +
            ", priority=" + getPriority() +
            ", viewCounter=" + getViewCounter() +
            ", scheduledClosingTime='" + getScheduledClosingTime() + "'" +
            ", facebookPageUrl='" + getFacebookPageUrl() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
