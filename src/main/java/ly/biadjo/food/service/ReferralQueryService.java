package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.Referral;
import ly.biadjo.food.repository.ReferralRepository;
import ly.biadjo.food.service.criteria.ReferralCriteria;
import ly.biadjo.food.service.dto.ReferralDTO;
import ly.biadjo.food.service.mapper.ReferralMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Referral} entities in the database.
 * The main input is a {@link ReferralCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ReferralDTO} or a {@link Page} of {@link ReferralDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ReferralQueryService extends QueryService<Referral> {

    private final Logger log = LoggerFactory.getLogger(ReferralQueryService.class);

    private final ReferralRepository referralRepository;

    private final ReferralMapper referralMapper;

    public ReferralQueryService(ReferralRepository referralRepository, ReferralMapper referralMapper) {
        this.referralRepository = referralRepository;
        this.referralMapper = referralMapper;
    }

    /**
     * Return a {@link List} of {@link ReferralDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ReferralDTO> findByCriteria(ReferralCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Referral> specification = createSpecification(criteria);
        return referralMapper.toDto(referralRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ReferralDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ReferralDTO> findByCriteria(ReferralCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Referral> specification = createSpecification(criteria);
        return referralRepository.findAll(specification, page).map(referralMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ReferralCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Referral> specification = createSpecification(criteria);
        return referralRepository.count(specification);
    }

    /**
     * Function to convert {@link ReferralCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Referral> createSpecification(ReferralCriteria criteria) {
        Specification<Referral> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Referral_.id));
            }
            if (criteria.getReferralCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReferralCode(), Referral_.referralCode));
            }
            if (criteria.getReferrerAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReferrerAmount(), Referral_.referrerAmount));
            }
            if (criteria.getReferredCustomerAmount() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getReferredCustomerAmount(), Referral_.referredCustomerAmount));
            }
            if (criteria.getExpiryDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExpiryDate(), Referral_.expiryDate));
            }
            if (criteria.getIsUsed() != null) {
                specification = specification.and(buildSpecification(criteria.getIsUsed(), Referral_.isUsed));
            }
            if (criteria.getUsedDateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUsedDateTime(), Referral_.usedDateTime));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), Referral_.notes));
            }
            if (criteria.getReferredCustomerId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getReferredCustomerId(),
                            root -> root.join(Referral_.referredCustomer, JoinType.LEFT).get(Customer_.id)
                        )
                    );
            }
            if (criteria.getReferrerCustomerId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getReferrerCustomerId(),
                            root -> root.join(Referral_.referrerCustomer, JoinType.LEFT).get(Customer_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
