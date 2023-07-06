package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.RestaurantDistancePrice;
import ly.biadjo.food.repository.RestaurantDistancePriceRepository;
import ly.biadjo.food.service.criteria.RestaurantDistancePriceCriteria;
import ly.biadjo.food.service.dto.RestaurantDistancePriceDTO;
import ly.biadjo.food.service.mapper.RestaurantDistancePriceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RestaurantDistancePrice} entities in the database.
 * The main input is a {@link RestaurantDistancePriceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RestaurantDistancePriceDTO} or a {@link Page} of {@link RestaurantDistancePriceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RestaurantDistancePriceQueryService extends QueryService<RestaurantDistancePrice> {

    private final Logger log = LoggerFactory.getLogger(RestaurantDistancePriceQueryService.class);

    private final RestaurantDistancePriceRepository restaurantDistancePriceRepository;

    private final RestaurantDistancePriceMapper restaurantDistancePriceMapper;

    public RestaurantDistancePriceQueryService(
        RestaurantDistancePriceRepository restaurantDistancePriceRepository,
        RestaurantDistancePriceMapper restaurantDistancePriceMapper
    ) {
        this.restaurantDistancePriceRepository = restaurantDistancePriceRepository;
        this.restaurantDistancePriceMapper = restaurantDistancePriceMapper;
    }

    /**
     * Return a {@link List} of {@link RestaurantDistancePriceDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RestaurantDistancePriceDTO> findByCriteria(RestaurantDistancePriceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RestaurantDistancePrice> specification = createSpecification(criteria);
        return restaurantDistancePriceMapper.toDto(restaurantDistancePriceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RestaurantDistancePriceDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RestaurantDistancePriceDTO> findByCriteria(RestaurantDistancePriceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RestaurantDistancePrice> specification = createSpecification(criteria);
        return restaurantDistancePriceRepository.findAll(specification, page).map(restaurantDistancePriceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RestaurantDistancePriceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RestaurantDistancePrice> specification = createSpecification(criteria);
        return restaurantDistancePriceRepository.count(specification);
    }

    /**
     * Function to convert {@link RestaurantDistancePriceCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RestaurantDistancePrice> createSpecification(RestaurantDistancePriceCriteria criteria) {
        Specification<RestaurantDistancePrice> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RestaurantDistancePrice_.id));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), RestaurantDistancePrice_.price));
            }
            if (criteria.getFromKm() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFromKm(), RestaurantDistancePrice_.fromKm));
            }
            if (criteria.getToKm() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getToKm(), RestaurantDistancePrice_.toKm));
            }
            if (criteria.getIsAvailable() != null) {
                specification = specification.and(buildSpecification(criteria.getIsAvailable(), RestaurantDistancePrice_.isAvailable));
            }
            if (criteria.getRestaurantId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRestaurantId(),
                            root -> root.join(RestaurantDistancePrice_.restaurant, JoinType.LEFT).get(Restaurant_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
