package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.Slider;
import ly.biadjo.food.repository.SliderRepository;
import ly.biadjo.food.service.dto.SliderDTO;
import ly.biadjo.food.service.mapper.SliderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Slider}.
 */
@Service
@Transactional
public class SliderService {

    private final Logger log = LoggerFactory.getLogger(SliderService.class);

    private final SliderRepository sliderRepository;

    private final SliderMapper sliderMapper;

    public SliderService(SliderRepository sliderRepository, SliderMapper sliderMapper) {
        this.sliderRepository = sliderRepository;
        this.sliderMapper = sliderMapper;
    }

    /**
     * Save a slider.
     *
     * @param sliderDTO the entity to save.
     * @return the persisted entity.
     */
    public SliderDTO save(SliderDTO sliderDTO) {
        log.debug("Request to save Slider : {}", sliderDTO);
        Slider slider = sliderMapper.toEntity(sliderDTO);
        slider = sliderRepository.save(slider);
        return sliderMapper.toDto(slider);
    }

    /**
     * Update a slider.
     *
     * @param sliderDTO the entity to save.
     * @return the persisted entity.
     */
    public SliderDTO update(SliderDTO sliderDTO) {
        log.debug("Request to update Slider : {}", sliderDTO);
        Slider slider = sliderMapper.toEntity(sliderDTO);
        slider = sliderRepository.save(slider);
        return sliderMapper.toDto(slider);
    }

    /**
     * Partially update a slider.
     *
     * @param sliderDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SliderDTO> partialUpdate(SliderDTO sliderDTO) {
        log.debug("Request to partially update Slider : {}", sliderDTO);

        return sliderRepository
            .findById(sliderDTO.getId())
            .map(existingSlider -> {
                sliderMapper.partialUpdate(existingSlider, sliderDTO);

                return existingSlider;
            })
            .map(sliderRepository::save)
            .map(sliderMapper::toDto);
    }

    /**
     * Get all the sliders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SliderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sliders");
        return sliderRepository.findAll(pageable).map(sliderMapper::toDto);
    }

    /**
     * Get one slider by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SliderDTO> findOne(Long id) {
        log.debug("Request to get Slider : {}", id);
        return sliderRepository.findById(id).map(sliderMapper::toDto);
    }

    /**
     * Delete the slider by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Slider : {}", id);
        sliderRepository.deleteById(id);
    }
}
