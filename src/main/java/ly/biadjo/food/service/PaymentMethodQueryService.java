package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.PaymentMethod;
import ly.biadjo.food.repository.PaymentMethodRepository;
import ly.biadjo.food.service.criteria.PaymentMethodCriteria;
import ly.biadjo.food.service.dto.PaymentMethodDTO;
import ly.biadjo.food.service.mapper.PaymentMethodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link PaymentMethod} entities in the database.
 * The main input is a {@link PaymentMethodCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PaymentMethodDTO} or a {@link Page} of {@link PaymentMethodDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PaymentMethodQueryService extends QueryService<PaymentMethod> {

    private final Logger log = LoggerFactory.getLogger(PaymentMethodQueryService.class);

    private final PaymentMethodRepository paymentMethodRepository;

    private final PaymentMethodMapper paymentMethodMapper;

    public PaymentMethodQueryService(PaymentMethodRepository paymentMethodRepository, PaymentMethodMapper paymentMethodMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentMethodMapper = paymentMethodMapper;
    }

    /**
     * Return a {@link List} of {@link PaymentMethodDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PaymentMethodDTO> findByCriteria(PaymentMethodCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PaymentMethod> specification = createSpecification(criteria);
        return paymentMethodMapper.toDto(paymentMethodRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PaymentMethodDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PaymentMethodDTO> findByCriteria(PaymentMethodCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PaymentMethod> specification = createSpecification(criteria);
        return paymentMethodRepository.findAll(specification, page).map(paymentMethodMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PaymentMethodCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PaymentMethod> specification = createSpecification(criteria);
        return paymentMethodRepository.count(specification);
    }

    /**
     * Function to convert {@link PaymentMethodCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<PaymentMethod> createSpecification(PaymentMethodCriteria criteria) {
        Specification<PaymentMethod> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PaymentMethod_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), PaymentMethod_.name));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), PaymentMethod_.nameAr));
            }
            if (criteria.getNameEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameEn(), PaymentMethod_.nameEn));
            }
            if (criteria.getMenuOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMenuOrder(), PaymentMethod_.menuOrder));
            }
            if (criteria.getImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUrl(), PaymentMethod_.imageUrl));
            }
            if (criteria.getDetails() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDetails(), PaymentMethod_.details));
            }
            if (criteria.getFeePercentage() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeePercentage(), PaymentMethod_.feePercentage));
            }
            if (criteria.getPaymentType() != null) {
                specification = specification.and(buildSpecification(criteria.getPaymentType(), PaymentMethod_.paymentType));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), PaymentMethod_.isActive));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), PaymentMethod_.notes));
            }
        }
        return specification;
    }
}
