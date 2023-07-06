package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.Slider;
import ly.biadjo.food.repository.SliderRepository;
import ly.biadjo.food.service.criteria.SliderCriteria;
import ly.biadjo.food.service.dto.SliderDTO;
import ly.biadjo.food.service.mapper.SliderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Slider} entities in the database.
 * The main input is a {@link SliderCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SliderDTO} or a {@link Page} of {@link SliderDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SliderQueryService extends QueryService<Slider> {

    private final Logger log = LoggerFactory.getLogger(SliderQueryService.class);

    private final SliderRepository sliderRepository;

    private final SliderMapper sliderMapper;

    public SliderQueryService(SliderRepository sliderRepository, SliderMapper sliderMapper) {
        this.sliderRepository = sliderRepository;
        this.sliderMapper = sliderMapper;
    }

    /**
     * Return a {@link List} of {@link SliderDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SliderDTO> findByCriteria(SliderCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Slider> specification = createSpecification(criteria);
        return sliderMapper.toDto(sliderRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SliderDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SliderDTO> findByCriteria(SliderCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Slider> specification = createSpecification(criteria);
        return sliderRepository.findAll(specification, page).map(sliderMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SliderCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Slider> specification = createSpecification(criteria);
        return sliderRepository.count(specification);
    }

    /**
     * Function to convert {@link SliderCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Slider> createSpecification(SliderCriteria criteria) {
        Specification<Slider> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Slider_.id));
            }
            if (criteria.getDetails() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDetails(), Slider_.details));
            }
            if (criteria.getDetailsAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDetailsAr(), Slider_.detailsAr));
            }
            if (criteria.getDetailsEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDetailsEn(), Slider_.detailsEn));
            }
            if (criteria.getMenuOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMenuOrder(), Slider_.menuOrder));
            }
            if (criteria.getImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUrl(), Slider_.imageUrl));
            }
            if (criteria.getUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrl(), Slider_.url));
            }
            if (criteria.getRestaurantId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRestaurantId(), Slider_.restaurantId));
            }
            if (criteria.getCategoryId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCategoryId(), Slider_.categoryId));
            }
            if (criteria.getFoodId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFoodId(), Slider_.foodId));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), Slider_.notes));
            }
        }
        return specification;
    }
}
