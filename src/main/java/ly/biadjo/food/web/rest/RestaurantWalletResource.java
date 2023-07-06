package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.RestaurantWalletRepository;
import ly.biadjo.food.service.RestaurantWalletQueryService;
import ly.biadjo.food.service.RestaurantWalletService;
import ly.biadjo.food.service.criteria.RestaurantWalletCriteria;
import ly.biadjo.food.service.dto.RestaurantWalletDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.RestaurantWallet}.
 */
@RestController
@RequestMapping("/api")
public class RestaurantWalletResource {

    private final Logger log = LoggerFactory.getLogger(RestaurantWalletResource.class);

    private static final String ENTITY_NAME = "restaurantWallet";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RestaurantWalletService restaurantWalletService;

    private final RestaurantWalletRepository restaurantWalletRepository;

    private final RestaurantWalletQueryService restaurantWalletQueryService;

    public RestaurantWalletResource(
        RestaurantWalletService restaurantWalletService,
        RestaurantWalletRepository restaurantWalletRepository,
        RestaurantWalletQueryService restaurantWalletQueryService
    ) {
        this.restaurantWalletService = restaurantWalletService;
        this.restaurantWalletRepository = restaurantWalletRepository;
        this.restaurantWalletQueryService = restaurantWalletQueryService;
    }

    /**
     * {@code POST  /restaurant-wallets} : Create a new restaurantWallet.
     *
     * @param restaurantWalletDTO the restaurantWalletDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new restaurantWalletDTO, or with status {@code 400 (Bad Request)} if the restaurantWallet has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/restaurant-wallets")
    public ResponseEntity<RestaurantWalletDTO> createRestaurantWallet(@RequestBody RestaurantWalletDTO restaurantWalletDTO)
        throws URISyntaxException {
        log.debug("REST request to save RestaurantWallet : {}", restaurantWalletDTO);
        if (restaurantWalletDTO.getId() != null) {
            throw new BadRequestAlertException("A new restaurantWallet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RestaurantWalletDTO result = restaurantWalletService.save(restaurantWalletDTO);
        return ResponseEntity
            .created(new URI("/api/restaurant-wallets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /restaurant-wallets/:id} : Updates an existing restaurantWallet.
     *
     * @param id                  the id of the restaurantWalletDTO to save.
     * @param restaurantWalletDTO the restaurantWalletDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantWalletDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantWalletDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the restaurantWalletDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/restaurant-wallets/{id}")
    public ResponseEntity<RestaurantWalletDTO> updateRestaurantWallet(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantWalletDTO restaurantWalletDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RestaurantWallet : {}, {}", id, restaurantWalletDTO);
        if (restaurantWalletDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantWalletDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantWalletRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RestaurantWalletDTO result = restaurantWalletService.update(restaurantWalletDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantWalletDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /restaurant-wallets/:id} : Partial updates given fields of an existing restaurantWallet, field will ignore if it is null
     *
     * @param id                  the id of the restaurantWalletDTO to save.
     * @param restaurantWalletDTO the restaurantWalletDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantWalletDTO,
     * or with status {@code 400 (Bad Request)} if the restaurantWalletDTO is not valid,
     * or with status {@code 404 (Not Found)} if the restaurantWalletDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the restaurantWalletDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/restaurant-wallets/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<RestaurantWalletDTO> partialUpdateRestaurantWallet(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantWalletDTO restaurantWalletDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RestaurantWallet partially : {}, {}", id, restaurantWalletDTO);
        if (restaurantWalletDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantWalletDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantWalletRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RestaurantWalletDTO> result = restaurantWalletService.partialUpdate(restaurantWalletDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantWalletDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /restaurant-wallets} : get all the restaurantWallets.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of restaurantWallets in body.
     */
    @GetMapping("/restaurant-wallets")
    public ResponseEntity<List<RestaurantWalletDTO>> getAllRestaurantWallets(
        RestaurantWalletCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RestaurantWallets by criteria: {}", criteria);

        Page<RestaurantWalletDTO> page = restaurantWalletQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /restaurant-wallets/count} : count all the restaurantWallets.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/restaurant-wallets/count")
    public ResponseEntity<Long> countRestaurantWallets(RestaurantWalletCriteria criteria) {
        log.debug("REST request to count RestaurantWallets by criteria: {}", criteria);
        return ResponseEntity.ok().body(restaurantWalletQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /restaurant-wallets/:id} : get the "id" restaurantWallet.
     *
     * @param id the id of the restaurantWalletDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the restaurantWalletDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/restaurant-wallets/{id}")
    public ResponseEntity<RestaurantWalletDTO> getRestaurantWallet(@PathVariable Long id) {
        log.debug("REST request to get RestaurantWallet : {}", id);
        Optional<RestaurantWalletDTO> restaurantWalletDTO = restaurantWalletService.findOne(id);
        return ResponseUtil.wrapOrNotFound(restaurantWalletDTO);
    }

    /**
     * {@code DELETE  /restaurant-wallets/:id} : delete the "id" restaurantWallet.
     *
     * @param id the id of the restaurantWalletDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/restaurant-wallets/{id}")
    public ResponseEntity<Void> deleteRestaurantWallet(@PathVariable Long id) {
        log.debug("REST request to delete RestaurantWallet : {}", id);
        restaurantWalletService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
