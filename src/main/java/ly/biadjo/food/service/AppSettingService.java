package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.AppSetting;
import ly.biadjo.food.repository.AppSettingRepository;
import ly.biadjo.food.service.dto.AppSettingDTO;
import ly.biadjo.food.service.mapper.AppSettingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AppSetting}.
 */
@Service
@Transactional
public class AppSettingService {

    private final Logger log = LoggerFactory.getLogger(AppSettingService.class);

    private final AppSettingRepository appSettingRepository;

    private final AppSettingMapper appSettingMapper;

    public AppSettingService(AppSettingRepository appSettingRepository, AppSettingMapper appSettingMapper) {
        this.appSettingRepository = appSettingRepository;
        this.appSettingMapper = appSettingMapper;
    }

    /**
     * Save a appSetting.
     *
     * @param appSettingDTO the entity to save.
     * @return the persisted entity.
     */
    public AppSettingDTO save(AppSettingDTO appSettingDTO) {
        log.debug("Request to save AppSetting : {}", appSettingDTO);
        AppSetting appSetting = appSettingMapper.toEntity(appSettingDTO);
        appSetting = appSettingRepository.save(appSetting);
        return appSettingMapper.toDto(appSetting);
    }

    /**
     * Update a appSetting.
     *
     * @param appSettingDTO the entity to save.
     * @return the persisted entity.
     */
    public AppSettingDTO update(AppSettingDTO appSettingDTO) {
        log.debug("Request to update AppSetting : {}", appSettingDTO);
        AppSetting appSetting = appSettingMapper.toEntity(appSettingDTO);
        appSetting = appSettingRepository.save(appSetting);
        return appSettingMapper.toDto(appSetting);
    }

    /**
     * Partially update a appSetting.
     *
     * @param appSettingDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AppSettingDTO> partialUpdate(AppSettingDTO appSettingDTO) {
        log.debug("Request to partially update AppSetting : {}", appSettingDTO);

        return appSettingRepository
            .findById(appSettingDTO.getId())
            .map(existingAppSetting -> {
                appSettingMapper.partialUpdate(existingAppSetting, appSettingDTO);

                return existingAppSetting;
            })
            .map(appSettingRepository::save)
            .map(appSettingMapper::toDto);
    }

    /**
     * Get all the appSettings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AppSettingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AppSettings");
        return appSettingRepository.findAll(pageable).map(appSettingMapper::toDto);
    }

    /**
     * Get one appSetting by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AppSettingDTO> findOne(Long id) {
        log.debug("Request to get AppSetting : {}", id);
        return appSettingRepository.findById(id).map(appSettingMapper::toDto);
    }

    /**
     * Delete the appSetting by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AppSetting : {}", id);
        appSettingRepository.deleteById(id);
    }
}
