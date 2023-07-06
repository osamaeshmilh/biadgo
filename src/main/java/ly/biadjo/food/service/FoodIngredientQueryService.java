package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.FoodIngredient;
import ly.biadjo.food.repository.FoodIngredientRepository;
import ly.biadjo.food.service.criteria.FoodIngredientCriteria;
import ly.biadjo.food.service.dto.FoodIngredientDTO;
import ly.biadjo.food.service.mapper.FoodIngredientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link FoodIngredient} entities in the database.
 * The main input is a {@link FoodIngredientCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FoodIngredientDTO} or a {@link Page} of {@link FoodIngredientDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FoodIngredientQueryService extends QueryService<FoodIngredient> {

    private final Logger log = LoggerFactory.getLogger(FoodIngredientQueryService.class);

    private final FoodIngredientRepository foodIngredientRepository;

    private final FoodIngredientMapper foodIngredientMapper;

    public FoodIngredientQueryService(FoodIngredientRepository foodIngredientRepository, FoodIngredientMapper foodIngredientMapper) {
        this.foodIngredientRepository = foodIngredientRepository;
        this.foodIngredientMapper = foodIngredientMapper;
    }

    /**
     * Return a {@link List} of {@link FoodIngredientDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FoodIngredientDTO> findByCriteria(FoodIngredientCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FoodIngredient> specification = createSpecification(criteria);
        return foodIngredientMapper.toDto(foodIngredientRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FoodIngredientDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FoodIngredientDTO> findByCriteria(FoodIngredientCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FoodIngredient> specification = createSpecification(criteria);
        return foodIngredientRepository.findAll(specification, page).map(foodIngredientMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FoodIngredientCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FoodIngredient> specification = createSpecification(criteria);
        return foodIngredientRepository.count(specification);
    }

    /**
     * Function to convert {@link FoodIngredientCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<FoodIngredient> createSpecification(FoodIngredientCriteria criteria) {
        Specification<FoodIngredient> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FoodIngredient_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), FoodIngredient_.name));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), FoodIngredient_.nameAr));
            }
            if (criteria.getNameEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameEn(), FoodIngredient_.nameEn));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), FoodIngredient_.notes));
            }
            if (criteria.getFoodId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getFoodId(), root -> root.join(FoodIngredient_.food, JoinType.LEFT).get(Food_.id))
                    );
            }
        }
        return specification;
    }
}
