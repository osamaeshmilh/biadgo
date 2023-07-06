package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.FoodExtraRepository;
import ly.biadjo.food.service.FoodExtraQueryService;
import ly.biadjo.food.service.FoodExtraService;
import ly.biadjo.food.service.criteria.FoodExtraCriteria;
import ly.biadjo.food.service.dto.FoodExtraDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.FoodExtra}.
 */
@RestController
@RequestMapping("/api")
public class FoodExtraResource {

    private final Logger log = LoggerFactory.getLogger(FoodExtraResource.class);

    private static final String ENTITY_NAME = "foodExtra";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FoodExtraService foodExtraService;

    private final FoodExtraRepository foodExtraRepository;

    private final FoodExtraQueryService foodExtraQueryService;

    public FoodExtraResource(
        FoodExtraService foodExtraService,
        FoodExtraRepository foodExtraRepository,
        FoodExtraQueryService foodExtraQueryService
    ) {
        this.foodExtraService = foodExtraService;
        this.foodExtraRepository = foodExtraRepository;
        this.foodExtraQueryService = foodExtraQueryService;
    }

    /**
     * {@code POST  /food-extras} : Create a new foodExtra.
     *
     * @param foodExtraDTO the foodExtraDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new foodExtraDTO, or with status {@code 400 (Bad Request)} if the foodExtra has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/food-extras")
    public ResponseEntity<FoodExtraDTO> createFoodExtra(@RequestBody FoodExtraDTO foodExtraDTO) throws URISyntaxException {
        log.debug("REST request to save FoodExtra : {}", foodExtraDTO);
        if (foodExtraDTO.getId() != null) {
            throw new BadRequestAlertException("A new foodExtra cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FoodExtraDTO result = foodExtraService.save(foodExtraDTO);
        return ResponseEntity
            .created(new URI("/api/food-extras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /food-extras/:id} : Updates an existing foodExtra.
     *
     * @param id           the id of the foodExtraDTO to save.
     * @param foodExtraDTO the foodExtraDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodExtraDTO,
     * or with status {@code 400 (Bad Request)} if the foodExtraDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the foodExtraDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/food-extras/{id}")
    public ResponseEntity<FoodExtraDTO> updateFoodExtra(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FoodExtraDTO foodExtraDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FoodExtra : {}, {}", id, foodExtraDTO);
        if (foodExtraDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, foodExtraDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!foodExtraRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FoodExtraDTO result = foodExtraService.update(foodExtraDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, foodExtraDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /food-extras/:id} : Partial updates given fields of an existing foodExtra, field will ignore if it is null
     *
     * @param id           the id of the foodExtraDTO to save.
     * @param foodExtraDTO the foodExtraDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodExtraDTO,
     * or with status {@code 400 (Bad Request)} if the foodExtraDTO is not valid,
     * or with status {@code 404 (Not Found)} if the foodExtraDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the foodExtraDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/food-extras/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<FoodExtraDTO> partialUpdateFoodExtra(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FoodExtraDTO foodExtraDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FoodExtra partially : {}, {}", id, foodExtraDTO);
        if (foodExtraDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, foodExtraDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!foodExtraRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FoodExtraDTO> result = foodExtraService.partialUpdate(foodExtraDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, foodExtraDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /food-extras} : get all the foodExtras.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foodExtras in body.
     */
    @GetMapping("/food-extras")
    public ResponseEntity<List<FoodExtraDTO>> getAllFoodExtras(
        FoodExtraCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get FoodExtras by criteria: {}", criteria);

        Page<FoodExtraDTO> page = foodExtraQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /food-extras/count} : count all the foodExtras.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/food-extras/count")
    public ResponseEntity<Long> countFoodExtras(FoodExtraCriteria criteria) {
        log.debug("REST request to count FoodExtras by criteria: {}", criteria);
        return ResponseEntity.ok().body(foodExtraQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /food-extras/:id} : get the "id" foodExtra.
     *
     * @param id the id of the foodExtraDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the foodExtraDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/food-extras/{id}")
    public ResponseEntity<FoodExtraDTO> getFoodExtra(@PathVariable Long id) {
        log.debug("REST request to get FoodExtra : {}", id);
        Optional<FoodExtraDTO> foodExtraDTO = foodExtraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(foodExtraDTO);
    }

    /**
     * {@code DELETE  /food-extras/:id} : delete the "id" foodExtra.
     *
     * @param id the id of the foodExtraDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/food-extras/{id}")
    public ResponseEntity<Void> deleteFoodExtra(@PathVariable Long id) {
        log.debug("REST request to delete FoodExtra : {}", id);
        foodExtraService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
