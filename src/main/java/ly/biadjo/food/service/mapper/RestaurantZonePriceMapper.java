package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Restaurant;
import ly.biadjo.food.domain.RestaurantZonePrice;
import ly.biadjo.food.domain.Zone;
import ly.biadjo.food.service.dto.RestaurantDTO;
import ly.biadjo.food.service.dto.RestaurantZonePriceDTO;
import ly.biadjo.food.service.dto.ZoneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RestaurantZonePrice} and its DTO {@link RestaurantZonePriceDTO}.
 */
@Mapper(componentModel = "spring")
public interface RestaurantZonePriceMapper extends EntityMapper<RestaurantZonePriceDTO, RestaurantZonePrice> {
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "restaurantName")
    @Mapping(target = "zone", source = "zone", qualifiedByName = "zoneName")
    RestaurantZonePriceDTO toDto(RestaurantZonePrice s);

    @Named("restaurantName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    RestaurantDTO toDtoRestaurantName(Restaurant restaurant);

    @Named("zoneName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ZoneDTO toDtoZoneName(Zone zone);
}
