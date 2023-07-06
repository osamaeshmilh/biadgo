package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.Zone;
import ly.biadjo.food.repository.ZoneRepository;
import ly.biadjo.food.service.criteria.ZoneCriteria;
import ly.biadjo.food.service.dto.ZoneDTO;
import ly.biadjo.food.service.mapper.ZoneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Zone} entities in the database.
 * The main input is a {@link ZoneCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ZoneDTO} or a {@link Page} of {@link ZoneDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ZoneQueryService extends QueryService<Zone> {

    private final Logger log = LoggerFactory.getLogger(ZoneQueryService.class);

    private final ZoneRepository zoneRepository;

    private final ZoneMapper zoneMapper;

    public ZoneQueryService(ZoneRepository zoneRepository, ZoneMapper zoneMapper) {
        this.zoneRepository = zoneRepository;
        this.zoneMapper = zoneMapper;
    }

    /**
     * Return a {@link List} of {@link ZoneDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ZoneDTO> findByCriteria(ZoneCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Zone> specification = createSpecification(criteria);
        return zoneMapper.toDto(zoneRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ZoneDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ZoneDTO> findByCriteria(ZoneCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Zone> specification = createSpecification(criteria);
        return zoneRepository.findAll(specification, page).map(zoneMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ZoneCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Zone> specification = createSpecification(criteria);
        return zoneRepository.count(specification);
    }

    /**
     * Function to convert {@link ZoneCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Zone> createSpecification(ZoneCriteria criteria) {
        Specification<Zone> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Zone_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Zone_.name));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), Zone_.nameAr));
            }
            if (criteria.getNameEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameEn(), Zone_.nameEn));
            }
            if (criteria.getLatitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatitude(), Zone_.latitude));
            }
            if (criteria.getLongitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLongitude(), Zone_.longitude));
            }
            if (criteria.getPlusCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPlusCode(), Zone_.plusCode));
            }
            if (criteria.getRadius() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRadius(), Zone_.radius));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), Zone_.isActive));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), Zone_.notes));
            }
            if (criteria.getCityId() != null) {
                specification =
                    specification.and(buildSpecification(criteria.getCityId(), root -> root.join(Zone_.city, JoinType.LEFT).get(City_.id)));
            }
        }
        return specification;
    }
}
