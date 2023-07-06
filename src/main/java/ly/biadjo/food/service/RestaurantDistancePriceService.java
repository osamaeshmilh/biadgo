package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.RestaurantDistancePrice;
import ly.biadjo.food.repository.RestaurantDistancePriceRepository;
import ly.biadjo.food.service.dto.RestaurantDistancePriceDTO;
import ly.biadjo.food.service.mapper.RestaurantDistancePriceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RestaurantDistancePrice}.
 */
@Service
@Transactional
public class RestaurantDistancePriceService {

    private final Logger log = LoggerFactory.getLogger(RestaurantDistancePriceService.class);

    private final RestaurantDistancePriceRepository restaurantDistancePriceRepository;

    private final RestaurantDistancePriceMapper restaurantDistancePriceMapper;

    public RestaurantDistancePriceService(
        RestaurantDistancePriceRepository restaurantDistancePriceRepository,
        RestaurantDistancePriceMapper restaurantDistancePriceMapper
    ) {
        this.restaurantDistancePriceRepository = restaurantDistancePriceRepository;
        this.restaurantDistancePriceMapper = restaurantDistancePriceMapper;
    }

    /**
     * Save a restaurantDistancePrice.
     *
     * @param restaurantDistancePriceDTO the entity to save.
     * @return the persisted entity.
     */
    public RestaurantDistancePriceDTO save(RestaurantDistancePriceDTO restaurantDistancePriceDTO) {
        log.debug("Request to save RestaurantDistancePrice : {}", restaurantDistancePriceDTO);
        RestaurantDistancePrice restaurantDistancePrice = restaurantDistancePriceMapper.toEntity(restaurantDistancePriceDTO);
        restaurantDistancePrice = restaurantDistancePriceRepository.save(restaurantDistancePrice);
        return restaurantDistancePriceMapper.toDto(restaurantDistancePrice);
    }

    /**
     * Update a restaurantDistancePrice.
     *
     * @param restaurantDistancePriceDTO the entity to save.
     * @return the persisted entity.
     */
    public RestaurantDistancePriceDTO update(RestaurantDistancePriceDTO restaurantDistancePriceDTO) {
        log.debug("Request to update RestaurantDistancePrice : {}", restaurantDistancePriceDTO);
        RestaurantDistancePrice restaurantDistancePrice = restaurantDistancePriceMapper.toEntity(restaurantDistancePriceDTO);
        restaurantDistancePrice = restaurantDistancePriceRepository.save(restaurantDistancePrice);
        return restaurantDistancePriceMapper.toDto(restaurantDistancePrice);
    }

    /**
     * Partially update a restaurantDistancePrice.
     *
     * @param restaurantDistancePriceDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RestaurantDistancePriceDTO> partialUpdate(RestaurantDistancePriceDTO restaurantDistancePriceDTO) {
        log.debug("Request to partially update RestaurantDistancePrice : {}", restaurantDistancePriceDTO);

        return restaurantDistancePriceRepository
            .findById(restaurantDistancePriceDTO.getId())
            .map(existingRestaurantDistancePrice -> {
                restaurantDistancePriceMapper.partialUpdate(existingRestaurantDistancePrice, restaurantDistancePriceDTO);

                return existingRestaurantDistancePrice;
            })
            .map(restaurantDistancePriceRepository::save)
            .map(restaurantDistancePriceMapper::toDto);
    }

    /**
     * Get all the restaurantDistancePrices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RestaurantDistancePriceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RestaurantDistancePrices");
        return restaurantDistancePriceRepository.findAll(pageable).map(restaurantDistancePriceMapper::toDto);
    }

    /**
     * Get all the restaurantDistancePrices with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<RestaurantDistancePriceDTO> findAllWithEagerRelationships(Pageable pageable) {
        return restaurantDistancePriceRepository.findAllWithEagerRelationships(pageable).map(restaurantDistancePriceMapper::toDto);
    }

    /**
     * Get one restaurantDistancePrice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RestaurantDistancePriceDTO> findOne(Long id) {
        log.debug("Request to get RestaurantDistancePrice : {}", id);
        return restaurantDistancePriceRepository.findOneWithEagerRelationships(id).map(restaurantDistancePriceMapper::toDto);
    }

    /**
     * Delete the restaurantDistancePrice by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RestaurantDistancePrice : {}", id);
        restaurantDistancePriceRepository.deleteById(id);
    }
}
