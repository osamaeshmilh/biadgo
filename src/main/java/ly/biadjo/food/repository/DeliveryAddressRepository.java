package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.DeliveryAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DeliveryAddress entity.
 */
@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long>, JpaSpecificationExecutor<DeliveryAddress> {
    default Optional<DeliveryAddress> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<DeliveryAddress> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<DeliveryAddress> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select deliveryAddress from DeliveryAddress deliveryAddress left join fetch deliveryAddress.customer left join fetch deliveryAddress.zone",
        countQuery = "select count(deliveryAddress) from DeliveryAddress deliveryAddress"
    )
    Page<DeliveryAddress> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select deliveryAddress from DeliveryAddress deliveryAddress left join fetch deliveryAddress.customer left join fetch deliveryAddress.zone"
    )
    List<DeliveryAddress> findAllWithToOneRelationships();

    @Query(
        "select deliveryAddress from DeliveryAddress deliveryAddress left join fetch deliveryAddress.customer left join fetch deliveryAddress.zone where deliveryAddress.id =:id"
    )
    Optional<DeliveryAddress> findOneWithToOneRelationships(@Param("id") Long id);
}
