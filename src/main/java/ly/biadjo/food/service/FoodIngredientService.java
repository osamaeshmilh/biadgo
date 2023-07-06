package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.FoodIngredient;
import ly.biadjo.food.repository.FoodIngredientRepository;
import ly.biadjo.food.service.dto.FoodIngredientDTO;
import ly.biadjo.food.service.mapper.FoodIngredientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FoodIngredient}.
 */
@Service
@Transactional
public class FoodIngredientService {

    private final Logger log = LoggerFactory.getLogger(FoodIngredientService.class);

    private final FoodIngredientRepository foodIngredientRepository;

    private final FoodIngredientMapper foodIngredientMapper;

    public FoodIngredientService(FoodIngredientRepository foodIngredientRepository, FoodIngredientMapper foodIngredientMapper) {
        this.foodIngredientRepository = foodIngredientRepository;
        this.foodIngredientMapper = foodIngredientMapper;
    }

    /**
     * Save a foodIngredient.
     *
     * @param foodIngredientDTO the entity to save.
     * @return the persisted entity.
     */
    public FoodIngredientDTO save(FoodIngredientDTO foodIngredientDTO) {
        log.debug("Request to save FoodIngredient : {}", foodIngredientDTO);
        FoodIngredient foodIngredient = foodIngredientMapper.toEntity(foodIngredientDTO);
        foodIngredient = foodIngredientRepository.save(foodIngredient);
        return foodIngredientMapper.toDto(foodIngredient);
    }

    /**
     * Update a foodIngredient.
     *
     * @param foodIngredientDTO the entity to save.
     * @return the persisted entity.
     */
    public FoodIngredientDTO update(FoodIngredientDTO foodIngredientDTO) {
        log.debug("Request to update FoodIngredient : {}", foodIngredientDTO);
        FoodIngredient foodIngredient = foodIngredientMapper.toEntity(foodIngredientDTO);
        foodIngredient = foodIngredientRepository.save(foodIngredient);
        return foodIngredientMapper.toDto(foodIngredient);
    }

    /**
     * Partially update a foodIngredient.
     *
     * @param foodIngredientDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FoodIngredientDTO> partialUpdate(FoodIngredientDTO foodIngredientDTO) {
        log.debug("Request to partially update FoodIngredient : {}", foodIngredientDTO);

        return foodIngredientRepository
            .findById(foodIngredientDTO.getId())
            .map(existingFoodIngredient -> {
                foodIngredientMapper.partialUpdate(existingFoodIngredient, foodIngredientDTO);

                return existingFoodIngredient;
            })
            .map(foodIngredientRepository::save)
            .map(foodIngredientMapper::toDto);
    }

    /**
     * Get all the foodIngredients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FoodIngredientDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FoodIngredients");
        return foodIngredientRepository.findAll(pageable).map(foodIngredientMapper::toDto);
    }

    /**
     * Get all the foodIngredients with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<FoodIngredientDTO> findAllWithEagerRelationships(Pageable pageable) {
        return foodIngredientRepository.findAllWithEagerRelationships(pageable).map(foodIngredientMapper::toDto);
    }

    /**
     * Get one foodIngredient by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FoodIngredientDTO> findOne(Long id) {
        log.debug("Request to get FoodIngredient : {}", id);
        return foodIngredientRepository.findOneWithEagerRelationships(id).map(foodIngredientMapper::toDto);
    }

    /**
     * Delete the foodIngredient by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FoodIngredient : {}", id);
        foodIngredientRepository.deleteById(id);
    }
}
