package ly.biadjo.food.repository;

import ly.biadjo.food.domain.Referral;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Referral entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReferralRepository extends JpaRepository<Referral, Long>, JpaSpecificationExecutor<Referral> {
}
