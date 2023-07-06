package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.Cuisine;
import ly.biadjo.food.repository.CuisineRepository;
import ly.biadjo.food.service.criteria.CuisineCriteria;
import ly.biadjo.food.service.dto.CuisineDTO;
import ly.biadjo.food.service.mapper.CuisineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Cuisine} entities in the database.
 * The main input is a {@link CuisineCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CuisineDTO} or a {@link Page} of {@link CuisineDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CuisineQueryService extends QueryService<Cuisine> {

    private final Logger log = LoggerFactory.getLogger(CuisineQueryService.class);

    private final CuisineRepository cuisineRepository;

    private final CuisineMapper cuisineMapper;

    public CuisineQueryService(CuisineRepository cuisineRepository, CuisineMapper cuisineMapper) {
        this.cuisineRepository = cuisineRepository;
        this.cuisineMapper = cuisineMapper;
    }

    /**
     * Return a {@link List} of {@link CuisineDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CuisineDTO> findByCriteria(CuisineCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Cuisine> specification = createSpecification(criteria);
        return cuisineMapper.toDto(cuisineRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CuisineDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CuisineDTO> findByCriteria(CuisineCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Cuisine> specification = createSpecification(criteria);
        return cuisineRepository.findAll(specification, page).map(cuisineMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CuisineCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Cuisine> specification = createSpecification(criteria);
        return cuisineRepository.count(specification);
    }

    /**
     * Function to convert {@link CuisineCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Cuisine> createSpecification(CuisineCriteria criteria) {
        Specification<Cuisine> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Cuisine_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Cuisine_.name));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), Cuisine_.nameAr));
            }
            if (criteria.getNameEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameEn(), Cuisine_.nameEn));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), Cuisine_.isActive));
            }
        }
        return specification;
    }
}
