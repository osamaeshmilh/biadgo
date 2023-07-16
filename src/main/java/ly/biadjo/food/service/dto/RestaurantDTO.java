package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import ly.biadjo.food.domain.*;
import ly.biadjo.food.domain.enumeration.DeliveryPriceType;

/**
 * A DTO for the {@link ly.biadjo.food.domain.Restaurant} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestaurantDTO implements Serializable {

    private Long id;

    private String name;

    private String nameAr;

    private String nameEn;

    private String description;

    private String address;

    private Double latitude;

    private Double longitude;

    private String plusCode;

    private String mobile;

    private Integer estimitedTimeMins;

    private Double deliveryFee;

    private Double deliveryRange;

    private Boolean availableForDelivery;

    private Boolean availableForPickup;

    private Double minimumOrderTotalForDelivery;

    private DeliveryPriceType deliveryPriceType;

    private Boolean isOpen;

    private Boolean isFeatured;

    private Boolean isListedInOffers;

    private Boolean isActive;

    private String offerBanner;

    private Integer priority;

    private Integer viewCounter;

    private String scheduledClosingTime;

    private String facebookPageUrl;

    private String notes;

    private UserDTO user;

    private CuisineDTO cuisine;

    private Set<CategoryDTO> categories = new HashSet<>();

    private Set<RestaurantImageDTO> restaurantImages = new HashSet<>();

    private Set<RestaurantScheduleDTO> restaurantSchedules = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getEstimitedTimeMins() {
        return estimitedTimeMins;
    }

    public void setEstimitedTimeMins(Integer estimitedTimeMins) {
        this.estimitedTimeMins = estimitedTimeMins;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getDeliveryRange() {
        return deliveryRange;
    }

    public void setDeliveryRange(Double deliveryRange) {
        this.deliveryRange = deliveryRange;
    }

    public Boolean getAvailableForDelivery() {
        return availableForDelivery;
    }

    public void setAvailableForDelivery(Boolean availableForDelivery) {
        this.availableForDelivery = availableForDelivery;
    }

    public Boolean getAvailableForPickup() {
        return availableForPickup;
    }

    public void setAvailableForPickup(Boolean availableForPickup) {
        this.availableForPickup = availableForPickup;
    }

    public Double getMinimumOrderTotalForDelivery() {
        return minimumOrderTotalForDelivery;
    }

    public void setMinimumOrderTotalForDelivery(Double minimumOrderTotalForDelivery) {
        this.minimumOrderTotalForDelivery = minimumOrderTotalForDelivery;
    }

    public DeliveryPriceType getDeliveryPriceType() {
        return deliveryPriceType;
    }

    public void setDeliveryPriceType(DeliveryPriceType deliveryPriceType) {
        this.deliveryPriceType = deliveryPriceType;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Boolean getIsListedInOffers() {
        return isListedInOffers;
    }

    public void setIsListedInOffers(Boolean isListedInOffers) {
        this.isListedInOffers = isListedInOffers;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getOfferBanner() {
        return offerBanner;
    }

    public void setOfferBanner(String offerBanner) {
        this.offerBanner = offerBanner;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getViewCounter() {
        return viewCounter;
    }

    public void setViewCounter(Integer viewCounter) {
        this.viewCounter = viewCounter;
    }

    public String getScheduledClosingTime() {
        return scheduledClosingTime;
    }

    public void setScheduledClosingTime(String scheduledClosingTime) {
        this.scheduledClosingTime = scheduledClosingTime;
    }

    public String getFacebookPageUrl() {
        return facebookPageUrl;
    }

    public void setFacebookPageUrl(String facebookPageUrl) {
        this.facebookPageUrl = facebookPageUrl;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CuisineDTO getCuisine() {
        return cuisine;
    }

    public void setCuisine(CuisineDTO cuisine) {
        this.cuisine = cuisine;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RestaurantDTO)) {
            return false;
        }

        RestaurantDTO restaurantDTO = (RestaurantDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, restaurantDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantDTO{" +
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
            ", user=" + getUser() +
            ", cuisine=" + getCuisine() +
            ", categories=" + getCategories() +
            "}";
    }

    public Set<RestaurantImageDTO> getRestaurantImages() {
        return restaurantImages;
    }

    public void setRestaurantImages(Set<RestaurantImageDTO> restaurantImages) {
        this.restaurantImages = restaurantImages;
    }

    public Set<RestaurantScheduleDTO> getRestaurantSchedules() {
        return restaurantSchedules;
    }

    public void setRestaurantSchedules(Set<RestaurantScheduleDTO> restaurantSchedules) {
        this.restaurantSchedules = restaurantSchedules;
    }
}
