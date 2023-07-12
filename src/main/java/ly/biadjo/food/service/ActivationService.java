package ly.biadjo.food.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import ly.biadjo.food.domain.Activation;
import ly.biadjo.food.domain.User;
import ly.biadjo.food.repository.ActivationRepository;
import ly.biadjo.food.service.dto.ActivationDTO;
import ly.biadjo.food.service.mapper.ActivationMapper;
import ly.biadjo.food.web.rest.errors.BadRequestAlertException;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Activation}.
 */
@Service
@Transactional
public class ActivationService {

    private final Logger log = LoggerFactory.getLogger(ActivationService.class);

    private final ActivationRepository activationRepository;

    private final ActivationMapper activationMapper;

    private final SMSService smsService;

    private final MailService mailService;

    public ActivationService(ActivationRepository activationRepository, ActivationMapper activationMapper, SMSService smsService, MailService mailService) {
        this.activationRepository = activationRepository;
        this.activationMapper = activationMapper;
        this.smsService = smsService;
        this.mailService = mailService;
    }

    /**
     * Save a activation.
     *
     * @param activationDTO the entity to save.
     * @return the persisted entity.
     */
    public ActivationDTO save(ActivationDTO activationDTO) {
        log.debug("Request to save Activation : {}", activationDTO);
        Activation activation = activationMapper.toEntity(activationDTO);
        activation = activationRepository.save(activation);
        return activationMapper.toDto(activation);
    }

    /**
     * Update a activation.
     *
     * @param activationDTO the entity to save.
     * @return the persisted entity.
     */
    public ActivationDTO update(ActivationDTO activationDTO) {
        log.debug("Request to update Activation : {}", activationDTO);
        Activation activation = activationMapper.toEntity(activationDTO);
        activation = activationRepository.save(activation);
        return activationMapper.toDto(activation);
    }

    /**
     * Partially update a activation.
     *
     * @param activationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ActivationDTO> partialUpdate(ActivationDTO activationDTO) {
        log.debug("Request to partially update Activation : {}", activationDTO);

        return activationRepository
            .findById(activationDTO.getId())
            .map(existingActivation -> {
                activationMapper.partialUpdate(existingActivation, activationDTO);

                return existingActivation;
            })
            .map(activationRepository::save)
            .map(activationMapper::toDto);
    }

    /**
     * Get all the activations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ActivationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Activations");
        return activationRepository.findAll(pageable).map(activationMapper::toDto);
    }

    /**
     * Get one activation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ActivationDTO> findOne(Long id) {
        log.debug("Request to get Activation : {}", id);
        return activationRepository.findById(id).map(activationMapper::toDto);
    }

    /**
     * Delete the activation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Activation : {}", id);
        activationRepository.deleteById(id);
    }

    public void sendEmailOTP(String email) {
        Activation newActivation;
        Optional<Activation> activationOptional = activationRepository.findByEmail(email);

        newActivation = activationOptional.orElseGet(Activation::new);
        String code = RandomStringUtils.randomNumeric(4);
        newActivation.setEmail(email);
        newActivation.setCode(code);
        newActivation.setIsUsed(false);
        newActivation.setValidUntil(Instant.now().plus(1, ChronoUnit.HOURS));
        Activation result = activationRepository.save(newActivation);

        User user = new User();
        user.setEmail(email);
        user.setLogin(email);
        user.setResetKey(result.getCode());
        mailService.sendOtpMail(user);
    }

    public void sendSMSOTP(String mobileNo) {
        Activation newActivation;
        Optional<Activation> activationOptional = activationRepository.findFirstByMobileNoContains(mobileNo);

        newActivation = activationOptional.orElseGet(Activation::new);
        String code = RandomStringUtils.randomNumeric(4);
        newActivation.setMobileNo(mobileNo);
        newActivation.setCode(code);
        newActivation.setIsUsed(false);
        newActivation.setValidUntil(Instant.now().plus(1, ChronoUnit.HOURS));
        Activation result = activationRepository.save(newActivation);
        smsService.sendSMS(result.getMobileNo(), result.getCode());
    }

    public Optional<Activation> checkCodeWithEmail(String email, String otp) {
        Optional<Activation> activationOptional = activationRepository.findByEmailAndCode(email, otp);
        if (!activationOptional.isPresent()) {
            throw new BadRequestAlertException("OTP and Email not match!", "", "EMAIL_CODE_NOT_MATCH");
        }
        if (activationOptional.get().getIsUsed()) {
            throw new BadRequestAlertException("OTP Already Used!", "", "OTP_USED");
        }
        if (Instant.now().isAfter(activationOptional.get().getValidUntil())) {
            throw new BadRequestAlertException("OTP Expired!", "", "OTP_Expired");
        }
        activationOptional.get().setIsUsed(true);
        save(activationMapper.toDto(activationOptional.get()));
        return activationOptional;
    }

    public Optional<Activation> checkCodeWithMobileNo(String mobileNo, String otp) {
        Optional<Activation> activationOptional = activationRepository.findFirstByMobileNoContainsAndCode(mobileNo, otp);
        if (!activationOptional.isPresent()) {
            throw new BadRequestAlertException("OTP and Mobile not match!", "", "MOBILE_CODE_NOT_MATCH");
        }
        if (activationOptional.get().getIsUsed()) {
            throw new BadRequestAlertException("OTP Already Used!", "", "OTP_USED");
        }
        if (Instant.now().isAfter(activationOptional.get().getValidUntil())) {
            throw new BadRequestAlertException("OTP Expired!", "", "OTP_Expired");
        }
        activationOptional.get().setIsUsed(true);
        save(activationMapper.toDto(activationOptional.get()));
        return activationOptional;
    }
}
