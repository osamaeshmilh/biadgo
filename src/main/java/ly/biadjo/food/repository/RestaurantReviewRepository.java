package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.RestaurantReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RestaurantReview entity.
 */
@Repository
public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Long>, JpaSpecificationExecutor<RestaurantReview> {
    default Optional<RestaurantReview> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<RestaurantReview> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<RestaurantReview> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select restaurantReview from RestaurantReview restaurantReview left join fetch restaurantReview.customer left join fetch restaurantReview.restaurant",
        countQuery = "select count(restaurantReview) from RestaurantReview restaurantReview"
    )
    Page<RestaurantReview> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select restaurantReview from RestaurantReview restaurantReview left join fetch restaurantReview.customer left join fetch restaurantReview.restaurant"
    )
    List<RestaurantReview> findAllWithToOneRelationships();

    @Query(
        "select restaurantReview from RestaurantReview restaurantReview left join fetch restaurantReview.customer left join fetch restaurantReview.restaurant where restaurantReview.id =:id"
    )
    Optional<RestaurantReview> findOneWithToOneRelationships(@Param("id") Long id);
}
