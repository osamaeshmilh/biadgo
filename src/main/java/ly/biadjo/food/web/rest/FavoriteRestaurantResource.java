package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.FavoriteRestaurantRepository;
import ly.biadjo.food.security.AuthoritiesConstants;
import ly.biadjo.food.security.SecurityUtils;
import ly.biadjo.food.service.CustomerService;
import ly.biadjo.food.service.FavoriteRestaurantQueryService;
import ly.biadjo.food.service.FavoriteRestaurantService;
import ly.biadjo.food.service.RestaurantService;
import ly.biadjo.food.service.criteria.FavoriteRestaurantCriteria;
import ly.biadjo.food.service.dto.FavoriteRestaurantDTO;
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
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.biadjo.food.domain.FavoriteRestaurant}.
 */
@RestController
@RequestMapping("/api")
public class FavoriteRestaurantResource {

    private final Logger log = LoggerFactory.getLogger(FavoriteRestaurantResource.class);

    private static final String ENTITY_NAME = "favoriteRestaurant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FavoriteRestaurantService favoriteRestaurantService;

    private final FavoriteRestaurantRepository favoriteRestaurantRepository;

    private final FavoriteRestaurantQueryService favoriteRestaurantQueryService;

    private final CustomerService customerService;

    private final RestaurantService restaurantService;

    public FavoriteRestaurantResource(
        FavoriteRestaurantService favoriteRestaurantService,
        FavoriteRestaurantRepository favoriteRestaurantRepository,
        FavoriteRestaurantQueryService favoriteRestaurantQueryService,
        CustomerService customerService, RestaurantService restaurantService) {
        this.favoriteRestaurantService = favoriteRestaurantService;
        this.favoriteRestaurantRepository = favoriteRestaurantRepository;
        this.favoriteRestaurantQueryService = favoriteRestaurantQueryService;
        this.customerService = customerService;
        this.restaurantService = restaurantService;
    }

