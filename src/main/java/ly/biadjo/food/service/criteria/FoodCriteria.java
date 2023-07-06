package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.Food} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.FoodResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /foods?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FoodCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter nameAr;

    private StringFilter nameEn;

    private DoubleFilter price;

    private DoubleFilter discountPrice;

    private StringFilter description;

    private StringFilter descriptionAr;

    private StringFilter descriptionEn;

    private DoubleFilter packageItemsCount;

    private IntegerFilter dailyQuantity;

    private BooleanFilter isAvailable;

    private BooleanFilter isDiscount;

    private BooleanFilter isFeatured;

    private BooleanFilter isActive;

    private IntegerFilter viewCounter;

    private StringFilter notes;

    private LongFilter restaurantId;

    private LongFilter categoryId;

    private Boolean distinct;

    public FoodCriteria() {
    }

    public FoodCriteria(FoodCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.nameAr = other.nameAr == null ? null : other.nameAr.copy();
        this.nameEn = other.nameEn == null ? null : other.nameEn.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.discountPrice = other.discountPrice == null ? null : other.discountPrice.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.descriptionAr = other.descriptionAr == null ? null : other.descriptionAr.copy();
        this.descriptionEn = other.descriptionEn == null ? null : other.descriptionEn.copy();
        this.packageItemsCount = other.packageItemsCount == null ? null : other.packageItemsCount.copy();
        this.dailyQuantity = other.dailyQuantity == null ? null : other.dailyQuantity.copy();
        this.isAvailable = other.isAvailable == null ? null : other.isAvailable.copy();
        this.isDiscount = other.isDiscount == null ? null : other.isDiscount.copy();
        this.isFeatured = other.isFeatured == null ? null : other.isFeatured.copy();
        this.isActive = other.isActive == null ? null : other.isActive.copy();
        this.viewCounter = other.viewCounter == null ? null : other.viewCounter.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.restaurantId = other.restaurantId == null ? null : other.restaurantId.copy();
        this.categoryId = other.categoryId == null ? null : other.categoryId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public FoodCriteria copy() {
        return new FoodCriteria(this);
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

    public DoubleFilter getPrice() {
        return price;
    }

    public DoubleFilter price() {
        if (price == null) {
            price = new DoubleFilter();
        }
        return price;
    }

    public void setPrice(DoubleFilter price) {
        this.price = price;
    }

    public DoubleFilter getDiscountPrice() {
        return discountPrice;
    }

    public DoubleFilter discountPrice() {
        if (discountPrice == null) {
            discountPrice = new DoubleFilter();
        }
        return discountPrice;
    }

    public void setDiscountPrice(DoubleFilter discountPrice) {
        this.discountPrice = discountPrice;
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

    public DoubleFilter getPackageItemsCount() {
        return packageItemsCount;
    }

    public DoubleFilter packageItemsCount() {
        if (packageItemsCount == null) {
            packageItemsCount = new DoubleFilter();
        }
        return packageItemsCount;
    }

    public void setPackageItemsCount(DoubleFilter packageItemsCount) {
        this.packageItemsCount = packageItemsCount;
    }

    public IntegerFilter getDailyQuantity() {
        return dailyQuantity;
    }

    public IntegerFilter dailyQuantity() {
        if (dailyQuantity == null) {
            dailyQuantity = new IntegerFilter();
        }
        return dailyQuantity;
    }

    public void setDailyQuantity(IntegerFilter dailyQuantity) {
        this.dailyQuantity = dailyQuantity;
    }

    public BooleanFilter getIsAvailable() {
        return isAvailable;
    }

    public BooleanFilter isAvailable() {
        if (isAvailable == null) {
            isAvailable = new BooleanFilter();
        }
        return isAvailable;
    }

    public void setIsAvailable(BooleanFilter isAvailable) {
        this.isAvailable = isAvailable;
    }

    public BooleanFilter getIsDiscount() {
        return isDiscount;
    }

    public BooleanFilter isDiscount() {
        if (isDiscount == null) {
            isDiscount = new BooleanFilter();
        }
        return isDiscount;
    }

    public void setIsDiscount(BooleanFilter isDiscount) {
        this.isDiscount = isDiscount;
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

    public LongFilter getCategoryId() {
        return categoryId;
    }

    public LongFilter categoryId() {
        if (categoryId == null) {
            categoryId = new LongFilter();
        }
        return categoryId;
    }

    public void setCategoryId(LongFilter categoryId) {
        this.categoryId = categoryId;
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
        final FoodCriteria that = (FoodCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(nameAr, that.nameAr) &&
                Objects.equals(nameEn, that.nameEn) &&
                Objects.equals(price, that.price) &&
                Objects.equals(discountPrice, that.discountPrice) &&
                Objects.equals(description, that.description) &&
                Objects.equals(descriptionAr, that.descriptionAr) &&
                Objects.equals(descriptionEn, that.descriptionEn) &&
                Objects.equals(packageItemsCount, that.packageItemsCount) &&
                Objects.equals(dailyQuantity, that.dailyQuantity) &&
                Objects.equals(isAvailable, that.isAvailable) &&
                Objects.equals(isDiscount, that.isDiscount) &&
                Objects.equals(isFeatured, that.isFeatured) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(viewCounter, that.viewCounter) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(categoryId, that.categoryId) &&
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
            price,
            discountPrice,
            description,
            descriptionAr,
            descriptionEn,
            packageItemsCount,
            dailyQuantity,
            isAvailable,
            isDiscount,
            isFeatured,
            isActive,
            viewCounter,
            notes,
            restaurantId,
            categoryId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (nameAr != null ? "nameAr=" + nameAr + ", " : "") +
            (nameEn != null ? "nameEn=" + nameEn + ", " : "") +
            (price != null ? "price=" + price + ", " : "") +
            (discountPrice != null ? "discountPrice=" + discountPrice + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (descriptionAr != null ? "descriptionAr=" + descriptionAr + ", " : "") +
            (descriptionEn != null ? "descriptionEn=" + descriptionEn + ", " : "") +
            (packageItemsCount != null ? "packageItemsCount=" + packageItemsCount + ", " : "") +
            (dailyQuantity != null ? "dailyQuantity=" + dailyQuantity + ", " : "") +
            (isAvailable != null ? "isAvailable=" + isAvailable + ", " : "") +
            (isDiscount != null ? "isDiscount=" + isDiscount + ", " : "") +
            (isFeatured != null ? "isFeatured=" + isFeatured + ", " : "") +
            (isActive != null ? "isActive=" + isActive + ", " : "") +
            (viewCounter != null ? "viewCounter=" + viewCounter + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (restaurantId != null ? "restaurantId=" + restaurantId + ", " : "") +
            (categoryId != null ? "categoryId=" + categoryId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
