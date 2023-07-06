package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.CustomerWallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CustomerWallet entity.
 */
@Repository
public interface CustomerWalletRepository extends JpaRepository<CustomerWallet, Long>, JpaSpecificationExecutor<CustomerWallet> {
    default Optional<CustomerWallet> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<CustomerWallet> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<CustomerWallet> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select customerWallet from CustomerWallet customerWallet left join fetch customerWallet.customer",
        countQuery = "select count(customerWallet) from CustomerWallet customerWallet"
    )
    Page<CustomerWallet> findAllWithToOneRelationships(Pageable pageable);

    @Query("select customerWallet from CustomerWallet customerWallet left join fetch customerWallet.customer")
    List<CustomerWallet> findAllWithToOneRelationships();

    @Query("select customerWallet from CustomerWallet customerWallet left join fetch customerWallet.customer where customerWallet.id =:id")
    Optional<CustomerWallet> findOneWithToOneRelationships(@Param("id") Long id);
}
