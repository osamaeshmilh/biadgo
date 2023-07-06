package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.FoodImageRepository;
import ly.biadjo.food.service.FoodImageQueryService;
import ly.biadjo.food.service.FoodImageService;
import ly.biadjo.food.service.criteria.FoodImageCriteria;
import ly.biadjo.food.service.dto.FoodImageDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.FoodImage}.
 */
@RestController
@RequestMapping("/api")
public class FoodImageResource {

    private final Logger log = LoggerFactory.getLogger(FoodImageResource.class);

    private static final String ENTITY_NAME = "foodImage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FoodImageService foodImageService;

    private final FoodImageRepository foodImageRepository;

    private final FoodImageQueryService foodImageQueryService;

    public FoodImageResource(
        FoodImageService foodImageService,
        FoodImageRepository foodImageRepository,
        FoodImageQueryService foodImageQueryService
    ) {
        this.foodImageService = foodImageService;
        this.foodImageRepository = foodImageRepository;
        this.foodImageQueryService = foodImageQueryService;
    }

    /**
     * {@code POST  /food-images} : Create a new foodImage.
     *
     * @param foodImageDTO the foodImageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new foodImageDTO, or with status {@code 400 (Bad Request)} if the foodImage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/food-images")
    public ResponseEntity<FoodImageDTO> createFoodImage(@RequestBody FoodImageDTO foodImageDTO) throws URISyntaxException {
        log.debug("REST request to save FoodImage : {}", foodImageDTO);
        if (foodImageDTO.getId() != null) {
            throw new BadRequestAlertException("A new foodImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FoodImageDTO result = foodImageService.save(foodImageDTO);
        return ResponseEntity
            .created(new URI("/api/food-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /food-images/:id} : Updates an existing foodImage.
     *
     * @param id           the id of the foodImageDTO to save.
     * @param foodImageDTO the foodImageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodImageDTO,
     * or with status {@code 400 (Bad Request)} if the foodImageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the foodImageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/food-images/{id}")
    public ResponseEntity<FoodImageDTO> updateFoodImage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FoodImageDTO foodImageDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FoodImage : {}, {}", id, foodImageDTO);
        if (foodImageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, foodImageDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!foodImageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FoodImageDTO result = foodImageService.update(foodImageDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, foodImageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /food-images/:id} : Partial updates given fields of an existing foodImage, field will ignore if it is null
     *
     * @param id           the id of the foodImageDTO to save.
     * @param foodImageDTO the foodImageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodImageDTO,
     * or with status {@code 400 (Bad Request)} if the foodImageDTO is not valid,
     * or with status {@code 404 (Not Found)} if the foodImageDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the foodImageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/food-images/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<FoodImageDTO> partialUpdateFoodImage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FoodImageDTO foodImageDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FoodImage partially : {}, {}", id, foodImageDTO);
        if (foodImageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, foodImageDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!foodImageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FoodImageDTO> result = foodImageService.partialUpdate(foodImageDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, foodImageDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /food-images} : get all the foodImages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foodImages in body.
     */
    @GetMapping("/food-images")
    public ResponseEntity<List<FoodImageDTO>> getAllFoodImages(
        FoodImageCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get FoodImages by criteria: {}", criteria);

        Page<FoodImageDTO> page = foodImageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /food-images/count} : count all the foodImages.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/food-images/count")
    public ResponseEntity<Long> countFoodImages(FoodImageCriteria criteria) {
        log.debug("REST request to count FoodImages by criteria: {}", criteria);
        return ResponseEntity.ok().body(foodImageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /food-images/:id} : get the "id" foodImage.
     *
     * @param id the id of the foodImageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the foodImageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/food-images/{id}")
    public ResponseEntity<FoodImageDTO> getFoodImage(@PathVariable Long id) {
        log.debug("REST request to get FoodImage : {}", id);
        Optional<FoodImageDTO> foodImageDTO = foodImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(foodImageDTO);
    }

    /**
     * {@code DELETE  /food-images/:id} : delete the "id" foodImage.
     *
     * @param id the id of the foodImageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/food-images/{id}")
    public ResponseEntity<Void> deleteFoodImage(@PathVariable Long id) {
        log.debug("REST request to delete FoodImage : {}", id);
        foodImageService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
