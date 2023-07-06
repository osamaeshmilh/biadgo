package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.RestaurantZonePrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RestaurantZonePrice entity.
 */
@Repository
public interface RestaurantZonePriceRepository
    extends JpaRepository<RestaurantZonePrice, Long>, JpaSpecificationExecutor<RestaurantZonePrice> {
    default Optional<RestaurantZonePrice> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<RestaurantZonePrice> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<RestaurantZonePrice> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select restaurantZonePrice from RestaurantZonePrice restaurantZonePrice left join fetch restaurantZonePrice.restaurant left join fetch restaurantZonePrice.zone",
        countQuery = "select count(restaurantZonePrice) from RestaurantZonePrice restaurantZonePrice"
    )
    Page<RestaurantZonePrice> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select restaurantZonePrice from RestaurantZonePrice restaurantZonePrice left join fetch restaurantZonePrice.restaurant left join fetch restaurantZonePrice.zone"
    )
    List<RestaurantZonePrice> findAllWithToOneRelationships();

    @Query(
        "select restaurantZonePrice from RestaurantZonePrice restaurantZonePrice left join fetch restaurantZonePrice.restaurant left join fetch restaurantZonePrice.zone where restaurantZonePrice.id =:id"
    )
    Optional<RestaurantZonePrice> findOneWithToOneRelationships(@Param("id") Long id);
}
