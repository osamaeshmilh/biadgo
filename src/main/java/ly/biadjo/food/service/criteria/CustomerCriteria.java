package ly.biadjo.food.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.biadjo.food.domain.Customer} entity. This class is used
 * in {@link ly.biadjo.food.web.rest.CustomerResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /customers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomerCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter email;

    private StringFilter mobileNo;

    private StringFilter googleId;

    private StringFilter facebookId;

    private StringFilter appleId;

    private BooleanFilter isBanned;

    private BooleanFilter isVerified;

    private BooleanFilter verifiedByEmail;

    private BooleanFilter verifiedByMobileNo;

    private StringFilter walletPublicKey;

    private StringFilter imageUrl;

    private StringFilter languageCode;

    private StringFilter notes;

    private LongFilter userId;

    private Boolean distinct;

    public CustomerCriteria() {
    }

    public CustomerCriteria(CustomerCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.mobileNo = other.mobileNo == null ? null : other.mobileNo.copy();
        this.googleId = other.googleId == null ? null : other.googleId.copy();
        this.facebookId = other.facebookId == null ? null : other.facebookId.copy();
        this.appleId = other.appleId == null ? null : other.appleId.copy();
        this.isBanned = other.isBanned == null ? null : other.isBanned.copy();
        this.isVerified = other.isVerified == null ? null : other.isVerified.copy();
        this.verifiedByEmail = other.verifiedByEmail == null ? null : other.verifiedByEmail.copy();
        this.verifiedByMobileNo = other.verifiedByMobileNo == null ? null : other.verifiedByMobileNo.copy();
        this.walletPublicKey = other.walletPublicKey == null ? null : other.walletPublicKey.copy();
        this.imageUrl = other.imageUrl == null ? null : other.imageUrl.copy();
        this.languageCode = other.languageCode == null ? null : other.languageCode.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CustomerCriteria copy() {
        return new CustomerCriteria(this);
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

    public StringFilter getGoogleId() {
        return googleId;
    }

    public StringFilter googleId() {
        if (googleId == null) {
            googleId = new StringFilter();
        }
        return googleId;
    }

    public void setGoogleId(StringFilter googleId) {
        this.googleId = googleId;
    }

    public StringFilter getFacebookId() {
        return facebookId;
    }

    public StringFilter facebookId() {
        if (facebookId == null) {
            facebookId = new StringFilter();
        }
        return facebookId;
    }

    public void setFacebookId(StringFilter facebookId) {
        this.facebookId = facebookId;
    }

    public StringFilter getAppleId() {
        return appleId;
    }

    public StringFilter appleId() {
        if (appleId == null) {
            appleId = new StringFilter();
        }
        return appleId;
    }

    public void setAppleId(StringFilter appleId) {
        this.appleId = appleId;
    }

    public BooleanFilter getIsBanned() {
        return isBanned;
    }

    public BooleanFilter isBanned() {
        if (isBanned == null) {
            isBanned = new BooleanFilter();
        }
        return isBanned;
    }

    public void setIsBanned(BooleanFilter isBanned) {
        this.isBanned = isBanned;
    }

    public BooleanFilter getIsVerified() {
        return isVerified;
    }

    public BooleanFilter isVerified() {
        if (isVerified == null) {
            isVerified = new BooleanFilter();
        }
        return isVerified;
    }

    public void setIsVerified(BooleanFilter isVerified) {
        this.isVerified = isVerified;
    }

    public BooleanFilter getVerifiedByEmail() {
        return verifiedByEmail;
    }

    public BooleanFilter verifiedByEmail() {
        if (verifiedByEmail == null) {
            verifiedByEmail = new BooleanFilter();
        }
        return verifiedByEmail;
    }

    public void setVerifiedByEmail(BooleanFilter verifiedByEmail) {
        this.verifiedByEmail = verifiedByEmail;
    }

    public BooleanFilter getVerifiedByMobileNo() {
        return verifiedByMobileNo;
    }

    public BooleanFilter verifiedByMobileNo() {
        if (verifiedByMobileNo == null) {
            verifiedByMobileNo = new BooleanFilter();
        }
        return verifiedByMobileNo;
    }

    public void setVerifiedByMobileNo(BooleanFilter verifiedByMobileNo) {
        this.verifiedByMobileNo = verifiedByMobileNo;
    }

    public StringFilter getWalletPublicKey() {
        return walletPublicKey;
    }

    public StringFilter walletPublicKey() {
        if (walletPublicKey == null) {
            walletPublicKey = new StringFilter();
        }
        return walletPublicKey;
    }

    public void setWalletPublicKey(StringFilter walletPublicKey) {
        this.walletPublicKey = walletPublicKey;
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

    public StringFilter getLanguageCode() {
        return languageCode;
    }

    public StringFilter languageCode() {
        if (languageCode == null) {
            languageCode = new StringFilter();
        }
        return languageCode;
    }

    public void setLanguageCode(StringFilter languageCode) {
        this.languageCode = languageCode;
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

    public LongFilter getUserId() {
        return userId;
    }

    public LongFilter userId() {
        if (userId == null) {
            userId = new LongFilter();
        }
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
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
        final CustomerCriteria that = (CustomerCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(mobileNo, that.mobileNo) &&
                Objects.equals(googleId, that.googleId) &&
                Objects.equals(facebookId, that.facebookId) &&
                Objects.equals(appleId, that.appleId) &&
                Objects.equals(isBanned, that.isBanned) &&
                Objects.equals(isVerified, that.isVerified) &&
                Objects.equals(verifiedByEmail, that.verifiedByEmail) &&
                Objects.equals(verifiedByMobileNo, that.verifiedByMobileNo) &&
                Objects.equals(walletPublicKey, that.walletPublicKey) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(languageCode, that.languageCode) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            email,
            mobileNo,
            googleId,
            facebookId,
            appleId,
            isBanned,
            isVerified,
            verifiedByEmail,
            verifiedByMobileNo,
            walletPublicKey,
            imageUrl,
            languageCode,
            notes,
            userId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (mobileNo != null ? "mobileNo=" + mobileNo + ", " : "") +
            (googleId != null ? "googleId=" + googleId + ", " : "") +
            (facebookId != null ? "facebookId=" + facebookId + ", " : "") +
            (appleId != null ? "appleId=" + appleId + ", " : "") +
            (isBanned != null ? "isBanned=" + isBanned + ", " : "") +
            (isVerified != null ? "isVerified=" + isVerified + ", " : "") +
            (verifiedByEmail != null ? "verifiedByEmail=" + verifiedByEmail + ", " : "") +
            (verifiedByMobileNo != null ? "verifiedByMobileNo=" + verifiedByMobileNo + ", " : "") +
            (walletPublicKey != null ? "walletPublicKey=" + walletPublicKey + ", " : "") +
            (imageUrl != null ? "imageUrl=" + imageUrl + ", " : "") +
            (languageCode != null ? "languageCode=" + languageCode + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
