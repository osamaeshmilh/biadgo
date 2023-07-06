package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.RestaurantSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RestaurantSchedule entity.
 */
@Repository
public interface RestaurantScheduleRepository
    extends JpaRepository<RestaurantSchedule, Long>, JpaSpecificationExecutor<RestaurantSchedule> {
    default Optional<RestaurantSchedule> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<RestaurantSchedule> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<RestaurantSchedule> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select restaurantSchedule from RestaurantSchedule restaurantSchedule left join fetch restaurantSchedule.restaurant",
        countQuery = "select count(restaurantSchedule) from RestaurantSchedule restaurantSchedule"
    )
    Page<RestaurantSchedule> findAllWithToOneRelationships(Pageable pageable);

    @Query("select restaurantSchedule from RestaurantSchedule restaurantSchedule left join fetch restaurantSchedule.restaurant")
    List<RestaurantSchedule> findAllWithToOneRelationships();

    @Query(
        "select restaurantSchedule from RestaurantSchedule restaurantSchedule left join fetch restaurantSchedule.restaurant where restaurantSchedule.id =:id"
    )
    Optional<RestaurantSchedule> findOneWithToOneRelationships(@Param("id") Long id);
}
