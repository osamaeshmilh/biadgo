package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.RestaurantImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RestaurantImage entity.
 */
@Repository
public interface RestaurantImageRepository extends JpaRepository<RestaurantImage, Long>, JpaSpecificationExecutor<RestaurantImage> {
    default Optional<RestaurantImage> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<RestaurantImage> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<RestaurantImage> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select restaurantImage from RestaurantImage restaurantImage left join fetch restaurantImage.restaurant",
        countQuery = "select count(restaurantImage) from RestaurantImage restaurantImage"
    )
    Page<RestaurantImage> findAllWithToOneRelationships(Pageable pageable);

    @Query("select restaurantImage from RestaurantImage restaurantImage left join fetch restaurantImage.restaurant")
    List<RestaurantImage> findAllWithToOneRelationships();

    @Query(
        "select restaurantImage from RestaurantImage restaurantImage left join fetch restaurantImage.restaurant where restaurantImage.id =:id"
    )
    Optional<RestaurantImage> findOneWithToOneRelationships(@Param("id") Long id);
}
