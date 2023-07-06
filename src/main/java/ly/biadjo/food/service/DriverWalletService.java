package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.DriverWallet;
import ly.biadjo.food.repository.DriverWalletRepository;
import ly.biadjo.food.service.dto.DriverWalletDTO;
import ly.biadjo.food.service.mapper.DriverWalletMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DriverWallet}.
 */
@Service
@Transactional
public class DriverWalletService {

    private final Logger log = LoggerFactory.getLogger(DriverWalletService.class);

    private final DriverWalletRepository driverWalletRepository;

    private final DriverWalletMapper driverWalletMapper;

    public DriverWalletService(DriverWalletRepository driverWalletRepository, DriverWalletMapper driverWalletMapper) {
        this.driverWalletRepository = driverWalletRepository;
        this.driverWalletMapper = driverWalletMapper;
    }

    /**
     * Save a driverWallet.
     *
     * @param driverWalletDTO the entity to save.
     * @return the persisted entity.
     */
    public DriverWalletDTO save(DriverWalletDTO driverWalletDTO) {
        log.debug("Request to save DriverWallet : {}", driverWalletDTO);
        DriverWallet driverWallet = driverWalletMapper.toEntity(driverWalletDTO);
        driverWallet = driverWalletRepository.save(driverWallet);
        return driverWalletMapper.toDto(driverWallet);
    }

    /**
     * Update a driverWallet.
     *
     * @param driverWalletDTO the entity to save.
     * @return the persisted entity.
     */
    public DriverWalletDTO update(DriverWalletDTO driverWalletDTO) {
        log.debug("Request to update DriverWallet : {}", driverWalletDTO);
        DriverWallet driverWallet = driverWalletMapper.toEntity(driverWalletDTO);
        driverWallet = driverWalletRepository.save(driverWallet);
        return driverWalletMapper.toDto(driverWallet);
    }

    /**
     * Partially update a driverWallet.
     *
     * @param driverWalletDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DriverWalletDTO> partialUpdate(DriverWalletDTO driverWalletDTO) {
        log.debug("Request to partially update DriverWallet : {}", driverWalletDTO);

        return driverWalletRepository
            .findById(driverWalletDTO.getId())
            .map(existingDriverWallet -> {
                driverWalletMapper.partialUpdate(existingDriverWallet, driverWalletDTO);

                return existingDriverWallet;
            })
            .map(driverWalletRepository::save)
            .map(driverWalletMapper::toDto);
    }

    /**
     * Get all the driverWallets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DriverWalletDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DriverWallets");
        return driverWalletRepository.findAll(pageable).map(driverWalletMapper::toDto);
    }

    /**
     * Get all the driverWallets with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DriverWalletDTO> findAllWithEagerRelationships(Pageable pageable) {
        return driverWalletRepository.findAllWithEagerRelationships(pageable).map(driverWalletMapper::toDto);
    }

    /**
     * Get one driverWallet by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DriverWalletDTO> findOne(Long id) {
        log.debug("Request to get DriverWallet : {}", id);
        return driverWalletRepository.findOneWithEagerRelationships(id).map(driverWalletMapper::toDto);
    }

    /**
     * Delete the driverWallet by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DriverWallet : {}", id);
        driverWalletRepository.deleteById(id);
    }
}
