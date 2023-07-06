package ly.biadjo.food.service;

import jakarta.persistence.criteria.JoinType;

import java.util.List;

import ly.biadjo.food.domain.*; // for static metamodels
import ly.biadjo.food.domain.Activation;
import ly.biadjo.food.repository.ActivationRepository;
import ly.biadjo.food.service.criteria.ActivationCriteria;
import ly.biadjo.food.service.dto.ActivationDTO;
import ly.biadjo.food.service.mapper.ActivationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Activation} entities in the database.
 * The main input is a {@link ActivationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ActivationDTO} or a {@link Page} of {@link ActivationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ActivationQueryService extends QueryService<Activation> {

    private final Logger log = LoggerFactory.getLogger(ActivationQueryService.class);

    private final ActivationRepository activationRepository;

    private final ActivationMapper activationMapper;

    public ActivationQueryService(ActivationRepository activationRepository, ActivationMapper activationMapper) {
        this.activationRepository = activationRepository;
        this.activationMapper = activationMapper;
    }

    /**
     * Return a {@link List} of {@link ActivationDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ActivationDTO> findByCriteria(ActivationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Activation> specification = createSpecification(criteria);
        return activationMapper.toDto(activationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ActivationDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ActivationDTO> findByCriteria(ActivationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Activation> specification = createSpecification(criteria);
        return activationRepository.findAll(specification, page).map(activationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ActivationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Activation> specification = createSpecification(criteria);
        return activationRepository.count(specification);
    }

    /**
     * Function to convert {@link ActivationCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Activation> createSpecification(ActivationCriteria criteria) {
        Specification<Activation> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Activation_.id));
            }
            if (criteria.getMobileNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobileNo(), Activation_.mobileNo));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Activation_.email));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Activation_.code));
            }
            if (criteria.getSentOn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSentOn(), Activation_.sentOn));
            }
            if (criteria.getValidUntil() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValidUntil(), Activation_.validUntil));
            }
            if (criteria.getIsUsed() != null) {
                specification = specification.and(buildSpecification(criteria.getIsUsed(), Activation_.isUsed));
            }
        }
        return specification;
    }
}
