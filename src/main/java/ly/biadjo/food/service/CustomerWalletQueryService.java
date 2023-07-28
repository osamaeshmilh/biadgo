package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.CustomerWallet;
import ly.biadjo.food.domain.enumeration.WalletAction;
import ly.biadjo.food.repository.CustomerWalletRepository;
import ly.biadjo.food.service.criteria.CustomerWalletCriteria;
import ly.biadjo.food.service.dto.CustomerWalletDTO;
import ly.biadjo.food.service.mapper.CustomerWalletMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link CustomerWallet} entities in the database.
 * The main input is a {@link CustomerWalletCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CustomerWalletDTO} or a {@link Page} of {@link CustomerWalletDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomerWalletQueryService extends QueryService<CustomerWallet> {

    private final Logger log = LoggerFactory.getLogger(CustomerWalletQueryService.class);

    private final CustomerWalletRepository customerWalletRepository;

    private final CustomerWalletMapper customerWalletMapper;

    public CustomerWalletQueryService(CustomerWalletRepository customerWalletRepository, CustomerWalletMapper customerWalletMapper) {
        this.customerWalletRepository = customerWalletRepository;
        this.customerWalletMapper = customerWalletMapper;
    }

    /**
     * Return a {@link List} of {@link CustomerWalletDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CustomerWalletDTO> findByCriteria(CustomerWalletCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CustomerWallet> specification = createSpecification(criteria);
        return customerWalletMapper.toDto(customerWalletRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CustomerWalletDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerWalletDTO> findByCriteria(CustomerWalletCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CustomerWallet> specification = createSpecification(criteria);
        return customerWalletRepository.findAll(specification, page).map(customerWalletMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CustomerWalletCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CustomerWallet> specification = createSpecification(criteria);
        return customerWalletRepository.count(specification);
    }

    /**
     * Function to convert {@link CustomerWalletCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CustomerWallet> createSpecification(CustomerWalletCriteria criteria) {
        Specification<CustomerWallet> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CustomerWallet_.id));
            }
            if (criteria.getTransactionNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTransactionNo(), CustomerWallet_.transactionNo));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), CustomerWallet_.amount));
            }
            if (criteria.getWalletAction() != null) {
                specification = specification.and(buildSpecification(criteria.getWalletAction(), CustomerWallet_.walletAction));
            }
            if (criteria.getTotalBeforeAction() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTotalBeforeAction(), CustomerWallet_.totalBeforeAction));
            }
            if (criteria.getTotalAfterAction() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTotalAfterAction(), CustomerWallet_.totalAfterAction));
            }
            if (criteria.getPaymentType() != null) {
                specification = specification.and(buildSpecification(criteria.getPaymentType(), CustomerWallet_.paymentType));
            }
            if (criteria.getPaymentReference() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getPaymentReference(), CustomerWallet_.paymentReference));
            }
            if (criteria.getOrderId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrderId(), CustomerWallet_.orderId));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), CustomerWallet_.notes));
            }
            if (criteria.getCustomerId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getCustomerId(),
                            root -> root.join(CustomerWallet_.customer, JoinType.LEFT).get(Customer_.id)
                        )
                    );
            }
        }
        return specification;
    }

    @Transactional(readOnly = true)
    public Double sumAmountByCriteria(CustomerWalletCriteria criteria) {
        log.debug("sum by criteria : {}", criteria);
        final Specification<CustomerWallet> specification = createSpecification(criteria);
        List<CustomerWallet> customerWallets = customerWalletRepository.findAll(specification);
        Double total = 0.0;
        for (CustomerWallet customerWallet : customerWallets) {
            if (customerWallet.getWalletAction() == WalletAction.DEPOSIT) {
                total += customerWallet.getAmount();
            } else if (customerWallet.getWalletAction() == WalletAction.WITHDRAW) {
                total -= customerWallet.getAmount();
            }
        }
        return total;
    }
}
