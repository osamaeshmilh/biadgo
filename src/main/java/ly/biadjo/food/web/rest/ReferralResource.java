package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.ReferralRepository;
import ly.biadjo.food.service.ReferralQueryService;
import ly.biadjo.food.service.ReferralService;
import ly.biadjo.food.service.criteria.ReferralCriteria;
import ly.biadjo.food.service.dto.ReferralDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.Referral}.
 */
@RestController
@RequestMapping("/api")
public class ReferralResource {

    private final Logger log = LoggerFactory.getLogger(ReferralResource.class);

    private static final String ENTITY_NAME = "referral";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReferralService referralService;

    private final ReferralRepository referralRepository;

    private final ReferralQueryService referralQueryService;

    public ReferralResource(
        ReferralService referralService,
        ReferralRepository referralRepository,
        ReferralQueryService referralQueryService
    ) {
        this.referralService = referralService;
        this.referralRepository = referralRepository;
        this.referralQueryService = referralQueryService;
    }

    /**
     * {@code POST  /referrals} : Create a new referral.
     *
     * @param referralDTO the referralDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new referralDTO, or with status {@code 400 (Bad Request)} if the referral has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/referrals")
    public ResponseEntity<ReferralDTO> createReferral(@RequestBody ReferralDTO referralDTO) throws URISyntaxException {
        log.debug("REST request to save Referral : {}", referralDTO);
        if (referralDTO.getId() != null) {
            throw new BadRequestAlertException("A new referral cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReferralDTO result = referralService.save(referralDTO);
        return ResponseEntity
            .created(new URI("/api/referrals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /referrals/:id} : Updates an existing referral.
     *
     * @param id          the id of the referralDTO to save.
     * @param referralDTO the referralDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated referralDTO,
     * or with status {@code 400 (Bad Request)} if the referralDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the referralDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/referrals/{id}")
    public ResponseEntity<ReferralDTO> updateReferral(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ReferralDTO referralDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Referral : {}, {}", id, referralDTO);
        if (referralDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, referralDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!referralRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReferralDTO result = referralService.update(referralDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, referralDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /referrals/:id} : Partial updates given fields of an existing referral, field will ignore if it is null
     *
     * @param id          the id of the referralDTO to save.
     * @param referralDTO the referralDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated referralDTO,
     * or with status {@code 400 (Bad Request)} if the referralDTO is not valid,
     * or with status {@code 404 (Not Found)} if the referralDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the referralDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/referrals/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<ReferralDTO> partialUpdateReferral(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ReferralDTO referralDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Referral partially : {}, {}", id, referralDTO);
        if (referralDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, referralDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!referralRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReferralDTO> result = referralService.partialUpdate(referralDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, referralDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /referrals} : get all the referrals.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of referrals in body.
     */
    @GetMapping("/referrals")
    public ResponseEntity<List<ReferralDTO>> getAllReferrals(
        ReferralCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Referrals by criteria: {}", criteria);

        Page<ReferralDTO> page = referralQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /referrals/count} : count all the referrals.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/referrals/count")
    public ResponseEntity<Long> countReferrals(ReferralCriteria criteria) {
        log.debug("REST request to count Referrals by criteria: {}", criteria);
        return ResponseEntity.ok().body(referralQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /referrals/:id} : get the "id" referral.
     *
     * @param id the id of the referralDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the referralDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/referrals/{id}")
    public ResponseEntity<ReferralDTO> getReferral(@PathVariable Long id) {
        log.debug("REST request to get Referral : {}", id);
        Optional<ReferralDTO> referralDTO = referralService.findOne(id);
        return ResponseUtil.wrapOrNotFound(referralDTO);
    }

    /**
     * {@code DELETE  /referrals/:id} : delete the "id" referral.
     *
     * @param id the id of the referralDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/referrals/{id}")
    public ResponseEntity<Void> deleteReferral(@PathVariable Long id) {
        log.debug("REST request to delete Referral : {}", id);
        referralService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
