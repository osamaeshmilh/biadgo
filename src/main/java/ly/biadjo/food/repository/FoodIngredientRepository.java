package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.FoodIngredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FoodIngredient entity.
 */
@Repository
public interface FoodIngredientRepository extends JpaRepository<FoodIngredient, Long>, JpaSpecificationExecutor<FoodIngredient> {
    default Optional<FoodIngredient> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<FoodIngredient> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<FoodIngredient> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select foodIngredient from FoodIngredient foodIngredient left join fetch foodIngredient.food",
        countQuery = "select count(foodIngredient) from FoodIngredient foodIngredient"
    )
    Page<FoodIngredient> findAllWithToOneRelationships(Pageable pageable);

    @Query("select foodIngredient from FoodIngredient foodIngredient left join fetch foodIngredient.food")
    List<FoodIngredient> findAllWithToOneRelationships();

    @Query("select foodIngredient from FoodIngredient foodIngredient left join fetch foodIngredient.food where foodIngredient.id =:id")
    Optional<FoodIngredient> findOneWithToOneRelationships(@Param("id") Long id);
}
