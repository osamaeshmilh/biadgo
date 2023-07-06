package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.Order;
import ly.biadjo.food.repository.OrderRepository;
import ly.biadjo.food.service.criteria.OrderCriteria;
import ly.biadjo.food.service.dto.OrderDTO;
import ly.biadjo.food.service.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Order} entities in the database.
 * The main input is a {@link OrderCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrderDTO} or a {@link Page} of {@link OrderDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrderQueryService extends QueryService<Order> {

    private final Logger log = LoggerFactory.getLogger(OrderQueryService.class);

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public OrderQueryService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    /**
     * Return a {@link List} of {@link OrderDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrderDTO> findByCriteria(OrderCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Order> specification = createSpecification(criteria);
        return orderMapper.toDto(orderRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrderDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderDTO> findByCriteria(OrderCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Order> specification = createSpecification(criteria);
        return orderRepository.findAll(specification, page).map(orderMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrderCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Order> specification = createSpecification(criteria);
        return orderRepository.count(specification);
    }

    /**
     * Function to convert {@link OrderCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Order> createSpecification(OrderCriteria criteria) {
        Specification<Order> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Order_.id));
            }
            if (criteria.getOrderNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrderNo(), Order_.orderNo));
            }
            if (criteria.getCompanyAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCompanyAmount(), Order_.companyAmount));
            }
            if (criteria.getRestaurantAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRestaurantAmount(), Order_.restaurantAmount));
            }
            if (criteria.getDeliveryFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeliveryFee(), Order_.deliveryFee));
            }
            if (criteria.getItemsPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getItemsPrice(), Order_.itemsPrice));
            }
            if (criteria.getDiscount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDiscount(), Order_.discount));
            }
            if (criteria.getTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotal(), Order_.total));
            }
            if (criteria.getLatitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatitude(), Order_.latitude));
            }
            if (criteria.getLongitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLongitude(), Order_.longitude));
            }
            if (criteria.getPlusCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPlusCode(), Order_.plusCode));
            }
            if (criteria.getPaymentType() != null) {
                specification = specification.and(buildSpecification(criteria.getPaymentType(), Order_.paymentType));
            }
            if (criteria.getIsApprovedByDriver() != null) {
                specification = specification.and(buildSpecification(criteria.getIsApprovedByDriver(), Order_.isApprovedByDriver));
            }
            if (criteria.getIsApprovedByRestaurant() != null) {
                specification = specification.and(buildSpecification(criteria.getIsApprovedByRestaurant(), Order_.isApprovedByRestaurant));
            }
            if (criteria.getIsPayed() != null) {
                specification = specification.and(buildSpecification(criteria.getIsPayed(), Order_.isPayed));
            }
            if (criteria.getAmountRecived() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmountRecived(), Order_.amountRecived));
            }
            if (criteria.getAmountRemaining() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmountRemaining(), Order_.amountRemaining));
            }
            if (criteria.getCustomerNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCustomerNotes(), Order_.customerNotes));
            }
            if (criteria.getRestaurantNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRestaurantNotes(), Order_.restaurantNotes));
            }
            if (criteria.getDriverNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDriverNotes(), Order_.driverNotes));
            }
            if (criteria.getOrderStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getOrderStatus(), Order_.orderStatus));
            }
            if (criteria.getOrderType() != null) {
                specification = specification.and(buildSpecification(criteria.getOrderType(), Order_.orderType));
            }
            if (criteria.getDeliveredAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeliveredAt(), Order_.deliveredAt));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), Order_.notes));
            }
            if (criteria.getCustomerId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCustomerId(), root -> root.join(Order_.customer, JoinType.LEFT).get(Customer_.id))
                    );
            }
            if (criteria.getCouponId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCouponId(), root -> root.join(Order_.coupon, JoinType.LEFT).get(Coupon_.id))
                    );
            }
            if (criteria.getDriverId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getDriverId(), root -> root.join(Order_.driver, JoinType.LEFT).get(Driver_.id))
                    );
            }
            if (criteria.getDeliveryAddressId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getDeliveryAddressId(),
                            root -> root.join(Order_.deliveryAddress, JoinType.LEFT).get(DeliveryAddress_.id)
                        )
                    );
            }
            if (criteria.getRestaurantId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRestaurantId(),
                            root -> root.join(Order_.restaurant, JoinType.LEFT).get(Restaurant_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
