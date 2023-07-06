package ly.biadjo.food.service;

import java.util.Optional;

import ly.biadjo.food.domain.Coupon;
import ly.biadjo.food.repository.CouponRepository;
import ly.biadjo.food.service.dto.CouponDTO;
import ly.biadjo.food.service.mapper.CouponMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Coupon}.
 */
@Service
@Transactional
public class CouponService {

    private final Logger log = LoggerFactory.getLogger(CouponService.class);

    private final CouponRepository couponRepository;

    private final CouponMapper couponMapper;

    public CouponService(CouponRepository couponRepository, CouponMapper couponMapper) {
        this.couponRepository = couponRepository;
        this.couponMapper = couponMapper;
    }

    /**
     * Save a coupon.
     *
     * @param couponDTO the entity to save.
     * @return the persisted entity.
     */
    public CouponDTO save(CouponDTO couponDTO) {
        log.debug("Request to save Coupon : {}", couponDTO);
        Coupon coupon = couponMapper.toEntity(couponDTO);
        coupon = couponRepository.save(coupon);
        return couponMapper.toDto(coupon);
    }

    /**
     * Update a coupon.
     *
     * @param couponDTO the entity to save.
     * @return the persisted entity.
     */
    public CouponDTO update(CouponDTO couponDTO) {
        log.debug("Request to update Coupon : {}", couponDTO);
        Coupon coupon = couponMapper.toEntity(couponDTO);
        coupon = couponRepository.save(coupon);
        return couponMapper.toDto(coupon);
    }

    /**
     * Partially update a coupon.
     *
     * @param couponDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CouponDTO> partialUpdate(CouponDTO couponDTO) {
        log.debug("Request to partially update Coupon : {}", couponDTO);

        return couponRepository
            .findById(couponDTO.getId())
            .map(existingCoupon -> {
                couponMapper.partialUpdate(existingCoupon, couponDTO);

                return existingCoupon;
            })
            .map(couponRepository::save)
            .map(couponMapper::toDto);
    }

    /**
     * Get all the coupons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CouponDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Coupons");
        return couponRepository.findAll(pageable).map(couponMapper::toDto);
    }

    /**
     * Get one coupon by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CouponDTO> findOne(Long id) {
        log.debug("Request to get Coupon : {}", id);
        return couponRepository.findById(id).map(couponMapper::toDto);
    }

    /**
     * Delete the coupon by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Coupon : {}", id);
        couponRepository.deleteById(id);
    }
}
