package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.RestaurantDistancePrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RestaurantDistancePrice entity.
 */
@Repository
public interface RestaurantDistancePriceRepository
    extends JpaRepository<RestaurantDistancePrice, Long>, JpaSpecificationExecutor<RestaurantDistancePrice> {
    default Optional<RestaurantDistancePrice> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<RestaurantDistancePrice> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<RestaurantDistancePrice> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select restaurantDistancePrice from RestaurantDistancePrice restaurantDistancePrice left join fetch restaurantDistancePrice.restaurant",
        countQuery = "select count(restaurantDistancePrice) from RestaurantDistancePrice restaurantDistancePrice"
    )
    Page<RestaurantDistancePrice> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select restaurantDistancePrice from RestaurantDistancePrice restaurantDistancePrice left join fetch restaurantDistancePrice.restaurant"
    )
    List<RestaurantDistancePrice> findAllWithToOneRelationships();

    @Query(
        "select restaurantDistancePrice from RestaurantDistancePrice restaurantDistancePrice left join fetch restaurantDistancePrice.restaurant where restaurantDistancePrice.id =:id"
    )
    Optional<RestaurantDistancePrice> findOneWithToOneRelationships(@Param("id") Long id);
}
