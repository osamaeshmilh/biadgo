package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Food entity.
 */
@Repository
public interface FoodRepository extends JpaRepository<Food, Long>, JpaSpecificationExecutor<Food> {
    default Optional<Food> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Food> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Food> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select food from Food food left join fetch food.restaurant left join fetch food.category",
        countQuery = "select count(food) from Food food"
    )
    Page<Food> findAllWithToOneRelationships(Pageable pageable);

    @Query("select food from Food food left join fetch food.restaurant left join fetch food.category")
    List<Food> findAllWithToOneRelationships();

    @Query("select food from Food food left join fetch food.restaurant left join fetch food.category where food.id =:id")
    Optional<Food> findOneWithToOneRelationships(@Param("id") Long id);
}
