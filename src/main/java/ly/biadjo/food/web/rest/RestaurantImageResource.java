package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.RestaurantImageRepository;
import ly.biadjo.food.service.RestaurantImageQueryService;
import ly.biadjo.food.service.RestaurantImageService;
import ly.biadjo.food.service.criteria.RestaurantImageCriteria;
import ly.biadjo.food.service.dto.RestaurantImageDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.RestaurantImage}.
 */
@RestController
@RequestMapping("/api")
public class RestaurantImageResource {

    private final Logger log = LoggerFactory.getLogger(RestaurantImageResource.class);

    private static final String ENTITY_NAME = "restaurantImage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RestaurantImageService restaurantImageService;

    private final RestaurantImageRepository restaurantImageRepository;

    private final RestaurantImageQueryService restaurantImageQueryService;

    public RestaurantImageResource(
        RestaurantImageService restaurantImageService,
        RestaurantImageRepository restaurantImageRepository,
        RestaurantImageQueryService restaurantImageQueryService
    ) {
        this.restaurantImageService = restaurantImageService;
        this.restaurantImageRepository = restaurantImageRepository;
        this.restaurantImageQueryService = restaurantImageQueryService;
    }

    /**
     * {@code POST  /restaurant-images} : Create a new restaurantImage.
     *
     * @param restaurantImageDTO the restaurantImageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new restaurantImageDTO, or with status {@code 400 (Bad Request)} if the restaurantImage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/restaurant-images")
    public ResponseEntity<RestaurantImageDTO> createRestaurantImage(@RequestBody RestaurantImageDTO restaurantImageDTO)
        throws URISyntaxException {
        log.debug("REST request to save RestaurantImage : {}", restaurantImageDTO);
        if (restaurantImageDTO.getId() != null) {
            throw new BadRequestAlertException("A new restaurantImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RestaurantImageDTO result = restaurantImageService.save(restaurantImageDTO);
        return ResponseEntity
            .created(new URI("/api/restaurant-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /restaurant-images/:id} : Updates an existing restaurantImage.
     *
     * @param id                 the id of the restaurantImageDTO to save.
     * @param restaurantImageDTO the restaurantImageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantImageDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantImageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the restaurantImageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/restaurant-images/{id}")
    public ResponseEntity<RestaurantImageDTO> updateRestaurantImage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantImageDTO restaurantImageDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RestaurantImage : {}, {}", id, restaurantImageDTO);
        if (restaurantImageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantImageDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantImageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RestaurantImageDTO result = restaurantImageService.update(restaurantImageDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantImageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /restaurant-images/:id} : Partial updates given fields of an existing restaurantImage, field will ignore if it is null
     *
     * @param id                 the id of the restaurantImageDTO to save.
     * @param restaurantImageDTO the restaurantImageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantImageDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantImageDTO is not valid,
     * or with status {@code 404 (Not Found)} if the restaurantImageDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the restaurantImageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/restaurant-images/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<RestaurantImageDTO> partialUpdateRestaurantImage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantImageDTO restaurantImageDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RestaurantImage partially : {}, {}", id, restaurantImageDTO);
        if (restaurantImageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantImageDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantImageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RestaurantImageDTO> result = restaurantImageService.partialUpdate(restaurantImageDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantImageDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /restaurant-images} : get all the restaurantImages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of restaurantImages in body.
     */
    @GetMapping("/restaurant-images")
    public ResponseEntity<List<RestaurantImageDTO>> getAllRestaurantImages(
        RestaurantImageCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RestaurantImages by criteria: {}", criteria);

        Page<RestaurantImageDTO> page = restaurantImageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /restaurant-images/count} : count all the restaurantImages.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/restaurant-images/count")
    public ResponseEntity<Long> countRestaurantImages(RestaurantImageCriteria criteria) {
        log.debug("REST request to count RestaurantImages by criteria: {}", criteria);
        return ResponseEntity.ok().body(restaurantImageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /restaurant-images/:id} : get the "id" restaurantImage.
     *
     * @param id the id of the restaurantImageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the restaurantImageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/restaurant-images/{id}")
    public ResponseEntity<RestaurantImageDTO> getRestaurantImage(@PathVariable Long id) {
        log.debug("REST request to get RestaurantImage : {}", id);
        Optional<RestaurantImageDTO> restaurantImageDTO = restaurantImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(restaurantImageDTO);
    }

    /**
     * {@code DELETE  /restaurant-images/:id} : delete the "id" restaurantImage.
     *
     * @param id the id of the restaurantImageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/restaurant-images/{id}")
    public ResponseEntity<Void> deleteRestaurantImage(@PathVariable Long id) {
        log.debug("REST request to delete RestaurantImage : {}", id);
        restaurantImageService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
