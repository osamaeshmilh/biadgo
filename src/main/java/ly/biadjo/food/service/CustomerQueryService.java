package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.Customer;
import ly.biadjo.food.repository.CustomerRepository;
import ly.biadjo.food.service.criteria.CustomerCriteria;
import ly.biadjo.food.service.dto.CustomerDTO;
import ly.biadjo.food.service.mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Customer} entities in the database.
 * The main input is a {@link CustomerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CustomerDTO} or a {@link Page} of {@link CustomerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomerQueryService extends QueryService<Customer> {

    private final Logger log = LoggerFactory.getLogger(CustomerQueryService.class);

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerQueryService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    /**
     * Return a {@link List} of {@link CustomerDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CustomerDTO> findByCriteria(CustomerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Customer> specification = createSpecification(criteria);
        return customerMapper.toDto(customerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CustomerDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerDTO> findByCriteria(CustomerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Customer> specification = createSpecification(criteria);
        return customerRepository.findAll(specification, page).map(customerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CustomerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Customer> specification = createSpecification(criteria);
        return customerRepository.count(specification);
    }

    /**
     * Function to convert {@link CustomerCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Customer> createSpecification(CustomerCriteria criteria) {
        Specification<Customer> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Customer_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Customer_.name));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Customer_.email));
            }
            if (criteria.getMobileNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobileNo(), Customer_.mobileNo));
            }
            if (criteria.getGoogleId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGoogleId(), Customer_.googleId));
            }
            if (criteria.getFacebookId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFacebookId(), Customer_.facebookId));
            }
            if (criteria.getAppleId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAppleId(), Customer_.appleId));
            }
            if (criteria.getIsBanned() != null) {
                specification = specification.and(buildSpecification(criteria.getIsBanned(), Customer_.isBanned));
            }
            if (criteria.getIsVerified() != null) {
                specification = specification.and(buildSpecification(criteria.getIsVerified(), Customer_.isVerified));
            }
            if (criteria.getVerifiedByEmail() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedByEmail(), Customer_.verifiedByEmail));
            }
            if (criteria.getVerifiedByMobileNo() != null) {
                specification = specification.and(buildSpecification(criteria.getVerifiedByMobileNo(), Customer_.verifiedByMobileNo));
            }
            if (criteria.getWalletPublicKey() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWalletPublicKey(), Customer_.walletPublicKey));
            }
            if (criteria.getImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUrl(), Customer_.imageUrl));
            }
            if (criteria.getLanguageCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLanguageCode(), Customer_.languageCode));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), Customer_.notes));
            }
            if (criteria.getUserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getUserId(), root -> root.join(Customer_.user, JoinType.LEFT).get(User_.id))
                    );
            }
        }
        return specification;
    }
}
