package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Restaurant;
import ly.biadjo.food.domain.RestaurantSchedule;
import ly.biadjo.food.service.dto.RestaurantDTO;
import ly.biadjo.food.service.dto.RestaurantScheduleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RestaurantSchedule} and its DTO {@link RestaurantScheduleDTO}.
 */
@Mapper(componentModel = "spring")
public interface RestaurantScheduleMapper extends EntityMapper<RestaurantScheduleDTO, RestaurantSchedule> {
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "restaurantName")
    RestaurantScheduleDTO toDto(RestaurantSchedule s);

    @Named("restaurantName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    RestaurantDTO toDtoRestaurantName(Restaurant restaurant);
}
