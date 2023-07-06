package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.FoodIngredientRepository;
import ly.biadjo.food.service.FoodIngredientQueryService;
import ly.biadjo.food.service.FoodIngredientService;
import ly.biadjo.food.service.criteria.FoodIngredientCriteria;
import ly.biadjo.food.service.dto.FoodIngredientDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.FoodIngredient}.
 */
@RestController
@RequestMapping("/api")
public class FoodIngredientResource {

    private final Logger log = LoggerFactory.getLogger(FoodIngredientResource.class);

    private static final String ENTITY_NAME = "foodIngredient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FoodIngredientService foodIngredientService;

    private final FoodIngredientRepository foodIngredientRepository;

    private final FoodIngredientQueryService foodIngredientQueryService;

    public FoodIngredientResource(
        FoodIngredientService foodIngredientService,
        FoodIngredientRepository foodIngredientRepository,
        FoodIngredientQueryService foodIngredientQueryService
    ) {
        this.foodIngredientService = foodIngredientService;
        this.foodIngredientRepository = foodIngredientRepository;
        this.foodIngredientQueryService = foodIngredientQueryService;
    }

    /**
     * {@code POST  /food-ingredients} : Create a new foodIngredient.
     *
     * @param foodIngredientDTO the foodIngredientDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new foodIngredientDTO, or with status {@code 400 (Bad Request)} if the foodIngredient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/food-ingredients")
    public ResponseEntity<FoodIngredientDTO> createFoodIngredient(@RequestBody FoodIngredientDTO foodIngredientDTO)
        throws URISyntaxException {
        log.debug("REST request to save FoodIngredient : {}", foodIngredientDTO);
        if (foodIngredientDTO.getId() != null) {
            throw new BadRequestAlertException("A new foodIngredient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FoodIngredientDTO result = foodIngredientService.save(foodIngredientDTO);
        return ResponseEntity
            .created(new URI("/api/food-ingredients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /food-ingredients/:id} : Updates an existing foodIngredient.
     *
     * @param id                the id of the foodIngredientDTO to save.
     * @param foodIngredientDTO the foodIngredientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodIngredientDTO,
     * or with status {@code 400 (Bad Request)} if the foodIngredientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the foodIngredientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/food-ingredients/{id}")
    public ResponseEntity<FoodIngredientDTO> updateFoodIngredient(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FoodIngredientDTO foodIngredientDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FoodIngredient : {}, {}", id, foodIngredientDTO);
        if (foodIngredientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, foodIngredientDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!foodIngredientRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FoodIngredientDTO result = foodIngredientService.update(foodIngredientDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, foodIngredientDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /food-ingredients/:id} : Partial updates given fields of an existing foodIngredient, field will ignore if it is null
     *
     * @param id                the id of the foodIngredientDTO to save.
     * @param foodIngredientDTO the foodIngredientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodIngredientDTO,
     * or with status {@code 400 (Bad Request)} if the foodIngredientDTO is not valid,
     * or with status {@code 404 (Not Found)} if the foodIngredientDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the foodIngredientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/food-ingredients/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<FoodIngredientDTO> partialUpdateFoodIngredient(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FoodIngredientDTO foodIngredientDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FoodIngredient partially : {}, {}", id, foodIngredientDTO);
        if (foodIngredientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, foodIngredientDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!foodIngredientRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FoodIngredientDTO> result = foodIngredientService.partialUpdate(foodIngredientDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, foodIngredientDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /food-ingredients} : get all the foodIngredients.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foodIngredients in body.
     */
    @GetMapping("/food-ingredients")
    public ResponseEntity<List<FoodIngredientDTO>> getAllFoodIngredients(
        FoodIngredientCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get FoodIngredients by criteria: {}", criteria);

        Page<FoodIngredientDTO> page = foodIngredientQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /food-ingredients/count} : count all the foodIngredients.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/food-ingredients/count")
    public ResponseEntity<Long> countFoodIngredients(FoodIngredientCriteria criteria) {
        log.debug("REST request to count FoodIngredients by criteria: {}", criteria);
        return ResponseEntity.ok().body(foodIngredientQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /food-ingredients/:id} : get the "id" foodIngredient.
     *
     * @param id the id of the foodIngredientDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the foodIngredientDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/food-ingredients/{id}")
    public ResponseEntity<FoodIngredientDTO> getFoodIngredient(@PathVariable Long id) {
        log.debug("REST request to get FoodIngredient : {}", id);
        Optional<FoodIngredientDTO> foodIngredientDTO = foodIngredientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(foodIngredientDTO);
    }

    /**
     * {@code DELETE  /food-ingredients/:id} : delete the "id" foodIngredient.
     *
     * @param id the id of the foodIngredientDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/food-ingredients/{id}")
    public ResponseEntity<Void> deleteFoodIngredient(@PathVariable Long id) {
        log.debug("REST request to delete FoodIngredient : {}", id);
        foodIngredientService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
