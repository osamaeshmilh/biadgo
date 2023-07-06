package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.FoodOrderRepository;
import ly.biadjo.food.service.FoodOrderQueryService;
import ly.biadjo.food.service.FoodOrderService;
import ly.biadjo.food.service.criteria.FoodOrderCriteria;
import ly.biadjo.food.service.dto.FoodOrderDTO;
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
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.biadjo.food.domain.FoodOrder}.
 */
@RestController
@RequestMapping("/api")
public class FoodOrderResource {

    private final Logger log = LoggerFactory.getLogger(FoodOrderResource.class);

    private static final String ENTITY_NAME = "foodOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FoodOrderService foodOrderService;

    private final FoodOrderRepository foodOrderRepository;

    private final FoodOrderQueryService foodOrderQueryService;

    public FoodOrderResource(
        FoodOrderService foodOrderService,
        FoodOrderRepository foodOrderRepository,
        FoodOrderQueryService foodOrderQueryService
    ) {
        this.foodOrderService = foodOrderService;
        this.foodOrderRepository = foodOrderRepository;
        this.foodOrderQueryService = foodOrderQueryService;
    }

    /**
     * {@code POST  /food-orders} : Create a new foodOrder.
     *
     * @param foodOrderDTO the foodOrderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new foodOrderDTO, or with status {@code 400 (Bad Request)} if the foodOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/food-orders")
    public ResponseEntity<FoodOrderDTO> createFoodOrder(@RequestBody FoodOrderDTO foodOrderDTO) throws URISyntaxException {
        log.debug("REST request to save FoodOrder : {}", foodOrderDTO);
        if (foodOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new foodOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FoodOrderDTO result = foodOrderService.save(foodOrderDTO);
        return ResponseEntity
            .created(new URI("/api/food-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /food-orders/:id} : Updates an existing foodOrder.
     *
     * @param id           the id of the foodOrderDTO to save.
     * @param foodOrderDTO the foodOrderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodOrderDTO,
     * or with status {@code 400 (Bad Request)} if the foodOrderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the foodOrderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/food-orders/{id}")
    public ResponseEntity<FoodOrderDTO> updateFoodOrder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FoodOrderDTO foodOrderDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FoodOrder : {}, {}", id, foodOrderDTO);
        if (foodOrderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, foodOrderDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!foodOrderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FoodOrderDTO result = foodOrderService.update(foodOrderDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, foodOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /food-orders/:id} : Partial updates given fields of an existing foodOrder, field will ignore if it is null
     *
     * @param id           the id of the foodOrderDTO to save.
     * @param foodOrderDTO the foodOrderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodOrderDTO,
     * or with status {@code 400 (Bad Request)} if the foodOrderDTO is not valid,
     * or with status {@code 404 (Not Found)} if the foodOrderDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the foodOrderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/food-orders/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<FoodOrderDTO> partialUpdateFoodOrder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FoodOrderDTO foodOrderDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FoodOrder partially : {}, {}", id, foodOrderDTO);
        if (foodOrderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, foodOrderDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!foodOrderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FoodOrderDTO> result = foodOrderService.partialUpdate(foodOrderDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, foodOrderDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /food-orders} : get all the foodOrders.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foodOrders in body.
     */
    @GetMapping("/food-orders")
    public ResponseEntity<List<FoodOrderDTO>> getAllFoodOrders(
        FoodOrderCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get FoodOrders by criteria: {}", criteria);

        Page<FoodOrderDTO> page = foodOrderQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /food-orders/count} : count all the foodOrders.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/food-orders/count")
    public ResponseEntity<Long> countFoodOrders(FoodOrderCriteria criteria) {
        log.debug("REST request to count FoodOrders by criteria: {}", criteria);
        return ResponseEntity.ok().body(foodOrderQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /food-orders/:id} : get the "id" foodOrder.
     *
     * @param id the id of the foodOrderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the foodOrderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/food-orders/{id}")
    public ResponseEntity<FoodOrderDTO> getFoodOrder(@PathVariable Long id) {
        log.debug("REST request to get FoodOrder : {}", id);
        Optional<FoodOrderDTO> foodOrderDTO = foodOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(foodOrderDTO);
    }

    /**
     * {@code DELETE  /food-orders/:id} : delete the "id" foodOrder.
     *
     * @param id the id of the foodOrderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/food-orders/{id}")
    public ResponseEntity<Void> deleteFoodOrder(@PathVariable Long id) {
        log.debug("REST request to delete FoodOrder : {}", id);
        foodOrderService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
