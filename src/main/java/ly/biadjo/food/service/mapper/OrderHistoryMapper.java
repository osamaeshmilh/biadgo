package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Order;
import ly.biadjo.food.domain.OrderHistory;
import ly.biadjo.food.service.dto.OrderDTO;
import ly.biadjo.food.service.dto.OrderHistoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderHistory} and its DTO {@link OrderHistoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderHistoryMapper extends EntityMapper<OrderHistoryDTO, OrderHistory> {
    @Mapping(target = "order", source = "order", qualifiedByName = "orderOrderNo")
    OrderHistoryDTO toDto(OrderHistory s);

    @Named("orderOrderNo")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderNo", source = "orderNo")
    OrderDTO toDtoOrderOrderNo(Order order);
}
