package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.RestaurantWallet;
import ly.biadjo.food.repository.RestaurantWalletRepository;
import ly.biadjo.food.service.criteria.RestaurantWalletCriteria;
import ly.biadjo.food.service.dto.RestaurantWalletDTO;
import ly.biadjo.food.service.mapper.RestaurantWalletMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RestaurantWallet} entities in the database.
 * The main input is a {@link RestaurantWalletCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RestaurantWalletDTO} or a {@link Page} of {@link RestaurantWalletDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RestaurantWalletQueryService extends QueryService<RestaurantWallet> {

    private final Logger log = LoggerFactory.getLogger(RestaurantWalletQueryService.class);

    private final RestaurantWalletRepository restaurantWalletRepository;

    private final RestaurantWalletMapper restaurantWalletMapper;

    public RestaurantWalletQueryService(
        RestaurantWalletRepository restaurantWalletRepository,
        RestaurantWalletMapper restaurantWalletMapper
    ) {
        this.restaurantWalletRepository = restaurantWalletRepository;
        this.restaurantWalletMapper = restaurantWalletMapper;
    }

    /**
     * Return a {@link List} of {@link RestaurantWalletDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RestaurantWalletDTO> findByCriteria(RestaurantWalletCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RestaurantWallet> specification = createSpecification(criteria);
        return restaurantWalletMapper.toDto(restaurantWalletRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RestaurantWalletDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RestaurantWalletDTO> findByCriteria(RestaurantWalletCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RestaurantWallet> specification = createSpecification(criteria);
        return restaurantWalletRepository.findAll(specification, page).map(restaurantWalletMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RestaurantWalletCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RestaurantWallet> specification = createSpecification(criteria);
        return restaurantWalletRepository.count(specification);
    }

    /**
     * Function to convert {@link RestaurantWalletCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RestaurantWallet> createSpecification(RestaurantWalletCriteria criteria) {
        Specification<RestaurantWallet> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RestaurantWallet_.id));
            }
            if (criteria.getTransactionNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTransactionNo(), RestaurantWallet_.transactionNo));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), RestaurantWallet_.amount));
            }
            if (criteria.getWalletAction() != null) {
                specification = specification.and(buildSpecification(criteria.getWalletAction(), RestaurantWallet_.walletAction));
            }
            if (criteria.getTotalBeforeAction() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTotalBeforeAction(), RestaurantWallet_.totalBeforeAction));
            }
            if (criteria.getTotalAfterAction() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTotalAfterAction(), RestaurantWallet_.totalAfterAction));
            }
            if (criteria.getPaymentType() != null) {
                specification = specification.and(buildSpecification(criteria.getPaymentType(), RestaurantWallet_.paymentType));
            }
            if (criteria.getPaymentReference() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getPaymentReference(), RestaurantWallet_.paymentReference));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), RestaurantWallet_.notes));
            }
            if (criteria.getRestaurantId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRestaurantId(),
                            root -> root.join(RestaurantWallet_.restaurant, JoinType.LEFT).get(Restaurant_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
