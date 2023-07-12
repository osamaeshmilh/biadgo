package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.RestaurantImage;
import ly.biadjo.food.repository.RestaurantImageRepository;
import ly.biadjo.food.service.criteria.RestaurantImageCriteria;
import ly.biadjo.food.service.dto.RestaurantImageDTO;
import ly.biadjo.food.service.mapper.RestaurantImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RestaurantImage} entities in the database.
 * The main input is a {@link RestaurantImageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RestaurantImageDTO} or a {@link Page} of {@link RestaurantImageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RestaurantImageQueryService extends QueryService<RestaurantImage> {

    private final Logger log = LoggerFactory.getLogger(RestaurantImageQueryService.class);

    private final RestaurantImageRepository restaurantImageRepository;

    private final RestaurantImageMapper restaurantImageMapper;

    public RestaurantImageQueryService(RestaurantImageRepository restaurantImageRepository, RestaurantImageMapper restaurantImageMapper) {
        this.restaurantImageRepository = restaurantImageRepository;
        this.restaurantImageMapper = restaurantImageMapper;
    }

    /**
     * Return a {@link List} of {@link RestaurantImageDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RestaurantImageDTO> findByCriteria(RestaurantImageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RestaurantImage> specification = createSpecification(criteria);
        return restaurantImageMapper.toDto(restaurantImageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RestaurantImageDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RestaurantImageDTO> findByCriteria(RestaurantImageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RestaurantImage> specification = createSpecification(criteria);
        return restaurantImageRepository.findAll(specification, page).map(restaurantImageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RestaurantImageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RestaurantImage> specification = createSpecification(criteria);
        return restaurantImageRepository.count(specification);
    }

    /**
     * Function to convert {@link RestaurantImageCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RestaurantImage> createSpecification(RestaurantImageCriteria criteria) {
        Specification<RestaurantImage> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RestaurantImage_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), RestaurantImage_.description));
            }
            if (criteria.getDescriptionAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescriptionAr(), RestaurantImage_.descriptionAr));
            }
            if (criteria.getDescriptionEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescriptionEn(), RestaurantImage_.descriptionEn));
            }
            if (criteria.getImageType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageType(), RestaurantImage_.imageType));
            }
            if (criteria.getMenuOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMenuOrder(), RestaurantImage_.menuOrder));
            }
            if (criteria.getImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUrl(), RestaurantImage_.imageUrl));
            }
            if (criteria.getRestaurantId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRestaurantId(),
                            root -> root.join(RestaurantImage_.restaurant, JoinType.LEFT).get(Restaurant_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
