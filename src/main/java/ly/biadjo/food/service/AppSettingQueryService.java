package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.AppSetting;
import ly.biadjo.food.repository.AppSettingRepository;
import ly.biadjo.food.service.criteria.AppSettingCriteria;
import ly.biadjo.food.service.dto.AppSettingDTO;
import ly.biadjo.food.service.mapper.AppSettingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link AppSetting} entities in the database.
 * The main input is a {@link AppSettingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AppSettingDTO} or a {@link Page} of {@link AppSettingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AppSettingQueryService extends QueryService<AppSetting> {

    private final Logger log = LoggerFactory.getLogger(AppSettingQueryService.class);

    private final AppSettingRepository appSettingRepository;

    private final AppSettingMapper appSettingMapper;

    public AppSettingQueryService(AppSettingRepository appSettingRepository, AppSettingMapper appSettingMapper) {
        this.appSettingRepository = appSettingRepository;
        this.appSettingMapper = appSettingMapper;
    }

    /**
     * Return a {@link List} of {@link AppSettingDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AppSettingDTO> findByCriteria(AppSettingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AppSetting> specification = createSpecification(criteria);
        return appSettingMapper.toDto(appSettingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AppSettingDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AppSettingDTO> findByCriteria(AppSettingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AppSetting> specification = createSpecification(criteria);
        return appSettingRepository.findAll(specification, page).map(appSettingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AppSettingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AppSetting> specification = createSpecification(criteria);
        return appSettingRepository.count(specification);
    }

    /**
     * Function to convert {@link AppSettingCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AppSetting> createSpecification(AppSettingCriteria criteria) {
        Specification<AppSetting> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), AppSetting_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), AppSetting_.name));
            }
            if (criteria.getKey() != null) {
                specification = specification.and(buildStringSpecification(criteria.getKey(), AppSetting_.key));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), AppSetting_.type));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValue(), AppSetting_.value));
            }
        }
        return specification;
    }
}
