package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.Coupon;
import ly.biadjo.food.repository.CouponRepository;
import ly.biadjo.food.service.criteria.CouponCriteria;
import ly.biadjo.food.service.dto.CouponDTO;
import ly.biadjo.food.service.mapper.CouponMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Coupon} entities in the database.
 * The main input is a {@link CouponCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CouponDTO} or a {@link Page} of {@link CouponDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CouponQueryService extends QueryService<Coupon> {

    private final Logger log = LoggerFactory.getLogger(CouponQueryService.class);

    private final CouponRepository couponRepository;

    private final CouponMapper couponMapper;

    public CouponQueryService(CouponRepository couponRepository, CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
    }

    /**
     * Return a {@link List} of {@link CouponDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CouponDTO> findByCriteria(CouponCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Coupon> specification = createSpecification(criteria);
        return couponMapper.toDto(couponRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CouponDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CouponDTO> findByCriteria(CouponCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Coupon> specification = createSpecification(criteria);
        return couponRepository.findAll(specification, page).map(couponMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CouponCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Coupon> specification = createSpecification(criteria);
        return couponRepository.count(specification);
    }

    /**
     * Function to convert {@link CouponCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Coupon> createSpecification(CouponCriteria criteria) {
        Specification<Coupon> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Coupon_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Coupon_.code));
            }
            if (criteria.getCouponType() != null) {
                specification = specification.and(buildSpecification(criteria.getCouponType(), Coupon_.couponType));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), Coupon_.amount));
            }
            if (criteria.getMinimumAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMinimumAmount(), Coupon_.minimumAmount));
            }
            if (criteria.getUseLimit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUseLimit(), Coupon_.useLimit));
            }
            if (criteria.getUseCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUseCount(), Coupon_.useCount));
            }
            if (criteria.getExpiryDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExpiryDate(), Coupon_.expiryDate));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), Coupon_.isActive));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), Coupon_.notes));
            }
        }
        return specification;
    }
}
