package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.RestaurantRepository;
import ly.biadjo.food.security.AuthoritiesConstants;
import ly.biadjo.food.security.SecurityUtils;
import ly.biadjo.food.service.*;
import ly.biadjo.food.service.criteria.RestaurantCriteria;
import ly.biadjo.food.service.criteria.RestaurantReviewCriteria;
import ly.biadjo.food.service.dto.RestaurantDTO;
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
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.biadjo.food.domain.Restaurant}.
 */
@RestController
@RequestMapping("/api")
public class RestaurantResource {

    private final Logger log = LoggerFactory.getLogger(RestaurantResource.class);

    private static final String ENTITY_NAME = "restaurant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RestaurantService restaurantService;

    private final RestaurantRepository restaurantRepository;

    private final RestaurantQueryService restaurantQueryService;

    private final RestaurantReviewQueryService restaurantReviewQueryService;

    private final FavoriteRestaurantService favoriteRestaurantService;

    private final CustomerService customerService;

    public RestaurantResource(
        RestaurantService restaurantService,
        RestaurantRepository restaurantRepository,
        RestaurantQueryService restaurantQueryService,
        RestaurantReviewQueryService restaurantReviewQueryService, FavoriteRestaurantService favoriteRestaurantService, CustomerService customerService) {
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
        this.restaurantQueryService = restaurantQueryService;
        this.restaurantReviewQueryService = restaurantReviewQueryService;
        this.favoriteRestaurantService = favoriteRestaurantService;
        this.customerService = customerService;
    }

