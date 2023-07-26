package ly.biadjo.food.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.repository.CustomerRepository;
import ly.biadjo.food.security.AuthoritiesConstants;
import ly.biadjo.food.security.SecurityUtils;
import ly.biadjo.food.service.ActivationService;
import ly.biadjo.food.service.CustomerQueryService;
import ly.biadjo.food.service.CustomerService;
import ly.biadjo.food.service.NotificationService;
import ly.biadjo.food.service.criteria.CustomerCriteria;
import ly.biadjo.food.service.dto.CustomerDTO;
import ly.biadjo.food.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.biadjo.food.domain.Customer}.
 */
@RestController
@RequestMapping("/api")
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private static final String ENTITY_NAME = "customer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerService customerService;

    private final CustomerRepository customerRepository;

    private final CustomerQueryService customerQueryService;

    private final ActivationService activationService;

    private final NotificationService notificationService;

    public CustomerResource(
        CustomerService customerService,
        CustomerRepository customerRepository,
        CustomerQueryService customerQueryService,
        ActivationService activationService, NotificationService notificationService) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.customerQueryService = customerQueryService;
        this.activationService = activationService;
        this.notificationService = notificationService;
    }

    /**
     * {@code POST  /customers} : Create a new customer.
     *
     * @param customerDTO the customerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customerDTO, or with status {@code 400 (Bad Request)} if the customer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        log.debug("REST request to save Customer : {}", customerDTO);
        if (customerDTO.getId() != null) {
            throw new BadRequestAlertException("A new customer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerDTO result = customerService.save(customerDTO);
        return ResponseEntity
            .created(new URI("/api/customers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /customers/:id} : Updates an existing customer.
     *
     * @param id          the id of the customerDTO to save.
     * @param customerDTO the customerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerDTO,
     * or with status {@code 400 (Bad Request)} if the customerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CustomerDTO customerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Customer : {}, {}", id, customerDTO);
        if (customerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CustomerDTO result = customerService.update(customerDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, customerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /customers/:id} : Partial updates given fields of an existing customer, field will ignore if it is null
     *
     * @param id          the id of the customerDTO to save.
     * @param customerDTO the customerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerDTO,
     * or with status {@code 400 (Bad Request)} if the customerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the customerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the customerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/customers/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<CustomerDTO> partialUpdateCustomer(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CustomerDTO customerDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Customer partially : {}, {}", id, customerDTO);
        if (customerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CustomerDTO> result = customerService.partialUpdate(customerDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, customerDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /customers} : get all the customers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customers in body.
     */
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(
        CustomerCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Customers by criteria: {}", criteria);

        Page<CustomerDTO> page = customerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /customers/count} : count all the customers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/customers/count")
    public ResponseEntity<Long> countCustomers(CustomerCriteria criteria) {
        log.debug("REST request to count Customers by criteria: {}", criteria);
        return ResponseEntity.ok().body(customerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /customers/:id} : get the "id" customer.
     *
     * @param id the id of the customerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        log.debug("REST request to get Customer : {}", id);
        Optional<CustomerDTO> customerDTO = customerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerDTO);
    }

    /**
     * {@code DELETE  /customers/:id} : delete the "id" customer.
     *
     * @param id the id of the customerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        log.debug("REST request to delete Customer : {}", id);
        customerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/public/customers/is-email-registered")
    public ResponseEntity<String> checkEmailCustomer(@RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        if (customerService.findOneByEmail(customerDTO.getEmail()).isPresent()) {
            return ResponseEntity.ok("true");
        } else {
            return ResponseEntity.ok("false");
        }
    }

    @PostMapping("/public/customers/register")
    public ResponseEntity<CustomerDTO> registerCustomer(@RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        log.debug("REST request to register Customer : {}", customerDTO);
        if (customerDTO.getId() != null) {
            throw new BadRequestAlertException("A new customer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (customerService.findOneByMobileNo(customerDTO.getMobileNo()).isPresent()) {
            throw new BadRequestAlertException("Mobile Number Already Used!", ENTITY_NAME, "MOBILE_USED");
        }

        if (!customerDTO.getMobileNo().startsWith("+218")) {
            customerDTO.setVerifiedByEmail(true);
            customerDTO.setVerifiedByMobileNo(false);
        }

//        if (customerDTO.getVerifiedByEmail()) {
//            activationService.checkCodeWithEmail(customerDTO.getEmail(), customerDTO.getOtp());
//        } else
        if (customerDTO.getVerifiedByMobileNo()) {
            activationService.checkCodeWithMobileNo(customerDTO.getMobileNo(), customerDTO.getOtp());
        }
//        else if (customerDTO.getVerifiedBySocialId()) {
//            customerDTO.setVerifiedByEmail(true);
//        }
        else {
            throw new BadRequestAlertException("No Verification!", ENTITY_NAME, "NO_VERIFICATION");
        }

        CustomerDTO result = customerService.create(customerDTO);
        return ResponseEntity
            .created(new URI("/api/customers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/customers/update-profile")
    public ResponseEntity<CustomerDTO> updateCustomerProfile(@RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        log.debug("REST request to update Customer : {}", customerDTO);
        if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CUSTOMER)) {
            customerDTO.setId(customerService.findOneByUser().getId());
        } else {
            throw new BadRequestAlertException("not customer", ENTITY_NAME, "idnull");
        }
        if (customerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomerDTO result = customerService.save(customerDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, customerDTO.getId().toString()))
            .body(result);
    }

    @Secured(AuthoritiesConstants.CUSTOMER)
    @GetMapping("/customers/get-profile")
    public ResponseEntity<CustomerDTO> getCustomerProfile() throws URISyntaxException {
        Optional<CustomerDTO> customerDTO = customerService.findOne(customerService.findOneByUser().getId());
        return ResponseUtil.wrapOrNotFound(customerDTO);
    }

    @GetMapping("/customers/by-public-key/{walletPublicKey}")
    public ResponseEntity<CustomerDTO> getCustomerByWalletPublicKey(@PathVariable String walletPublicKey) throws URISyntaxException {
        Optional<CustomerDTO> customerDTO = customerService.findOneByWalletPublicKey(walletPublicKey);
        if (!customerDTO.isPresent())
            throw new BadRequestAlertException("Wallet Key not found!", ENTITY_NAME, "CUSTOMER_NOT_FOUND");
        return ResponseUtil.wrapOrNotFound(customerDTO);
    }

    @GetMapping("/customers/by-mobile/{mobileNo}")
    public ResponseEntity<CustomerDTO> getCustomerByMobileNo(@PathVariable String mobileNo) throws URISyntaxException {
        Optional<CustomerDTO> customerDTO = customerService.findOneByMobileNo(mobileNo);
        if (!customerDTO.isPresent()) {
            throw new BadRequestAlertException("Mobile not found!", ENTITY_NAME, "CUSTOMER_NOT_FOUND");
        }
        return ResponseUtil.wrapOrNotFound(customerDTO);
    }


    @Secured(AuthoritiesConstants.CUSTOMER)
    @GetMapping("/customers/request-delete")
    public ResponseEntity<CustomerDTO> requestAccountDelete() {
        CustomerDTO customerDTO = customerService.findOneDTOByUser();
        if (customerDTO.getAppleId().contains("DELETE")) throw new BadRequestAlertException(
            "Account delete already requested!",
            ENTITY_NAME,
            "ACCOUNT_DELETE_REQUESTED"
        );

        customerDTO.setAppleId("DELETE");

        CustomerDTO result = customerService.save(customerDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, customerDTO.getId().toString()))
            .body(result);
    }

    @PostMapping("/customers/{id}/notify")
    public ResponseEntity<Void> sendNotification(@PathVariable Long id, @RequestBody String message) {
        notificationService.sendNotificationToCustomer(id, "Notification", message);
        return ResponseEntity.ok().build();
    }
}
