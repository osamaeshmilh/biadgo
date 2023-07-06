package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Coupon;
import ly.biadjo.food.service.dto.CouponDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Coupon} and its DTO {@link CouponDTO}.
 */
@Mapper(componentModel = "spring")
public interface CouponMapper extends EntityMapper<CouponDTO, Coupon> {
}
