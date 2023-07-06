package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.RestaurantSchedule;
import ly.biadjo.food.repository.RestaurantScheduleRepository;
import ly.biadjo.food.service.criteria.RestaurantScheduleCriteria;
import ly.biadjo.food.service.dto.RestaurantScheduleDTO;
import ly.biadjo.food.service.mapper.RestaurantScheduleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RestaurantSchedule} entities in the database.
 * The main input is a {@link RestaurantScheduleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RestaurantScheduleDTO} or a {@link Page} of {@link RestaurantScheduleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RestaurantScheduleQueryService extends QueryService<RestaurantSchedule> {

    private final Logger log = LoggerFactory.getLogger(RestaurantScheduleQueryService.class);

    private final RestaurantScheduleRepository restaurantScheduleRepository;

    private final RestaurantScheduleMapper restaurantScheduleMapper;

    public RestaurantScheduleQueryService(
        RestaurantScheduleRepository restaurantScheduleRepository,
        RestaurantScheduleMapper restaurantScheduleMapper
    ) {
        this.restaurantScheduleRepository = restaurantScheduleRepository;
        this.restaurantScheduleMapper = restaurantScheduleMapper;
    }

    /**
     * Return a {@link List} of {@link RestaurantScheduleDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RestaurantScheduleDTO> findByCriteria(RestaurantScheduleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RestaurantSchedule> specification = createSpecification(criteria);
        return restaurantScheduleMapper.toDto(restaurantScheduleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RestaurantScheduleDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RestaurantScheduleDTO> findByCriteria(RestaurantScheduleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RestaurantSchedule> specification = createSpecification(criteria);
        return restaurantScheduleRepository.findAll(specification, page).map(restaurantScheduleMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RestaurantScheduleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RestaurantSchedule> specification = createSpecification(criteria);
        return restaurantScheduleRepository.count(specification);
    }

    /**
     * Function to convert {@link RestaurantScheduleCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RestaurantSchedule> createSpecification(RestaurantScheduleCriteria criteria) {
        Specification<RestaurantSchedule> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RestaurantSchedule_.id));
            }
            if (criteria.getDayOfWeek() != null) {
                specification = specification.and(buildSpecification(criteria.getDayOfWeek(), RestaurantSchedule_.dayOfWeek));
            }
            if (criteria.getOpeningTime() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOpeningTime(), RestaurantSchedule_.openingTime));
            }
            if (criteria.getClosingTime() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClosingTime(), RestaurantSchedule_.closingTime));
            }
            if (criteria.getRestaurantId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRestaurantId(),
                            root -> root.join(RestaurantSchedule_.restaurant, JoinType.LEFT).get(Restaurant_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
