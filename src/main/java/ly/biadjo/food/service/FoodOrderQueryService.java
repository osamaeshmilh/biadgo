package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.FoodOrder;
import ly.biadjo.food.repository.FoodOrderRepository;
import ly.biadjo.food.service.criteria.FoodOrderCriteria;
import ly.biadjo.food.service.dto.FoodOrderDTO;
import ly.biadjo.food.service.mapper.FoodOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link FoodOrder} entities in the database.
 * The main input is a {@link FoodOrderCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FoodOrderDTO} or a {@link Page} of {@link FoodOrderDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FoodOrderQueryService extends QueryService<FoodOrder> {

    private final Logger log = LoggerFactory.getLogger(FoodOrderQueryService.class);

    private final FoodOrderRepository foodOrderRepository;

    private final FoodOrderMapper foodOrderMapper;

    public FoodOrderQueryService(FoodOrderRepository foodOrderRepository, FoodOrderMapper foodOrderMapper) {
        this.foodOrderRepository = foodOrderRepository;
        this.foodOrderMapper = foodOrderMapper;
    }

    /**
     * Return a {@link List} of {@link FoodOrderDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FoodOrderDTO> findByCriteria(FoodOrderCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FoodOrder> specification = createSpecification(criteria);
        return foodOrderMapper.toDto(foodOrderRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FoodOrderDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FoodOrderDTO> findByCriteria(FoodOrderCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FoodOrder> specification = createSpecification(criteria);
        return foodOrderRepository.findAll(specification, page).map(foodOrderMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FoodOrderCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FoodOrder> specification = createSpecification(criteria);
        return foodOrderRepository.count(specification);
    }

    /**
     * Function to convert {@link FoodOrderCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<FoodOrder> createSpecification(FoodOrderCriteria criteria) {
        Specification<FoodOrder> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FoodOrder_.id));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), FoodOrder_.price));
            }
            if (criteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantity(), FoodOrder_.quantity));
            }
            if (criteria.getTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotal(), FoodOrder_.total));
            }
            if (criteria.getSpecialNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSpecialNotes(), FoodOrder_.specialNotes));
            }
            if (criteria.getFoodExtraIdsList() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFoodExtraIdsList(), FoodOrder_.foodExtraIdsList));
            }
            if (criteria.getFoodIngredientIds() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFoodIngredientIds(), FoodOrder_.foodIngredientIds));
            }
            if (criteria.getFoodIngredientRemovedIds() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getFoodIngredientRemovedIds(), FoodOrder_.foodIngredientRemovedIds)
                    );
            }
            if (criteria.getOrderId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getOrderId(), root -> root.join(FoodOrder_.order, JoinType.LEFT).get(Order_.id))
                    );
            }
        }
        return specification;
    }
}
