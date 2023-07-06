package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.FoodExtra;
import ly.biadjo.food.repository.FoodExtraRepository;
import ly.biadjo.food.service.criteria.FoodExtraCriteria;
import ly.biadjo.food.service.dto.FoodExtraDTO;
import ly.biadjo.food.service.mapper.FoodExtraMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link FoodExtra} entities in the database.
 * The main input is a {@link FoodExtraCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FoodExtraDTO} or a {@link Page} of {@link FoodExtraDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FoodExtraQueryService extends QueryService<FoodExtra> {

    private final Logger log = LoggerFactory.getLogger(FoodExtraQueryService.class);

    private final FoodExtraRepository foodExtraRepository;

    private final FoodExtraMapper foodExtraMapper;

    public FoodExtraQueryService(FoodExtraRepository foodExtraRepository, FoodExtraMapper foodExtraMapper) {
        this.foodExtraRepository = foodExtraRepository;
        this.foodExtraMapper = foodExtraMapper;
    }

    /**
     * Return a {@link List} of {@link FoodExtraDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FoodExtraDTO> findByCriteria(FoodExtraCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FoodExtra> specification = createSpecification(criteria);
        return foodExtraMapper.toDto(foodExtraRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FoodExtraDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FoodExtraDTO> findByCriteria(FoodExtraCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FoodExtra> specification = createSpecification(criteria);
        return foodExtraRepository.findAll(specification, page).map(foodExtraMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FoodExtraCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FoodExtra> specification = createSpecification(criteria);
        return foodExtraRepository.count(specification);
    }

    /**
     * Function to convert {@link FoodExtraCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<FoodExtra> createSpecification(FoodExtraCriteria criteria) {
        Specification<FoodExtra> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FoodExtra_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), FoodExtra_.name));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), FoodExtra_.nameAr));
            }
            if (criteria.getNameEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameEn(), FoodExtra_.nameEn));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), FoodExtra_.price));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), FoodExtra_.notes));
            }
            if (criteria.getFoodId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getFoodId(), root -> root.join(FoodExtra_.food, JoinType.LEFT).get(Food_.id))
                    );
            }
        }
        return specification;
    }
}
