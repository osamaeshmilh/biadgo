package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.FoodOrder;
import ly.biadjo.food.repository.FoodOrderRepository;
import ly.biadjo.food.service.dto.FoodOrderDTO;
import ly.biadjo.food.service.mapper.FoodOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FoodOrder}.
 */
@Service
@Transactional
public class FoodOrderService {

    private final Logger log = LoggerFactory.getLogger(FoodOrderService.class);

    private final FoodOrderRepository foodOrderRepository;

    private final FoodOrderMapper foodOrderMapper;

    public FoodOrderService(FoodOrderRepository foodOrderRepository, FoodOrderMapper foodOrderMapper) {
        this.foodOrderRepository = foodOrderRepository;
        this.foodOrderMapper = foodOrderMapper;
    }

    /**
     * Save a foodOrder.
     *
     * @param foodOrderDTO the entity to save.
     * @return the persisted entity.
     */
    public FoodOrderDTO save(FoodOrderDTO foodOrderDTO) {
        log.debug("Request to save FoodOrder : {}", foodOrderDTO);
        FoodOrder foodOrder = foodOrderMapper.toEntity(foodOrderDTO);
        foodOrder = foodOrderRepository.save(foodOrder);
        return foodOrderMapper.toDto(foodOrder);
    }

    /**
     * Update a foodOrder.
     *
     * @param foodOrderDTO the entity to save.
     * @return the persisted entity.
     */
    public FoodOrderDTO update(FoodOrderDTO foodOrderDTO) {
        log.debug("Request to update FoodOrder : {}", foodOrderDTO);
        FoodOrder foodOrder = foodOrderMapper.toEntity(foodOrderDTO);
        foodOrder = foodOrderRepository.save(foodOrder);
        return foodOrderMapper.toDto(foodOrder);
    }

    /**
     * Partially update a foodOrder.
     *
     * @param foodOrderDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FoodOrderDTO> partialUpdate(FoodOrderDTO foodOrderDTO) {
        log.debug("Request to partially update FoodOrder : {}", foodOrderDTO);

        return foodOrderRepository
            .findById(foodOrderDTO.getId())
            .map(existingFoodOrder -> {
                foodOrderMapper.partialUpdate(existingFoodOrder, foodOrderDTO);

                return existingFoodOrder;
            })
            .map(foodOrderRepository::save)
            .map(foodOrderMapper::toDto);
    }

    /**
     * Get all the foodOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FoodOrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FoodOrders");
        return foodOrderRepository.findAll(pageable).map(foodOrderMapper::toDto);
    }

    /**
     * Get all the foodOrders with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<FoodOrderDTO> findAllWithEagerRelationships(Pageable pageable) {
        return foodOrderRepository.findAllWithEagerRelationships(pageable).map(foodOrderMapper::toDto);
    }

    /**
     * Get one foodOrder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FoodOrderDTO> findOne(Long id) {
        log.debug("Request to get FoodOrder : {}", id);
        return foodOrderRepository.findOneWithEagerRelationships(id).map(foodOrderMapper::toDto);
    }

    /**
     * Delete the foodOrder by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FoodOrder : {}", id);
        foodOrderRepository.deleteById(id);
    }
}
