package ly.biadjo.food.service.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.biadjo.food.domain.Cuisine} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CuisineDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String nameAr;

    private String nameEn;

    private Boolean isActive;

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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CuisineDTO)) {
            return false;
        }

        CuisineDTO cuisineDTO = (CuisineDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cuisineDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CuisineDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", isActive='" + getIsActive() + "'" +
            "}";
    }
}
