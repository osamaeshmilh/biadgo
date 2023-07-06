package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.FoodOrder;
import ly.biadjo.food.domain.Order;
import ly.biadjo.food.service.dto.FoodOrderDTO;
import ly.biadjo.food.service.dto.OrderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FoodOrder} and its DTO {@link FoodOrderDTO}.
 */
@Mapper(componentModel = "spring")
public interface FoodOrderMapper extends EntityMapper<FoodOrderDTO, FoodOrder> {
    @Mapping(target = "order", source = "order", qualifiedByName = "orderOrderNo")
    FoodOrderDTO toDto(FoodOrder s);

    @Named("orderOrderNo")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderNo", source = "orderNo")
    OrderDTO toDtoOrderOrderNo(Order order);
}
