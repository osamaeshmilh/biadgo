package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.DeliveryPriceType;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.Restaurant} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.RestaurantResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /restaurants?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestaurantCriteria implements Serializable, Criteria {

    /**
     * Class for filtering DeliveryPriceType
     */
    public static class DeliveryPriceTypeFilter extends Filter<DeliveryPriceType> {

        public DeliveryPriceTypeFilter() {
        }

        public DeliveryPriceTypeFilter(DeliveryPriceTypeFilter filter) {
            super(filter);
        }

        @Override
        public DeliveryPriceTypeFilter copy() {
            return new DeliveryPriceTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter nameAr;

    private StringFilter nameEn;

    private StringFilter description;

    private StringFilter address;

    private DoubleFilter latitude;

    private DoubleFilter longitude;

    private StringFilter plusCode;

    private StringFilter mobile;

    private IntegerFilter estimitedTimeMins;

    private DoubleFilter deliveryFee;

    private DoubleFilter deliveryRange;

    private BooleanFilter availableForDelivery;

    private BooleanFilter availableForPickup;

    private DoubleFilter minimumOrderTotalForDelivery;

    private DeliveryPriceTypeFilter deliveryPriceType;

    private BooleanFilter isOpen;

    private BooleanFilter isFeatured;

    private BooleanFilter isListedInOffers;

    private BooleanFilter isActive;

    private StringFilter offerBanner;

    private IntegerFilter priority;

    private IntegerFilter viewCounter;

    private StringFilter scheduledClosingTime;

    private StringFilter facebookPageUrl;

    private StringFilter notes;

    private LongFilter userId;

    private LongFilter cuisineId;

    private LongFilter categoriesId;

    private Boolean distinct;

    public RestaurantCriteria() {
    }

    public RestaurantCriteria(RestaurantCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.nameAr = other.nameAr == null ? null : other.nameAr.copy();
        this.nameEn = other.nameEn == null ? null : other.nameEn.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.latitude = other.latitude == null ? null : other.latitude.copy();
        this.longitude = other.longitude == null ? null : other.longitude.copy();
        this.plusCode = other.plusCode == null ? null : other.plusCode.copy();
        this.mobile = other.mobile == null ? null : other.mobile.copy();
        this.estimitedTimeMins = other.estimitedTimeMins == null ? null : other.estimitedTimeMins.copy();
        this.deliveryFee = other.deliveryFee == null ? null : other.deliveryFee.copy();
        this.deliveryRange = other.deliveryRange == null ? null : other.deliveryRange.copy();
        this.availableForDelivery = other.availableForDelivery == null ? null : other.availableForDelivery.copy();
        this.availableForPickup = other.availableForPickup == null ? null : other.availableForPickup.copy();
        this.minimumOrderTotalForDelivery = other.minimumOrderTotalForDelivery == null ? null : other.minimumOrderTotalForDelivery.copy();
        this.deliveryPriceType = other.deliveryPriceType == null ? null : other.deliveryPriceType.copy();
        this.isOpen = other.isOpen == null ? null : other.isOpen.copy();
        this.isFeatured = other.isFeatured == null ? null : other.isFeatured.copy();
        this.isListedInOffers = other.isListedInOffers == null ? null : other.isListedInOffers.copy();
        this.isActive = other.isActive == null ? null : other.isActive.copy();
        this.offerBanner = other.offerBanner == null ? null : other.offerBanner.copy();
        this.priority = other.priority == null ? null : other.priority.copy();
        this.viewCounter = other.viewCounter == null ? null : other.viewCounter.copy();
        this.scheduledClosingTime = other.scheduledClosingTime == null ? null : other.scheduledClosingTime.copy();
        this.facebookPageUrl = other.facebookPageUrl == null ? null : other.facebookPageUrl.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.cuisineId = other.cuisineId == null ? null : other.cuisineId.copy();
        this.categoriesId = other.categoriesId == null ? null : other.categoriesId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public RestaurantCriteria copy() {
        return new RestaurantCriteria(this);
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

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getNameAr() {
        return nameAr;
    }

    public StringFilter nameAr() {
        if (nameAr == null) {
            nameAr = new StringFilter();
        }
        return nameAr;
    }

    public void setNameAr(StringFilter nameAr) {
        this.nameAr = nameAr;
    }

    public StringFilter getNameEn() {
        return nameEn;
    }

    public StringFilter nameEn() {
        if (nameEn == null) {
            nameEn = new StringFilter();
        }
        return nameEn;
    }

    public void setNameEn(StringFilter nameEn) {
        this.nameEn = nameEn;
    }

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getAddress() {
        return address;
    }

    public StringFilter address() {
        if (address == null) {
            address = new StringFilter();
        }
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
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

    public StringFilter getMobile() {
        return mobile;
    }

    public StringFilter mobile() {
        if (mobile == null) {
            mobile = new StringFilter();
        }
        return mobile;
    }

    public void setMobile(StringFilter mobile) {
        this.mobile = mobile;
    }

    public IntegerFilter getEstimitedTimeMins() {
        return estimitedTimeMins;
    }

    public IntegerFilter estimitedTimeMins() {
        if (estimitedTimeMins == null) {
            estimitedTimeMins = new IntegerFilter();
        }
        return estimitedTimeMins;
    }

    public void setEstimitedTimeMins(IntegerFilter estimitedTimeMins) {
        this.estimitedTimeMins = estimitedTimeMins;
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

    public DoubleFilter getDeliveryRange() {
        return deliveryRange;
    }

    public DoubleFilter deliveryRange() {
        if (deliveryRange == null) {
            deliveryRange = new DoubleFilter();
        }
        return deliveryRange;
    }

    public void setDeliveryRange(DoubleFilter deliveryRange) {
        this.deliveryRange = deliveryRange;
    }

    public BooleanFilter getAvailableForDelivery() {
        return availableForDelivery;
    }

    public BooleanFilter availableForDelivery() {
        if (availableForDelivery == null) {
            availableForDelivery = new BooleanFilter();
        }
        return availableForDelivery;
    }

    public void setAvailableForDelivery(BooleanFilter availableForDelivery) {
        this.availableForDelivery = availableForDelivery;
    }

    public BooleanFilter getAvailableForPickup() {
        return availableForPickup;
    }

    public BooleanFilter availableForPickup() {
        if (availableForPickup == null) {
            availableForPickup = new BooleanFilter();
        }
        return availableForPickup;
    }

    public void setAvailableForPickup(BooleanFilter availableForPickup) {
        this.availableForPickup = availableForPickup;
    }

    public DoubleFilter getMinimumOrderTotalForDelivery() {
        return minimumOrderTotalForDelivery;
    }

    public DoubleFilter minimumOrderTotalForDelivery() {
        if (minimumOrderTotalForDelivery == null) {
            minimumOrderTotalForDelivery = new DoubleFilter();
        }
        return minimumOrderTotalForDelivery;
    }

    public void setMinimumOrderTotalForDelivery(DoubleFilter minimumOrderTotalForDelivery) {
        this.minimumOrderTotalForDelivery = minimumOrderTotalForDelivery;
    }

    public DeliveryPriceTypeFilter getDeliveryPriceType() {
        return deliveryPriceType;
    }

    public DeliveryPriceTypeFilter deliveryPriceType() {
        if (deliveryPriceType == null) {
            deliveryPriceType = new DeliveryPriceTypeFilter();
        }
        return deliveryPriceType;
    }

    public void setDeliveryPriceType(DeliveryPriceTypeFilter deliveryPriceType) {
        this.deliveryPriceType = deliveryPriceType;
    }

    public BooleanFilter getIsOpen() {
        return isOpen;
    }

    public BooleanFilter isOpen() {
        if (isOpen == null) {
            isOpen = new BooleanFilter();
        }
        return isOpen;
    }

    public void setIsOpen(BooleanFilter isOpen) {
        this.isOpen = isOpen;
    }

    public BooleanFilter getIsFeatured() {
        return isFeatured;
    }

    public BooleanFilter isFeatured() {
        if (isFeatured == null) {
            isFeatured = new BooleanFilter();
        }
        return isFeatured;
    }

    public void setIsFeatured(BooleanFilter isFeatured) {
        this.isFeatured = isFeatured;
    }

    public BooleanFilter getIsListedInOffers() {
        return isListedInOffers;
    }

    public BooleanFilter isListedInOffers() {
        if (isListedInOffers == null) {
            isListedInOffers = new BooleanFilter();
        }
        return isListedInOffers;
    }

    public void setIsListedInOffers(BooleanFilter isListedInOffers) {
        this.isListedInOffers = isListedInOffers;
    }

    public BooleanFilter getIsActive() {
        return isActive;
    }

    public BooleanFilter isActive() {
        if (isActive == null) {
            isActive = new BooleanFilter();
        }
        return isActive;
    }

    public void setIsActive(BooleanFilter isActive) {
        this.isActive = isActive;
    }

    public StringFilter getOfferBanner() {
        return offerBanner;
    }

    public StringFilter offerBanner() {
        if (offerBanner == null) {
            offerBanner = new StringFilter();
        }
        return offerBanner;
    }

    public void setOfferBanner(StringFilter offerBanner) {
        this.offerBanner = offerBanner;
    }

    public IntegerFilter getPriority() {
        return priority;
    }

    public IntegerFilter priority() {
        if (priority == null) {
            priority = new IntegerFilter();
        }
        return priority;
    }

    public void setPriority(IntegerFilter priority) {
        this.priority = priority;
    }

    public IntegerFilter getViewCounter() {
        return viewCounter;
    }

    public IntegerFilter viewCounter() {
        if (viewCounter == null) {
            viewCounter = new IntegerFilter();
        }
        return viewCounter;
    }

    public void setViewCounter(IntegerFilter viewCounter) {
        this.viewCounter = viewCounter;
    }

    public StringFilter getScheduledClosingTime() {
        return scheduledClosingTime;
    }

    public StringFilter scheduledClosingTime() {
        if (scheduledClosingTime == null) {
            scheduledClosingTime = new StringFilter();
        }
        return scheduledClosingTime;
    }

    public void setScheduledClosingTime(StringFilter scheduledClosingTime) {
        this.scheduledClosingTime = scheduledClosingTime;
    }

    public StringFilter getFacebookPageUrl() {
        return facebookPageUrl;
    }

    public StringFilter facebookPageUrl() {
        if (facebookPageUrl == null) {
            facebookPageUrl = new StringFilter();
        }
        return facebookPageUrl;
    }

    public void setFacebookPageUrl(StringFilter facebookPageUrl) {
        this.facebookPageUrl = facebookPageUrl;
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

    public LongFilter getUserId() {
        return userId;
    }

    public LongFilter userId() {
        if (userId == null) {
            userId = new LongFilter();
        }
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getCuisineId() {
        return cuisineId;
    }

    public LongFilter cuisineId() {
        if (cuisineId == null) {
            cuisineId = new LongFilter();
        }
        return cuisineId;
    }

    public void setCuisineId(LongFilter cuisineId) {
        this.cuisineId = cuisineId;
    }

    public LongFilter getCategoriesId() {
        return categoriesId;
    }

    public LongFilter categoriesId() {
        if (categoriesId == null) {
            categoriesId = new LongFilter();
        }
        return categoriesId;
    }

    public void setCategoriesId(LongFilter categoriesId) {
        this.categoriesId = categoriesId;
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
        final RestaurantCriteria that = (RestaurantCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(nameAr, that.nameAr) &&
                Objects.equals(nameEn, that.nameEn) &&
                Objects.equals(description, that.description) &&
                Objects.equals(address, that.address) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(plusCode, that.plusCode) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(estimitedTimeMins, that.estimitedTimeMins) &&
                Objects.equals(deliveryFee, that.deliveryFee) &&
                Objects.equals(deliveryRange, that.deliveryRange) &&
                Objects.equals(availableForDelivery, that.availableForDelivery) &&
                Objects.equals(availableForPickup, that.availableForPickup) &&
                Objects.equals(minimumOrderTotalForDelivery, that.minimumOrderTotalForDelivery) &&
                Objects.equals(deliveryPriceType, that.deliveryPriceType) &&
                Objects.equals(isOpen, that.isOpen) &&
                Objects.equals(isFeatured, that.isFeatured) &&
                Objects.equals(isListedInOffers, that.isListedInOffers) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(offerBanner, that.offerBanner) &&
                Objects.equals(priority, that.priority) &&
                Objects.equals(viewCounter, that.viewCounter) &&
                Objects.equals(scheduledClosingTime, that.scheduledClosingTime) &&
                Objects.equals(facebookPageUrl, that.facebookPageUrl) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(cuisineId, that.cuisineId) &&
                Objects.equals(categoriesId, that.categoriesId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            nameAr,
            nameEn,
            description,
            address,
            latitude,
            longitude,
            plusCode,
            mobile,
            estimitedTimeMins,
            deliveryFee,
            deliveryRange,
            availableForDelivery,
            availableForPickup,
            minimumOrderTotalForDelivery,
            deliveryPriceType,
            isOpen,
            isFeatured,
            isListedInOffers,
            isActive,
            offerBanner,
            priority,
            viewCounter,
            scheduledClosingTime,
            facebookPageUrl,
            notes,
            userId,
            cuisineId,
            categoriesId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (nameAr != null ? "nameAr=" + nameAr + ", " : "") +
            (nameEn != null ? "nameEn=" + nameEn + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (address != null ? "address=" + address + ", " : "") +
            (latitude != null ? "latitude=" + latitude + ", " : "") +
            (longitude != null ? "longitude=" + longitude + ", " : "") +
            (plusCode != null ? "plusCode=" + plusCode + ", " : "") +
            (mobile != null ? "mobile=" + mobile + ", " : "") +
            (estimitedTimeMins != null ? "estimitedTimeMins=" + estimitedTimeMins + ", " : "") +
            (deliveryFee != null ? "deliveryFee=" + deliveryFee + ", " : "") +
            (deliveryRange != null ? "deliveryRange=" + deliveryRange + ", " : "") +
            (availableForDelivery != null ? "availableForDelivery=" + availableForDelivery + ", " : "") +
            (availableForPickup != null ? "availableForPickup=" + availableForPickup + ", " : "") +
            (minimumOrderTotalForDelivery != null ? "minimumOrderTotalForDelivery=" + minimumOrderTotalForDelivery + ", " : "") +
            (deliveryPriceType != null ? "deliveryPriceType=" + deliveryPriceType + ", " : "") +
            (isOpen != null ? "isOpen=" + isOpen + ", " : "") +
            (isFeatured != null ? "isFeatured=" + isFeatured + ", " : "") +
            (isListedInOffers != null ? "isListedInOffers=" + isListedInOffers + ", " : "") +
            (isActive != null ? "isActive=" + isActive + ", " : "") +
            (offerBanner != null ? "offerBanner=" + offerBanner + ", " : "") +
            (priority != null ? "priority=" + priority + ", " : "") +
            (viewCounter != null ? "viewCounter=" + viewCounter + ", " : "") +
            (scheduledClosingTime != null ? "scheduledClosingTime=" + scheduledClosingTime + ", " : "") +
            (facebookPageUrl != null ? "facebookPageUrl=" + facebookPageUrl + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (cuisineId != null ? "cuisineId=" + cuisineId + ", " : "") +
            (categoriesId != null ? "categoriesId=" + categoriesId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
