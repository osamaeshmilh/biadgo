package ly.biadjo.food.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.FoodOrder;
import ly.biadjo.food.domain.Order;
import ly.biadjo.food.domain.enumeration.OrderStatus;
import ly.biadjo.food.repository.OrderRepository;
import ly.biadjo.food.service.dto.CartDTO;
import ly.biadjo.food.service.dto.FoodOrderDTO;
import ly.biadjo.food.service.dto.OrderDTO;
import ly.biadjo.food.service.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Order}.
 */
@Service
@Transactional
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    /**
     * Save a order.
     *
     * @param orderDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderDTO save(OrderDTO orderDTO) {
        log.debug("Request to save Order : {}", orderDTO);
        Order order = orderMapper.toEntity(orderDTO);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    /**
     * Update a order.
     *
     * @param orderDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderDTO update(OrderDTO orderDTO) {
        log.debug("Request to update Order : {}", orderDTO);
        Order order = orderMapper.toEntity(orderDTO);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    /**
     * Partially update a order.
     *
     * @param orderDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OrderDTO> partialUpdate(OrderDTO orderDTO) {
        log.debug("Request to partially update Order : {}", orderDTO);

        return orderRepository
            .findById(orderDTO.getId())
            .map(existingOrder -> {
                orderMapper.partialUpdate(existingOrder, orderDTO);

                return existingOrder;
            })
            .map(orderRepository::save)
            .map(orderMapper::toDto);
    }

    /**
     * Get all the orders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Orders");
        return orderRepository.findAll(pageable).map(orderMapper::toDto);
    }

    /**
     * Get all the orders with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<OrderDTO> findAllWithEagerRelationships(Pageable pageable) {
        return orderRepository.findAllWithEagerRelationships(pageable).map(orderMapper::toDto);
    }

    /**
     * Get one order by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrderDTO> findOne(Long id) {
        log.debug("Request to get Order : {}", id);
        return orderRepository.findOneWithEagerRelationships(id).map(orderMapper::toDto);
    }

    /**
     * Delete the order by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Order : {}", id);
        orderRepository.deleteById(id);
    }

    public OrderDTO createCustomerOrder(OrderDTO orderDTO, List<CartDTO> cartList) {

        //TODO :: PRENDING search fro driver and resturant acceptance
        orderDTO.setOrderStatus(OrderStatus.PENDING);
        OrderDTO savedOrder = save(orderDTO);


        double total = 0.0;
        for (CartDTO cart : cartList) {
            FoodOrderDTO foodOrderDTO = new FoodOrderDTO();
            foodOrderDTO.setFood(cart.getFood());
            foodOrderDTO.setOrder(savedOrder);
            foodOrderDTO.setFoodExtraIdsList(cart.getFoodExtraIdsList());
            foodOrderDTO.setFoodIngredientRemovedIds(cart.getFoodIngredientRemovedIds());
            foodOrderDTO.setFoodIngredientIds(cart.getFoodIngredientIds());
            //TODO:: Discount Price
            foodOrderDTO.setPrice(cart.getFood().getPrice());
            foodOrderDTO.setQuantity(cart.getQuantity());
            foodOrderDTO.setTotal(cart.getQuantity() * cart.getFood().getPrice());
            foodOrderDTO.setSpecialNotes(cart.getCustomerNotes());

            total += cart.getQuantity() * cart.getFood().getPrice();
        }
        orderDTO.setTotal(total);
        String orderNumber = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + "-" + String.format("%06d", savedOrder.getId());
        savedOrder.setOrderNo(orderNumber);

        return save(savedOrder);
    }
}
