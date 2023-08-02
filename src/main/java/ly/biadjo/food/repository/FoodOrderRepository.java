package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;
import ly.biadjo.food.domain.FoodOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FoodOrder entity.
 */
@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long>, JpaSpecificationExecutor<FoodOrder> {
    default Optional<FoodOrder> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<FoodOrder> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<FoodOrder> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select foodOrder from FoodOrder foodOrder left join fetch foodOrder.order left join fetch foodOrder.food",
        countQuery = "select count(foodOrder) from FoodOrder foodOrder"
    )
    Page<FoodOrder> findAllWithToOneRelationships(Pageable pageable);

    @Query("select foodOrder from FoodOrder foodOrder left join fetch foodOrder.order left join fetch foodOrder.food")
    List<FoodOrder> findAllWithToOneRelationships();

    @Query(
        "select foodOrder from FoodOrder foodOrder left join fetch foodOrder.order left join fetch foodOrder.food where foodOrder.id =:id"
    )
    Optional<FoodOrder> findOneWithToOneRelationships(@Param("id") Long id);
}
