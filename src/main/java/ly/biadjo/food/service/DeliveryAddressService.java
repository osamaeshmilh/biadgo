package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.DeliveryAddress;
import ly.biadjo.food.repository.DeliveryAddressRepository;
import ly.biadjo.food.service.dto.DeliveryAddressDTO;
import ly.biadjo.food.service.mapper.DeliveryAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DeliveryAddress}.
 */
@Service
@Transactional
public class DeliveryAddressService {

    private final Logger log = LoggerFactory.getLogger(DeliveryAddressService.class);

    private final DeliveryAddressRepository deliveryAddressRepository;

    private final DeliveryAddressMapper deliveryAddressMapper;

    public DeliveryAddressService(DeliveryAddressRepository deliveryAddressRepository, DeliveryAddressMapper deliveryAddressMapper) {
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.deliveryAddressMapper = deliveryAddressMapper;
    }

    /**
     * Save a deliveryAddress.
     *
     * @param deliveryAddressDTO the entity to save.
     * @return the persisted entity.
     */
    public DeliveryAddressDTO save(DeliveryAddressDTO deliveryAddressDTO) {
        log.debug("Request to save DeliveryAddress : {}", deliveryAddressDTO);
        DeliveryAddress deliveryAddress = deliveryAddressMapper.toEntity(deliveryAddressDTO);
        deliveryAddress = deliveryAddressRepository.save(deliveryAddress);
        return deliveryAddressMapper.toDto(deliveryAddress);
    }

    /**
     * Update a deliveryAddress.
     *
     * @param deliveryAddressDTO the entity to save.
     * @return the persisted entity.
     */
    public DeliveryAddressDTO update(DeliveryAddressDTO deliveryAddressDTO) {
        log.debug("Request to update DeliveryAddress : {}", deliveryAddressDTO);
        DeliveryAddress deliveryAddress = deliveryAddressMapper.toEntity(deliveryAddressDTO);
        deliveryAddress = deliveryAddressRepository.save(deliveryAddress);
        return deliveryAddressMapper.toDto(deliveryAddress);
    }

    /**
     * Partially update a deliveryAddress.
     *
     * @param deliveryAddressDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DeliveryAddressDTO> partialUpdate(DeliveryAddressDTO deliveryAddressDTO) {
        log.debug("Request to partially update DeliveryAddress : {}", deliveryAddressDTO);

        return deliveryAddressRepository
            .findById(deliveryAddressDTO.getId())
            .map(existingDeliveryAddress -> {
                deliveryAddressMapper.partialUpdate(existingDeliveryAddress, deliveryAddressDTO);

                return existingDeliveryAddress;
            })
            .map(deliveryAddressRepository::save)
            .map(deliveryAddressMapper::toDto);
    }

    /**
     * Get all the deliveryAddresses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DeliveryAddressDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryAddresses");
        return deliveryAddressRepository.findAll(pageable).map(deliveryAddressMapper::toDto);
    }

    /**
     * Get all the deliveryAddresses with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DeliveryAddressDTO> findAllWithEagerRelationships(Pageable pageable) {
        return deliveryAddressRepository.findAllWithEagerRelationships(pageable).map(deliveryAddressMapper::toDto);
    }

    /**
     * Get one deliveryAddress by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DeliveryAddressDTO> findOne(Long id) {
        log.debug("Request to get DeliveryAddress : {}", id);
        return deliveryAddressRepository.findOneWithEagerRelationships(id).map(deliveryAddressMapper::toDto);
    }

    /**
     * Delete the deliveryAddress by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DeliveryAddress : {}", id);
        deliveryAddressRepository.deleteById(id);
    }
}
