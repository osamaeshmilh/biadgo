package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.DriverReviewRepository;
import ly.biadjo.food.service.DriverReviewQueryService;
import ly.biadjo.food.service.DriverReviewService;
import ly.biadjo.food.service.criteria.DriverReviewCriteria;
import ly.biadjo.food.service.dto.DriverReviewDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.DriverReview}.
 */
@RestController
@RequestMapping("/api")
public class DriverReviewResource {

    private final Logger log = LoggerFactory.getLogger(DriverReviewResource.class);

    private static final String ENTITY_NAME = "driverReview";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DriverReviewService driverReviewService;

    private final DriverReviewRepository driverReviewRepository;

    private final DriverReviewQueryService driverReviewQueryService;

    public DriverReviewResource(
        DriverReviewService driverReviewService,
        DriverReviewRepository driverReviewRepository,
        DriverReviewQueryService driverReviewQueryService
    ) {
        this.driverReviewService = driverReviewService;
        this.driverReviewRepository = driverReviewRepository;
        this.driverReviewQueryService = driverReviewQueryService;
    }

    /**
     * {@code POST  /driver-reviews} : Create a new driverReview.
     *
     * @param driverReviewDTO the driverReviewDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new driverReviewDTO, or with status {@code 400 (Bad Request)} if the driverReview has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/driver-reviews")
    public ResponseEntity<DriverReviewDTO> createDriverReview(@RequestBody DriverReviewDTO driverReviewDTO) throws URISyntaxException {
        log.debug("REST request to save DriverReview : {}", driverReviewDTO);
        if (driverReviewDTO.getId() != null) {
            throw new BadRequestAlertException("A new driverReview cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DriverReviewDTO result = driverReviewService.save(driverReviewDTO);
        return ResponseEntity
            .created(new URI("/api/driver-reviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /driver-reviews/:id} : Updates an existing driverReview.
     *
     * @param id              the id of the driverReviewDTO to save.
     * @param driverReviewDTO the driverReviewDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated driverReviewDTO,
     * or with status {@code 400 (Bad Request)} if the driverReviewDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the driverReviewDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/driver-reviews/{id}")
    public ResponseEntity<DriverReviewDTO> updateDriverReview(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DriverReviewDTO driverReviewDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DriverReview : {}, {}", id, driverReviewDTO);
        if (driverReviewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, driverReviewDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!driverReviewRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DriverReviewDTO result = driverReviewService.update(driverReviewDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, driverReviewDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /driver-reviews/:id} : Partial updates given fields of an existing driverReview, field will ignore if it is null
     *
     * @param id              the id of the driverReviewDTO to save.
     * @param driverReviewDTO the driverReviewDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated driverReviewDTO,
     * or with status {@code 400 (Bad Request)} if the driverReviewDTO is not valid,
     * or with status {@code 404 (Not Found)} if the driverReviewDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the driverReviewDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/driver-reviews/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<DriverReviewDTO> partialUpdateDriverReview(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DriverReviewDTO driverReviewDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DriverReview partially : {}, {}", id, driverReviewDTO);
        if (driverReviewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, driverReviewDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!driverReviewRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DriverReviewDTO> result = driverReviewService.partialUpdate(driverReviewDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, driverReviewDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /driver-reviews} : get all the driverReviews.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of driverReviews in body.
     */
    @GetMapping("/driver-reviews")
    public ResponseEntity<List<DriverReviewDTO>> getAllDriverReviews(
        DriverReviewCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get DriverReviews by criteria: {}", criteria);

        Page<DriverReviewDTO> page = driverReviewQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /driver-reviews/count} : count all the driverReviews.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/driver-reviews/count")
    public ResponseEntity<Long> countDriverReviews(DriverReviewCriteria criteria) {
        log.debug("REST request to count DriverReviews by criteria: {}", criteria);
        return ResponseEntity.ok().body(driverReviewQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /driver-reviews/:id} : get the "id" driverReview.
     *
     * @param id the id of the driverReviewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the driverReviewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/driver-reviews/{id}")
    public ResponseEntity<DriverReviewDTO> getDriverReview(@PathVariable Long id) {
        log.debug("REST request to get DriverReview : {}", id);
        Optional<DriverReviewDTO> driverReviewDTO = driverReviewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(driverReviewDTO);
    }

    /**
     * {@code DELETE  /driver-reviews/:id} : delete the "id" driverReview.
     *
     * @param id the id of the driverReviewDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/driver-reviews/{id}")
    public ResponseEntity<Void> deleteDriverReview(@PathVariable Long id) {
        log.debug("REST request to delete DriverReview : {}", id);
        driverReviewService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
