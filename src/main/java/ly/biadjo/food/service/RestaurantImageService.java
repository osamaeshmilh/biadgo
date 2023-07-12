package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.RestaurantImage;
import ly.biadjo.food.repository.RestaurantImageRepository;
import ly.biadjo.food.service.dto.RestaurantImageDTO;
import ly.biadjo.food.service.mapper.RestaurantImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RestaurantImage}.
 */
@Service
@Transactional
public class RestaurantImageService {

    private final Logger log = LoggerFactory.getLogger(RestaurantImageService.class);

    private final RestaurantImageRepository restaurantImageRepository;

    private final RestaurantImageMapper restaurantImageMapper;

    public RestaurantImageService(RestaurantImageRepository restaurantImageRepository, RestaurantImageMapper restaurantImageMapper) {
        this.restaurantImageRepository = restaurantImageRepository;
        this.restaurantImageMapper = restaurantImageMapper;
    }

    /**
     * Save a restaurantImage.
     *
     * @param restaurantImageDTO the entity to save.
     * @return the persisted entity.
     */
    public RestaurantImageDTO save(RestaurantImageDTO restaurantImageDTO) {
        log.debug("Request to save RestaurantImage : {}", restaurantImageDTO);
        RestaurantImage restaurantImage = restaurantImageMapper.toEntity(restaurantImageDTO);
        restaurantImage = restaurantImageRepository.save(restaurantImage);
        return restaurantImageMapper.toDto(restaurantImage);
    }

    /**
     * Update a restaurantImage.
     *
     * @param restaurantImageDTO the entity to save.
     * @return the persisted entity.
     */
    public RestaurantImageDTO update(RestaurantImageDTO restaurantImageDTO) {
        log.debug("Request to update RestaurantImage : {}", restaurantImageDTO);
        RestaurantImage restaurantImage = restaurantImageMapper.toEntity(restaurantImageDTO);
        restaurantImage = restaurantImageRepository.save(restaurantImage);
        return restaurantImageMapper.toDto(restaurantImage);
    }

    /**
     * Partially update a restaurantImage.
     *
     * @param restaurantImageDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RestaurantImageDTO> partialUpdate(RestaurantImageDTO restaurantImageDTO) {
        log.debug("Request to partially update RestaurantImage : {}", restaurantImageDTO);

        return restaurantImageRepository
            .findById(restaurantImageDTO.getId())
            .map(existingRestaurantImage -> {
                restaurantImageMapper.partialUpdate(existingRestaurantImage, restaurantImageDTO);

                return existingRestaurantImage;
            })
            .map(restaurantImageRepository::save)
            .map(restaurantImageMapper::toDto);
    }

    /**
     * Get all the restaurantImages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RestaurantImageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RestaurantImages");
        return restaurantImageRepository.findAll(pageable).map(restaurantImageMapper::toDto);
    }

    /**
     * Get all the restaurantImages with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<RestaurantImageDTO> findAllWithEagerRelationships(Pageable pageable) {
        return restaurantImageRepository.findAllWithEagerRelationships(pageable).map(restaurantImageMapper::toDto);
    }

    /**
     * Get one restaurantImage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RestaurantImageDTO> findOne(Long id) {
        log.debug("Request to get RestaurantImage : {}", id);
        return restaurantImageRepository.findOneWithEagerRelationships(id).map(restaurantImageMapper::toDto);
    }

    /**
     * Delete the restaurantImage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RestaurantImage : {}", id);
        restaurantImageRepository.deleteById(id);
    }
}
