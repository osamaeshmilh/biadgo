package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.FoodImage;
import ly.biadjo.food.repository.FoodImageRepository;
import ly.biadjo.food.service.dto.FoodImageDTO;
import ly.biadjo.food.service.mapper.FoodImageMapper;
import ly.biadjo.food.service.utils.FileTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FoodImage}.
 */
@Service
@Transactional
public class FoodImageService {

    private final Logger log = LoggerFactory.getLogger(FoodImageService.class);

    private final FoodImageRepository foodImageRepository;

    private final FoodImageMapper foodImageMapper;

    public FoodImageService(FoodImageRepository foodImageRepository, FoodImageMapper foodImageMapper) {
        this.foodImageRepository = foodImageRepository;
        this.foodImageMapper = foodImageMapper;
    }

    /**
     * Save a foodImage.
     *
     * @param foodImageDTO the entity to save.
     * @return the persisted entity.
     */
    public FoodImageDTO save(FoodImageDTO foodImageDTO) {
        log.debug("Request to save FoodImage : {}", foodImageDTO);
        FoodImage foodImage = foodImageMapper.toEntity(foodImageDTO);

        if (foodImageDTO.getImage() != null) {
            String filePath = FileTools.upload(foodImage.getImage(), foodImage.getImageContentType(), "FoodImage");
            foodImage.setImage(null);
            foodImage.setImageContentType(foodImageDTO.getImageContentType());
            foodImage.setImageUrl(filePath);
        }

        foodImage = foodImageRepository.save(foodImage);
        return foodImageMapper.toDto(foodImage);
    }

    /**
     * Update a foodImage.
     *
     * @param foodImageDTO the entity to save.
     * @return the persisted entity.
     */
    public FoodImageDTO update(FoodImageDTO foodImageDTO) {
        log.debug("Request to update FoodImage : {}", foodImageDTO);
        FoodImage foodImage = foodImageMapper.toEntity(foodImageDTO);
        foodImage = foodImageRepository.save(foodImage);
        return foodImageMapper.toDto(foodImage);
    }

    /**
     * Partially update a foodImage.
     *
     * @param foodImageDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FoodImageDTO> partialUpdate(FoodImageDTO foodImageDTO) {
        log.debug("Request to partially update FoodImage : {}", foodImageDTO);

        return foodImageRepository
            .findById(foodImageDTO.getId())
            .map(existingFoodImage -> {
                foodImageMapper.partialUpdate(existingFoodImage, foodImageDTO);

                return existingFoodImage;
            })
            .map(foodImageRepository::save)
            .map(foodImageMapper::toDto);
    }

    /**
     * Get all the foodImages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FoodImageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FoodImages");
        return foodImageRepository.findAll(pageable).map(foodImageMapper::toDto);
    }

    /**
     * Get all the foodImages with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<FoodImageDTO> findAllWithEagerRelationships(Pageable pageable) {
        return foodImageRepository.findAllWithEagerRelationships(pageable).map(foodImageMapper::toDto);
    }

    /**
     * Get one foodImage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FoodImageDTO> findOne(Long id) {
        log.debug("Request to get FoodImage : {}", id);
        return foodImageRepository.findOneWithEagerRelationships(id).map(foodImageMapper::toDto);
    }

    /**
     * Delete the foodImage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FoodImage : {}", id);
        foodImageRepository.deleteById(id);
    }
}
