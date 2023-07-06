package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.FoodImage} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.FoodImageResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /food-images?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FoodImageCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter description;

    private StringFilter descriptionAr;

    private StringFilter descriptionEn;

    private IntegerFilter menuOrder;

    private StringFilter imageUrl;

    private LongFilter foodId;

    private Boolean distinct;

    public FoodImageCriteria() {
    }

    public FoodImageCriteria(FoodImageCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.descriptionAr = other.descriptionAr == null ? null : other.descriptionAr.copy();
        this.descriptionEn = other.descriptionEn == null ? null : other.descriptionEn.copy();
        this.menuOrder = other.menuOrder == null ? null : other.menuOrder.copy();
        this.imageUrl = other.imageUrl == null ? null : other.imageUrl.copy();
        this.foodId = other.foodId == null ? null : other.foodId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public FoodImageCriteria copy() {
        return new FoodImageCriteria(this);
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

    public LongFilter getFoodId() {
        return foodId;
    }

    public LongFilter foodId() {
        if (foodId == null) {
            foodId = new LongFilter();
        }
        return foodId;
    }

    public void setFoodId(LongFilter foodId) {
        this.foodId = foodId;
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
        final FoodImageCriteria that = (FoodImageCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(descriptionAr, that.descriptionAr) &&
                Objects.equals(descriptionEn, that.descriptionEn) &&
                Objects.equals(menuOrder, that.menuOrder) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(foodId, that.foodId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, descriptionAr, descriptionEn, menuOrder, imageUrl, foodId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodImageCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (descriptionAr != null ? "descriptionAr=" + descriptionAr + ", " : "") +
            (descriptionEn != null ? "descriptionEn=" + descriptionEn + ", " : "") +
            (menuOrder != null ? "menuOrder=" + menuOrder + ", " : "") +
            (imageUrl != null ? "imageUrl=" + imageUrl + ", " : "") +
            (foodId != null ? "foodId=" + foodId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
