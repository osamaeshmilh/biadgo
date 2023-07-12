package ly.biadjo.food.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ly.biadjo.food.domain.Customer;
import ly.biadjo.food.domain.User;
import ly.biadjo.food.repository.CustomerRepository;
import ly.biadjo.food.security.AuthoritiesConstants;
import ly.biadjo.food.service.dto.CustomerDTO;
import ly.biadjo.food.service.mapper.CustomerMapper;
import ly.biadjo.food.service.utils.FileTools;
import ly.biadjo.food.web.rest.errors.BadRequestAlertException;
import ly.biadjo.food.web.rest.vm.ManagedUserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Customer}.
 */
@Service
@Transactional
public class CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final UserService userService;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, UserService userService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.userService = userService;
    }

    /**
     * Save a customer.
     *
     * @param customerDTO the entity to save.
     * @return the persisted entity.
     */
    public CustomerDTO save(CustomerDTO customerDTO) {
        log.debug("Request to save Customer : {}", customerDTO);
        Customer customer = customerMapper.toEntity(customerDTO);

        if (customerDTO.getImage() != null) {
            String filePath = FileTools.upload(customer.getImage(), customer.getImageContentType(), "customer");
            customer.setImage(null);
            customer.setImageContentType(customerDTO.getImageContentType());
            customer.setImageUrl(filePath);
        }

        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    /**
     * Update a customer.
     *
     * @param customerDTO the entity to save.
     * @return the persisted entity.
     */
    public CustomerDTO update(CustomerDTO customerDTO) {
        log.debug("Request to update Customer : {}", customerDTO);
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    /**
     * Partially update a customer.
     *
     * @param customerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CustomerDTO> partialUpdate(CustomerDTO customerDTO) {
        log.debug("Request to partially update Customer : {}", customerDTO);

        return customerRepository
            .findById(customerDTO.getId())
            .map(existingCustomer -> {
                customerMapper.partialUpdate(existingCustomer, customerDTO);

                return existingCustomer;
            })
            .map(customerRepository::save)
            .map(customerMapper::toDto);
    }

    /**
     * Get all the customers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Customers");
        return customerRepository.findAll(pageable).map(customerMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<CustomerDTO> findAll() {
        log.debug("Request to get all Customers");
        return customerMapper.toDto(customerRepository.findAll());
    }

    /**
     * Get one customer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> findOne(Long id) {
        log.debug("Request to get Customer : {}", id);
        return customerRepository.findById(id).map(customerMapper::toDto);
    }

    /**
     * Delete the customer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Customer : {}", id);
        customerRepository.deleteById(id);
    }

    public CustomerDTO create(CustomerDTO customerDTO) {
        ManagedUserVM managedUserVM = new ManagedUserVM();
        managedUserVM.setFirstName(customerDTO.getName());
        managedUserVM.setEmail(customerDTO.getEmail());
        managedUserVM.setLogin(customerDTO.getEmail());
        managedUserVM.setPhone(customerDTO.getMobileNo());
        User user = userService.createAndAssignUserWithPassword(managedUserVM, AuthoritiesConstants.CUSTOMER, customerDTO.getNewPassword());

        Customer customer = customerMapper.toEntity(customerDTO);
        customer.setUser(user);
        customer.setWalletPublicKey(UUID.randomUUID().toString());
        customer.setIsBanned(false);
        customer.setIsVerified(true);

        if (customerDTO.getImage() != null) {
            String filePath = FileTools.upload(customer.getImage(), customer.getImageContentType(), "customer");
            customer.setImage(null);
            customer.setImageContentType(customerDTO.getImageContentType());
            customer.setImageUrl(filePath);
        }

        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    public Customer findOneByUser() {
        if (userService.getUserWithAuthorities().isPresent()) return customerRepository.findByUser(
            userService.getUserWithAuthorities().get()
        );
        else throw new BadRequestAlertException("Customer User Not Found !", "", "CUSTOMER_NOT_FOUND");
    }

    public CustomerDTO findOneDTOByUser() {
        if (userService.getUserWithAuthorities().isPresent()) return customerMapper.toDto(
            customerRepository.findByUser(userService.getUserWithAuthorities().get())
        );
        else throw new BadRequestAlertException("Customer User Not Found !", "", "CUSTOMER_NOT_FOUND");
    }

    public User findOneUser() {
        if (userService.getUserWithAuthorities().isPresent()) return userService
            .getUserWithAuthorities()
            .get();
        else throw new BadRequestAlertException("Customer User Not Found !", "", "CUSTOMER_NOT_FOUND");
    }

    public Optional<CustomerDTO> findOneByWalletPublicKey(String walletPublicKey) {
        return customerRepository.findTopByWalletPublicKey(walletPublicKey).map(customerMapper::toDto);
    }

    public Optional<CustomerDTO> findOneByMobileNo(String mobileNo) {
        return customerRepository.findFirstByMobileNoContaining(mobileNo).map(customerMapper::toDto);
    }

    public Optional<CustomerDTO> findOneByEmail(String email) {
        return customerRepository.findFirstByEmail(email).map(customerMapper::toDto);
    }

    public void verifyUser(User user) {
        CustomerDTO customerDTO = customerMapper.toDto(customerRepository.findByUser(user));
        customerDTO.setIsVerified(true);
        System.out.println(customerDTO.getName());
        save(customerDTO);
    }
}
