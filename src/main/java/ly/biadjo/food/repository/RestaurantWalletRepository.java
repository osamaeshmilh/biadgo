package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.RestaurantWallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RestaurantWallet entity.
 */
@Repository
public interface RestaurantWalletRepository extends JpaRepository<RestaurantWallet, Long>, JpaSpecificationExecutor<RestaurantWallet> {
    default Optional<RestaurantWallet> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<RestaurantWallet> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<RestaurantWallet> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select restaurantWallet from RestaurantWallet restaurantWallet left join fetch restaurantWallet.restaurant",
        countQuery = "select count(restaurantWallet) from RestaurantWallet restaurantWallet"
    )
    Page<RestaurantWallet> findAllWithToOneRelationships(Pageable pageable);

    @Query("select restaurantWallet from RestaurantWallet restaurantWallet left join fetch restaurantWallet.restaurant")
    List<RestaurantWallet> findAllWithToOneRelationships();

    @Query(
        "select restaurantWallet from RestaurantWallet restaurantWallet left join fetch restaurantWallet.restaurant where restaurantWallet.id =:id"
    )
    Optional<RestaurantWallet> findOneWithToOneRelationships(@Param("id") Long id);
}
