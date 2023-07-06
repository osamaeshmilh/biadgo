package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.DriverReview;
import ly.biadjo.food.repository.DriverReviewRepository;
import ly.biadjo.food.service.criteria.DriverReviewCriteria;
import ly.biadjo.food.service.dto.DriverReviewDTO;
import ly.biadjo.food.service.mapper.DriverReviewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DriverReview} entities in the database.
 * The main input is a {@link DriverReviewCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DriverReviewDTO} or a {@link Page} of {@link DriverReviewDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DriverReviewQueryService extends QueryService<DriverReview> {

    private final Logger log = LoggerFactory.getLogger(DriverReviewQueryService.class);

    private final DriverReviewRepository driverReviewRepository;

    private final DriverReviewMapper driverReviewMapper;

    public DriverReviewQueryService(DriverReviewRepository driverReviewRepository, DriverReviewMapper driverReviewMapper) {
        this.driverReviewRepository = driverReviewRepository;
        this.driverReviewMapper = driverReviewMapper;
    }

    /**
     * Return a {@link List} of {@link DriverReviewDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DriverReviewDTO> findByCriteria(DriverReviewCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DriverReview> specification = createSpecification(criteria);
        return driverReviewMapper.toDto(driverReviewRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DriverReviewDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DriverReviewDTO> findByCriteria(DriverReviewCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DriverReview> specification = createSpecification(criteria);
        return driverReviewRepository.findAll(specification, page).map(driverReviewMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DriverReviewCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DriverReview> specification = createSpecification(criteria);
        return driverReviewRepository.count(specification);
    }

    /**
     * Function to convert {@link DriverReviewCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DriverReview> createSpecification(DriverReviewCriteria criteria) {
        Specification<DriverReview> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DriverReview_.id));
            }
            if (criteria.getDetails() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDetails(), DriverReview_.details));
            }
            if (criteria.getRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRate(), DriverReview_.rate));
            }
            if (criteria.getCustomerId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getCustomerId(),
                            root -> root.join(DriverReview_.customer, JoinType.LEFT).get(Customer_.id)
                        )
                    );
            }
            if (criteria.getDriverId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getDriverId(), root -> root.join(DriverReview_.driver, JoinType.LEFT).get(Driver_.id))
                    );
            }
        }
        return specification;
    }
}
