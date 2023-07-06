package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.DriverReview;
import ly.biadjo.food.repository.DriverReviewRepository;
import ly.biadjo.food.service.dto.DriverReviewDTO;
import ly.biadjo.food.service.mapper.DriverReviewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DriverReview}.
 */
@Service
@Transactional
public class DriverReviewService {

    private final Logger log = LoggerFactory.getLogger(DriverReviewService.class);

    private final DriverReviewRepository driverReviewRepository;

    private final DriverReviewMapper driverReviewMapper;

    public DriverReviewService(DriverReviewRepository driverReviewRepository, DriverReviewMapper driverReviewMapper) {
        this.driverReviewRepository = driverReviewRepository;
        this.driverReviewMapper = driverReviewMapper;
    }

    /**
     * Save a driverReview.
     *
     * @param driverReviewDTO the entity to save.
     * @return the persisted entity.
     */
    public DriverReviewDTO save(DriverReviewDTO driverReviewDTO) {
        log.debug("Request to save DriverReview : {}", driverReviewDTO);
        DriverReview driverReview = driverReviewMapper.toEntity(driverReviewDTO);
        driverReview = driverReviewRepository.save(driverReview);
        return driverReviewMapper.toDto(driverReview);
    }

    /**
     * Update a driverReview.
     *
     * @param driverReviewDTO the entity to save.
     * @return the persisted entity.
     */
    public DriverReviewDTO update(DriverReviewDTO driverReviewDTO) {
        log.debug("Request to update DriverReview : {}", driverReviewDTO);
        DriverReview driverReview = driverReviewMapper.toEntity(driverReviewDTO);
        driverReview = driverReviewRepository.save(driverReview);
        return driverReviewMapper.toDto(driverReview);
    }

    /**
     * Partially update a driverReview.
     *
     * @param driverReviewDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DriverReviewDTO> partialUpdate(DriverReviewDTO driverReviewDTO) {
        log.debug("Request to partially update DriverReview : {}", driverReviewDTO);

        return driverReviewRepository
            .findById(driverReviewDTO.getId())
            .map(existingDriverReview -> {
                driverReviewMapper.partialUpdate(existingDriverReview, driverReviewDTO);

                return existingDriverReview;
            })
            .map(driverReviewRepository::save)
            .map(driverReviewMapper::toDto);
    }

    /**
     * Get all the driverReviews.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DriverReviewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DriverReviews");
        return driverReviewRepository.findAll(pageable).map(driverReviewMapper::toDto);
    }

    /**
     * Get all the driverReviews with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DriverReviewDTO> findAllWithEagerRelationships(Pageable pageable) {
        return driverReviewRepository.findAllWithEagerRelationships(pageable).map(driverReviewMapper::toDto);
    }

    /**
     * Get one driverReview by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DriverReviewDTO> findOne(Long id) {
        log.debug("Request to get DriverReview : {}", id);
        return driverReviewRepository.findOneWithEagerRelationships(id).map(driverReviewMapper::toDto);
    }

    /**
     * Delete the driverReview by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DriverReview : {}", id);
        driverReviewRepository.deleteById(id);
    }
}
