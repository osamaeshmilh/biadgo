package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Food;
import ly.biadjo.food.domain.FoodOrder;
import ly.biadjo.food.domain.Order;
import ly.biadjo.food.service.dto.FoodDTO;
import ly.biadjo.food.service.dto.FoodOrderDTO;
import ly.biadjo.food.service.dto.OrderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FoodOrder} and its DTO {@link FoodOrderDTO}.
 */
@Mapper(componentModel = "spring")
public interface FoodOrderMapper extends EntityMapper<FoodOrderDTO, FoodOrder> {
    @Mapping(target = "order", source = "order", qualifiedByName = "orderOrderNo")
    @Mapping(target = "food", source = "food", qualifiedByName = "foodName")
    FoodOrderDTO toDto(FoodOrder s);

    @Named("orderOrderNo")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderNo", source = "orderNo")
    OrderDTO toDtoOrderOrderNo(Order order);

    @Named("foodName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    FoodDTO toDtoFoodName(Food food);
}
