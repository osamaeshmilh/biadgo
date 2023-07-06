package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.FavoriteRestaurant;
import ly.biadjo.food.repository.FavoriteRestaurantRepository;
import ly.biadjo.food.service.dto.FavoriteRestaurantDTO;
import ly.biadjo.food.service.mapper.FavoriteRestaurantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FavoriteRestaurant}.
 */
@Service
@Transactional
public class FavoriteRestaurantService {

    private final Logger log = LoggerFactory.getLogger(FavoriteRestaurantService.class);

    private final FavoriteRestaurantRepository favoriteRestaurantRepository;

    private final FavoriteRestaurantMapper favoriteRestaurantMapper;

    public FavoriteRestaurantService(
        FavoriteRestaurantRepository favoriteRestaurantRepository,
        FavoriteRestaurantMapper favoriteRestaurantMapper
    ) {
        this.favoriteRestaurantRepository = favoriteRestaurantRepository;
        this.favoriteRestaurantMapper = favoriteRestaurantMapper;
    }

    /**
     * Save a favoriteRestaurant.
     *
     * @param favoriteRestaurantDTO the entity to save.
     * @return the persisted entity.
     */
    public FavoriteRestaurantDTO save(FavoriteRestaurantDTO favoriteRestaurantDTO) {
        log.debug("Request to save FavoriteRestaurant : {}", favoriteRestaurantDTO);
        FavoriteRestaurant favoriteRestaurant = favoriteRestaurantMapper.toEntity(favoriteRestaurantDTO);
        favoriteRestaurant = favoriteRestaurantRepository.save(favoriteRestaurant);
        return favoriteRestaurantMapper.toDto(favoriteRestaurant);
    }

    /**
     * Update a favoriteRestaurant.
     *
     * @param favoriteRestaurantDTO the entity to save.
     * @return the persisted entity.
     */
    public FavoriteRestaurantDTO update(FavoriteRestaurantDTO favoriteRestaurantDTO) {
        log.debug("Request to update FavoriteRestaurant : {}", favoriteRestaurantDTO);
        FavoriteRestaurant favoriteRestaurant = favoriteRestaurantMapper.toEntity(favoriteRestaurantDTO);
        favoriteRestaurant = favoriteRestaurantRepository.save(favoriteRestaurant);
        return favoriteRestaurantMapper.toDto(favoriteRestaurant);
    }

    /**
     * Partially update a favoriteRestaurant.
     *
     * @param favoriteRestaurantDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FavoriteRestaurantDTO> partialUpdate(FavoriteRestaurantDTO favoriteRestaurantDTO) {
        log.debug("Request to partially update FavoriteRestaurant : {}", favoriteRestaurantDTO);

        return favoriteRestaurantRepository
            .findById(favoriteRestaurantDTO.getId())
            .map(existingFavoriteRestaurant -> {
                favoriteRestaurantMapper.partialUpdate(existingFavoriteRestaurant, favoriteRestaurantDTO);

                return existingFavoriteRestaurant;
            })
            .map(favoriteRestaurantRepository::save)
            .map(favoriteRestaurantMapper::toDto);
    }

    /**
     * Get all the favoriteRestaurants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FavoriteRestaurantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FavoriteRestaurants");
        return favoriteRestaurantRepository.findAll(pageable).map(favoriteRestaurantMapper::toDto);
    }

    /**
     * Get all the favoriteRestaurants with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<FavoriteRestaurantDTO> findAllWithEagerRelationships(Pageable pageable) {
        return favoriteRestaurantRepository.findAllWithEagerRelationships(pageable).map(favoriteRestaurantMapper::toDto);
    }

    /**
     * Get one favoriteRestaurant by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FavoriteRestaurantDTO> findOne(Long id) {
        log.debug("Request to get FavoriteRestaurant : {}", id);
        return favoriteRestaurantRepository.findOneWithEagerRelationships(id).map(favoriteRestaurantMapper::toDto);
    }

    /**
     * Delete the favoriteRestaurant by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FavoriteRestaurant : {}", id);
        favoriteRestaurantRepository.deleteById(id);
    }
}