    /**
     * {@code POST  /restaurants} : Create a new restaurant.
     *
     * @param restaurantDTO the restaurantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new restaurantDTO, or with status {@code 400 (Bad Request)} if the restaurant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/restaurants")
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO restaurantDTO) throws URISyntaxException {
        log.debug("REST request to save Restaurant : {}", restaurantDTO);
        if (restaurantDTO.getId() != null) {
            throw new BadRequestAlertException("A new restaurant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RestaurantDTO result = restaurantService.save(restaurantDTO);
        return ResponseEntity
            .created(new URI("/api/restaurants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /restaurants/:id} : Updates an existing restaurant.
     *
     * @param id            the id of the restaurantDTO to save.
     * @param restaurantDTO the restaurantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the restaurantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantDTO restaurantDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Restaurant : {}, {}", id, restaurantDTO);
        if (restaurantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RestaurantDTO result = restaurantService.update(restaurantDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /restaurants/:id} : Partial updates given fields of an existing restaurant, field will ignore if it is null
     *
     * @param id            the id of the restaurantDTO to save.
     * @param restaurantDTO the restaurantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantDTO is not valid,
     * or with status {@code 404 (Not Found)} if the restaurantDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the restaurantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/restaurants/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<RestaurantDTO> partialUpdateRestaurant(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantDTO restaurantDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Restaurant partially : {}, {}", id, restaurantDTO);
        if (restaurantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RestaurantDTO> result = restaurantService.partialUpdate(restaurantDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /restaurants} : get all the restaurants.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of restaurants in body.
     */
    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants(
        RestaurantCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Restaurants by criteria: {}", criteria);

        Page<RestaurantDTO> page = restaurantQueryService.findByCriteria(criteria, pageable);

        page.forEach(restaurantDTO -> {
            Long restaurantId = restaurantDTO.getId();

            LongFilter productIdFilter = new LongFilter();
            productIdFilter.setEquals(restaurantId);

            RestaurantReviewCriteria restaurantReviewCriteria = new RestaurantReviewCriteria();
            restaurantReviewCriteria.setRestaurantId(productIdFilter);

            // Set review count and calculate rating
            long reviewsCount = restaurantReviewQueryService.countByCriteria(restaurantReviewCriteria);
            restaurantDTO.setReviewsCount(reviewsCount);

            float rating = reviewsCount > 0
                ? restaurantReviewQueryService.sumRatingByCriteria(restaurantReviewCriteria) / (float) reviewsCount
                : 0.0F;
            restaurantDTO.setRating(rating);

            // If user is authenticated, set favorite
            if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CUSTOMER)) {
                boolean isFavorite = favoriteRestaurantService.isFavorite(customerService.findOneDTOByUser().getId(), restaurantId);
                restaurantDTO.setFavorite(isFavorite);
            }
        });

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /restaurants/count} : count all the restaurants.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/restaurants/count")
    public ResponseEntity<Long> countRestaurants(RestaurantCriteria criteria) {
        log.debug("REST request to count Restaurants by criteria: {}", criteria);
        return ResponseEntity.ok().body(restaurantQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /restaurants/:id} : get the "id" restaurant.
     *
     * @param id the id of the restaurantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the restaurantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Long id) {
        log.debug("REST request to get Restaurant : {}", id);
        Optional<RestaurantDTO> restaurantDTO = restaurantService.findOne(id);

        LongFilter productIdFilter = new LongFilter();
        productIdFilter.setEquals(id);

        RestaurantReviewCriteria restaurantReviewCriteria = new RestaurantReviewCriteria();
        restaurantReviewCriteria.setRestaurantId(productIdFilter);

        // Set review count and calculate rating
        long reviewsCount = restaurantReviewQueryService.countByCriteria(restaurantReviewCriteria);
        restaurantDTO.get().setReviewsCount(reviewsCount);

        float rating = reviewsCount > 0
            ? restaurantReviewQueryService.sumRatingByCriteria(restaurantReviewCriteria) / (float) reviewsCount
            : 0.0F;
        restaurantDTO.get().setRating(rating);

        // If user is authenticated, set favorite
        if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CUSTOMER)) {
            boolean isFavorite = favoriteRestaurantService.isFavorite(customerService.findOneDTOByUser().getId(), restaurantDTO.get().getId());
            restaurantDTO.get().setFavorite(isFavorite);
        }

        return ResponseUtil.wrapOrNotFound(restaurantDTO);
    }

    /**
     * {@code DELETE  /restaurants/:id} : delete the "id" restaurant.
     *
     * @param id the id of the restaurantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        log.debug("REST request to delete Restaurant : {}", id);
        restaurantService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/public/restaurants/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantPublic(@PathVariable Long id) {
        log.debug("REST request to get Restaurant : {}", id);
        Optional<RestaurantDTO> restaurantDTO = restaurantService.findOne(id);

        LongFilter productIdFilter = new LongFilter();
        productIdFilter.setEquals(id);

        RestaurantReviewCriteria restaurantReviewCriteria = new RestaurantReviewCriteria();
        restaurantReviewCriteria.setRestaurantId(productIdFilter);

        // Set review count and calculate rating
        long reviewsCount = restaurantReviewQueryService.countByCriteria(restaurantReviewCriteria);
        restaurantDTO.get().setReviewsCount(reviewsCount);

        float rating = reviewsCount > 0
            ? restaurantReviewQueryService.sumRatingByCriteria(restaurantReviewCriteria) / (float) reviewsCount
            : 0.0F;
        restaurantDTO.get().setRating(rating);

        // If user is authenticated, set favorite
        if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CUSTOMER)) {
            boolean isFavorite = favoriteRestaurantService.isFavorite(customerService.findOneDTOByUser().getId(), restaurantDTO.get().getId());
            restaurantDTO.get().setFavorite(isFavorite);
        }

        return ResponseUtil.wrapOrNotFound(restaurantDTO);
    }

    @GetMapping("/public/restaurants")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurantsPublic(
        RestaurantCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Restaurants by criteria: {}", criteria);

        Page<RestaurantDTO> page = restaurantQueryService.findByCriteria(criteria, pageable);

        page.forEach(restaurantDTO -> {
            Long restaurantId = restaurantDTO.getId();

            LongFilter productIdFilter = new LongFilter();
            productIdFilter.setEquals(restaurantId);

            RestaurantReviewCriteria restaurantReviewCriteria = new RestaurantReviewCriteria();
            restaurantReviewCriteria.setRestaurantId(productIdFilter);

            // Set review count and calculate rating
            long reviewsCount = restaurantReviewQueryService.countByCriteria(restaurantReviewCriteria);
            restaurantDTO.setReviewsCount(reviewsCount);

            float rating = reviewsCount > 0
                ? restaurantReviewQueryService.sumRatingByCriteria(restaurantReviewCriteria) / (float) reviewsCount
                : 0.0F;
            restaurantDTO.setRating(rating);

            // If user is authenticated, set favorite
            if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CUSTOMER)) {
                boolean isFavorite = favoriteRestaurantService.isFavorite(customerService.findOneDTOByUser().getId(), restaurantId);
                restaurantDTO.setFavorite(isFavorite);
            }
        });

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/public/restaurants/nearby")
    public ResponseEntity<List<RestaurantDTO>> getAllNearRestaurantsPublic(Double customerLat, Double customerLng) {
        List<RestaurantDTO> restaurantDTOList = restaurantService.findByDistance("10", customerLat, customerLng);
        return ResponseEntity.ok().body(restaurantDTOList);
    }

}
