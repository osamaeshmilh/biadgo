package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.DriverWallet;
import ly.biadjo.food.repository.DriverWalletRepository;
import ly.biadjo.food.service.criteria.DriverWalletCriteria;
import ly.biadjo.food.service.dto.DriverWalletDTO;
import ly.biadjo.food.service.mapper.DriverWalletMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DriverWallet} entities in the database.
 * The main input is a {@link DriverWalletCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DriverWalletDTO} or a {@link Page} of {@link DriverWalletDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DriverWalletQueryService extends QueryService<DriverWallet> {

    private final Logger log = LoggerFactory.getLogger(DriverWalletQueryService.class);

    private final DriverWalletRepository driverWalletRepository;

    private final DriverWalletMapper driverWalletMapper;

    public DriverWalletQueryService(DriverWalletRepository driverWalletRepository, DriverWalletMapper driverWalletMapper) {
        this.driverWalletRepository = driverWalletRepository;
        this.driverWalletMapper = driverWalletMapper;
    }

    /**
     * Return a {@link List} of {@link DriverWalletDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DriverWalletDTO> findByCriteria(DriverWalletCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DriverWallet> specification = createSpecification(criteria);
        return driverWalletMapper.toDto(driverWalletRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DriverWalletDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DriverWalletDTO> findByCriteria(DriverWalletCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DriverWallet> specification = createSpecification(criteria);
        return driverWalletRepository.findAll(specification, page).map(driverWalletMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DriverWalletCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DriverWallet> specification = createSpecification(criteria);
        return driverWalletRepository.count(specification);
    }

    /**
     * Function to convert {@link DriverWalletCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DriverWallet> createSpecification(DriverWalletCriteria criteria) {
        Specification<DriverWallet> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DriverWallet_.id));
            }
            if (criteria.getTransactionNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTransactionNo(), DriverWallet_.transactionNo));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), DriverWallet_.amount));
            }
            if (criteria.getWalletAction() != null) {
                specification = specification.and(buildSpecification(criteria.getWalletAction(), DriverWallet_.walletAction));
            }
            if (criteria.getTotalBeforeAction() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTotalBeforeAction(), DriverWallet_.totalBeforeAction));
            }
            if (criteria.getTotalAfterAction() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalAfterAction(), DriverWallet_.totalAfterAction));
            }
            if (criteria.getPaymentType() != null) {
                specification = specification.and(buildSpecification(criteria.getPaymentType(), DriverWallet_.paymentType));
            }
            if (criteria.getPaymentReference() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPaymentReference(), DriverWallet_.paymentReference));
            }
            if (criteria.getOrderId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrderId(), DriverWallet_.orderId));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), DriverWallet_.notes));
            }
            if (criteria.getDriverId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getDriverId(), root -> root.join(DriverWallet_.driver, JoinType.LEFT).get(Driver_.id))
                    );
            }
        }
        return specification;
    }
}
