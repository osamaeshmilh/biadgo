package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.FoodExtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FoodExtra entity.
 */
@Repository
public interface FoodExtraRepository extends JpaRepository<FoodExtra, Long>, JpaSpecificationExecutor<FoodExtra> {
    default Optional<FoodExtra> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<FoodExtra> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<FoodExtra> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select foodExtra from FoodExtra foodExtra left join fetch foodExtra.food",
        countQuery = "select count(foodExtra) from FoodExtra foodExtra"
    )
    Page<FoodExtra> findAllWithToOneRelationships(Pageable pageable);

    @Query("select foodExtra from FoodExtra foodExtra left join fetch foodExtra.food")
    List<FoodExtra> findAllWithToOneRelationships();

    @Query("select foodExtra from FoodExtra foodExtra left join fetch foodExtra.food where foodExtra.id =:id")
    Optional<FoodExtra> findOneWithToOneRelationships(@Param("id") Long id);
}
