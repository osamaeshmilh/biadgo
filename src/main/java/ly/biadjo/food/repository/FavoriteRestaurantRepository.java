package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.FavoriteRestaurant;
import ly.biadjo.food.service.dto.FavoriteRestaurantDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FavoriteRestaurant entity.
 */
@Repository
public interface FavoriteRestaurantRepository
    extends JpaRepository<FavoriteRestaurant, Long>, JpaSpecificationExecutor<FavoriteRestaurant> {
    default Optional<FavoriteRestaurant> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<FavoriteRestaurant> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<FavoriteRestaurant> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select favoriteRestaurant from FavoriteRestaurant favoriteRestaurant left join fetch favoriteRestaurant.customer left join fetch favoriteRestaurant.restaurant",
        countQuery = "select count(favoriteRestaurant) from FavoriteRestaurant favoriteRestaurant"
    )
    Page<FavoriteRestaurant> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select favoriteRestaurant from FavoriteRestaurant favoriteRestaurant left join fetch favoriteRestaurant.customer left join fetch favoriteRestaurant.restaurant"
    )
    List<FavoriteRestaurant> findAllWithToOneRelationships();

    @Query(
        "select favoriteRestaurant from FavoriteRestaurant favoriteRestaurant left join fetch favoriteRestaurant.customer left join fetch favoriteRestaurant.restaurant where favoriteRestaurant.id =:id"
    )
    Optional<FavoriteRestaurant> findOneWithToOneRelationships(@Param("id") Long id);

    Optional<FavoriteRestaurant> findOneByCustomerIdAndRestaurantId(Long customerId, Long restaurantId);
}
