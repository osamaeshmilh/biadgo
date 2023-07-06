package ly.biadjo.food.domain;

import jakarta.persistence.*;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Slider.
 */
@Entity
@Table(name = "slider")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Slider implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "details")
    private String details;

    @Column(name = "details_ar")
    private String detailsAr;

    @Column(name = "details_en")
    private String detailsEn;

    @Column(name = "menu_order")
    private Integer menuOrder;

    @Column(name = "image_url")
    private String imageUrl;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "url")
    private String url;

    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "notes")
    private String notes;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Slider id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return this.details;
    }

    public Slider details(String details) {
        this.setDetails(details);
        return this;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetailsAr() {
        return this.detailsAr;
    }

    public Slider detailsAr(String detailsAr) {
        this.setDetailsAr(detailsAr);
        return this;
    }

    public void setDetailsAr(String detailsAr) {
        this.detailsAr = detailsAr;
    }

    public String getDetailsEn() {
        return this.detailsEn;
    }

    public Slider detailsEn(String detailsEn) {
        this.setDetailsEn(detailsEn);
        return this;
    }

    public void setDetailsEn(String detailsEn) {
        this.detailsEn = detailsEn;
    }

    public Integer getMenuOrder() {
        return this.menuOrder;
    }

    public Slider menuOrder(Integer menuOrder) {
        this.setMenuOrder(menuOrder);
        return this;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Slider imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public byte[] getImage() {
        return this.image;
    }

    public Slider image(byte[] image) {
        this.setImage(image);
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return this.imageContentType;
    }

    public Slider imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getUrl() {
        return this.url;
    }

    public Slider url(String url) {
        this.setUrl(url);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRestaurantId() {
        return this.restaurantId;
    }

    public Slider restaurantId(Integer restaurantId) {
        this.setRestaurantId(restaurantId);
        return this;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public Slider categoryId(Integer categoryId) {
        this.setCategoryId(categoryId);
        return this;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getFoodId() {
        return this.foodId;
    }

    public Slider foodId(Integer foodId) {
        this.setFoodId(foodId);
        return this;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getNotes() {
        return this.notes;
    }

    public Slider notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Slider)) {
            return false;
        }
        return id != null && id.equals(((Slider) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Slider{" +
            "id=" + getId() +
            ", details='" + getDetails() + "'" +
            ", detailsAr='" + getDetailsAr() + "'" +
            ", detailsEn='" + getDetailsEn() + "'" +
            ", menuOrder=" + getMenuOrder() +
            ", imageUrl='" + getImageUrl() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", url='" + getUrl() + "'" +
            ", restaurantId=" + getRestaurantId() +
            ", categoryId=" + getCategoryId() +
            ", foodId=" + getFoodId() +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
