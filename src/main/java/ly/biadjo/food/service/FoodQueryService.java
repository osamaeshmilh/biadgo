package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.Food;
import ly.biadjo.food.repository.FoodRepository;
import ly.biadjo.food.service.criteria.FoodCriteria;
import ly.biadjo.food.service.dto.FoodDTO;
import ly.biadjo.food.service.mapper.FoodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Food} entities in the database.
 * The main input is a {@link FoodCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FoodDTO} or a {@link Page} of {@link FoodDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FoodQueryService extends QueryService<Food> {

    private final Logger log = LoggerFactory.getLogger(FoodQueryService.class);

    private final FoodRepository foodRepository;

    private final FoodMapper foodMapper;

    public FoodQueryService(FoodRepository foodRepository, FoodMapper foodMapper) {
        this.foodRepository = foodRepository;
        this.foodMapper = foodMapper;
    }

    /**
     * Return a {@link List} of {@link FoodDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FoodDTO> findByCriteria(FoodCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Food> specification = createSpecification(criteria);
        return foodMapper.toDto(foodRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FoodDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FoodDTO> findByCriteria(FoodCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Food> specification = createSpecification(criteria);
        return foodRepository.findAll(specification, page).map(foodMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FoodCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Food> specification = createSpecification(criteria);
        return foodRepository.count(specification);
    }

    /**
     * Function to convert {@link FoodCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Food> createSpecification(FoodCriteria criteria) {
        Specification<Food> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Food_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Food_.name));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), Food_.nameAr));
            }
            if (criteria.getNameEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameEn(), Food_.nameEn));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), Food_.price));
            }
            if (criteria.getDiscountPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiscountPrice(), Food_.discountPrice));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Food_.description));
            }
            if (criteria.getDescriptionAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescriptionAr(), Food_.descriptionAr));
            }
            if (criteria.getDescriptionEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescriptionEn(), Food_.descriptionEn));
            }
            if (criteria.getPackageItemsCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPackageItemsCount(), Food_.packageItemsCount));
            }
            if (criteria.getDailyQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDailyQuantity(), Food_.dailyQuantity));
            }
            if (criteria.getIsAvailable() != null) {
                specification = specification.and(buildSpecification(criteria.getIsAvailable(), Food_.isAvailable));
            }
            if (criteria.getIsDiscount() != null) {
                specification = specification.and(buildSpecification(criteria.getIsDiscount(), Food_.isDiscount));
            }
            if (criteria.getIsFeatured() != null) {
                specification = specification.and(buildSpecification(criteria.getIsFeatured(), Food_.isFeatured));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), Food_.isActive));
            }
            if (criteria.getViewCounter() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getViewCounter(), Food_.viewCounter));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), Food_.notes));
            }
            if (criteria.getRestaurantId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRestaurantId(),
                            root -> root.join(Food_.restaurant, JoinType.LEFT).get(Restaurant_.id)
                        )
                    );
            }
            if (criteria.getCategoryId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCategoryId(), root -> root.join(Food_.category, JoinType.LEFT).get(Category_.id))
                    );
            }
        }
        return specification;
    }
}
