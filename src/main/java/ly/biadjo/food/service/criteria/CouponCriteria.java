package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import ly.biadjo.food.domain.enumeration.CouponType;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.Coupon} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.CouponResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /coupons?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CouponCriteria implements Serializable, Criteria {

    /**
     * Class for filtering CouponType
     */
    public static class CouponTypeFilter extends Filter<CouponType> {

        public CouponTypeFilter() {
        }

        public CouponTypeFilter(CouponTypeFilter filter) {
            super(filter);
        }

        @Override
        public CouponTypeFilter copy() {
            return new CouponTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private CouponTypeFilter couponType;

    private IntegerFilter amount;

    private IntegerFilter minimumAmount;

    private IntegerFilter useLimit;

    private IntegerFilter useCount;

    private LocalDateFilter expiryDate;

    private BooleanFilter isActive;

    private StringFilter notes;

    private Boolean distinct;

    public CouponCriteria() {
    }

    public CouponCriteria(CouponCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.couponType = other.couponType == null ? null : other.couponType.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.minimumAmount = other.minimumAmount == null ? null : other.minimumAmount.copy();
        this.useLimit = other.useLimit == null ? null : other.useLimit.copy();
        this.useCount = other.useCount == null ? null : other.useCount.copy();
        this.expiryDate = other.expiryDate == null ? null : other.expiryDate.copy();
        this.isActive = other.isActive == null ? null : other.isActive.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CouponCriteria copy() {
        return new CouponCriteria(this);
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

    public StringFilter getCode() {
        return code;
    }

    public StringFilter code() {
        if (code == null) {
            code = new StringFilter();
        }
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public CouponTypeFilter getCouponType() {
        return couponType;
    }

    public CouponTypeFilter couponType() {
        if (couponType == null) {
            couponType = new CouponTypeFilter();
        }
        return couponType;
    }

    public void setCouponType(CouponTypeFilter couponType) {
        this.couponType = couponType;
    }

    public IntegerFilter getAmount() {
        return amount;
    }

    public IntegerFilter amount() {
        if (amount == null) {
            amount = new IntegerFilter();
        }
        return amount;
    }

    public void setAmount(IntegerFilter amount) {
        this.amount = amount;
    }

    public IntegerFilter getMinimumAmount() {
        return minimumAmount;
    }

    public IntegerFilter minimumAmount() {
        if (minimumAmount == null) {
            minimumAmount = new IntegerFilter();
        }
        return minimumAmount;
    }

    public void setMinimumAmount(IntegerFilter minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public IntegerFilter getUseLimit() {
        return useLimit;
    }

    public IntegerFilter useLimit() {
        if (useLimit == null) {
            useLimit = new IntegerFilter();
        }
        return useLimit;
    }

    public void setUseLimit(IntegerFilter useLimit) {
        this.useLimit = useLimit;
    }

    public IntegerFilter getUseCount() {
        return useCount;
    }

    public IntegerFilter useCount() {
        if (useCount == null) {
            useCount = new IntegerFilter();
        }
        return useCount;
    }

    public void setUseCount(IntegerFilter useCount) {
        this.useCount = useCount;
    }

    public LocalDateFilter getExpiryDate() {
        return expiryDate;
    }

    public LocalDateFilter expiryDate() {
        if (expiryDate == null) {
            expiryDate = new LocalDateFilter();
        }
        return expiryDate;
    }

    public void setExpiryDate(LocalDateFilter expiryDate) {
        this.expiryDate = expiryDate;
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
        final CouponCriteria that = (CouponCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(couponType, that.couponType) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(minimumAmount, that.minimumAmount) &&
                Objects.equals(useLimit, that.useLimit) &&
                Objects.equals(useCount, that.useCount) &&
                Objects.equals(expiryDate, that.expiryDate) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, couponType, amount, minimumAmount, useLimit, useCount, expiryDate, isActive, notes, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CouponCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            (couponType != null ? "couponType=" + couponType + ", " : "") +
            (amount != null ? "amount=" + amount + ", " : "") +
            (minimumAmount != null ? "minimumAmount=" + minimumAmount + ", " : "") +
            (useLimit != null ? "useLimit=" + useLimit + ", " : "") +
            (useCount != null ? "useCount=" + useCount + ", " : "") +
            (expiryDate != null ? "expiryDate=" + expiryDate + ", " : "") +
            (isActive != null ? "isActive=" + isActive + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
