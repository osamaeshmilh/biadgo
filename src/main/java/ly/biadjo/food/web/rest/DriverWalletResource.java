package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.DriverWalletRepository;
import ly.biadjo.food.service.DriverWalletQueryService;
import ly.biadjo.food.service.DriverWalletService;
import ly.biadjo.food.service.criteria.DriverWalletCriteria;
import ly.biadjo.food.service.dto.DriverWalletDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.DriverWallet}.
 */
@RestController
@RequestMapping("/api")
public class DriverWalletResource {

    private final Logger log = LoggerFactory.getLogger(DriverWalletResource.class);

    private static final String ENTITY_NAME = "driverWallet";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DriverWalletService driverWalletService;

    private final DriverWalletRepository driverWalletRepository;

    private final DriverWalletQueryService driverWalletQueryService;

    public DriverWalletResource(
        DriverWalletService driverWalletService,
        DriverWalletRepository driverWalletRepository,
        DriverWalletQueryService driverWalletQueryService
    ) {
        this.driverWalletService = driverWalletService;
        this.driverWalletRepository = driverWalletRepository;
        this.driverWalletQueryService = driverWalletQueryService;
    }

    /**
     * {@code POST  /driver-wallets} : Create a new driverWallet.
     *
     * @param driverWalletDTO the driverWalletDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new driverWalletDTO, or with status {@code 400 (Bad Request)} if the driverWallet has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/driver-wallets")
    public ResponseEntity<DriverWalletDTO> createDriverWallet(@RequestBody DriverWalletDTO driverWalletDTO) throws URISyntaxException {
        log.debug("REST request to save DriverWallet : {}", driverWalletDTO);
        if (driverWalletDTO.getId() != null) {
            throw new BadRequestAlertException("A new driverWallet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DriverWalletDTO result = driverWalletService.save(driverWalletDTO);
        return ResponseEntity
            .created(new URI("/api/driver-wallets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /driver-wallets/:id} : Updates an existing driverWallet.
     *
     * @param id              the id of the driverWalletDTO to save.
     * @param driverWalletDTO the driverWalletDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated driverWalletDTO,
     * or with status {@code 400 (Bad Request)} if the driverWalletDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the driverWalletDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/driver-wallets/{id}")
    public ResponseEntity<DriverWalletDTO> updateDriverWallet(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DriverWalletDTO driverWalletDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DriverWallet : {}, {}", id, driverWalletDTO);
        if (driverWalletDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, driverWalletDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!driverWalletRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DriverWalletDTO result = driverWalletService.update(driverWalletDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, driverWalletDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /driver-wallets/:id} : Partial updates given fields of an existing driverWallet, field will ignore if it is null
     *
     * @param id              the id of the driverWalletDTO to save.
     * @param driverWalletDTO the driverWalletDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated driverWalletDTO,
     * or with status {@code 400 (Bad Request)} if the driverWalletDTO is not valid,
     * or with status {@code 404 (Not Found)} if the driverWalletDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the driverWalletDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/driver-wallets/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<DriverWalletDTO> partialUpdateDriverWallet(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DriverWalletDTO driverWalletDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DriverWallet partially : {}, {}", id, driverWalletDTO);
        if (driverWalletDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, driverWalletDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!driverWalletRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DriverWalletDTO> result = driverWalletService.partialUpdate(driverWalletDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, driverWalletDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /driver-wallets} : get all the driverWallets.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of driverWallets in body.
     */
    @GetMapping("/driver-wallets")
    public ResponseEntity<List<DriverWalletDTO>> getAllDriverWallets(
        DriverWalletCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get DriverWallets by criteria: {}", criteria);

        Page<DriverWalletDTO> page = driverWalletQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /driver-wallets/count} : count all the driverWallets.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/driver-wallets/count")
    public ResponseEntity<Long> countDriverWallets(DriverWalletCriteria criteria) {
        log.debug("REST request to count DriverWallets by criteria: {}", criteria);
        return ResponseEntity.ok().body(driverWalletQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /driver-wallets/:id} : get the "id" driverWallet.
     *
     * @param id the id of the driverWalletDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the driverWalletDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/driver-wallets/{id}")
    public ResponseEntity<DriverWalletDTO> getDriverWallet(@PathVariable Long id) {
        log.debug("REST request to get DriverWallet : {}", id);
        Optional<DriverWalletDTO> driverWalletDTO = driverWalletService.findOne(id);
        return ResponseUtil.wrapOrNotFound(driverWalletDTO);
    }

    /**
     * {@code DELETE  /driver-wallets/:id} : delete the "id" driverWallet.
     *
     * @param id the id of the driverWalletDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/driver-wallets/{id}")
    public ResponseEntity<Void> deleteDriverWallet(@PathVariable Long id) {
        log.debug("REST request to delete DriverWallet : {}", id);
        driverWalletService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
