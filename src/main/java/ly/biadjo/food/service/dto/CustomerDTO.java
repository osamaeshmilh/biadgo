package ly.biadjo.food.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.Customer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomerDTO implements Serializable {

    private Long id;

    private String name;

    private String email;

    private String mobileNo;

    private String googleId;

    private String facebookId;

    private String appleId;

    private Boolean isBanned;

    private Boolean isVerified;

    private Boolean verifiedByEmail;

    private Boolean verifiedByMobileNo;

    private String walletPublicKey;

    @Lob
    private byte[] image;

    private String imageContentType;
    private String imageUrl;

    private String languageCode;

    private String notes;

    private UserDTO user;

    private String newPassword;

    private String otp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getAppleId() {
        return appleId;
    }

    public void setAppleId(String appleId) {
        this.appleId = appleId;
    }

    public Boolean getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(Boolean isBanned) {
        this.isBanned = isBanned;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Boolean getVerifiedByEmail() {
        return verifiedByEmail;
    }

    public void setVerifiedByEmail(Boolean verifiedByEmail) {
        this.verifiedByEmail = verifiedByEmail;
    }

    public Boolean getVerifiedByMobileNo() {
        return verifiedByMobileNo;
    }

    public void setVerifiedByMobileNo(Boolean verifiedByMobileNo) {
        this.verifiedByMobileNo = verifiedByMobileNo;
    }

    public String getWalletPublicKey() {
        return walletPublicKey;
    }

    public void setWalletPublicKey(String walletPublicKey) {
        this.walletPublicKey = walletPublicKey;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerDTO)) {
            return false;
        }

        CustomerDTO customerDTO = (CustomerDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, customerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerDTO{" +
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
            ", imageUrl='" + getImageUrl() + "'" +
            ", languageCode='" + getLanguageCode() + "'" +
            ", notes='" + getNotes() + "'" +
            ", user=" + getUser() +
            "}";
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
