package ly.biadjo.food.repository;

import ly.biadjo.food.domain.Cuisine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Cuisine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long>, JpaSpecificationExecutor<Cuisine> {
}
