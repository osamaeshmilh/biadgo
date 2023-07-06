package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.RestaurantReview;
import ly.biadjo.food.repository.RestaurantReviewRepository;
import ly.biadjo.food.service.dto.RestaurantReviewDTO;
import ly.biadjo.food.service.mapper.RestaurantReviewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RestaurantReview}.
 */
@Service
@Transactional
public class RestaurantReviewService {

    private final Logger log = LoggerFactory.getLogger(RestaurantReviewService.class);

    private final RestaurantReviewRepository restaurantReviewRepository;

    private final RestaurantReviewMapper restaurantReviewMapper;

    public RestaurantReviewService(RestaurantReviewRepository restaurantReviewRepository, RestaurantReviewMapper restaurantReviewMapper) {
        this.restaurantReviewRepository = restaurantReviewRepository;
        this.restaurantReviewMapper = restaurantReviewMapper;
    }

    /**
     * Save a restaurantReview.
     *
     * @param restaurantReviewDTO the entity to save.
     * @return the persisted entity.
     */
    public RestaurantReviewDTO save(RestaurantReviewDTO restaurantReviewDTO) {
        log.debug("Request to save RestaurantReview : {}", restaurantReviewDTO);
        RestaurantReview restaurantReview = restaurantReviewMapper.toEntity(restaurantReviewDTO);
        restaurantReview = restaurantReviewRepository.save(restaurantReview);
        return restaurantReviewMapper.toDto(restaurantReview);
    }

    /**
     * Update a restaurantReview.
     *
     * @param restaurantReviewDTO the entity to save.
     * @return the persisted entity.
     */
    public RestaurantReviewDTO update(RestaurantReviewDTO restaurantReviewDTO) {
        log.debug("Request to update RestaurantReview : {}", restaurantReviewDTO);
        RestaurantReview restaurantReview = restaurantReviewMapper.toEntity(restaurantReviewDTO);
        restaurantReview = restaurantReviewRepository.save(restaurantReview);
        return restaurantReviewMapper.toDto(restaurantReview);
    }

    /**
     * Partially update a restaurantReview.
     *
     * @param restaurantReviewDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RestaurantReviewDTO> partialUpdate(RestaurantReviewDTO restaurantReviewDTO) {
        log.debug("Request to partially update RestaurantReview : {}", restaurantReviewDTO);

        return restaurantReviewRepository
            .findById(restaurantReviewDTO.getId())
            .map(existingRestaurantReview -> {
                restaurantReviewMapper.partialUpdate(existingRestaurantReview, restaurantReviewDTO);

                return existingRestaurantReview;
            })
            .map(restaurantReviewRepository::save)
            .map(restaurantReviewMapper::toDto);
    }

    /**
     * Get all the restaurantReviews.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RestaurantReviewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RestaurantReviews");
        return restaurantReviewRepository.findAll(pageable).map(restaurantReviewMapper::toDto);
    }

    /**
     * Get all the restaurantReviews with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<RestaurantReviewDTO> findAllWithEagerRelationships(Pageable pageable) {
        return restaurantReviewRepository.findAllWithEagerRelationships(pageable).map(restaurantReviewMapper::toDto);
    }

    /**
     * Get one restaurantReview by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RestaurantReviewDTO> findOne(Long id) {
        log.debug("Request to get RestaurantReview : {}", id);
        return restaurantReviewRepository.findOneWithEagerRelationships(id).map(restaurantReviewMapper::toDto);
    }

    /**
     * Delete the restaurantReview by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RestaurantReview : {}", id);
        restaurantReviewRepository.deleteById(id);
    }
}
