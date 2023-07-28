package ly.biadjo.food.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ly.biadjo.food.domain.enumeration.WalletAction;
import ly.biadjo.food.repository.CustomerWalletRepository;
import ly.biadjo.food.security.AuthoritiesConstants;
import ly.biadjo.food.security.SecurityUtils;
import ly.biadjo.food.service.CustomerService;
import ly.biadjo.food.service.CustomerWalletQueryService;
import ly.biadjo.food.service.CustomerWalletService;
import ly.biadjo.food.service.criteria.CustomerWalletCriteria;
import ly.biadjo.food.service.dto.CustomerWalletDTO;
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
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.biadjo.food.domain.CustomerWallet}.
 */
@RestController
@RequestMapping("/api")
public class CustomerWalletResource {

    private final Logger log = LoggerFactory.getLogger(CustomerWalletResource.class);

    private static final String ENTITY_NAME = "customerWallet";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerWalletService customerWalletService;

    private final CustomerWalletRepository customerWalletRepository;

    private final CustomerWalletQueryService customerWalletQueryService;

    private final CustomerService customerService;

    public CustomerWalletResource(
        CustomerWalletService customerWalletService,
        CustomerWalletRepository customerWalletRepository,
        CustomerWalletQueryService customerWalletQueryService,
        CustomerService customerService
    ) {
        this.customerWalletService = customerWalletService;
        this.customerWalletRepository = customerWalletRepository;
        this.customerWalletQueryService = customerWalletQueryService;
        this.customerService = customerService;
    }

    /**
     * {@code PATCH  /customer-wallets/:id} : Partial updates given fields of an existing customerWallet, field will ignore if it is null
     *
     * @param id                the id of the customerWalletDTO to save.
     * @param customerWalletDTO the customerWalletDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customerWalletDTO,
     * or with status {@code 400 (Bad Request)} if the customerWalletDTO is not valid,
     * or with status {@code 404 (Not Found)} if the customerWalletDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the customerWalletDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/customer-wallets/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<CustomerWalletDTO> partialUpdateCustomerWallet(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CustomerWalletDTO customerWalletDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CustomerWallet partially : {}, {}", id, customerWalletDTO);
        if (customerWalletDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customerWalletDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customerWalletRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CustomerWalletDTO> result = customerWalletService.partialUpdate(customerWalletDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, customerWalletDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /customer-wallets} : get all the customerWallets.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customerWallets in body.
     */
    @GetMapping("/customer-wallets")
    public ResponseEntity<List<CustomerWalletDTO>> getAllCustomerWallets(CustomerWalletCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CustomerWallets by criteria: {}", criteria);
        Page<CustomerWalletDTO> page;
        LongFilter longFilter = new LongFilter();
        if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CUSTOMER)) {
            longFilter.setEquals(customerService.findOneByUser().getId());
            criteria.setCustomerId(longFilter);
            page = customerWalletQueryService.findByCriteria(criteria, pageable);
        } else if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.ADMIN)) {
            page = customerWalletQueryService.findByCriteria(criteria, pageable);
        } else {
            page = Page.empty();
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /customer-wallets/count} : count all the customerWallets.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/customer-wallets/count")
    public ResponseEntity<Long> countCustomerWallets(CustomerWalletCriteria criteria) {
        log.debug("REST request to count CustomerWallets by criteria: {}", criteria);
        return ResponseEntity.ok().body(customerWalletQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /customer-wallets/:id} : get the "id" customerWallet.
     *
     * @param id the id of the customerWalletDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customerWalletDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer-wallets/{id}")
    public ResponseEntity<CustomerWalletDTO> getCustomerWallet(@PathVariable Long id) {
        log.debug("REST request to get CustomerWallet : {}", id);
        Optional<CustomerWalletDTO> customerWalletDTO = customerWalletService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customerWalletDTO);
    }

    @DeleteMapping("/customer-wallets/{id}")
    public ResponseEntity<Void> deleteCustomerWallet(@PathVariable Long id) {
        log.debug("REST request to delete CustomerWallet : {}", id);
        customerWalletService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/customer-wallets/balance")
    public ResponseEntity<Double> getCustomerWalletBalance(CustomerWalletCriteria criteria) {
        if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CUSTOMER)) {
            LongFilter longFilter = new LongFilter();
            longFilter.setEquals(customerService.findOneByUser().getId());
            criteria.setCustomerId(longFilter);
        }
        return ResponseEntity.ok().body(customerWalletQueryService.sumAmountByCriteria(criteria));
    }

//    @PostMapping("/customer-wallets/transfer")
//    public ResponseEntity<CustomerWalletDTO> getCustomerWalletTransfer(
//        @RequestParam String toCustomerPublicKey,
//        @RequestParam Float amount,
//        @RequestParam String otp
//    ) throws URISyntaxException {
//        System.out.println(customerService.findOneUser().getResetKey() + " " + otp);
//
//        CustomerWalletDTO result = null;
//        if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CUSTOMER)) {
//            if (!customerService.findOneByWalletPublicKey(toCustomerPublicKey).isPresent()) throw new BadRequestAlertException(
//                "Wallet Key not found!",
//                ENTITY_NAME,
//                "CUSTOMER_NOT_FOUND"
//            );
//
//            CustomerDTO fromCustomer = customerService.findOneDTOByUser();
//            CustomerDTO toCustomer = customerService.findOneByWalletPublicKey(toCustomerPublicKey).get();
//            if (fromCustomer.getVerifiedByMobileNo()) activationService.checkCodeWithMobileNo(fromCustomer.getMobileNo(), otp); else if (
//                fromCustomer.getVerifiedByEmail()
//            ) activationService.checkCodeWithEmail(fromCustomer.getEmail(), otp); else throw new BadRequestAlertException(
//                    "No Verification Way !",
//                    "",
//                    "NO_VERIFICATION_WAY"
//                );
//
//            if (fromCustomer.getIsBanned()) {
//                throw new BadRequestAlertException("Customer Banned !", "", "CUSTOMER_BANNED");
//            }
//            if (!fromCustomer.getIsVerified()) {
//                throw new BadRequestAlertException("Customer Not Verified !", "", "CUSTOMER_NOT_VERIFIED");
//            }
//
//            if (fromCustomer.getId() == toCustomer.getId()) throw new BadRequestAlertException(
//                "Can not transfer to yourself!",
//                ENTITY_NAME,
//                "TRANSFER_NOT_ALLOWED_TO_SAME_CUSTOMER"
//            );
//
//            result = customerWalletService.transfer(fromCustomer, toCustomer, amount);
//        }
//        return ResponseEntity
//            .created(new URI("/api/customer-wallets/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

