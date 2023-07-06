package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.FavoriteRestaurant;
import ly.biadjo.food.repository.FavoriteRestaurantRepository;
import ly.biadjo.food.service.criteria.FavoriteRestaurantCriteria;
import ly.biadjo.food.service.dto.FavoriteRestaurantDTO;
import ly.biadjo.food.service.mapper.FavoriteRestaurantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link FavoriteRestaurant} entities in the database.
 * The main input is a {@link FavoriteRestaurantCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FavoriteRestaurantDTO} or a {@link Page} of {@link FavoriteRestaurantDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FavoriteRestaurantQueryService extends QueryService<FavoriteRestaurant> {

    private final Logger log = LoggerFactory.getLogger(FavoriteRestaurantQueryService.class);

    private final FavoriteRestaurantRepository favoriteRestaurantRepository;

    private final FavoriteRestaurantMapper favoriteRestaurantMapper;

    public FavoriteRestaurantQueryService(
        FavoriteRestaurantRepository favoriteRestaurantRepository,
        FavoriteRestaurantMapper favoriteRestaurantMapper
    ) {
        this.favoriteRestaurantRepository = favoriteRestaurantRepository;
        this.favoriteRestaurantMapper = favoriteRestaurantMapper;
    }

    /**
     * Return a {@link List} of {@link FavoriteRestaurantDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FavoriteRestaurantDTO> findByCriteria(FavoriteRestaurantCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FavoriteRestaurant> specification = createSpecification(criteria);
        return favoriteRestaurantMapper.toDto(favoriteRestaurantRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FavoriteRestaurantDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FavoriteRestaurantDTO> findByCriteria(FavoriteRestaurantCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FavoriteRestaurant> specification = createSpecification(criteria);
        return favoriteRestaurantRepository.findAll(specification, page).map(favoriteRestaurantMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FavoriteRestaurantCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FavoriteRestaurant> specification = createSpecification(criteria);
        return favoriteRestaurantRepository.count(specification);
    }

    /**
     * Function to convert {@link FavoriteRestaurantCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<FavoriteRestaurant> createSpecification(FavoriteRestaurantCriteria criteria) {
        Specification<FavoriteRestaurant> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FavoriteRestaurant_.id));
            }
            if (criteria.getCustomerId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getCustomerId(),
                            root -> root.join(FavoriteRestaurant_.customer, JoinType.LEFT).get(Customer_.id)
                        )
                    );
            }
            if (criteria.getRestaurantId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRestaurantId(),
                            root -> root.join(FavoriteRestaurant_.restaurant, JoinType.LEFT).get(Restaurant_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
