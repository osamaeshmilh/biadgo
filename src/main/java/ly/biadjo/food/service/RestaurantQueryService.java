package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.Restaurant;
import ly.biadjo.food.repository.RestaurantRepository;
import ly.biadjo.food.service.criteria.RestaurantCriteria;
import ly.biadjo.food.service.dto.RestaurantDTO;
import ly.biadjo.food.service.mapper.RestaurantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Restaurant} entities in the database.
 * The main input is a {@link RestaurantCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RestaurantDTO} or a {@link Page} of {@link RestaurantDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RestaurantQueryService extends QueryService<Restaurant> {

    private final Logger log = LoggerFactory.getLogger(RestaurantQueryService.class);

    private final RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper;

    public RestaurantQueryService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    /**
     * Return a {@link List} of {@link RestaurantDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RestaurantDTO> findByCriteria(RestaurantCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Restaurant> specification = createSpecification(criteria);
        return restaurantMapper.toDto(restaurantRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RestaurantDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RestaurantDTO> findByCriteria(RestaurantCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Restaurant> specification = createSpecification(criteria);
        return restaurantRepository.findAll(specification, page).map(restaurantMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RestaurantCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Restaurant> specification = createSpecification(criteria);
        return restaurantRepository.count(specification);
    }

    /**
     * Function to convert {@link RestaurantCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Restaurant> createSpecification(RestaurantCriteria criteria) {
        Specification<Restaurant> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Restaurant_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Restaurant_.name));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), Restaurant_.nameAr));
            }
            if (criteria.getNameEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameEn(), Restaurant_.nameEn));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Restaurant_.description));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Restaurant_.address));
            }
            if (criteria.getLatitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatitude(), Restaurant_.latitude));
            }
            if (criteria.getLongitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLongitude(), Restaurant_.longitude));
            }
            if (criteria.getPlusCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPlusCode(), Restaurant_.plusCode));
            }
            if (criteria.getMobile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobile(), Restaurant_.mobile));
            }
            if (criteria.getEstimitedTimeMins() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstimitedTimeMins(), Restaurant_.estimitedTimeMins));
            }
            if (criteria.getDeliveryFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeliveryFee(), Restaurant_.deliveryFee));
            }
            if (criteria.getDeliveryRange() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeliveryRange(), Restaurant_.deliveryRange));
            }
            if (criteria.getAvailableForDelivery() != null) {
                specification = specification.and(buildSpecification(criteria.getAvailableForDelivery(), Restaurant_.availableForDelivery));
            }
            if (criteria.getAvailableForPickup() != null) {
                specification = specification.and(buildSpecification(criteria.getAvailableForPickup(), Restaurant_.availableForPickup));
            }
            if (criteria.getMinimumOrderTotalForDelivery() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getMinimumOrderTotalForDelivery(), Restaurant_.minimumOrderTotalForDelivery)
                    );
            }
            if (criteria.getDeliveryPriceType() != null) {
                specification = specification.and(buildSpecification(criteria.getDeliveryPriceType(), Restaurant_.deliveryPriceType));
            }
            if (criteria.getIsOpen() != null) {
                specification = specification.and(buildSpecification(criteria.getIsOpen(), Restaurant_.isOpen));
            }
            if (criteria.getIsFeatured() != null) {
                specification = specification.and(buildSpecification(criteria.getIsFeatured(), Restaurant_.isFeatured));
            }
            if (criteria.getIsListedInOffers() != null) {
                specification = specification.and(buildSpecification(criteria.getIsListedInOffers(), Restaurant_.isListedInOffers));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), Restaurant_.isActive));
            }
            if (criteria.getOfferBanner() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOfferBanner(), Restaurant_.offerBanner));
            }
            if (criteria.getPriority() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriority(), Restaurant_.priority));
            }
            if (criteria.getViewCounter() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getViewCounter(), Restaurant_.viewCounter));
            }
            if (criteria.getScheduledClosingTime() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getScheduledClosingTime(), Restaurant_.scheduledClosingTime));
            }
            if (criteria.getFacebookPageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFacebookPageUrl(), Restaurant_.facebookPageUrl));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), Restaurant_.notes));
            }
            if (criteria.getUserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getUserId(), root -> root.join(Restaurant_.user, JoinType.LEFT).get(User_.id))
                    );
            }
            if (criteria.getCuisineId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCuisineId(), root -> root.join(Restaurant_.cuisine, JoinType.LEFT).get(Cuisine_.id))
                    );
            }
            if (criteria.getCategoriesId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getCategoriesId(),
                            root -> root.join(Restaurant_.categories, JoinType.LEFT).get(Category_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
