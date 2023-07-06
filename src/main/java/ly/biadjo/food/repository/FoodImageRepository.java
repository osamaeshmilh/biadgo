package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.FoodImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FoodImage entity.
 */
@Repository
public interface FoodImageRepository extends JpaRepository<FoodImage, Long>, JpaSpecificationExecutor<FoodImage> {
    default Optional<FoodImage> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<FoodImage> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<FoodImage> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select foodImage from FoodImage foodImage left join fetch foodImage.food",
        countQuery = "select count(foodImage) from FoodImage foodImage"
    )
    Page<FoodImage> findAllWithToOneRelationships(Pageable pageable);

    @Query("select foodImage from FoodImage foodImage left join fetch foodImage.food")
    List<FoodImage> findAllWithToOneRelationships();

    @Query("select foodImage from FoodImage foodImage left join fetch foodImage.food where foodImage.id =:id")
    Optional<FoodImage> findOneWithToOneRelationships(@Param("id") Long id);
}
