package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.DeliveryAddress;
import ly.biadjo.food.repository.DeliveryAddressRepository;
import ly.biadjo.food.service.criteria.DeliveryAddressCriteria;
import ly.biadjo.food.service.dto.DeliveryAddressDTO;
import ly.biadjo.food.service.mapper.DeliveryAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DeliveryAddress} entities in the database.
 * The main input is a {@link DeliveryAddressCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DeliveryAddressDTO} or a {@link Page} of {@link DeliveryAddressDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DeliveryAddressQueryService extends QueryService<DeliveryAddress> {

    private final Logger log = LoggerFactory.getLogger(DeliveryAddressQueryService.class);

    private final DeliveryAddressRepository deliveryAddressRepository;

    private final DeliveryAddressMapper deliveryAddressMapper;

    public DeliveryAddressQueryService(DeliveryAddressRepository deliveryAddressRepository, DeliveryAddressMapper deliveryAddressMapper) {
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.deliveryAddressMapper = deliveryAddressMapper;
    }

    /**
     * Return a {@link List} of {@link DeliveryAddressDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DeliveryAddressDTO> findByCriteria(DeliveryAddressCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DeliveryAddress> specification = createSpecification(criteria);
        return deliveryAddressMapper.toDto(deliveryAddressRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DeliveryAddressDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DeliveryAddressDTO> findByCriteria(DeliveryAddressCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DeliveryAddress> specification = createSpecification(criteria);
        return deliveryAddressRepository.findAll(specification, page).map(deliveryAddressMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DeliveryAddressCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DeliveryAddress> specification = createSpecification(criteria);
        return deliveryAddressRepository.count(specification);
    }

    /**
     * Function to convert {@link DeliveryAddressCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DeliveryAddress> createSpecification(DeliveryAddressCriteria criteria) {
        Specification<DeliveryAddress> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DeliveryAddress_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), DeliveryAddress_.title));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), DeliveryAddress_.address));
            }
            if (criteria.getDetails() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDetails(), DeliveryAddress_.details));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), DeliveryAddress_.phone));
            }
            if (criteria.getIsDefault() != null) {
                specification = specification.and(buildSpecification(criteria.getIsDefault(), DeliveryAddress_.isDefault));
            }
            if (criteria.getLatitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLatitude(), DeliveryAddress_.latitude));
            }
            if (criteria.getLongitude() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLongitude(), DeliveryAddress_.longitude));
            }
            if (criteria.getPlusCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPlusCode(), DeliveryAddress_.plusCode));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), DeliveryAddress_.isActive));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), DeliveryAddress_.notes));
            }
            if (criteria.getCustomerId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getCustomerId(),
                            root -> root.join(DeliveryAddress_.customer, JoinType.LEFT).get(Customer_.id)
                        )
                    );
            }
            if (criteria.getZoneId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getZoneId(), root -> root.join(DeliveryAddress_.zone, JoinType.LEFT).get(Zone_.id))
                    );
            }
        }
        return specification;
    }
}
