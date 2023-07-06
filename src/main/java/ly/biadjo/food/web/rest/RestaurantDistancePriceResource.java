package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.RestaurantDistancePriceRepository;
import ly.biadjo.food.service.RestaurantDistancePriceQueryService;
import ly.biadjo.food.service.RestaurantDistancePriceService;
import ly.biadjo.food.service.criteria.RestaurantDistancePriceCriteria;
import ly.biadjo.food.service.dto.RestaurantDistancePriceDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.RestaurantDistancePrice}.
 */
@RestController
@RequestMapping("/api")
public class RestaurantDistancePriceResource {

    private final Logger log = LoggerFactory.getLogger(RestaurantDistancePriceResource.class);

    private static final String ENTITY_NAME = "restaurantDistancePrice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RestaurantDistancePriceService restaurantDistancePriceService;

    private final RestaurantDistancePriceRepository restaurantDistancePriceRepository;

    private final RestaurantDistancePriceQueryService restaurantDistancePriceQueryService;

    public RestaurantDistancePriceResource(
        RestaurantDistancePriceService restaurantDistancePriceService,
        RestaurantDistancePriceRepository restaurantDistancePriceRepository,
        RestaurantDistancePriceQueryService restaurantDistancePriceQueryService
    ) {
        this.restaurantDistancePriceService = restaurantDistancePriceService;
        this.restaurantDistancePriceRepository = restaurantDistancePriceRepository;
        this.restaurantDistancePriceQueryService = restaurantDistancePriceQueryService;
    }

    /**
     * {@code POST  /restaurant-distance-prices} : Create a new restaurantDistancePrice.
     *
     * @param restaurantDistancePriceDTO the restaurantDistancePriceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new restaurantDistancePriceDTO, or with status {@code 400 (Bad Request)} if the restaurantDistancePrice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/restaurant-distance-prices")
    public ResponseEntity<RestaurantDistancePriceDTO> createRestaurantDistancePrice(
        @RequestBody RestaurantDistancePriceDTO restaurantDistancePriceDTO
    ) throws URISyntaxException {
        log.debug("REST request to save RestaurantDistancePrice : {}", restaurantDistancePriceDTO);
        if (restaurantDistancePriceDTO.getId() != null) {
            throw new BadRequestAlertException("A new restaurantDistancePrice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RestaurantDistancePriceDTO result = restaurantDistancePriceService.save(restaurantDistancePriceDTO);
        return ResponseEntity
            .created(new URI("/api/restaurant-distance-prices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /restaurant-distance-prices/:id} : Updates an existing restaurantDistancePrice.
     *
     * @param id                         the id of the restaurantDistancePriceDTO to save.
     * @param restaurantDistancePriceDTO the restaurantDistancePriceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantDistancePriceDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantDistancePriceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the restaurantDistancePriceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/restaurant-distance-prices/{id}")
    public ResponseEntity<RestaurantDistancePriceDTO> updateRestaurantDistancePrice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantDistancePriceDTO restaurantDistancePriceDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RestaurantDistancePrice : {}, {}", id, restaurantDistancePriceDTO);
        if (restaurantDistancePriceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantDistancePriceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantDistancePriceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RestaurantDistancePriceDTO result = restaurantDistancePriceService.update(restaurantDistancePriceDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantDistancePriceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /restaurant-distance-prices/:id} : Partial updates given fields of an existing restaurantDistancePrice, field will ignore if it is null
     *
     * @param id                         the id of the restaurantDistancePriceDTO to save.
     * @param restaurantDistancePriceDTO the restaurantDistancePriceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantDistancePriceDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantDistancePriceDTO is not valid,
     * or with status {@code 404 (Not Found)} if the restaurantDistancePriceDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the restaurantDistancePriceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/restaurant-distance-prices/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<RestaurantDistancePriceDTO> partialUpdateRestaurantDistancePrice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantDistancePriceDTO restaurantDistancePriceDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RestaurantDistancePrice partially : {}, {}", id, restaurantDistancePriceDTO);
        if (restaurantDistancePriceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantDistancePriceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantDistancePriceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RestaurantDistancePriceDTO> result = restaurantDistancePriceService.partialUpdate(restaurantDistancePriceDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantDistancePriceDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /restaurant-distance-prices} : get all the restaurantDistancePrices.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of restaurantDistancePrices in body.
     */
    @GetMapping("/restaurant-distance-prices")
    public ResponseEntity<List<RestaurantDistancePriceDTO>> getAllRestaurantDistancePrices(
        RestaurantDistancePriceCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RestaurantDistancePrices by criteria: {}", criteria);

        Page<RestaurantDistancePriceDTO> page = restaurantDistancePriceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /restaurant-distance-prices/count} : count all the restaurantDistancePrices.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/restaurant-distance-prices/count")
    public ResponseEntity<Long> countRestaurantDistancePrices(RestaurantDistancePriceCriteria criteria) {
        log.debug("REST request to count RestaurantDistancePrices by criteria: {}", criteria);
        return ResponseEntity.ok().body(restaurantDistancePriceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /restaurant-distance-prices/:id} : get the "id" restaurantDistancePrice.
     *
     * @param id the id of the restaurantDistancePriceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the restaurantDistancePriceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/restaurant-distance-prices/{id}")
    public ResponseEntity<RestaurantDistancePriceDTO> getRestaurantDistancePrice(@PathVariable Long id) {
        log.debug("REST request to get RestaurantDistancePrice : {}", id);
        Optional<RestaurantDistancePriceDTO> restaurantDistancePriceDTO = restaurantDistancePriceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(restaurantDistancePriceDTO);
    }

    /**
     * {@code DELETE  /restaurant-distance-prices/:id} : delete the "id" restaurantDistancePrice.
     *
     * @param id the id of the restaurantDistancePriceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/restaurant-distance-prices/{id}")
    public ResponseEntity<Void> deleteRestaurantDistancePrice(@PathVariable Long id) {
        log.debug("REST request to delete RestaurantDistancePrice : {}", id);
        restaurantDistancePriceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
