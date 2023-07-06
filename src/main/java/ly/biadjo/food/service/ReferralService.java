package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.Referral;
import ly.biadjo.food.repository.ReferralRepository;
import ly.biadjo.food.service.dto.ReferralDTO;
import ly.biadjo.food.service.mapper.ReferralMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Referral}.
 */
@Service
@Transactional
public class ReferralService {

    private final Logger log = LoggerFactory.getLogger(ReferralService.class);

    private final ReferralRepository referralRepository;

    private final ReferralMapper referralMapper;

    public ReferralService(ReferralRepository referralRepository, ReferralMapper referralMapper) {
        this.referralRepository = referralRepository;
        this.referralMapper = referralMapper;
    }

    /**
     * Save a referral.
     *
     * @param referralDTO the entity to save.
     * @return the persisted entity.
     */
    public ReferralDTO save(ReferralDTO referralDTO) {
        log.debug("Request to save Referral : {}", referralDTO);
        Referral referral = referralMapper.toEntity(referralDTO);
        referral = referralRepository.save(referral);
        return referralMapper.toDto(referral);
    }

    /**
     * Update a referral.
     *
     * @param referralDTO the entity to save.
     * @return the persisted entity.
     */
    public ReferralDTO update(ReferralDTO referralDTO) {
        log.debug("Request to update Referral : {}", referralDTO);
        Referral referral = referralMapper.toEntity(referralDTO);
        referral = referralRepository.save(referral);
        return referralMapper.toDto(referral);
    }

    /**
     * Partially update a referral.
     *
     * @param referralDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ReferralDTO> partialUpdate(ReferralDTO referralDTO) {
        log.debug("Request to partially update Referral : {}", referralDTO);

        return referralRepository
            .findById(referralDTO.getId())
            .map(existingReferral -> {
                referralMapper.partialUpdate(existingReferral, referralDTO);

                return existingReferral;
            })
            .map(referralRepository::save)
            .map(referralMapper::toDto);
    }

    /**
     * Get all the referrals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ReferralDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Referrals");
        return referralRepository.findAll(pageable).map(referralMapper::toDto);
    }

    /**
     * Get one referral by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReferralDTO> findOne(Long id) {
        log.debug("Request to get Referral : {}", id);
        return referralRepository.findById(id).map(referralMapper::toDto);
    }

    /**
     * Delete the referral by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Referral : {}", id);
        referralRepository.deleteById(id);
    }
}
