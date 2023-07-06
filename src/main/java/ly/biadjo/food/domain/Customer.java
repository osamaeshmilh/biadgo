package ly.biadjo.food.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "google_id")
    private String googleId;

    @Column(name = "facebook_id")
    private String facebookId;

    @Column(name = "apple_id")
    private String appleId;

    @Column(name = "is_banned")
    private Boolean isBanned;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "verified_by_email")
    private Boolean verifiedByEmail;

    @Column(name = "verified_by_mobile_no")
    private Boolean verifiedByMobileNo;

    @Column(name = "wallet_public_key")
    private String walletPublicKey;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "language_code")
    private String languageCode;

    @Column(name = "notes")
    private String notes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Customer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Customer name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public Customer email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public Customer mobileNo(String mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getGoogleId() {
        return this.googleId;
    }

    public Customer googleId(String googleId) {
        this.setGoogleId(googleId);
        return this;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getFacebookId() {
        return this.facebookId;
    }

    public Customer facebookId(String facebookId) {
        this.setFacebookId(facebookId);
        return this;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getAppleId() {
        return this.appleId;
    }

    public Customer appleId(String appleId) {
        this.setAppleId(appleId);
        return this;
    }

    public void setAppleId(String appleId) {
        this.appleId = appleId;
    }

    public Boolean getIsBanned() {
        return this.isBanned;
    }

    public Customer isBanned(Boolean isBanned) {
        this.setIsBanned(isBanned);
        return this;
    }

    public void setIsBanned(Boolean isBanned) {
        this.isBanned = isBanned;
    }

    public Boolean getIsVerified() {
        return this.isVerified;
    }

    public Customer isVerified(Boolean isVerified) {
        this.setIsVerified(isVerified);
        return this;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Boolean getVerifiedByEmail() {
        return this.verifiedByEmail;
    }

    public Customer verifiedByEmail(Boolean verifiedByEmail) {
        this.setVerifiedByEmail(verifiedByEmail);
        return this;
    }

    public void setVerifiedByEmail(Boolean verifiedByEmail) {
        this.verifiedByEmail = verifiedByEmail;
    }

    public Boolean getVerifiedByMobileNo() {
        return this.verifiedByMobileNo;
    }

    public Customer verifiedByMobileNo(Boolean verifiedByMobileNo) {
        this.setVerifiedByMobileNo(verifiedByMobileNo);
        return this;
    }

    public void setVerifiedByMobileNo(Boolean verifiedByMobileNo) {
        this.verifiedByMobileNo = verifiedByMobileNo;
    }

    public String getWalletPublicKey() {
        return this.walletPublicKey;
    }

    public Customer walletPublicKey(String walletPublicKey) {
        this.setWalletPublicKey(walletPublicKey);
        return this;
    }

    public void setWalletPublicKey(String walletPublicKey) {
        this.walletPublicKey = walletPublicKey;
    }

    public byte[] getImage() {
        return this.image;
    }

    public Customer image(byte[] image) {
        this.setImage(image);
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return this.imageContentType;
    }

    public Customer imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Customer imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLanguageCode() {
        return this.languageCode;
    }

    public Customer languageCode(String languageCode) {
        this.setLanguageCode(languageCode);
        return this;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getNotes() {
        return this.notes;
    }

    public Customer notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer user(User user) {
        this.setUser(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", googleId='" + getGoogleId() + "'" +
            ", facebookId='" + getFacebookId() + "'" +
            ", appleId='" + getAppleId() + "'" +
            ", isBanned='" + getIsBanned() + "'" +
            ", isVerified='" + getIsVerified() + "'" +
            ", verifiedByEmail='" + getVerifiedByEmail() + "'" +
            ", verifiedByMobileNo='" + getVerifiedByMobileNo() + "'" +
            ", walletPublicKey='" + getWalletPublicKey() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", languageCode='" + getLanguageCode() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
