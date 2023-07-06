package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.DriverWallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DriverWallet entity.
 */
@Repository
public interface DriverWalletRepository extends JpaRepository<DriverWallet, Long>, JpaSpecificationExecutor<DriverWallet> {
    default Optional<DriverWallet> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<DriverWallet> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<DriverWallet> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select driverWallet from DriverWallet driverWallet left join fetch driverWallet.driver",
        countQuery = "select count(driverWallet) from DriverWallet driverWallet"
    )
    Page<DriverWallet> findAllWithToOneRelationships(Pageable pageable);

    @Query("select driverWallet from DriverWallet driverWallet left join fetch driverWallet.driver")
    List<DriverWallet> findAllWithToOneRelationships();

    @Query("select driverWallet from DriverWallet driverWallet left join fetch driverWallet.driver where driverWallet.id =:id")
    Optional<DriverWallet> findOneWithToOneRelationships(@Param("id") Long id);
}
