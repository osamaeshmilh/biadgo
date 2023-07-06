package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.Activation;
import ly.biadjo.food.repository.ActivationRepository;
import ly.biadjo.food.service.dto.ActivationDTO;
import ly.biadjo.food.service.mapper.ActivationMapper;
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

    public ActivationService(ActivationRepository activationRepository, ActivationMapper activationMapper) {
        this.activationRepository = activationRepository;
        this.activationMapper = activationMapper;
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
}
