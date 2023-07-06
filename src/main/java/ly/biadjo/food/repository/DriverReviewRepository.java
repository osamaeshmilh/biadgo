package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.DriverReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DriverReview entity.
 */
@Repository
public interface DriverReviewRepository extends JpaRepository<DriverReview, Long>, JpaSpecificationExecutor<DriverReview> {
    default Optional<DriverReview> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<DriverReview> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<DriverReview> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select driverReview from DriverReview driverReview left join fetch driverReview.customer left join fetch driverReview.driver",
        countQuery = "select count(driverReview) from DriverReview driverReview"
    )
    Page<DriverReview> findAllWithToOneRelationships(Pageable pageable);

    @Query("select driverReview from DriverReview driverReview left join fetch driverReview.customer left join fetch driverReview.driver")
    List<DriverReview> findAllWithToOneRelationships();

    @Query(
        "select driverReview from DriverReview driverReview left join fetch driverReview.customer left join fetch driverReview.driver where driverReview.id =:id"
    )
    Optional<DriverReview> findOneWithToOneRelationships(@Param("id") Long id);
}
