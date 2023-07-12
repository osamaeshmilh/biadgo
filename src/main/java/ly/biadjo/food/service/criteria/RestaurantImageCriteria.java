package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.RestaurantImage} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.RestaurantImageResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /restaurant-images?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RestaurantImageCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter description;

    private StringFilter descriptionAr;

    private StringFilter descriptionEn;

    private StringFilter imageType;

    private IntegerFilter menuOrder;

    private StringFilter imageUrl;

    private LongFilter restaurantId;

    private Boolean distinct;

    public RestaurantImageCriteria() {
    }

    public RestaurantImageCriteria(RestaurantImageCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.descriptionAr = other.descriptionAr == null ? null : other.descriptionAr.copy();
        this.descriptionEn = other.descriptionEn == null ? null : other.descriptionEn.copy();
        this.imageType = other.imageType == null ? null : other.imageType.copy();
        this.menuOrder = other.menuOrder == null ? null : other.menuOrder.copy();
        this.imageUrl = other.imageUrl == null ? null : other.imageUrl.copy();
        this.restaurantId = other.restaurantId == null ? null : other.restaurantId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public RestaurantImageCriteria copy() {
        return new RestaurantImageCriteria(this);
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

    public StringFilter getDescriptionAr() {
        return descriptionAr;
    }

    public StringFilter descriptionAr() {
        if (descriptionAr == null) {
            descriptionAr = new StringFilter();
        }
        return descriptionAr;
    }

    public void setDescriptionAr(StringFilter descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    public StringFilter getDescriptionEn() {
        return descriptionEn;
    }

    public StringFilter descriptionEn() {
        if (descriptionEn == null) {
            descriptionEn = new StringFilter();
        }
        return descriptionEn;
    }

    public void setDescriptionEn(StringFilter descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public StringFilter getImageType() {
        return imageType;
    }

    public StringFilter imageType() {
        if (imageType == null) {
            imageType = new StringFilter();
        }
        return imageType;
    }

    public void setImageType(StringFilter imageType) {
        this.imageType = imageType;
    }

    public IntegerFilter getMenuOrder() {
        return menuOrder;
    }

    public IntegerFilter menuOrder() {
        if (menuOrder == null) {
            menuOrder = new IntegerFilter();
        }
        return menuOrder;
    }

    public void setMenuOrder(IntegerFilter menuOrder) {
        this.menuOrder = menuOrder;
    }

    public StringFilter getImageUrl() {
        return imageUrl;
    }

    public StringFilter imageUrl() {
        if (imageUrl == null) {
            imageUrl = new StringFilter();
        }
        return imageUrl;
    }

    public void setImageUrl(StringFilter imageUrl) {
        this.imageUrl = imageUrl;
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
        final RestaurantImageCriteria that = (RestaurantImageCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(descriptionAr, that.descriptionAr) &&
                Objects.equals(descriptionEn, that.descriptionEn) &&
                Objects.equals(imageType, that.imageType) &&
                Objects.equals(menuOrder, that.menuOrder) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, descriptionAr, descriptionEn, imageType, menuOrder, imageUrl, restaurantId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantImageCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (descriptionAr != null ? "descriptionAr=" + descriptionAr + ", " : "") +
            (descriptionEn != null ? "descriptionEn=" + descriptionEn + ", " : "") +
            (imageType != null ? "imageType=" + imageType + ", " : "") +
            (menuOrder != null ? "menuOrder=" + menuOrder + ", " : "") +
            (imageUrl != null ? "imageUrl=" + imageUrl + ", " : "") +
            (restaurantId != null ? "restaurantId=" + restaurantId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
