package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.RestaurantReviewRepository;
import ly.biadjo.food.service.RestaurantReviewQueryService;
import ly.biadjo.food.service.RestaurantReviewService;
import ly.biadjo.food.service.criteria.RestaurantReviewCriteria;
import ly.biadjo.food.service.dto.RestaurantReviewDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.RestaurantReview}.
 */
@RestController
@RequestMapping("/api")
public class RestaurantReviewResource {

    private final Logger log = LoggerFactory.getLogger(RestaurantReviewResource.class);

    private static final String ENTITY_NAME = "restaurantReview";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RestaurantReviewService restaurantReviewService;

    private final RestaurantReviewRepository restaurantReviewRepository;

    private final RestaurantReviewQueryService restaurantReviewQueryService;

    public RestaurantReviewResource(
        RestaurantReviewService restaurantReviewService,
        RestaurantReviewRepository restaurantReviewRepository,
        RestaurantReviewQueryService restaurantReviewQueryService
    ) {
        this.restaurantReviewService = restaurantReviewService;
        this.restaurantReviewRepository = restaurantReviewRepository;
        this.restaurantReviewQueryService = restaurantReviewQueryService;
    }

    /**
     * {@code POST  /restaurant-reviews} : Create a new restaurantReview.
     *
     * @param restaurantReviewDTO the restaurantReviewDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new restaurantReviewDTO, or with status {@code 400 (Bad Request)} if the restaurantReview has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/restaurant-reviews")
    public ResponseEntity<RestaurantReviewDTO> createRestaurantReview(@RequestBody RestaurantReviewDTO restaurantReviewDTO)
        throws URISyntaxException {
        log.debug("REST request to save RestaurantReview : {}", restaurantReviewDTO);
        if (restaurantReviewDTO.getId() != null) {
            throw new BadRequestAlertException("A new restaurantReview cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RestaurantReviewDTO result = restaurantReviewService.save(restaurantReviewDTO);
        return ResponseEntity
            .created(new URI("/api/restaurant-reviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /restaurant-reviews/:id} : Updates an existing restaurantReview.
     *
     * @param id                  the id of the restaurantReviewDTO to save.
     * @param restaurantReviewDTO the restaurantReviewDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantReviewDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantReviewDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the restaurantReviewDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/restaurant-reviews/{id}")
    public ResponseEntity<RestaurantReviewDTO> updateRestaurantReview(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantReviewDTO restaurantReviewDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RestaurantReview : {}, {}", id, restaurantReviewDTO);
        if (restaurantReviewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantReviewDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantReviewRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RestaurantReviewDTO result = restaurantReviewService.update(restaurantReviewDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantReviewDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /restaurant-reviews/:id} : Partial updates given fields of an existing restaurantReview, field will ignore if it is null
     *
     * @param id                  the id of the restaurantReviewDTO to save.
     * @param restaurantReviewDTO the restaurantReviewDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantReviewDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantReviewDTO is not valid,
     * or with status {@code 404 (Not Found)} if the restaurantReviewDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the restaurantReviewDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/restaurant-reviews/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<RestaurantReviewDTO> partialUpdateRestaurantReview(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantReviewDTO restaurantReviewDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RestaurantReview partially : {}, {}", id, restaurantReviewDTO);
        if (restaurantReviewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantReviewDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantReviewRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RestaurantReviewDTO> result = restaurantReviewService.partialUpdate(restaurantReviewDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantReviewDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /restaurant-reviews} : get all the restaurantReviews.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of restaurantReviews in body.
     */
    @GetMapping("/restaurant-reviews")
    public ResponseEntity<List<RestaurantReviewDTO>> getAllRestaurantReviews(
        RestaurantReviewCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RestaurantReviews by criteria: {}", criteria);

        Page<RestaurantReviewDTO> page = restaurantReviewQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /restaurant-reviews/count} : count all the restaurantReviews.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/restaurant-reviews/count")
    public ResponseEntity<Long> countRestaurantReviews(RestaurantReviewCriteria criteria) {
        log.debug("REST request to count RestaurantReviews by criteria: {}", criteria);
        return ResponseEntity.ok().body(restaurantReviewQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /restaurant-reviews/:id} : get the "id" restaurantReview.
     *
     * @param id the id of the restaurantReviewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the restaurantReviewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/restaurant-reviews/{id}")
    public ResponseEntity<RestaurantReviewDTO> getRestaurantReview(@PathVariable Long id) {
        log.debug("REST request to get RestaurantReview : {}", id);
        Optional<RestaurantReviewDTO> restaurantReviewDTO = restaurantReviewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(restaurantReviewDTO);
    }

    /**
     * {@code DELETE  /restaurant-reviews/:id} : delete the "id" restaurantReview.
     *
     * @param id the id of the restaurantReviewDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/restaurant-reviews/{id}")
    public ResponseEntity<Void> deleteRestaurantReview(@PathVariable Long id) {
        log.debug("REST request to delete RestaurantReview : {}", id);
        restaurantReviewService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
