package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.RestaurantSchedule;
import ly.biadjo.food.repository.RestaurantScheduleRepository;
import ly.biadjo.food.service.dto.RestaurantScheduleDTO;
import ly.biadjo.food.service.mapper.RestaurantScheduleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RestaurantSchedule}.
 */
@Service
@Transactional
public class RestaurantScheduleService {

    private final Logger log = LoggerFactory.getLogger(RestaurantScheduleService.class);

    private final RestaurantScheduleRepository restaurantScheduleRepository;

    private final RestaurantScheduleMapper restaurantScheduleMapper;

    public RestaurantScheduleService(
        RestaurantScheduleRepository restaurantScheduleRepository,
        RestaurantScheduleMapper restaurantScheduleMapper
    ) {
        this.restaurantScheduleRepository = restaurantScheduleRepository;
        this.restaurantScheduleMapper = restaurantScheduleMapper;
    }

    /**
     * Save a restaurantSchedule.
     *
     * @param restaurantScheduleDTO the entity to save.
     * @return the persisted entity.
     */
    public RestaurantScheduleDTO save(RestaurantScheduleDTO restaurantScheduleDTO) {
        log.debug("Request to save RestaurantSchedule : {}", restaurantScheduleDTO);
        RestaurantSchedule restaurantSchedule = restaurantScheduleMapper.toEntity(restaurantScheduleDTO);
        restaurantSchedule = restaurantScheduleRepository.save(restaurantSchedule);
        return restaurantScheduleMapper.toDto(restaurantSchedule);
    }

    /**
     * Update a restaurantSchedule.
     *
     * @param restaurantScheduleDTO the entity to save.
     * @return the persisted entity.
     */
    public RestaurantScheduleDTO update(RestaurantScheduleDTO restaurantScheduleDTO) {
        log.debug("Request to update RestaurantSchedule : {}", restaurantScheduleDTO);
        RestaurantSchedule restaurantSchedule = restaurantScheduleMapper.toEntity(restaurantScheduleDTO);
        restaurantSchedule = restaurantScheduleRepository.save(restaurantSchedule);
        return restaurantScheduleMapper.toDto(restaurantSchedule);
    }

    /**
     * Partially update a restaurantSchedule.
     *
     * @param restaurantScheduleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RestaurantScheduleDTO> partialUpdate(RestaurantScheduleDTO restaurantScheduleDTO) {
        log.debug("Request to partially update RestaurantSchedule : {}", restaurantScheduleDTO);

        return restaurantScheduleRepository
            .findById(restaurantScheduleDTO.getId())
            .map(existingRestaurantSchedule -> {
                restaurantScheduleMapper.partialUpdate(existingRestaurantSchedule, restaurantScheduleDTO);

                return existingRestaurantSchedule;
            })
            .map(restaurantScheduleRepository::save)
            .map(restaurantScheduleMapper::toDto);
    }

    /**
     * Get all the restaurantSchedules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RestaurantScheduleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RestaurantSchedules");
        return restaurantScheduleRepository.findAll(pageable).map(restaurantScheduleMapper::toDto);
    }

    /**
     * Get all the restaurantSchedules with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<RestaurantScheduleDTO> findAllWithEagerRelationships(Pageable pageable) {
        return restaurantScheduleRepository.findAllWithEagerRelationships(pageable).map(restaurantScheduleMapper::toDto);
    }

    /**
     * Get one restaurantSchedule by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RestaurantScheduleDTO> findOne(Long id) {
        log.debug("Request to get RestaurantSchedule : {}", id);
        return restaurantScheduleRepository.findOneWithEagerRelationships(id).map(restaurantScheduleMapper::toDto);
    }

    /**
     * Delete the restaurantSchedule by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RestaurantSchedule : {}", id);
        restaurantScheduleRepository.deleteById(id);
    }
}
