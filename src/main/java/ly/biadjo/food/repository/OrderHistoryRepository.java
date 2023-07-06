package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.OrderHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OrderHistory entity.
 */
@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long>, JpaSpecificationExecutor<OrderHistory> {
    default Optional<OrderHistory> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<OrderHistory> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<OrderHistory> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select orderHistory from OrderHistory orderHistory left join fetch orderHistory.order",
        countQuery = "select count(orderHistory) from OrderHistory orderHistory"
    )
    Page<OrderHistory> findAllWithToOneRelationships(Pageable pageable);

    @Query("select orderHistory from OrderHistory orderHistory left join fetch orderHistory.order")
    List<OrderHistory> findAllWithToOneRelationships();

    @Query("select orderHistory from OrderHistory orderHistory left join fetch orderHistory.order where orderHistory.id =:id")
    Optional<OrderHistory> findOneWithToOneRelationships(@Param("id") Long id);
}
