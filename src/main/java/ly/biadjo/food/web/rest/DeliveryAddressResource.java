package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.DeliveryAddressRepository;
import ly.biadjo.food.security.AuthoritiesConstants;
import ly.biadjo.food.security.SecurityUtils;
import ly.biadjo.food.service.CustomerService;
import ly.biadjo.food.service.DeliveryAddressQueryService;
import ly.biadjo.food.service.DeliveryAddressService;
import ly.biadjo.food.service.criteria.DeliveryAddressCriteria;
import ly.biadjo.food.service.dto.DeliveryAddressDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.DeliveryAddress}.
 */
@RestController
@RequestMapping("/api")
public class DeliveryAddressResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryAddressResource.class);

    private static final String ENTITY_NAME = "deliveryAddress";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeliveryAddressService deliveryAddressService;

    private final CustomerService customerService;

    private final DeliveryAddressRepository deliveryAddressRepository;

    private final DeliveryAddressQueryService deliveryAddressQueryService;

    public DeliveryAddressResource(
        DeliveryAddressService deliveryAddressService,
        CustomerService customerService, DeliveryAddressRepository deliveryAddressRepository,
        DeliveryAddressQueryService deliveryAddressQueryService
    ) {
        this.deliveryAddressService = deliveryAddressService;
        this.customerService = customerService;
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.deliveryAddressQueryService = deliveryAddressQueryService;
    }

    /**
     * {@code POST  /delivery-addresses} : Create a new deliveryAddress.
     *
     * @param deliveryAddressDTO the deliveryAddressDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deliveryAddressDTO, or with status {@code 400 (Bad Request)} if the deliveryAddress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delivery-addresses")
    public ResponseEntity<DeliveryAddressDTO> createDeliveryAddress(@RequestBody DeliveryAddressDTO deliveryAddressDTO)
        throws URISyntaxException {
        log.debug("REST request to save DeliveryAddress : {}", deliveryAddressDTO);
        if (deliveryAddressDTO.getId() != null) {
            throw new BadRequestAlertException("A new deliveryAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CUSTOMER)) {
            deliveryAddressDTO.setCustomer(customerService.findOneDTOByUser());
        }
        DeliveryAddressDTO result = deliveryAddressService.save(deliveryAddressDTO);
        return ResponseEntity
            .created(new URI("/api/delivery-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /delivery-addresses/:id} : Updates an existing deliveryAddress.
     *
     * @param id                 the id of the deliveryAddressDTO to save.
     * @param deliveryAddressDTO the deliveryAddressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryAddressDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryAddressDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deliveryAddressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delivery-addresses/{id}")
    public ResponseEntity<DeliveryAddressDTO> updateDeliveryAddress(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DeliveryAddressDTO deliveryAddressDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DeliveryAddress : {}, {}", id, deliveryAddressDTO);
        if (deliveryAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deliveryAddressDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliveryAddressRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DeliveryAddressDTO result = deliveryAddressService.update(deliveryAddressDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, deliveryAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /delivery-addresses/:id} : Partial updates given fields of an existing deliveryAddress, field will ignore if it is null
     *
     * @param id                 the id of the deliveryAddressDTO to save.
     * @param deliveryAddressDTO the deliveryAddressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deliveryAddressDTO,
     * or with status {@code 400 (Bad Request)} if the deliveryAddressDTO is not valid,
     * or with status {@code 404 (Not Found)} if the deliveryAddressDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the deliveryAddressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/delivery-addresses/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<DeliveryAddressDTO> partialUpdateDeliveryAddress(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DeliveryAddressDTO deliveryAddressDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DeliveryAddress partially : {}, {}", id, deliveryAddressDTO);
        if (deliveryAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deliveryAddressDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deliveryAddressRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeliveryAddressDTO> result = deliveryAddressService.partialUpdate(deliveryAddressDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, deliveryAddressDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /delivery-addresses} : get all the deliveryAddresses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deliveryAddresses in body.
     */
    @GetMapping("/delivery-addresses")
    public ResponseEntity<List<DeliveryAddressDTO>> getAllDeliveryAddresses(
        DeliveryAddressCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get DeliveryAddresses by criteria: {}", criteria);

        Page<DeliveryAddressDTO> page = deliveryAddressQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /delivery-addresses/count} : count all the deliveryAddresses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/delivery-addresses/count")
    public ResponseEntity<Long> countDeliveryAddresses(DeliveryAddressCriteria criteria) {
        log.debug("REST request to count DeliveryAddresses by criteria: {}", criteria);
        return ResponseEntity.ok().body(deliveryAddressQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /delivery-addresses/:id} : get the "id" deliveryAddress.
     *
     * @param id the id of the deliveryAddressDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deliveryAddressDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delivery-addresses/{id}")
    public ResponseEntity<DeliveryAddressDTO> getDeliveryAddress(@PathVariable Long id) {
        log.debug("REST request to get DeliveryAddress : {}", id);
        Optional<DeliveryAddressDTO> deliveryAddressDTO = deliveryAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryAddressDTO);
    }

    /**
     * {@code DELETE  /delivery-addresses/:id} : delete the "id" deliveryAddress.
     *
     * @param id the id of the deliveryAddressDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delivery-addresses/{id}")
    public ResponseEntity<Void> deleteDeliveryAddress(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryAddress : {}", id);
        deliveryAddressService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
