package ly.biadjo.food.service.dto;

import jakarta.persistence.Lob;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.FoodImage} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FoodImageDTO implements Serializable {

    private Long id;

    private String description;

    private String descriptionAr;

    private String descriptionEn;

    private Integer menuOrder;

    private String imageUrl;

    @Lob
    private byte[] image;

    private String imageContentType;
    private FoodDTO food;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public FoodDTO getFood() {
        return food;
    }

    public void setFood(FoodDTO food) {
        this.food = food;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodImageDTO)) {
            return false;
        }

        FoodImageDTO foodImageDTO = (FoodImageDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, foodImageDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodImageDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", descriptionAr='" + getDescriptionAr() + "'" +
            ", descriptionEn='" + getDescriptionEn() + "'" +
            ", menuOrder=" + getMenuOrder() +
            ", imageUrl='" + getImageUrl() + "'" +
            ", image='" + getImage() + "'" +
            ", food=" + getFood() +
            "}";
    }
}
