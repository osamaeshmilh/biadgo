package ly.biadjo.food.service;

import java.util.Optional;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import ly.biadjo.food.domain.Notification;
import ly.biadjo.food.repository.NotificationRepository;
import ly.biadjo.food.service.dto.CustomerDTO;
import ly.biadjo.food.service.dto.NotificationDTO;
import ly.biadjo.food.service.mapper.NotificationMapper;
import ly.biadjo.food.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

/**
 * Service Implementation for managing {@link Notification}.
 */
@Service
@Transactional
public class NotificationService {

    private final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    private final CustomerService customerService;

    private final UserService userService;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper, CustomerService customerService, UserService userService) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.customerService = customerService;
        this.userService = userService;
    }

    /**
     * Save a notification.
     *
     * @param notificationDTO the entity to save.
     * @return the persisted entity.
     */
    public NotificationDTO save(NotificationDTO notificationDTO) {
        log.debug("Request to save Notification : {}", notificationDTO);
        Notification notification = notificationMapper.toEntity(notificationDTO);
        notification = notificationRepository.save(notification);
        return notificationMapper.toDto(notification);
    }

    /**
     * Update a notification.
     *
     * @param notificationDTO the entity to save.
     * @return the persisted entity.
     */
    public NotificationDTO update(NotificationDTO notificationDTO) {
        log.debug("Request to update Notification : {}", notificationDTO);
        Notification notification = notificationMapper.toEntity(notificationDTO);
        notification = notificationRepository.save(notification);
        return notificationMapper.toDto(notification);
    }

    /**
     * Partially update a notification.
     *
     * @param notificationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NotificationDTO> partialUpdate(NotificationDTO notificationDTO) {
        log.debug("Request to partially update Notification : {}", notificationDTO);

        return notificationRepository
            .findById(notificationDTO.getId())
            .map(existingNotification -> {
                notificationMapper.partialUpdate(existingNotification, notificationDTO);

                return existingNotification;
            })
            .map(notificationRepository::save)
            .map(notificationMapper::toDto);
    }

    /**
     * Get all the notifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NotificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Notifications");
        return notificationRepository.findAll(pageable).map(notificationMapper::toDto);
    }

    /**
     * Get one notification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NotificationDTO> findOne(Long id) {
        log.debug("Request to get Notification : {}", id);
        return notificationRepository.findById(id).map(notificationMapper::toDto);
    }

    /**
     * Delete the notification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Notification : {}", id);
        notificationRepository.deleteById(id);
    }

    public void sendNotificationToCustomer(Long customerId, String title, String message) {
        final String FIREBASE_API_KEY =
            "AAAAksIct0U:-LmSZ35-";
        Optional<CustomerDTO> customerDTO = customerService.findOne(customerId);
        log.debug("Sending notification to Customer: {}", customerId);

        if (customerDTO.isPresent()) {
            String firebaseId = userService.getUserWithAuthoritiesById(customerDTO.get().getUser().getId()).get().getFirebaseId();

            log.debug("Firebase ID: {}", firebaseId);

            try {
                JSONObject jsonInput = new JSONObject();
                jsonInput.put("to", firebaseId);

                JSONObject notification = new JSONObject();
                notification.put("title", title);
                notification.put("body", message);
                jsonInput.put("notification", notification);

                HttpResponse<String> response = Unirest
                    .post("https://fcm.googleapis.com/fcm/send")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "key=" + FIREBASE_API_KEY)
                    .body(jsonInput.toString())
                    .asString();

                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setTitle(title);
                notificationDTO.setDetails(message);
                notificationDTO.setCustomerId(customerId);

                save(notificationDTO);

                log.debug("Firebase response: {}", response.getBody());
            } catch (Exception ex) {
                log.error("Error sending Firebase notification", ex);
                throw new BadRequestAlertException("Failed to send notification", ex.toString(), "idnotfound");
            }
        } else {
            log.warn("Customer not found: {}", customerId);
            throw new BadRequestAlertException("Customer not found", ENTITY_NAME, "idnotfound");
        }
    }

}