    /**
     * {@code POST  /favorite-restaurants} : Create a new favoriteRestaurant.
     *
     * @param favoriteRestaurantDTO the favoriteRestaurantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new favoriteRestaurantDTO, or with status {@code 400 (Bad Request)} if the favoriteRestaurant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/favorite-restaurants")
    public ResponseEntity<FavoriteRestaurantDTO> createFavoriteRestaurant(@RequestBody FavoriteRestaurantDTO favoriteRestaurantDTO)
        throws URISyntaxException {
        log.debug("REST request to save FavoriteRestaurant : {}", favoriteRestaurantDTO);
        if (favoriteRestaurantDTO.getId() != null) {
            throw new BadRequestAlertException("A new favoriteRestaurant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FavoriteRestaurantDTO result = favoriteRestaurantService.save(favoriteRestaurantDTO);
        return ResponseEntity
            .created(new URI("/api/favorite-restaurants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /favorite-restaurants/:id} : Updates an existing favoriteRestaurant.
     *
     * @param id                    the id of the favoriteRestaurantDTO to save.
     * @param favoriteRestaurantDTO the favoriteRestaurantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated favoriteRestaurantDTO,
     * or with status {@code 400 (Bad Request)} if the favoriteRestaurantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the favoriteRestaurantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/favorite-restaurants/{id}")
    public ResponseEntity<FavoriteRestaurantDTO> updateFavoriteRestaurant(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FavoriteRestaurantDTO favoriteRestaurantDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FavoriteRestaurant : {}, {}", id, favoriteRestaurantDTO);
        if (favoriteRestaurantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, favoriteRestaurantDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!favoriteRestaurantRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FavoriteRestaurantDTO result = favoriteRestaurantService.update(favoriteRestaurantDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, favoriteRestaurantDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /favorite-restaurants/:id} : Partial updates given fields of an existing favoriteRestaurant, field will ignore if it is null
     *
     * @param id                    the id of the favoriteRestaurantDTO to save.
     * @param favoriteRestaurantDTO the favoriteRestaurantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated favoriteRestaurantDTO,
     * or with status {@code 400 (Bad Request)} if the favoriteRestaurantDTO is not valid,
     * or with status {@code 404 (Not Found)} if the favoriteRestaurantDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the favoriteRestaurantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/favorite-restaurants/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<FavoriteRestaurantDTO> partialUpdateFavoriteRestaurant(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FavoriteRestaurantDTO favoriteRestaurantDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FavoriteRestaurant partially : {}, {}", id, favoriteRestaurantDTO);
        if (favoriteRestaurantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, favoriteRestaurantDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!favoriteRestaurantRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FavoriteRestaurantDTO> result = favoriteRestaurantService.partialUpdate(favoriteRestaurantDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, favoriteRestaurantDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /favorite-restaurants} : get all the favoriteRestaurants.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of favoriteRestaurants in body.
     */
    @GetMapping("/favorite-restaurants")
    public ResponseEntity<List<FavoriteRestaurantDTO>> getAllFavoriteRestaurants(
        FavoriteRestaurantCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get FavoriteRestaurants by criteria: {}", criteria);

        Page<FavoriteRestaurantDTO> page = favoriteRestaurantQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /favorite-restaurants/count} : count all the favoriteRestaurants.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/favorite-restaurants/count")
    public ResponseEntity<Long> countFavoriteRestaurants(FavoriteRestaurantCriteria criteria) {
        log.debug("REST request to count FavoriteRestaurants by criteria: {}", criteria);
        return ResponseEntity.ok().body(favoriteRestaurantQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /favorite-restaurants/:id} : get the "id" favoriteRestaurant.
     *
     * @param id the id of the favoriteRestaurantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the favoriteRestaurantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/favorite-restaurants/{id}")
    public ResponseEntity<FavoriteRestaurantDTO> getFavoriteRestaurant(@PathVariable Long id) {
        log.debug("REST request to get FavoriteRestaurant : {}", id);
        Optional<FavoriteRestaurantDTO> favoriteRestaurantDTO = favoriteRestaurantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(favoriteRestaurantDTO);
    }

    /**
     * {@code DELETE  /favorite-restaurants/:id} : delete the "id" favoriteRestaurant.
     *
     * @param id the id of the favoriteRestaurantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/favorite-restaurants/{id}")
    public ResponseEntity<Void> deleteFavoriteRestaurant(@PathVariable Long id) {
        log.debug("REST request to delete FavoriteRestaurant : {}", id);
        favoriteRestaurantService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/favorite-restaurants/toggle/{restaurantId}")
    public ResponseEntity<RestaurantDTO> toggleFavorite(@PathVariable Long restaurantId) {

        RestaurantDTO restaurantDTO = restaurantService.findOne(restaurantId).get();

        if (favoriteRestaurantService.findOneByCustomerIdAndRestaurantId(customerService.findOneByUser().getId(), restaurantDTO.getId()).isPresent()) {
            favoriteRestaurantService.delete(favoriteRestaurantService.findOneByCustomerIdAndRestaurantId(customerService.findOneByUser().getId(), restaurantDTO.getId()).get().getId());
            restaurantDTO.setId(restaurantId);
            restaurantDTO.setFavorite(false);
        } else {
            if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CUSTOMER)) {
                FavoriteRestaurantDTO favoriteRestaurantDTO = new FavoriteRestaurantDTO();
                favoriteRestaurantDTO.setCustomer(customerService.findOneDTOByUser());
                favoriteRestaurantDTO.setRestaurant(restaurantDTO);
                favoriteRestaurantService.save(favoriteRestaurantDTO);
                restaurantDTO.setId(restaurantId);
                restaurantDTO.setFavorite(true);
            } else throw new BadRequestAlertException("User Is Not A Customer", "403", "");
        }
        return ResponseEntity.ok().body(restaurantDTO);
    }
}
