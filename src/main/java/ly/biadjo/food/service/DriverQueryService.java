package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.Driver;
import ly.biadjo.food.repository.DriverRepository;
import ly.biadjo.food.service.criteria.DriverCriteria;
import ly.biadjo.food.service.dto.DriverDTO;
import ly.biadjo.food.service.mapper.DriverMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Driver} entities in the database.
 * The main input is a {@link DriverCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DriverDTO} or a {@link Page} of {@link DriverDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DriverQueryService extends QueryService<Driver> {

    private final Logger log = LoggerFactory.getLogger(DriverQueryService.class);

    private final DriverRepository driverRepository;

    private final DriverMapper driverMapper;

    public DriverQueryService(DriverRepository driverRepository, DriverMapper driverMapper) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
    }

    /**
     * Return a {@link List} of {@link DriverDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DriverDTO> findByCriteria(DriverCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Driver> specification = createSpecification(criteria);
        return driverMapper.toDto(driverRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DriverDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DriverDTO> findByCriteria(DriverCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Driver> specification = createSpecification(criteria);
        return driverRepository.findAll(specification, page).map(driverMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DriverCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Driver> specification = createSpecification(criteria);
        return driverRepository.count(specification);
    }

    /**
     * Function to convert {@link DriverCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Driver> createSpecification(DriverCriteria criteria) {
        Specification<Driver> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Driver_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Driver_.name));
            }
            if (criteria.getNameAr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameAr(), Driver_.nameAr));
            }
            if (criteria.getNameEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNameEn(), Driver_.nameEn));
            }
            if (criteria.getMobileNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobileNo(), Driver_.mobileNo));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Driver_.email));
            }
            if (criteria.getImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUrl(), Driver_.imageUrl));
            }
            if (criteria.getDriverType() != null) {
                specification = specification.and(buildSpecification(criteria.getDriverType(), Driver_.driverType));
            }
            if (criteria.getDriverPaymentType() != null) {
                specification = specification.and(buildSpecification(criteria.getDriverPaymentType(), Driver_.driverPaymentType));
            }
            if (criteria.getVehicleType() != null) {
                specification = specification.and(buildSpecification(criteria.getVehicleType(), Driver_.vehicleType));
            }
            if (criteria.getDriverStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getDriverStatus(), Driver_.driverStatus));
            }
            if (criteria.getCommissionAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCommissionAmount(), Driver_.commissionAmount));
            }
            if (criteria.getSalaryAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSalaryAmount(), Driver_.salaryAmount));
            }
            if (criteria.getLatitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatitude(), Driver_.latitude));
            }
            if (criteria.getLongitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLongitude(), Driver_.longitude));
            }
            if (criteria.getPlusCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPlusCode(), Driver_.plusCode));
            }
            if (criteria.getLastLocationDateTime() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getLastLocationDateTime(), Driver_.lastLocationDateTime));
            }
            if (criteria.getIsAvailable() != null) {
                specification = specification.and(buildSpecification(criteria.getIsAvailable(), Driver_.isAvailable));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), Driver_.notes));
            }
            if (criteria.getUserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getUserId(), root -> root.join(Driver_.user, JoinType.LEFT).get(User_.id))
                    );
            }
            if (criteria.getZoneId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getZoneId(), root -> root.join(Driver_.zone, JoinType.LEFT).get(Zone_.id))
                    );
            }
        }
        return specification;
    }
}
