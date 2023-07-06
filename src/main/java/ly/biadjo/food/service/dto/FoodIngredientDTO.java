package ly.biadjo.food.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.FoodIngredient} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FoodIngredientDTO implements Serializable {

    private Long id;

    private String name;

    private String nameAr;

    private String nameEn;

    private String notes;

    private FoodDTO food;

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

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public FoodDTO getFood() {
        return food;
    }

    public void setFood(FoodDTO food) {
        this.food = food;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodIngredientDTO)) {
            return false;
        }

        FoodIngredientDTO foodIngredientDTO = (FoodIngredientDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, foodIngredientDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodIngredientDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", notes='" + getNotes() + "'" +
            ", food=" + getFood() +
            "}";
    }
}
