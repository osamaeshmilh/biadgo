package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.DriverLocation;
import ly.biadjo.food.repository.DriverLocationRepository;
import ly.biadjo.food.service.dto.DriverLocationDTO;
import ly.biadjo.food.service.mapper.DriverLocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DriverLocation}.
 */
@Service
@Transactional
public class DriverLocationService {

    private final Logger log = LoggerFactory.getLogger(DriverLocationService.class);

    private final DriverLocationRepository driverLocationRepository;

    private final DriverLocationMapper driverLocationMapper;

    public DriverLocationService(DriverLocationRepository driverLocationRepository, DriverLocationMapper driverLocationMapper) {
        this.driverLocationRepository = driverLocationRepository;
        this.driverLocationMapper = driverLocationMapper;
    }

    /**
     * Save a driverLocation.
     *
     * @param driverLocationDTO the entity to save.
     * @return the persisted entity.
     */
    public DriverLocationDTO save(DriverLocationDTO driverLocationDTO) {
        log.debug("Request to save DriverLocation : {}", driverLocationDTO);
        DriverLocation driverLocation = driverLocationMapper.toEntity(driverLocationDTO);
        driverLocation = driverLocationRepository.save(driverLocation);
        return driverLocationMapper.toDto(driverLocation);
    }

    /**
     * Update a driverLocation.
     *
     * @param driverLocationDTO the entity to save.
     * @return the persisted entity.
     */
    public DriverLocationDTO update(DriverLocationDTO driverLocationDTO) {
        log.debug("Request to update DriverLocation : {}", driverLocationDTO);
        DriverLocation driverLocation = driverLocationMapper.toEntity(driverLocationDTO);
        driverLocation = driverLocationRepository.save(driverLocation);
        return driverLocationMapper.toDto(driverLocation);
    }

    /**
     * Partially update a driverLocation.
     *
     * @param driverLocationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DriverLocationDTO> partialUpdate(DriverLocationDTO driverLocationDTO) {
        log.debug("Request to partially update DriverLocation : {}", driverLocationDTO);

        return driverLocationRepository
            .findById(driverLocationDTO.getId())
            .map(existingDriverLocation -> {
                driverLocationMapper.partialUpdate(existingDriverLocation, driverLocationDTO);

                return existingDriverLocation;
            })
            .map(driverLocationRepository::save)
            .map(driverLocationMapper::toDto);
    }

    /**
     * Get all the driverLocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DriverLocationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DriverLocations");
        return driverLocationRepository.findAll(pageable).map(driverLocationMapper::toDto);
    }

    /**
     * Get all the driverLocations with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DriverLocationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return driverLocationRepository.findAllWithEagerRelationships(pageable).map(driverLocationMapper::toDto);
    }

    /**
     * Get one driverLocation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DriverLocationDTO> findOne(Long id) {
        log.debug("Request to get DriverLocation : {}", id);
        return driverLocationRepository.findOneWithEagerRelationships(id).map(driverLocationMapper::toDto);
    }

    /**
     * Delete the driverLocation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DriverLocation : {}", id);
        driverLocationRepository.deleteById(id);
    }
}
