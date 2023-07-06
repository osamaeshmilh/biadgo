package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.Food} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FoodDTO implements Serializable {

    private Long id;

    private String name;

    private String nameAr;

    private String nameEn;

    private Double price;

    private Double discountPrice;

    private String description;

    private String descriptionAr;

    private String descriptionEn;

    private Double packageItemsCount;

    private Integer dailyQuantity;

    private Boolean isAvailable;

    private Boolean isDiscount;

    private Boolean isFeatured;

    private Boolean isActive;

    private Integer viewCounter;

    private String notes;

    private RestaurantDTO restaurant;

    private CategoryDTO category;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionAr() {
        return descriptionAr;
    }

    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public Double getPackageItemsCount() {
        return packageItemsCount;
    }

    public void setPackageItemsCount(Double packageItemsCount) {
        this.packageItemsCount = packageItemsCount;
    }

    public Integer getDailyQuantity() {
        return dailyQuantity;
    }

    public void setDailyQuantity(Integer dailyQuantity) {
        this.dailyQuantity = dailyQuantity;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Boolean getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Boolean isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getViewCounter() {
        return viewCounter;
    }

    public void setViewCounter(Integer viewCounter) {
        this.viewCounter = viewCounter;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodDTO)) {
            return false;
        }

        FoodDTO foodDTO = (FoodDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, foodDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", price=" + getPrice() +
            ", discountPrice=" + getDiscountPrice() +
            ", description='" + getDescription() + "'" +
            ", descriptionAr='" + getDescriptionAr() + "'" +
            ", descriptionEn='" + getDescriptionEn() + "'" +
            ", packageItemsCount=" + getPackageItemsCount() +
            ", dailyQuantity=" + getDailyQuantity() +
            ", isAvailable='" + getIsAvailable() + "'" +
            ", isDiscount='" + getIsDiscount() + "'" +
            ", isFeatured='" + getIsFeatured() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", viewCounter=" + getViewCounter() +
            ", notes='" + getNotes() + "'" +
            ", restaurant=" + getRestaurant() +
            ", category=" + getCategory() +
            "}";
    }
}
