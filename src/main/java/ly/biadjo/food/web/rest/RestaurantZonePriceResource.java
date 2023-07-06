package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.RestaurantZonePriceRepository;
import ly.biadjo.food.service.RestaurantZonePriceQueryService;
import ly.biadjo.food.service.RestaurantZonePriceService;
import ly.biadjo.food.service.criteria.RestaurantZonePriceCriteria;
import ly.biadjo.food.service.dto.RestaurantZonePriceDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.RestaurantZonePrice}.
 */
@RestController
@RequestMapping("/api")
public class RestaurantZonePriceResource {

    private final Logger log = LoggerFactory.getLogger(RestaurantZonePriceResource.class);

    private static final String ENTITY_NAME = "restaurantZonePrice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RestaurantZonePriceService restaurantZonePriceService;

    private final RestaurantZonePriceRepository restaurantZonePriceRepository;

    private final RestaurantZonePriceQueryService restaurantZonePriceQueryService;

    public RestaurantZonePriceResource(
        RestaurantZonePriceService restaurantZonePriceService,
        RestaurantZonePriceRepository restaurantZonePriceRepository,
        RestaurantZonePriceQueryService restaurantZonePriceQueryService
    ) {
        this.restaurantZonePriceService = restaurantZonePriceService;
        this.restaurantZonePriceRepository = restaurantZonePriceRepository;
        this.restaurantZonePriceQueryService = restaurantZonePriceQueryService;
    }

    /**
     * {@code POST  /restaurant-zone-prices} : Create a new restaurantZonePrice.
     *
     * @param restaurantZonePriceDTO the restaurantZonePriceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new restaurantZonePriceDTO, or with status {@code 400 (Bad Request)} if the restaurantZonePrice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/restaurant-zone-prices")
    public ResponseEntity<RestaurantZonePriceDTO> createRestaurantZonePrice(@RequestBody RestaurantZonePriceDTO restaurantZonePriceDTO)
        throws URISyntaxException {
        log.debug("REST request to save RestaurantZonePrice : {}", restaurantZonePriceDTO);
        if (restaurantZonePriceDTO.getId() != null) {
            throw new BadRequestAlertException("A new restaurantZonePrice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RestaurantZonePriceDTO result = restaurantZonePriceService.save(restaurantZonePriceDTO);
        return ResponseEntity
            .created(new URI("/api/restaurant-zone-prices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /restaurant-zone-prices/:id} : Updates an existing restaurantZonePrice.
     *
     * @param id                     the id of the restaurantZonePriceDTO to save.
     * @param restaurantZonePriceDTO the restaurantZonePriceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantZonePriceDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantZonePriceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the restaurantZonePriceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/restaurant-zone-prices/{id}")
    public ResponseEntity<RestaurantZonePriceDTO> updateRestaurantZonePrice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantZonePriceDTO restaurantZonePriceDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RestaurantZonePrice : {}, {}", id, restaurantZonePriceDTO);
        if (restaurantZonePriceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantZonePriceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantZonePriceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RestaurantZonePriceDTO result = restaurantZonePriceService.update(restaurantZonePriceDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantZonePriceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /restaurant-zone-prices/:id} : Partial updates given fields of an existing restaurantZonePrice, field will ignore if it is null
     *
     * @param id                     the id of the restaurantZonePriceDTO to save.
     * @param restaurantZonePriceDTO the restaurantZonePriceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantZonePriceDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantZonePriceDTO is not valid,
     * or with status {@code 404 (Not Found)} if the restaurantZonePriceDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the restaurantZonePriceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/restaurant-zone-prices/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<RestaurantZonePriceDTO> partialUpdateRestaurantZonePrice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantZonePriceDTO restaurantZonePriceDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RestaurantZonePrice partially : {}, {}", id, restaurantZonePriceDTO);
        if (restaurantZonePriceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantZonePriceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantZonePriceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RestaurantZonePriceDTO> result = restaurantZonePriceService.partialUpdate(restaurantZonePriceDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantZonePriceDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /restaurant-zone-prices} : get all the restaurantZonePrices.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of restaurantZonePrices in body.
     */
    @GetMapping("/restaurant-zone-prices")
    public ResponseEntity<List<RestaurantZonePriceDTO>> getAllRestaurantZonePrices(
        RestaurantZonePriceCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RestaurantZonePrices by criteria: {}", criteria);

        Page<RestaurantZonePriceDTO> page = restaurantZonePriceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /restaurant-zone-prices/count} : count all the restaurantZonePrices.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/restaurant-zone-prices/count")
    public ResponseEntity<Long> countRestaurantZonePrices(RestaurantZonePriceCriteria criteria) {
        log.debug("REST request to count RestaurantZonePrices by criteria: {}", criteria);
        return ResponseEntity.ok().body(restaurantZonePriceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /restaurant-zone-prices/:id} : get the "id" restaurantZonePrice.
     *
     * @param id the id of the restaurantZonePriceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the restaurantZonePriceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/restaurant-zone-prices/{id}")
    public ResponseEntity<RestaurantZonePriceDTO> getRestaurantZonePrice(@PathVariable Long id) {
        log.debug("REST request to get RestaurantZonePrice : {}", id);
        Optional<RestaurantZonePriceDTO> restaurantZonePriceDTO = restaurantZonePriceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(restaurantZonePriceDTO);
    }

    /**
     * {@code DELETE  /restaurant-zone-prices/:id} : delete the "id" restaurantZonePrice.
     *
     * @param id the id of the restaurantZonePriceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/restaurant-zone-prices/{id}")
    public ResponseEntity<Void> deleteRestaurantZonePrice(@PathVariable Long id) {
        log.debug("REST request to delete RestaurantZonePrice : {}", id);
        restaurantZonePriceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
