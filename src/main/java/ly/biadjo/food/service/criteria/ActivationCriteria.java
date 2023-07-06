package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.Activation} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.ActivationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /activations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ActivationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter mobileNo;

    private StringFilter email;

    private StringFilter code;

    private InstantFilter sentOn;

    private InstantFilter validUntil;

    private BooleanFilter isUsed;

    private Boolean distinct;

    public ActivationCriteria() {
    }

    public ActivationCriteria(ActivationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.mobileNo = other.mobileNo == null ? null : other.mobileNo.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.sentOn = other.sentOn == null ? null : other.sentOn.copy();
        this.validUntil = other.validUntil == null ? null : other.validUntil.copy();
        this.isUsed = other.isUsed == null ? null : other.isUsed.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ActivationCriteria copy() {
        return new ActivationCriteria(this);
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

    public StringFilter getMobileNo() {
        return mobileNo;
    }

    public StringFilter mobileNo() {
        if (mobileNo == null) {
            mobileNo = new StringFilter();
        }
        return mobileNo;
    }

    public void setMobileNo(StringFilter mobileNo) {
        this.mobileNo = mobileNo;
    }

    public StringFilter getEmail() {
        return email;
    }

    public StringFilter email() {
        if (email == null) {
            email = new StringFilter();
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
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

    public InstantFilter getSentOn() {
        return sentOn;
    }

    public InstantFilter sentOn() {
        if (sentOn == null) {
            sentOn = new InstantFilter();
        }
        return sentOn;
    }

    public void setSentOn(InstantFilter sentOn) {
        this.sentOn = sentOn;
    }

    public InstantFilter getValidUntil() {
        return validUntil;
    }

    public InstantFilter validUntil() {
        if (validUntil == null) {
            validUntil = new InstantFilter();
        }
        return validUntil;
    }

    public void setValidUntil(InstantFilter validUntil) {
        this.validUntil = validUntil;
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
        final ActivationCriteria that = (ActivationCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(mobileNo, that.mobileNo) &&
                Objects.equals(email, that.email) &&
                Objects.equals(code, that.code) &&
                Objects.equals(sentOn, that.sentOn) &&
                Objects.equals(validUntil, that.validUntil) &&
                Objects.equals(isUsed, that.isUsed) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mobileNo, email, code, sentOn, validUntil, isUsed, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActivationCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (mobileNo != null ? "mobileNo=" + mobileNo + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            (sentOn != null ? "sentOn=" + sentOn + ", " : "") +
            (validUntil != null ? "validUntil=" + validUntil + ", " : "") +
            (isUsed != null ? "isUsed=" + isUsed + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
