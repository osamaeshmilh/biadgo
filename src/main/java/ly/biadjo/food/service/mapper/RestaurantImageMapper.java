package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Restaurant;
import ly.biadjo.food.domain.RestaurantImage;
import ly.biadjo.food.service.dto.RestaurantDTO;
import ly.biadjo.food.service.dto.RestaurantImageDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RestaurantImage} and its DTO {@link RestaurantImageDTO}.
 */
@Mapper(componentModel = "spring")
public interface RestaurantImageMapper extends EntityMapper<RestaurantImageDTO, RestaurantImage> {
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "restaurantName")
    RestaurantImageDTO toDto(RestaurantImage s);

    @Named("restaurantName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    RestaurantDTO toDtoRestaurantName(Restaurant restaurant);
}
