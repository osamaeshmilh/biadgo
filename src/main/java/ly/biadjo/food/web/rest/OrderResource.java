package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.OrderRepository;
import ly.biadjo.food.security.AuthoritiesConstants;
import ly.biadjo.food.security.SecurityUtils;
import ly.biadjo.food.service.*;
import ly.biadjo.food.service.criteria.CartCriteria;
import ly.biadjo.food.service.criteria.FoodOrderCriteria;
import ly.biadjo.food.service.criteria.OrderCriteria;
import ly.biadjo.food.service.dto.CartDTO;
import ly.biadjo.food.service.dto.OrderDTO;
import ly.biadjo.food.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.biadjo.food.domain.Order}.
 */
@RestController
@RequestMapping("/api")
public class OrderResource {

    private final Logger log = LoggerFactory.getLogger(OrderResource.class);

    private static final String ENTITY_NAME = "order";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderService orderService;

    private final CustomerService customerService;

    private final OrderRepository orderRepository;

    private final OrderQueryService orderQueryService;

    private final CartQueryService cartQueryService;

    private final FoodOrderQueryService foodOrderQueryService;


    public OrderResource(OrderService orderService, CustomerService customerService, OrderRepository orderRepository, OrderQueryService orderQueryService, CartQueryService cartQueryService, CartService cartService, FoodOrderQueryService foodOrderQueryService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.orderRepository = orderRepository;
        this.orderQueryService = orderQueryService;
        this.cartQueryService = cartQueryService;
        this.foodOrderQueryService = foodOrderQueryService;
    }

    /**
     * {@code POST  /orders} : Create a new order.
     *
     * @param orderDTO the orderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderDTO, or with status {@code 400 (Bad Request)} if the order has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws URISyntaxException {
        log.debug("REST request to save Order : {}", orderDTO);
        if (orderDTO.getId() != null) {
            throw new BadRequestAlertException("A new order cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderDTO result = null;
        if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CUSTOMER)) {
            orderDTO.setCustomer(customerService.findOneDTOByUser());

            CartCriteria criteria = new CartCriteria();
            LongFilter longFilter = new LongFilter();
            longFilter.setEquals(customerService.findOneDTOByUser().getId());
            criteria.setCustomerId(longFilter);
            List<CartDTO> cartList = cartQueryService.findByCriteria(criteria);

            result = orderService.createCustomerOrder(orderDTO, cartList);


        }
        return ResponseEntity
            .created(new URI("/api/orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    /**
     * {@code PUT  /orders/:id} : Updates an existing order.
     *
     * @param id       the id of the orderDTO to save.
     * @param orderDTO the orderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderDTO,
     * or with status {@code 400 (Bad Request)} if the orderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> updateOrder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderDTO orderDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Order : {}, {}", id, orderDTO);
        if (orderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OrderDTO result = orderService.update(orderDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, orderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /orders/:id} : Partial updates given fields of an existing order, field will ignore if it is null
     *
     * @param id       the id of the orderDTO to save.
     * @param orderDTO the orderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderDTO,
     * or with status {@code 400 (Bad Request)} if the orderDTO is not valid,
     * or with status {@code 404 (Not Found)} if the orderDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the orderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/orders/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<OrderDTO> partialUpdateOrder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderDTO orderDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Order partially : {}, {}", id, orderDTO);
        if (orderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OrderDTO> result = orderService.partialUpdate(orderDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, orderDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /orders} : get all the orders.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orders in body.
     */
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders(
        OrderCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Orders by criteria: {}", criteria);

        Page<OrderDTO> page = orderQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /orders/count} : count all the orders.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/orders/count")
    public ResponseEntity<Long> countOrders(OrderCriteria criteria) {
        log.debug("REST request to count Orders by criteria: {}", criteria);
        return ResponseEntity.ok().body(orderQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /orders/:id} : get the "id" order.
     *
     * @param id the id of the orderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        log.debug("REST request to get Order : {}", id);

        Optional<OrderDTO> orderDTO = orderService.findOne(id);

        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(orderDTO.get().getId());

        FoodOrderCriteria foodOrderCriteria = new FoodOrderCriteria();
        foodOrderCriteria.setOrderId(longFilter);

        orderDTO.get().setFoodOrders(foodOrderQueryService.findByCriteria(foodOrderCriteria));

        return ResponseUtil.wrapOrNotFound(orderDTO);
    }

    /**
     * {@code DELETE  /orders/:id} : delete the "id" order.
     *
     * @param id the id of the orderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        log.debug("REST request to delete Order : {}", id);
        orderService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
