package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Restaurant;
import ly.biadjo.food.domain.RestaurantDistancePrice;
import ly.biadjo.food.service.dto.RestaurantDTO;
import ly.biadjo.food.service.dto.RestaurantDistancePriceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RestaurantDistancePrice} and its DTO {@link RestaurantDistancePriceDTO}.
 */
@Mapper(componentModel = "spring")
public interface RestaurantDistancePriceMapper extends EntityMapper<RestaurantDistancePriceDTO, RestaurantDistancePrice> {
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "restaurantName")
    RestaurantDistancePriceDTO toDto(RestaurantDistancePrice s);

    @Named("restaurantName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    RestaurantDTO toDtoRestaurantName(Restaurant restaurant);
}
