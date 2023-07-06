package ly.biadjo.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Food.
 */
@Entity
@Table(name = "food")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Food extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_ar")
    private String nameAr;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount_price")
    private Double discountPrice;

    @Column(name = "description")
    private String description;

    @Column(name = "description_ar")
    private String descriptionAr;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "package_items_count")
    private Double packageItemsCount;

    @Column(name = "daily_quantity")
    private Integer dailyQuantity;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name = "is_discount")
    private Boolean isDiscount;

    @Column(name = "is_featured")
    private Boolean isFeatured;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "view_counter")
    private Integer viewCounter;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user", "cuisine", "categories"}, allowSetters = true)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"restaurants"}, allowSetters = true)
    private Category category;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Food id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Food name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAr() {
        return this.nameAr;
    }

    public Food nameAr(String nameAr) {
        this.setNameAr(nameAr);
        return this;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public Food nameEn(String nameEn) {
        this.setNameEn(nameEn);
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Double getPrice() {
        return this.price;
    }

    public Food price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountPrice() {
        return this.discountPrice;
    }

    public Food discountPrice(Double discountPrice) {
        this.setDiscountPrice(discountPrice);
        return this;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDescription() {
        return this.description;
    }

    public Food description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionAr() {
        return this.descriptionAr;
    }

    public Food descriptionAr(String descriptionAr) {
        this.setDescriptionAr(descriptionAr);
        return this;
    }

    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    public String getDescriptionEn() {
        return this.descriptionEn;
    }

    public Food descriptionEn(String descriptionEn) {
        this.setDescriptionEn(descriptionEn);
        return this;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public Double getPackageItemsCount() {
        return this.packageItemsCount;
    }

    public Food packageItemsCount(Double packageItemsCount) {
        this.setPackageItemsCount(packageItemsCount);
        return this;
    }

    public void setPackageItemsCount(Double packageItemsCount) {
        this.packageItemsCount = packageItemsCount;
    }

    public Integer getDailyQuantity() {
        return this.dailyQuantity;
    }

    public Food dailyQuantity(Integer dailyQuantity) {
        this.setDailyQuantity(dailyQuantity);
        return this;
    }

    public void setDailyQuantity(Integer dailyQuantity) {
        this.dailyQuantity = dailyQuantity;
    }

    public Boolean getIsAvailable() {
        return this.isAvailable;
    }

    public Food isAvailable(Boolean isAvailable) {
        this.setIsAvailable(isAvailable);
        return this;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Boolean getIsDiscount() {
        return this.isDiscount;
    }

    public Food isDiscount(Boolean isDiscount) {
        this.setIsDiscount(isDiscount);
        return this;
    }

    public void setIsDiscount(Boolean isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Boolean getIsFeatured() {
        return this.isFeatured;
    }

    public Food isFeatured(Boolean isFeatured) {
        this.setIsFeatured(isFeatured);
        return this;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Food isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getViewCounter() {
        return this.viewCounter;
    }

    public Food viewCounter(Integer viewCounter) {
        this.setViewCounter(viewCounter);
        return this;
    }

    public void setViewCounter(Integer viewCounter) {
        this.viewCounter = viewCounter;
    }

    public String getNotes() {
        return this.notes;
    }

    public Food notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Food restaurant(Restaurant restaurant) {
        this.setRestaurant(restaurant);
        return this;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Food category(Category category) {
        this.setCategory(category);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Food)) {
            return false;
        }
        return id != null && id.equals(((Food) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Food{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", price=" + getPrice() +
            ", discountPrice=" + getDiscountPrice() +
            ", description='" + getDescription() + "'" +
            ", descriptionAr='" + getDescriptionAr() + "'" +
            ", descriptionEn='" + getDescriptionEn() + "'" +
            ", packageItemsCount=" + getPackageItemsCount() +
            ", dailyQuantity=" + getDailyQuantity() +
            ", isAvailable='" + getIsAvailable() + "'" +
            ", isDiscount='" + getIsDiscount() + "'" +
            ", isFeatured='" + getIsFeatured() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", viewCounter=" + getViewCounter() +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
