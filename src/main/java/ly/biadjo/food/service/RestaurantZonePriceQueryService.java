package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.RestaurantZonePrice;
import ly.biadjo.food.repository.RestaurantZonePriceRepository;
import ly.biadjo.food.service.criteria.RestaurantZonePriceCriteria;
import ly.biadjo.food.service.dto.RestaurantZonePriceDTO;
import ly.biadjo.food.service.mapper.RestaurantZonePriceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RestaurantZonePrice} entities in the database.
 * The main input is a {@link RestaurantZonePriceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RestaurantZonePriceDTO} or a {@link Page} of {@link RestaurantZonePriceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RestaurantZonePriceQueryService extends QueryService<RestaurantZonePrice> {

    private final Logger log = LoggerFactory.getLogger(RestaurantZonePriceQueryService.class);

    private final RestaurantZonePriceRepository restaurantZonePriceRepository;

    private final RestaurantZonePriceMapper restaurantZonePriceMapper;

    public RestaurantZonePriceQueryService(
        RestaurantZonePriceRepository restaurantZonePriceRepository,
        RestaurantZonePriceMapper restaurantZonePriceMapper
    ) {
        this.restaurantZonePriceRepository = restaurantZonePriceRepository;
        this.restaurantZonePriceMapper = restaurantZonePriceMapper;
    }

    /**
     * Return a {@link List} of {@link RestaurantZonePriceDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RestaurantZonePriceDTO> findByCriteria(RestaurantZonePriceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RestaurantZonePrice> specification = createSpecification(criteria);
        return restaurantZonePriceMapper.toDto(restaurantZonePriceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RestaurantZonePriceDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RestaurantZonePriceDTO> findByCriteria(RestaurantZonePriceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RestaurantZonePrice> specification = createSpecification(criteria);
        return restaurantZonePriceRepository.findAll(specification, page).map(restaurantZonePriceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RestaurantZonePriceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RestaurantZonePrice> specification = createSpecification(criteria);
        return restaurantZonePriceRepository.count(specification);
    }

    /**
     * Function to convert {@link RestaurantZonePriceCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RestaurantZonePrice> createSpecification(RestaurantZonePriceCriteria criteria) {
        Specification<RestaurantZonePrice> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RestaurantZonePrice_.id));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), RestaurantZonePrice_.price));
            }
            if (criteria.getIsAvailable() != null) {
                specification = specification.and(buildSpecification(criteria.getIsAvailable(), RestaurantZonePrice_.isAvailable));
            }
            if (criteria.getRestaurantId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRestaurantId(),
                            root -> root.join(RestaurantZonePrice_.restaurant, JoinType.LEFT).get(Restaurant_.id)
                        )
                    );
            }
            if (criteria.getZoneId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getZoneId(), root -> root.join(RestaurantZonePrice_.zone, JoinType.LEFT).get(Zone_.id))
                    );
            }
        }
        return specification;
    }
}
