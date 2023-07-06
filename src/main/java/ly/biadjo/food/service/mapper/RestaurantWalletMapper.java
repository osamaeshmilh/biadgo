package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Restaurant;
import ly.biadjo.food.domain.RestaurantWallet;
import ly.biadjo.food.service.dto.RestaurantDTO;
import ly.biadjo.food.service.dto.RestaurantWalletDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RestaurantWallet} and its DTO {@link RestaurantWalletDTO}.
 */
@Mapper(componentModel = "spring")
public interface RestaurantWalletMapper extends EntityMapper<RestaurantWalletDTO, RestaurantWallet> {
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "restaurantName")
    RestaurantWalletDTO toDto(RestaurantWallet s);

    @Named("restaurantName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    RestaurantDTO toDtoRestaurantName(Restaurant restaurant);
}
