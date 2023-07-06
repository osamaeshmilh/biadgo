package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Restaurant entity.
 * <p>
 * When extending this class, extend RestaurantRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface RestaurantRepository
    extends RestaurantRepositoryWithBagRelationships, JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {
    default Optional<Restaurant> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findOneWithToOneRelationships(id));
    }

    default List<Restaurant> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAllWithToOneRelationships());
    }

    default Page<Restaurant> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAllWithToOneRelationships(pageable));
    }

    @Query(
        value = "select restaurant from Restaurant restaurant left join fetch restaurant.cuisine",
        countQuery = "select count(restaurant) from Restaurant restaurant"
    )
    Page<Restaurant> findAllWithToOneRelationships(Pageable pageable);

    @Query("select restaurant from Restaurant restaurant left join fetch restaurant.cuisine")
    List<Restaurant> findAllWithToOneRelationships();

    @Query("select restaurant from Restaurant restaurant left join fetch restaurant.cuisine where restaurant.id =:id")
    Optional<Restaurant> findOneWithToOneRelationships(@Param("id") Long id);
}
