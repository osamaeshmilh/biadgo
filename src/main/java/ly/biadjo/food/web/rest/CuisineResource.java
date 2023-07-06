package ly.biadjo.food.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.CuisineRepository;
import ly.biadjo.food.service.CuisineQueryService;
import ly.biadjo.food.service.CuisineService;
import ly.biadjo.food.service.criteria.CuisineCriteria;
import ly.biadjo.food.service.dto.CuisineDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.Cuisine}.
 */
@RestController
@RequestMapping("/api")
public class CuisineResource {

    private final Logger log = LoggerFactory.getLogger(CuisineResource.class);

    private static final String ENTITY_NAME = "cuisine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CuisineService cuisineService;

    private final CuisineRepository cuisineRepository;

    private final CuisineQueryService cuisineQueryService;

    public CuisineResource(CuisineService cuisineService, CuisineRepository cuisineRepository, CuisineQueryService cuisineQueryService) {
        this.cuisineService = cuisineService;
        this.cuisineRepository = cuisineRepository;
        this.cuisineQueryService = cuisineQueryService;
    }

    /**
     * {@code POST  /cuisines} : Create a new cuisine.
     *
     * @param cuisineDTO the cuisineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cuisineDTO, or with status {@code 400 (Bad Request)} if the cuisine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cuisines")
    public ResponseEntity<CuisineDTO> createCuisine(@Valid @RequestBody CuisineDTO cuisineDTO) throws URISyntaxException {
        log.debug("REST request to save Cuisine : {}", cuisineDTO);
        if (cuisineDTO.getId() != null) {
            throw new BadRequestAlertException("A new cuisine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CuisineDTO result = cuisineService.save(cuisineDTO);
        return ResponseEntity
            .created(new URI("/api/cuisines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cuisines/:id} : Updates an existing cuisine.
     *
     * @param id         the id of the cuisineDTO to save.
     * @param cuisineDTO the cuisineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cuisineDTO,
     * or with status {@code 400 (Bad Request)} if the cuisineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cuisineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cuisines/{id}")
    public ResponseEntity<CuisineDTO> updateCuisine(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CuisineDTO cuisineDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Cuisine : {}, {}", id, cuisineDTO);
        if (cuisineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cuisineDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cuisineRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CuisineDTO result = cuisineService.update(cuisineDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cuisineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /cuisines/:id} : Partial updates given fields of an existing cuisine, field will ignore if it is null
     *
     * @param id         the id of the cuisineDTO to save.
     * @param cuisineDTO the cuisineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cuisineDTO,
     * or with status {@code 400 (Bad Request)} if the cuisineDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cuisineDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cuisineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cuisines/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<CuisineDTO> partialUpdateCuisine(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CuisineDTO cuisineDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Cuisine partially : {}, {}", id, cuisineDTO);
        if (cuisineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cuisineDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cuisineRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CuisineDTO> result = cuisineService.partialUpdate(cuisineDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cuisineDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /cuisines} : get all the cuisines.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cuisines in body.
     */
    @GetMapping("/cuisines")
    public ResponseEntity<List<CuisineDTO>> getAllCuisines(
        CuisineCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Cuisines by criteria: {}", criteria);

        Page<CuisineDTO> page = cuisineQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cuisines/count} : count all the cuisines.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/cuisines/count")
    public ResponseEntity<Long> countCuisines(CuisineCriteria criteria) {
        log.debug("REST request to count Cuisines by criteria: {}", criteria);
        return ResponseEntity.ok().body(cuisineQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cuisines/:id} : get the "id" cuisine.
     *
     * @param id the id of the cuisineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cuisineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cuisines/{id}")
    public ResponseEntity<CuisineDTO> getCuisine(@PathVariable Long id) {
        log.debug("REST request to get Cuisine : {}", id);
        Optional<CuisineDTO> cuisineDTO = cuisineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cuisineDTO);
    }

    /**
     * {@code DELETE  /cuisines/:id} : delete the "id" cuisine.
     *
     * @param id the id of the cuisineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cuisines/{id}")
    public ResponseEntity<Void> deleteCuisine(@PathVariable Long id) {
        log.debug("REST request to delete Cuisine : {}", id);
        cuisineService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
