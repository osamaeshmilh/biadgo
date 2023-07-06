package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Cart;
import ly.biadjo.food.domain.Customer;
import ly.biadjo.food.domain.Food;
import ly.biadjo.food.service.dto.CartDTO;
import ly.biadjo.food.service.dto.CustomerDTO;
import ly.biadjo.food.service.dto.FoodDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cart} and its DTO {@link CartDTO}.
 */
@Mapper(componentModel = "spring")
public interface CartMapper extends EntityMapper<CartDTO, Cart> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerName")
    @Mapping(target = "food", source = "food", qualifiedByName = "foodName")
    CartDTO toDto(Cart s);

    @Named("customerName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CustomerDTO toDtoCustomerName(Customer customer);

    @Named("foodName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    FoodDTO toDtoFoodName(Food food);
}
