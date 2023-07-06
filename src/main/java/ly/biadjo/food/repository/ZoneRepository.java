package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Zone entity.
 */
@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long>, JpaSpecificationExecutor<Zone> {
    default Optional<Zone> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Zone> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Zone> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(value = "select zone from Zone zone left join fetch zone.city", countQuery = "select count(zone) from Zone zone")
    Page<Zone> findAllWithToOneRelationships(Pageable pageable);

    @Query("select zone from Zone zone left join fetch zone.city")
    List<Zone> findAllWithToOneRelationships();

    @Query("select zone from Zone zone left join fetch zone.city where zone.id =:id")
    Optional<Zone> findOneWithToOneRelationships(@Param("id") Long id);
}
