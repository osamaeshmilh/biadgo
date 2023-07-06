package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.RestaurantWallet;
import ly.biadjo.food.repository.RestaurantWalletRepository;
import ly.biadjo.food.service.dto.RestaurantWalletDTO;
import ly.biadjo.food.service.mapper.RestaurantWalletMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RestaurantWallet}.
 */
@Service
@Transactional
public class RestaurantWalletService {

    private final Logger log = LoggerFactory.getLogger(RestaurantWalletService.class);

    private final RestaurantWalletRepository restaurantWalletRepository;

    private final RestaurantWalletMapper restaurantWalletMapper;

    public RestaurantWalletService(RestaurantWalletRepository restaurantWalletRepository, RestaurantWalletMapper restaurantWalletMapper) {
        this.restaurantWalletRepository = restaurantWalletRepository;
        this.restaurantWalletMapper = restaurantWalletMapper;
    }

    /**
     * Save a restaurantWallet.
     *
     * @param restaurantWalletDTO the entity to save.
     * @return the persisted entity.
     */
    public RestaurantWalletDTO save(RestaurantWalletDTO restaurantWalletDTO) {
        log.debug("Request to save RestaurantWallet : {}", restaurantWalletDTO);
        RestaurantWallet restaurantWallet = restaurantWalletMapper.toEntity(restaurantWalletDTO);
        restaurantWallet = restaurantWalletRepository.save(restaurantWallet);
        return restaurantWalletMapper.toDto(restaurantWallet);
    }

    /**
     * Update a restaurantWallet.
     *
     * @param restaurantWalletDTO the entity to save.
     * @return the persisted entity.
     */
    public RestaurantWalletDTO update(RestaurantWalletDTO restaurantWalletDTO) {
        log.debug("Request to update RestaurantWallet : {}", restaurantWalletDTO);
        RestaurantWallet restaurantWallet = restaurantWalletMapper.toEntity(restaurantWalletDTO);
        restaurantWallet = restaurantWalletRepository.save(restaurantWallet);
        return restaurantWalletMapper.toDto(restaurantWallet);
    }

    /**
     * Partially update a restaurantWallet.
     *
     * @param restaurantWalletDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RestaurantWalletDTO> partialUpdate(RestaurantWalletDTO restaurantWalletDTO) {
        log.debug("Request to partially update RestaurantWallet : {}", restaurantWalletDTO);

        return restaurantWalletRepository
            .findById(restaurantWalletDTO.getId())
            .map(existingRestaurantWallet -> {
                restaurantWalletMapper.partialUpdate(existingRestaurantWallet, restaurantWalletDTO);

                return existingRestaurantWallet;
            })
            .map(restaurantWalletRepository::save)
            .map(restaurantWalletMapper::toDto);
    }

    /**
     * Get all the restaurantWallets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RestaurantWalletDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RestaurantWallets");
        return restaurantWalletRepository.findAll(pageable).map(restaurantWalletMapper::toDto);
    }

    /**
     * Get all the restaurantWallets with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<RestaurantWalletDTO> findAllWithEagerRelationships(Pageable pageable) {
        return restaurantWalletRepository.findAllWithEagerRelationships(pageable).map(restaurantWalletMapper::toDto);
    }

    /**
     * Get one restaurantWallet by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RestaurantWalletDTO> findOne(Long id) {
        log.debug("Request to get RestaurantWallet : {}", id);
        return restaurantWalletRepository.findOneWithEagerRelationships(id).map(restaurantWalletMapper::toDto);
    }

    /**
     * Delete the restaurantWallet by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RestaurantWallet : {}", id);
        restaurantWalletRepository.deleteById(id);
    }
}
