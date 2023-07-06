package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.AppSettingRepository;
import ly.biadjo.food.service.AppSettingQueryService;
import ly.biadjo.food.service.AppSettingService;
import ly.biadjo.food.service.criteria.AppSettingCriteria;
import ly.biadjo.food.service.dto.AppSettingDTO;
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
 * REST controller for managing {@link ly.biadjo.food.domain.AppSetting}.
 */
@RestController
@RequestMapping("/api")
public class AppSettingResource {

    private final Logger log = LoggerFactory.getLogger(AppSettingResource.class);

    private static final String ENTITY_NAME = "appSetting";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppSettingService appSettingService;

    private final AppSettingRepository appSettingRepository;

    private final AppSettingQueryService appSettingQueryService;

    public AppSettingResource(
        AppSettingService appSettingService,
        AppSettingRepository appSettingRepository,
        AppSettingQueryService appSettingQueryService
    ) {
        this.appSettingService = appSettingService;
        this.appSettingRepository = appSettingRepository;
        this.appSettingQueryService = appSettingQueryService;
    }

    /**
     * {@code POST  /app-settings} : Create a new appSetting.
     *
     * @param appSettingDTO the appSettingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appSettingDTO, or with status {@code 400 (Bad Request)} if the appSetting has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/app-settings")
    public ResponseEntity<AppSettingDTO> createAppSetting(@RequestBody AppSettingDTO appSettingDTO) throws URISyntaxException {
        log.debug("REST request to save AppSetting : {}", appSettingDTO);
        if (appSettingDTO.getId() != null) {
            throw new BadRequestAlertException("A new appSetting cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppSettingDTO result = appSettingService.save(appSettingDTO);
        return ResponseEntity
            .created(new URI("/api/app-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /app-settings/:id} : Updates an existing appSetting.
     *
     * @param id            the id of the appSettingDTO to save.
     * @param appSettingDTO the appSettingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appSettingDTO,
     * or with status {@code 400 (Bad Request)} if the appSettingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appSettingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/app-settings/{id}")
    public ResponseEntity<AppSettingDTO> updateAppSetting(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AppSettingDTO appSettingDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AppSetting : {}, {}", id, appSettingDTO);
        if (appSettingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, appSettingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!appSettingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AppSettingDTO result = appSettingService.update(appSettingDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, appSettingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /app-settings/:id} : Partial updates given fields of an existing appSetting, field will ignore if it is null
     *
     * @param id            the id of the appSettingDTO to save.
     * @param appSettingDTO the appSettingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appSettingDTO,
     * or with status {@code 400 (Bad Request)} if the appSettingDTO is not valid,
     * or with status {@code 404 (Not Found)} if the appSettingDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the appSettingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/app-settings/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<AppSettingDTO> partialUpdateAppSetting(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AppSettingDTO appSettingDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AppSetting partially : {}, {}", id, appSettingDTO);
        if (appSettingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, appSettingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!appSettingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AppSettingDTO> result = appSettingService.partialUpdate(appSettingDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, appSettingDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /app-settings} : get all the appSettings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appSettings in body.
     */
    @GetMapping("/app-settings")
    public ResponseEntity<List<AppSettingDTO>> getAllAppSettings(
        AppSettingCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get AppSettings by criteria: {}", criteria);

        Page<AppSettingDTO> page = appSettingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /app-settings/count} : count all the appSettings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/app-settings/count")
    public ResponseEntity<Long> countAppSettings(AppSettingCriteria criteria) {
        log.debug("REST request to count AppSettings by criteria: {}", criteria);
        return ResponseEntity.ok().body(appSettingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /app-settings/:id} : get the "id" appSetting.
     *
     * @param id the id of the appSettingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appSettingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/app-settings/{id}")
    public ResponseEntity<AppSettingDTO> getAppSetting(@PathVariable Long id) {
        log.debug("REST request to get AppSetting : {}", id);
        Optional<AppSettingDTO> appSettingDTO = appSettingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(appSettingDTO);
    }

    /**
     * {@code DELETE  /app-settings/:id} : delete the "id" appSetting.
     *
     * @param id the id of the appSettingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/app-settings/{id}")
    public ResponseEntity<Void> deleteAppSetting(@PathVariable Long id) {
        log.debug("REST request to delete AppSetting : {}", id);
        appSettingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
