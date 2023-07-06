package ly.biadjo.food.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Activation.
 */
@Entity
@Table(name = "activation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Activation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "email")
    private String email;

    @Column(name = "code")
    private String code;

    @Column(name = "sent_on")
    private Instant sentOn;

    @Column(name = "valid_until")
    private Instant validUntil;

    @Column(name = "is_used")
    private Boolean isUsed;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Activation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public Activation mobileNo(String mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return this.email;
    }

    public Activation email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return this.code;
    }

    public Activation code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getSentOn() {
        return this.sentOn;
    }

    public Activation sentOn(Instant sentOn) {
        this.setSentOn(sentOn);
        return this;
    }

    public void setSentOn(Instant sentOn) {
        this.sentOn = sentOn;
    }

    public Instant getValidUntil() {
        return this.validUntil;
    }

    public Activation validUntil(Instant validUntil) {
        this.setValidUntil(validUntil);
        return this;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public Boolean getIsUsed() {
        return this.isUsed;
    }

    public Activation isUsed(Boolean isUsed) {
        this.setIsUsed(isUsed);
        return this;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Activation)) {
            return false;
        }
        return id != null && id.equals(((Activation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Activation{" +
            "id=" + getId() +
            ", mobileNo='" + getMobileNo() + "'" +
            ", email='" + getEmail() + "'" +
            ", code='" + getCode() + "'" +
            ", sentOn='" + getSentOn() + "'" +
            ", validUntil='" + getValidUntil() + "'" +
            ", isUsed='" + getIsUsed() + "'" +
            "}";
    }
}
