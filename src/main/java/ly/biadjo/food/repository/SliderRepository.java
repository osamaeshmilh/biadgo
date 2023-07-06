package ly.biadjo.food.repository;

import ly.biadjo.food.domain.Slider;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Slider entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SliderRepository extends JpaRepository<Slider, Long>, JpaSpecificationExecutor<Slider> {
}
