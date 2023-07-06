package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.Cart;
import ly.biadjo.food.repository.CartRepository;
import ly.biadjo.food.service.criteria.CartCriteria;
import ly.biadjo.food.service.dto.CartDTO;
import ly.biadjo.food.service.mapper.CartMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Cart} entities in the database.
 * The main input is a {@link CartCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CartDTO} or a {@link Page} of {@link CartDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CartQueryService extends QueryService<Cart> {

    private final Logger log = LoggerFactory.getLogger(CartQueryService.class);

    private final CartRepository cartRepository;

    private final CartMapper cartMapper;

    public CartQueryService(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    /**
     * Return a {@link List} of {@link CartDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CartDTO> findByCriteria(CartCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Cart> specification = createSpecification(criteria);
        return cartMapper.toDto(cartRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CartDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CartDTO> findByCriteria(CartCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Cart> specification = createSpecification(criteria);
        return cartRepository.findAll(specification, page).map(cartMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CartCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Cart> specification = createSpecification(criteria);
        return cartRepository.count(specification);
    }

    /**
     * Function to convert {@link CartCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Cart> createSpecification(CartCriteria criteria) {
        Specification<Cart> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Cart_.id));
            }
            if (criteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQuantity(), Cart_.quantity));
            }
            if (criteria.getCustomerNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCustomerNotes(), Cart_.customerNotes));
            }
            if (criteria.getFoodExtraIdsList() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFoodExtraIdsList(), Cart_.foodExtraIdsList));
            }
            if (criteria.getFoodIngredientIds() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFoodIngredientIds(), Cart_.foodIngredientIds));
            }
            if (criteria.getFoodIngredientRemovedIds() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getFoodIngredientRemovedIds(), Cart_.foodIngredientRemovedIds));
            }
            if (criteria.getCustomerId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCustomerId(), root -> root.join(Cart_.customer, JoinType.LEFT).get(Customer_.id))
                    );
            }
            if (criteria.getFoodId() != null) {
                specification =
                    specification.and(buildSpecification(criteria.getFoodId(), root -> root.join(Cart_.food, JoinType.LEFT).get(Food_.id)));
            }
        }
        return specification;
    }
}