//    @PostMapping("/customer-wallets/transfer-fees")
//    public ResponseEntity<String> getCustomerWalletTransferFees(@RequestParam Float amount) throws URISyntaxException {
//        String jsonResponse =
//            "[" + "{ \"name\" : \"ETRAVEL_FEES\", \"total\" : " + customerWalletService.getTransferFees(amount).toString() + " }" + "]";
//
//        return ResponseEntity.ok(jsonResponse);
//    }

//    @PostMapping("/customer-wallets/transfer-mobile")
//    public ResponseEntity<CustomerWalletDTO> customerWalletTransferMobile(
//        @RequestParam String mobileNo,
//        @RequestParam Float amount,
//        @RequestParam String otp
//    ) throws URISyntaxException {
//        mobileNo = mobileNo.trim();
//        mobileNo = mobileNo.replace("+", "");
//        mobileNo = mobileNo.replace(" ", "");
//
//        System.out.println(customerService.findOneUser().getResetKey() + " " + otp + " " + mobileNo);
//
//        CustomerWalletDTO result = null;
//        if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CUSTOMER)) {
//            CustomerDTO fromCustomer = customerService.findOneDTOByUser();
//            if (fromCustomer.getVerifiedByMobileNo()) activationService.checkCodeWithMobileNo(fromCustomer.getMobileNo(), otp); else if (
//                fromCustomer.getVerifiedByEmail()
//            ) activationService.checkCodeWithEmail(fromCustomer.getEmail(), otp); else throw new BadRequestAlertException(
//                    "No Verification Way !",
//                    "",
//                    "NO_VERIFICATION_WAY"
//                );
//
//            if (fromCustomer.getIsBanned()) {
//                throw new BadRequestAlertException("Customer Banned !", "", "CUSTOMER_BANNED");
//            }
//            if (!fromCustomer.getIsVerified()) {
//                throw new BadRequestAlertException("Customer Not Verified !", "", "CUSTOMER_NOT_VERIFIED");
//            }
//
//            if (!customerService.findOneByMobileNo(mobileNo).isPresent()) throw new BadRequestAlertException(
//                "Mobile not found!",
//                ENTITY_NAME,
//                "CUSTOMER_NOT_FOUND"
//            );
//
//            CustomerDTO toCustomer = customerService.findOneByMobileNo(mobileNo).get();
//
//            if (fromCustomer.getId() == toCustomer.getId()) throw new BadRequestAlertException(
//                "Can not transfer to yourself!",
//                ENTITY_NAME,
//                "TRANSFER_NOT_ALLOWED_TO_SAME_CUSTOMER"
//            );
//            result = customerWalletService.transfer(fromCustomer, toCustomer, amount);
//        }
//        return ResponseEntity
//            .created(new URI("/api/customer-wallets/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

//    @Secured(AuthoritiesConstants.ADMIN)
//    @PostMapping("/customer-wallets/add-credit")
//    public ResponseEntity<CustomerWalletDTO> addCustomerWalletBalance(@RequestBody CustomerWalletDTO customerWalletDTO)
//        throws URISyntaxException {
//        if (customerWalletDTO.getId() != null) {
//            throw new BadRequestAlertException("A new customerWallet cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        customerWalletDTO.setWalletAction(WalletAction.DEPOSIT);
//        customerWalletDTO.setNotes("Add Credit From Admin " + customerWalletDTO.getNotes());
//        CustomerWalletDTO result = customerWalletService.createFromAdmin(customerWalletDTO);
//
//        return ResponseEntity
//            .created(new URI("/api/customer-wallets/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

//    @Secured(AuthoritiesConstants.ADMIN)
//    @PostMapping("/customer-wallets/cash-withdrawal")
//    public ResponseEntity<CustomerWalletDTO> cashWithdrawal(@RequestBody CustomerWalletDTO customerWalletDTO) throws URISyntaxException {
//        if (customerWalletDTO.getId() != null) {
//            throw new BadRequestAlertException("A new customerWallet cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//
//        CustomerWalletCriteria criteria = new CustomerWalletCriteria();
//
//        LongFilter longFilter = new LongFilter();
//        longFilter.setEquals(customerWalletDTO.getCustomer().getId());
//        criteria.setCustomerId(longFilter);
//
//        Float withdrawalFee = Float.valueOf(settingService.getSettingByKey("cash_withdrawal").get().getValue());
//
//        if (customerWalletQueryService.sumAmountByCriteria(criteria) < (customerWalletDTO.getAmount() + withdrawalFee)) {
//            throw new BadRequestAlertException("No Credit in Wallet !", "", "NO_CREDIT");
//        }
//        CustomerWalletDTO result = customerWalletService.cashWithdrawalFromAdmin(customerWalletDTO);
//        return ResponseEntity
//            .created(new URI("/api/customer-wallets/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
}
