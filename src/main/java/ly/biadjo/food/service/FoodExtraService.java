package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.FoodExtra;
import ly.biadjo.food.repository.FoodExtraRepository;
import ly.biadjo.food.service.dto.FoodExtraDTO;
import ly.biadjo.food.service.mapper.FoodExtraMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FoodExtra}.
 */
@Service
@Transactional
public class FoodExtraService {

    private final Logger log = LoggerFactory.getLogger(FoodExtraService.class);

    private final FoodExtraRepository foodExtraRepository;

    private final FoodExtraMapper foodExtraMapper;

    public FoodExtraService(FoodExtraRepository foodExtraRepository, FoodExtraMapper foodExtraMapper) {
        this.foodExtraRepository = foodExtraRepository;
        this.foodExtraMapper = foodExtraMapper;
    }

    /**
     * Save a foodExtra.
     *
     * @param foodExtraDTO the entity to save.
     * @return the persisted entity.
     */
    public FoodExtraDTO save(FoodExtraDTO foodExtraDTO) {
        log.debug("Request to save FoodExtra : {}", foodExtraDTO);
        FoodExtra foodExtra = foodExtraMapper.toEntity(foodExtraDTO);
        foodExtra = foodExtraRepository.save(foodExtra);
        return foodExtraMapper.toDto(foodExtra);
    }

    /**
     * Update a foodExtra.
     *
     * @param foodExtraDTO the entity to save.
     * @return the persisted entity.
     */
    public FoodExtraDTO update(FoodExtraDTO foodExtraDTO) {
        log.debug("Request to update FoodExtra : {}", foodExtraDTO);
        FoodExtra foodExtra = foodExtraMapper.toEntity(foodExtraDTO);
        foodExtra = foodExtraRepository.save(foodExtra);
        return foodExtraMapper.toDto(foodExtra);
    }

    /**
     * Partially update a foodExtra.
     *
     * @param foodExtraDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FoodExtraDTO> partialUpdate(FoodExtraDTO foodExtraDTO) {
        log.debug("Request to partially update FoodExtra : {}", foodExtraDTO);

        return foodExtraRepository
            .findById(foodExtraDTO.getId())
            .map(existingFoodExtra -> {
                foodExtraMapper.partialUpdate(existingFoodExtra, foodExtraDTO);

                return existingFoodExtra;
            })
            .map(foodExtraRepository::save)
            .map(foodExtraMapper::toDto);
    }

    /**
     * Get all the foodExtras.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FoodExtraDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FoodExtras");
        return foodExtraRepository.findAll(pageable).map(foodExtraMapper::toDto);
    }

    /**
     * Get all the foodExtras with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<FoodExtraDTO> findAllWithEagerRelationships(Pageable pageable) {
        return foodExtraRepository.findAllWithEagerRelationships(pageable).map(foodExtraMapper::toDto);
    }

    /**
     * Get one foodExtra by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FoodExtraDTO> findOne(Long id) {
        log.debug("Request to get FoodExtra : {}", id);
        return foodExtraRepository.findOneWithEagerRelationships(id).map(foodExtraMapper::toDto);
    }

    /**
     * Delete the foodExtra by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FoodExtra : {}", id);
        foodExtraRepository.deleteById(id);
    }
}
