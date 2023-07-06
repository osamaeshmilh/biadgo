package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.DriverLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DriverLocation entity.
 */
@Repository
public interface DriverLocationRepository extends JpaRepository<DriverLocation, Long>, JpaSpecificationExecutor<DriverLocation> {
    default Optional<DriverLocation> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<DriverLocation> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<DriverLocation> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select driverLocation from DriverLocation driverLocation left join fetch driverLocation.driver",
        countQuery = "select count(driverLocation) from DriverLocation driverLocation"
    )
    Page<DriverLocation> findAllWithToOneRelationships(Pageable pageable);

    @Query("select driverLocation from DriverLocation driverLocation left join fetch driverLocation.driver")
    List<DriverLocation> findAllWithToOneRelationships();

    @Query("select driverLocation from DriverLocation driverLocation left join fetch driverLocation.driver where driverLocation.id =:id")
    Optional<DriverLocation> findOneWithToOneRelationships(@Param("id") Long id);
}
