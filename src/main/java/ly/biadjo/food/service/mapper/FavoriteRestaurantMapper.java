package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Customer;
import ly.biadjo.food.domain.FavoriteRestaurant;
import ly.biadjo.food.domain.Restaurant;
import ly.biadjo.food.service.dto.CustomerDTO;
import ly.biadjo.food.service.dto.FavoriteRestaurantDTO;
import ly.biadjo.food.service.dto.RestaurantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FavoriteRestaurant} and its DTO {@link FavoriteRestaurantDTO}.
 */
@Mapper(componentModel = "spring")
public interface FavoriteRestaurantMapper extends EntityMapper<FavoriteRestaurantDTO, FavoriteRestaurant> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerName")
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "restaurantName")
    FavoriteRestaurantDTO toDto(FavoriteRestaurant s);

    @Named("customerName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CustomerDTO toDtoCustomerName(Customer customer);

    @Named("restaurantName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    RestaurantDTO toDtoRestaurantName(Restaurant restaurant);
}
