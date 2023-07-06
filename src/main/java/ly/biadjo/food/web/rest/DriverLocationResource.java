package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.DriverLocationRepository;
import ly.biadjo.food.service.DriverLocationQueryService;
import ly.biadjo.food.service.DriverLocationService;
import ly.biadjo.food.service.criteria.DriverLocationCriteria;
import ly.biadjo.food.service.dto.DriverLocationDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.DriverLocation}.
 */
@RestController
@RequestMapping("/api")
public class DriverLocationResource {

    private final Logger log = LoggerFactory.getLogger(DriverLocationResource.class);

    private static final String ENTITY_NAME = "driverLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DriverLocationService driverLocationService;

    private final DriverLocationRepository driverLocationRepository;

    private final DriverLocationQueryService driverLocationQueryService;

    public DriverLocationResource(
        DriverLocationService driverLocationService,
        DriverLocationRepository driverLocationRepository,
        DriverLocationQueryService driverLocationQueryService
    ) {
        this.driverLocationService = driverLocationService;
        this.driverLocationRepository = driverLocationRepository;
        this.driverLocationQueryService = driverLocationQueryService;
    }

    /**
     * {@code POST  /driver-locations} : Create a new driverLocation.
     *
     * @param driverLocationDTO the driverLocationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new driverLocationDTO, or with status {@code 400 (Bad Request)} if the driverLocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/driver-locations")
    public ResponseEntity<DriverLocationDTO> createDriverLocation(@RequestBody DriverLocationDTO driverLocationDTO)
        throws URISyntaxException {
        log.debug("REST request to save DriverLocation : {}", driverLocationDTO);
        if (driverLocationDTO.getId() != null) {
            throw new BadRequestAlertException("A new driverLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DriverLocationDTO result = driverLocationService.save(driverLocationDTO);
        return ResponseEntity
            .created(new URI("/api/driver-locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /driver-locations/:id} : Updates an existing driverLocation.
     *
     * @param id                the id of the driverLocationDTO to save.
     * @param driverLocationDTO the driverLocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated driverLocationDTO,
     * or with status {@code 400 (Bad Request)} if the driverLocationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the driverLocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/driver-locations/{id}")
    public ResponseEntity<DriverLocationDTO> updateDriverLocation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DriverLocationDTO driverLocationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DriverLocation : {}, {}", id, driverLocationDTO);
        if (driverLocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, driverLocationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!driverLocationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DriverLocationDTO result = driverLocationService.update(driverLocationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, driverLocationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /driver-locations/:id} : Partial updates given fields of an existing driverLocation, field will ignore if it is null
     *
     * @param id                the id of the driverLocationDTO to save.
     * @param driverLocationDTO the driverLocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated driverLocationDTO,
     * or with status {@code 400 (Bad Request)} if the driverLocationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the driverLocationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the driverLocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/driver-locations/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<DriverLocationDTO> partialUpdateDriverLocation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DriverLocationDTO driverLocationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DriverLocation partially : {}, {}", id, driverLocationDTO);
        if (driverLocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, driverLocationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!driverLocationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DriverLocationDTO> result = driverLocationService.partialUpdate(driverLocationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, driverLocationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /driver-locations} : get all the driverLocations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of driverLocations in body.
     */
    @GetMapping("/driver-locations")
    public ResponseEntity<List<DriverLocationDTO>> getAllDriverLocations(
        DriverLocationCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get DriverLocations by criteria: {}", criteria);

        Page<DriverLocationDTO> page = driverLocationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /driver-locations/count} : count all the driverLocations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/driver-locations/count")
    public ResponseEntity<Long> countDriverLocations(DriverLocationCriteria criteria) {
        log.debug("REST request to count DriverLocations by criteria: {}", criteria);
        return ResponseEntity.ok().body(driverLocationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /driver-locations/:id} : get the "id" driverLocation.
     *
     * @param id the id of the driverLocationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the driverLocationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/driver-locations/{id}")
    public ResponseEntity<DriverLocationDTO> getDriverLocation(@PathVariable Long id) {
        log.debug("REST request to get DriverLocation : {}", id);
        Optional<DriverLocationDTO> driverLocationDTO = driverLocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(driverLocationDTO);
    }

    /**
     * {@code DELETE  /driver-locations/:id} : delete the "id" driverLocation.
     *
     * @param id the id of the driverLocationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/driver-locations/{id}")
    public ResponseEntity<Void> deleteDriverLocation(@PathVariable Long id) {
        log.debug("REST request to delete DriverLocation : {}", id);
        driverLocationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
