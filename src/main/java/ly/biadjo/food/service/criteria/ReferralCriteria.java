package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.Referral} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.ReferralResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /referrals?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReferralCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter referralCode;

    private DoubleFilter referrerAmount;

    private DoubleFilter referredCustomerAmount;

    private LocalDateFilter expiryDate;

    private BooleanFilter isUsed;

    private InstantFilter usedDateTime;

    private StringFilter notes;

    private LongFilter referredCustomerId;

    private LongFilter referrerCustomerId;

    private Boolean distinct;

    public ReferralCriteria() {
    }

    public ReferralCriteria(ReferralCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.referralCode = other.referralCode == null ? null : other.referralCode.copy();
        this.referrerAmount = other.referrerAmount == null ? null : other.referrerAmount.copy();
        this.referredCustomerAmount = other.referredCustomerAmount == null ? null : other.referredCustomerAmount.copy();
        this.expiryDate = other.expiryDate == null ? null : other.expiryDate.copy();
        this.isUsed = other.isUsed == null ? null : other.isUsed.copy();
        this.usedDateTime = other.usedDateTime == null ? null : other.usedDateTime.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.referredCustomerId = other.referredCustomerId == null ? null : other.referredCustomerId.copy();
        this.referrerCustomerId = other.referrerCustomerId == null ? null : other.referrerCustomerId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ReferralCriteria copy() {
        return new ReferralCriteria(this);
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

    public StringFilter getReferralCode() {
        return referralCode;
    }

    public StringFilter referralCode() {
        if (referralCode == null) {
            referralCode = new StringFilter();
        }
        return referralCode;
    }

    public void setReferralCode(StringFilter referralCode) {
        this.referralCode = referralCode;
    }

    public DoubleFilter getReferrerAmount() {
        return referrerAmount;
    }

    public DoubleFilter referrerAmount() {
        if (referrerAmount == null) {
            referrerAmount = new DoubleFilter();
        }
        return referrerAmount;
    }

    public void setReferrerAmount(DoubleFilter referrerAmount) {
        this.referrerAmount = referrerAmount;
    }

    public DoubleFilter getReferredCustomerAmount() {
        return referredCustomerAmount;
    }

    public DoubleFilter referredCustomerAmount() {
        if (referredCustomerAmount == null) {
            referredCustomerAmount = new DoubleFilter();
        }
        return referredCustomerAmount;
    }

    public void setReferredCustomerAmount(DoubleFilter referredCustomerAmount) {
        this.referredCustomerAmount = referredCustomerAmount;
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

    public BooleanFilter getIsUsed() {
        return isUsed;
    }

    public BooleanFilter isUsed() {
        if (isUsed == null) {
            isUsed = new BooleanFilter();
        }
        return isUsed;
    }

    public void setIsUsed(BooleanFilter isUsed) {
        this.isUsed = isUsed;
    }

    public InstantFilter getUsedDateTime() {
        return usedDateTime;
    }

    public InstantFilter usedDateTime() {
        if (usedDateTime == null) {
            usedDateTime = new InstantFilter();
        }
        return usedDateTime;
    }

    public void setUsedDateTime(InstantFilter usedDateTime) {
        this.usedDateTime = usedDateTime;
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

    public LongFilter getReferredCustomerId() {
        return referredCustomerId;
    }

    public LongFilter referredCustomerId() {
        if (referredCustomerId == null) {
            referredCustomerId = new LongFilter();
        }
        return referredCustomerId;
    }

    public void setReferredCustomerId(LongFilter referredCustomerId) {
        this.referredCustomerId = referredCustomerId;
    }

    public LongFilter getReferrerCustomerId() {
        return referrerCustomerId;
    }

    public LongFilter referrerCustomerId() {
        if (referrerCustomerId == null) {
            referrerCustomerId = new LongFilter();
        }
        return referrerCustomerId;
    }

    public void setReferrerCustomerId(LongFilter referrerCustomerId) {
        this.referrerCustomerId = referrerCustomerId;
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
        final ReferralCriteria that = (ReferralCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(referralCode, that.referralCode) &&
                Objects.equals(referrerAmount, that.referrerAmount) &&
                Objects.equals(referredCustomerAmount, that.referredCustomerAmount) &&
                Objects.equals(expiryDate, that.expiryDate) &&
                Objects.equals(isUsed, that.isUsed) &&
                Objects.equals(usedDateTime, that.usedDateTime) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(referredCustomerId, that.referredCustomerId) &&
                Objects.equals(referrerCustomerId, that.referrerCustomerId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            referralCode,
            referrerAmount,
            referredCustomerAmount,
            expiryDate,
            isUsed,
            usedDateTime,
            notes,
            referredCustomerId,
            referrerCustomerId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReferralCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (referralCode != null ? "referralCode=" + referralCode + ", " : "") +
            (referrerAmount != null ? "referrerAmount=" + referrerAmount + ", " : "") +
            (referredCustomerAmount != null ? "referredCustomerAmount=" + referredCustomerAmount + ", " : "") +
            (expiryDate != null ? "expiryDate=" + expiryDate + ", " : "") +
            (isUsed != null ? "isUsed=" + isUsed + ", " : "") +
            (usedDateTime != null ? "usedDateTime=" + usedDateTime + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (referredCustomerId != null ? "referredCustomerId=" + referredCustomerId + ", " : "") +
            (referrerCustomerId != null ? "referrerCustomerId=" + referrerCustomerId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
