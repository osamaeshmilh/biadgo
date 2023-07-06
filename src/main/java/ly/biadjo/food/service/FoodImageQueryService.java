package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.FoodImage;
import ly.biadjo.food.repository.FoodImageRepository;
import ly.biadjo.food.service.criteria.FoodImageCriteria;
import ly.biadjo.food.service.dto.FoodImageDTO;
import ly.biadjo.food.service.mapper.FoodImageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link FoodImage} entities in the database.
 * The main input is a {@link FoodImageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FoodImageDTO} or a {@link Page} of {@link FoodImageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FoodImageQueryService extends QueryService<FoodImage> {

    private final Logger log = LoggerFactory.getLogger(FoodImageQueryService.class);

    private final FoodImageRepository foodImageRepository;

    private final FoodImageMapper foodImageMapper;

    public FoodImageQueryService(FoodImageRepository foodImageRepository, FoodImageMapper foodImageMapper) {
        this.foodImageRepository = foodImageRepository;
        this.foodImageMapper = foodImageMapper;
    }

    /**
     * Return a {@link List} of {@link FoodImageDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FoodImageDTO> findByCriteria(FoodImageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FoodImage> specification = createSpecification(criteria);
        return foodImageMapper.toDto(foodImageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FoodImageDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FoodImageDTO> findByCriteria(FoodImageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FoodImage> specification = createSpecification(criteria);
        return foodImageRepository.findAll(specification, page).map(foodImageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FoodImageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FoodImage> specification = createSpecification(criteria);
        return foodImageRepository.count(specification);
    }

    /**
     * Function to convert {@link FoodImageCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<FoodImage> createSpecification(FoodImageCriteria criteria) {
        Specification<FoodImage> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FoodImage_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), FoodImage_.description));
            }
            if (criteria.getDescriptionAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescriptionAr(), FoodImage_.descriptionAr));
            }
            if (criteria.getDescriptionEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescriptionEn(), FoodImage_.descriptionEn));
            }
            if (criteria.getMenuOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMenuOrder(), FoodImage_.menuOrder));
            }
            if (criteria.getImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUrl(), FoodImage_.imageUrl));
            }
            if (criteria.getFoodId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getFoodId(), root -> root.join(FoodImage_.food, JoinType.LEFT).get(Food_.id))
                    );
            }
        }
        return specification;
    }
}
