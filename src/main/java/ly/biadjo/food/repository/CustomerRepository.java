package ly.biadjo.food.repository;

import ly.biadjo.food.domain.Customer;
import ly.biadjo.food.domain.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the Customer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    Customer findByUser(User user);

    Optional<Customer> findTopByWalletPublicKey(String walletPublicKey);

    Optional<Customer> findFirstByMobileNoContaining(String mobileNo);

    Optional<Customer> findFirstByEmail(String email);
}
