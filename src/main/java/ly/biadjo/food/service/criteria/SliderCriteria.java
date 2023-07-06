package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.Slider} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.SliderResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sliders?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SliderCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter details;

    private StringFilter detailsAr;

    private StringFilter detailsEn;

    private IntegerFilter menuOrder;

    private StringFilter imageUrl;

    private StringFilter url;

    private IntegerFilter restaurantId;

    private IntegerFilter categoryId;

    private IntegerFilter foodId;

    private StringFilter notes;

    private Boolean distinct;

    public SliderCriteria() {
    }

    public SliderCriteria(SliderCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.details = other.details == null ? null : other.details.copy();
        this.detailsAr = other.detailsAr == null ? null : other.detailsAr.copy();
        this.detailsEn = other.detailsEn == null ? null : other.detailsEn.copy();
        this.menuOrder = other.menuOrder == null ? null : other.menuOrder.copy();
        this.imageUrl = other.imageUrl == null ? null : other.imageUrl.copy();
        this.url = other.url == null ? null : other.url.copy();
        this.restaurantId = other.restaurantId == null ? null : other.restaurantId.copy();
        this.categoryId = other.categoryId == null ? null : other.categoryId.copy();
        this.foodId = other.foodId == null ? null : other.foodId.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.distinct = other.distinct;
    }

    @Override
    public SliderCriteria copy() {
        return new SliderCriteria(this);
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

    public StringFilter getDetails() {
        return details;
    }

    public StringFilter details() {
        if (details == null) {
            details = new StringFilter();
        }
        return details;
    }

    public void setDetails(StringFilter details) {
        this.details = details;
    }

    public StringFilter getDetailsAr() {
        return detailsAr;
    }

    public StringFilter detailsAr() {
        if (detailsAr == null) {
            detailsAr = new StringFilter();
        }
        return detailsAr;
    }

    public void setDetailsAr(StringFilter detailsAr) {
        this.detailsAr = detailsAr;
    }

    public StringFilter getDetailsEn() {
        return detailsEn;
    }

    public StringFilter detailsEn() {
        if (detailsEn == null) {
            detailsEn = new StringFilter();
        }
        return detailsEn;
    }

    public void setDetailsEn(StringFilter detailsEn) {
        this.detailsEn = detailsEn;
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

    public StringFilter getUrl() {
        return url;
    }

    public StringFilter url() {
        if (url == null) {
            url = new StringFilter();
        }
        return url;
    }

    public void setUrl(StringFilter url) {
        this.url = url;
    }

    public IntegerFilter getRestaurantId() {
        return restaurantId;
    }

    public IntegerFilter restaurantId() {
        if (restaurantId == null) {
            restaurantId = new IntegerFilter();
        }
        return restaurantId;
    }

    public void setRestaurantId(IntegerFilter restaurantId) {
        this.restaurantId = restaurantId;
    }

    public IntegerFilter getCategoryId() {
        return categoryId;
    }

    public IntegerFilter categoryId() {
        if (categoryId == null) {
            categoryId = new IntegerFilter();
        }
        return categoryId;
    }

    public void setCategoryId(IntegerFilter categoryId) {
        this.categoryId = categoryId;
    }

    public IntegerFilter getFoodId() {
        return foodId;
    }

    public IntegerFilter foodId() {
        if (foodId == null) {
            foodId = new IntegerFilter();
        }
        return foodId;
    }

    public void setFoodId(IntegerFilter foodId) {
        this.foodId = foodId;
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
        final SliderCriteria that = (SliderCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(details, that.details) &&
                Objects.equals(detailsAr, that.detailsAr) &&
                Objects.equals(detailsEn, that.detailsEn) &&
                Objects.equals(menuOrder, that.menuOrder) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(url, that.url) &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(foodId, that.foodId) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, details, detailsAr, detailsEn, menuOrder, imageUrl, url, restaurantId, categoryId, foodId, notes, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SliderCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (details != null ? "details=" + details + ", " : "") +
            (detailsAr != null ? "detailsAr=" + detailsAr + ", " : "") +
            (detailsEn != null ? "detailsEn=" + detailsEn + ", " : "") +
            (menuOrder != null ? "menuOrder=" + menuOrder + ", " : "") +
            (imageUrl != null ? "imageUrl=" + imageUrl + ", " : "") +
            (url != null ? "url=" + url + ", " : "") +
            (restaurantId != null ? "restaurantId=" + restaurantId + ", " : "") +
            (categoryId != null ? "categoryId=" + categoryId + ", " : "") +
            (foodId != null ? "foodId=" + foodId + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
