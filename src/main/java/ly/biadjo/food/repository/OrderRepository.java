package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Order entity.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    default Optional<Order> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Order> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Order> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select jhiOrder from Order jhiOrder left join fetch jhiOrder.customer left join fetch jhiOrder.coupon left join fetch jhiOrder.driver left join fetch jhiOrder.deliveryAddress left join fetch jhiOrder.restaurant left join fetch jhiOrder.foodOrders",
        countQuery = "select count(jhiOrder) from Order jhiOrder"
    )
    Page<Order> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select jhiOrder from Order jhiOrder left join fetch jhiOrder.customer left join fetch jhiOrder.coupon left join fetch jhiOrder.driver left join fetch jhiOrder.deliveryAddress left join fetch jhiOrder.restaurant left join fetch jhiOrder.foodOrders"
    )
    List<Order> findAllWithToOneRelationships();

    @Query(
        "select jhiOrder from Order jhiOrder left join fetch jhiOrder.customer left join fetch jhiOrder.coupon left join fetch jhiOrder.driver left join fetch jhiOrder.deliveryAddress left join fetch jhiOrder.restaurant left join fetch jhiOrder.foodOrders where jhiOrder.id =:id"
    )
    Optional<Order> findOneWithToOneRelationships(@Param("id") Long id);
}
