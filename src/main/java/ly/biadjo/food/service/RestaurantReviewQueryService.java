package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.RestaurantReview;
import ly.biadjo.food.repository.RestaurantReviewRepository;
import ly.biadjo.food.service.criteria.RestaurantReviewCriteria;
import ly.biadjo.food.service.dto.RestaurantReviewDTO;
import ly.biadjo.food.service.mapper.RestaurantReviewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RestaurantReview} entities in the database.
 * The main input is a {@link RestaurantReviewCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RestaurantReviewDTO} or a {@link Page} of {@link RestaurantReviewDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RestaurantReviewQueryService extends QueryService<RestaurantReview> {

    private final Logger log = LoggerFactory.getLogger(RestaurantReviewQueryService.class);

    private final RestaurantReviewRepository restaurantReviewRepository;

    private final RestaurantReviewMapper restaurantReviewMapper;

    public RestaurantReviewQueryService(
        RestaurantReviewRepository restaurantReviewRepository,
        RestaurantReviewMapper restaurantReviewMapper
    ) {
        this.restaurantReviewRepository = restaurantReviewRepository;
        this.restaurantReviewMapper = restaurantReviewMapper;
    }

    /**
     * Return a {@link List} of {@link RestaurantReviewDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RestaurantReviewDTO> findByCriteria(RestaurantReviewCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RestaurantReview> specification = createSpecification(criteria);
        return restaurantReviewMapper.toDto(restaurantReviewRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RestaurantReviewDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RestaurantReviewDTO> findByCriteria(RestaurantReviewCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RestaurantReview> specification = createSpecification(criteria);
        return restaurantReviewRepository.findAll(specification, page).map(restaurantReviewMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RestaurantReviewCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RestaurantReview> specification = createSpecification(criteria);
        return restaurantReviewRepository.count(specification);
    }

    /**
     * Function to convert {@link RestaurantReviewCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RestaurantReview> createSpecification(RestaurantReviewCriteria criteria) {
        Specification<RestaurantReview> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RestaurantReview_.id));
            }
            if (criteria.getReview() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReview(), RestaurantReview_.review));
            }
            if (criteria.getRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRate(), RestaurantReview_.rate));
            }
            if (criteria.getIsEdited() != null) {
                specification = specification.and(buildSpecification(criteria.getIsEdited(), RestaurantReview_.isEdited));
            }
            if (criteria.getCustomerId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getCustomerId(),
                            root -> root.join(RestaurantReview_.customer, JoinType.LEFT).get(Customer_.id)
                        )
                    );
            }
            if (criteria.getRestaurantId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRestaurantId(),
                            root -> root.join(RestaurantReview_.restaurant, JoinType.LEFT).get(Restaurant_.id)
                        )
                    );
            }
        }
        return specification;
    }

    @Transactional(readOnly = true)
    public float sumRatingByCriteria(RestaurantReviewCriteria restaurantReviewCriteria) {
        log.debug("sum by criteria : {}", restaurantReviewCriteria);
        final Specification<RestaurantReview> specification = createSpecification(restaurantReviewCriteria);
        List<RestaurantReview> restaurantReviews = restaurantReviewRepository.findAll(specification);
        Float total = 0.0F;
        for (RestaurantReview restaurantReview : restaurantReviews) {
            total += restaurantReview.getRate();
        }
        return total;
    }

}
