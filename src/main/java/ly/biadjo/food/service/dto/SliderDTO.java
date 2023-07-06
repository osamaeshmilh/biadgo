package ly.biadjo.food.service.dto;

import jakarta.persistence.Lob;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.Slider} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SliderDTO implements Serializable {

    private Long id;

    private String details;

    private String detailsAr;

    private String detailsEn;

    private Integer menuOrder;

    private String imageUrl;

    @Lob
    private byte[] image;

    private String imageContentType;
    private String url;

    private Integer restaurantId;

    private Integer categoryId;

    private Integer foodId;

    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetailsAr() {
        return detailsAr;
    }

    public void setDetailsAr(String detailsAr) {
        this.detailsAr = detailsAr;
    }

    public String getDetailsEn() {
        return detailsEn;
    }

    public void setDetailsEn(String detailsEn) {
        this.detailsEn = detailsEn;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SliderDTO)) {
            return false;
        }

        SliderDTO sliderDTO = (SliderDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sliderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SliderDTO{" +
            "id=" + getId() +
            ", details='" + getDetails() + "'" +
            ", detailsAr='" + getDetailsAr() + "'" +
            ", detailsEn='" + getDetailsEn() + "'" +
            ", menuOrder=" + getMenuOrder() +
            ", imageUrl='" + getImageUrl() + "'" +
            ", image='" + getImage() + "'" +
            ", url='" + getUrl() + "'" +
            ", restaurantId=" + getRestaurantId() +
            ", categoryId=" + getCategoryId() +
            ", foodId=" + getFoodId() +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
