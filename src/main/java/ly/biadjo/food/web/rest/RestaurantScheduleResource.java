package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.RestaurantScheduleRepository;
import ly.biadjo.food.service.RestaurantScheduleQueryService;
import ly.biadjo.food.service.RestaurantScheduleService;
import ly.biadjo.food.service.criteria.RestaurantScheduleCriteria;
import ly.biadjo.food.service.dto.RestaurantScheduleDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.RestaurantSchedule}.
 */
@RestController
@RequestMapping("/api")
public class RestaurantScheduleResource {

    private final Logger log = LoggerFactory.getLogger(RestaurantScheduleResource.class);

    private static final String ENTITY_NAME = "restaurantSchedule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RestaurantScheduleService restaurantScheduleService;

    private final RestaurantScheduleRepository restaurantScheduleRepository;

    private final RestaurantScheduleQueryService restaurantScheduleQueryService;

    public RestaurantScheduleResource(
        RestaurantScheduleService restaurantScheduleService,
        RestaurantScheduleRepository restaurantScheduleRepository,
        RestaurantScheduleQueryService restaurantScheduleQueryService
    ) {
        this.restaurantScheduleService = restaurantScheduleService;
        this.restaurantScheduleRepository = restaurantScheduleRepository;
        this.restaurantScheduleQueryService = restaurantScheduleQueryService;
    }

    /**
     * {@code POST  /restaurant-schedules} : Create a new restaurantSchedule.
     *
     * @param restaurantScheduleDTO the restaurantScheduleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new restaurantScheduleDTO, or with status {@code 400 (Bad Request)} if the restaurantSchedule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/restaurant-schedules")
    public ResponseEntity<RestaurantScheduleDTO> createRestaurantSchedule(@RequestBody RestaurantScheduleDTO restaurantScheduleDTO)
        throws URISyntaxException {
        log.debug("REST request to save RestaurantSchedule : {}", restaurantScheduleDTO);
        if (restaurantScheduleDTO.getId() != null) {
            throw new BadRequestAlertException("A new restaurantSchedule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RestaurantScheduleDTO result = restaurantScheduleService.save(restaurantScheduleDTO);
        return ResponseEntity
            .created(new URI("/api/restaurant-schedules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /restaurant-schedules/:id} : Updates an existing restaurantSchedule.
     *
     * @param id                    the id of the restaurantScheduleDTO to save.
     * @param restaurantScheduleDTO the restaurantScheduleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantScheduleDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantScheduleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the restaurantScheduleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/restaurant-schedules/{id}")
    public ResponseEntity<RestaurantScheduleDTO> updateRestaurantSchedule(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantScheduleDTO restaurantScheduleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RestaurantSchedule : {}, {}", id, restaurantScheduleDTO);
        if (restaurantScheduleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantScheduleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantScheduleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RestaurantScheduleDTO result = restaurantScheduleService.update(restaurantScheduleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantScheduleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /restaurant-schedules/:id} : Partial updates given fields of an existing restaurantSchedule, field will ignore if it is null
     *
     * @param id                    the id of the restaurantScheduleDTO to save.
     * @param restaurantScheduleDTO the restaurantScheduleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantScheduleDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantScheduleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the restaurantScheduleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the restaurantScheduleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/restaurant-schedules/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<RestaurantScheduleDTO> partialUpdateRestaurantSchedule(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantScheduleDTO restaurantScheduleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RestaurantSchedule partially : {}, {}", id, restaurantScheduleDTO);
        if (restaurantScheduleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantScheduleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantScheduleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RestaurantScheduleDTO> result = restaurantScheduleService.partialUpdate(restaurantScheduleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantScheduleDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /restaurant-schedules} : get all the restaurantSchedules.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of restaurantSchedules in body.
     */
    @GetMapping("/restaurant-schedules")
    public ResponseEntity<List<RestaurantScheduleDTO>> getAllRestaurantSchedules(
        RestaurantScheduleCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RestaurantSchedules by criteria: {}", criteria);

        Page<RestaurantScheduleDTO> page = restaurantScheduleQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /restaurant-schedules/count} : count all the restaurantSchedules.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/restaurant-schedules/count")
    public ResponseEntity<Long> countRestaurantSchedules(RestaurantScheduleCriteria criteria) {
        log.debug("REST request to count RestaurantSchedules by criteria: {}", criteria);
        return ResponseEntity.ok().body(restaurantScheduleQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /restaurant-schedules/:id} : get the "id" restaurantSchedule.
     *
     * @param id the id of the restaurantScheduleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the restaurantScheduleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/restaurant-schedules/{id}")
    public ResponseEntity<RestaurantScheduleDTO> getRestaurantSchedule(@PathVariable Long id) {
        log.debug("REST request to get RestaurantSchedule : {}", id);
        Optional<RestaurantScheduleDTO> restaurantScheduleDTO = restaurantScheduleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(restaurantScheduleDTO);
    }

    /**
     * {@code DELETE  /restaurant-schedules/:id} : delete the "id" restaurantSchedule.
     *
     * @param id the id of the restaurantScheduleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/restaurant-schedules/{id}")
    public ResponseEntity<Void> deleteRestaurantSchedule(@PathVariable Long id) {
        log.debug("REST request to delete RestaurantSchedule : {}", id);
        restaurantScheduleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
