package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.Cuisine;
import ly.biadjo.food.repository.CuisineRepository;
import ly.biadjo.food.service.dto.CuisineDTO;
import ly.biadjo.food.service.mapper.CuisineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Cuisine}.
 */
@Service
@Transactional
public class CuisineService {

    private final Logger log = LoggerFactory.getLogger(CuisineService.class);

    private final CuisineRepository cuisineRepository;

    private final CuisineMapper cuisineMapper;

    public CuisineService(CuisineRepository cuisineRepository, CuisineMapper cuisineMapper) {
        this.cuisineRepository = cuisineRepository;
        this.cuisineMapper = cuisineMapper;
    }

    /**
     * Save a cuisine.
     *
     * @param cuisineDTO the entity to save.
     * @return the persisted entity.
     */
    public CuisineDTO save(CuisineDTO cuisineDTO) {
        log.debug("Request to save Cuisine : {}", cuisineDTO);
        Cuisine cuisine = cuisineMapper.toEntity(cuisineDTO);
        cuisine = cuisineRepository.save(cuisine);
        return cuisineMapper.toDto(cuisine);
    }

    /**
     * Update a cuisine.
     *
     * @param cuisineDTO the entity to save.
     * @return the persisted entity.
     */
    public CuisineDTO update(CuisineDTO cuisineDTO) {
        log.debug("Request to update Cuisine : {}", cuisineDTO);
        Cuisine cuisine = cuisineMapper.toEntity(cuisineDTO);
        cuisine = cuisineRepository.save(cuisine);
        return cuisineMapper.toDto(cuisine);
    }

    /**
     * Partially update a cuisine.
     *
     * @param cuisineDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CuisineDTO> partialUpdate(CuisineDTO cuisineDTO) {
        log.debug("Request to partially update Cuisine : {}", cuisineDTO);

        return cuisineRepository
            .findById(cuisineDTO.getId())
            .map(existingCuisine -> {
                cuisineMapper.partialUpdate(existingCuisine, cuisineDTO);

                return existingCuisine;
            })
            .map(cuisineRepository::save)
            .map(cuisineMapper::toDto);
    }

    /**
     * Get all the cuisines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CuisineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cuisines");
        return cuisineRepository.findAll(pageable).map(cuisineMapper::toDto);
    }

    /**
     * Get one cuisine by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CuisineDTO> findOne(Long id) {
        log.debug("Request to get Cuisine : {}", id);
        return cuisineRepository.findById(id).map(cuisineMapper::toDto);
    }

    /**
     * Delete the cuisine by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Cuisine : {}", id);
        cuisineRepository.deleteById(id);
    }
}
