package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.RestaurantZonePrice;
import ly.biadjo.food.repository.RestaurantZonePriceRepository;
import ly.biadjo.food.service.dto.RestaurantZonePriceDTO;
import ly.biadjo.food.service.mapper.RestaurantZonePriceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RestaurantZonePrice}.
 */
@Service
@Transactional
public class RestaurantZonePriceService {

    private final Logger log = LoggerFactory.getLogger(RestaurantZonePriceService.class);

    private final RestaurantZonePriceRepository restaurantZonePriceRepository;

    private final RestaurantZonePriceMapper restaurantZonePriceMapper;

    public RestaurantZonePriceService(
        RestaurantZonePriceRepository restaurantZonePriceRepository,
        RestaurantZonePriceMapper restaurantZonePriceMapper
    ) {
        this.restaurantZonePriceRepository = restaurantZonePriceRepository;
        this.restaurantZonePriceMapper = restaurantZonePriceMapper;
    }

    /**
     * Save a restaurantZonePrice.
     *
     * @param restaurantZonePriceDTO the entity to save.
     * @return the persisted entity.
     */
    public RestaurantZonePriceDTO save(RestaurantZonePriceDTO restaurantZonePriceDTO) {
        log.debug("Request to save RestaurantZonePrice : {}", restaurantZonePriceDTO);
        RestaurantZonePrice restaurantZonePrice = restaurantZonePriceMapper.toEntity(restaurantZonePriceDTO);
        restaurantZonePrice = restaurantZonePriceRepository.save(restaurantZonePrice);
        return restaurantZonePriceMapper.toDto(restaurantZonePrice);
    }

    /**
     * Update a restaurantZonePrice.
     *
     * @param restaurantZonePriceDTO the entity to save.
     * @return the persisted entity.
     */
    public RestaurantZonePriceDTO update(RestaurantZonePriceDTO restaurantZonePriceDTO) {
        log.debug("Request to update RestaurantZonePrice : {}", restaurantZonePriceDTO);
        RestaurantZonePrice restaurantZonePrice = restaurantZonePriceMapper.toEntity(restaurantZonePriceDTO);
        restaurantZonePrice = restaurantZonePriceRepository.save(restaurantZonePrice);
        return restaurantZonePriceMapper.toDto(restaurantZonePrice);
    }

    /**
     * Partially update a restaurantZonePrice.
     *
     * @param restaurantZonePriceDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RestaurantZonePriceDTO> partialUpdate(RestaurantZonePriceDTO restaurantZonePriceDTO) {
        log.debug("Request to partially update RestaurantZonePrice : {}", restaurantZonePriceDTO);

        return restaurantZonePriceRepository
            .findById(restaurantZonePriceDTO.getId())
            .map(existingRestaurantZonePrice -> {
                restaurantZonePriceMapper.partialUpdate(existingRestaurantZonePrice, restaurantZonePriceDTO);

                return existingRestaurantZonePrice;
            })
            .map(restaurantZonePriceRepository::save)
            .map(restaurantZonePriceMapper::toDto);
    }

    /**
     * Get all the restaurantZonePrices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RestaurantZonePriceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RestaurantZonePrices");
        return restaurantZonePriceRepository.findAll(pageable).map(restaurantZonePriceMapper::toDto);
    }

    /**
     * Get all the restaurantZonePrices with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<RestaurantZonePriceDTO> findAllWithEagerRelationships(Pageable pageable) {
        return restaurantZonePriceRepository.findAllWithEagerRelationships(pageable).map(restaurantZonePriceMapper::toDto);
    }

    /**
     * Get one restaurantZonePrice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RestaurantZonePriceDTO> findOne(Long id) {
        log.debug("Request to get RestaurantZonePrice : {}", id);
        return restaurantZonePriceRepository.findOneWithEagerRelationships(id).map(restaurantZonePriceMapper::toDto);
    }

    /**
     * Delete the restaurantZonePrice by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RestaurantZonePrice : {}", id);
        restaurantZonePriceRepository.deleteById(id);
    }
}
