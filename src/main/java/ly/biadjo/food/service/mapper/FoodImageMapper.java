package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Food;
import ly.biadjo.food.domain.FoodImage;
import ly.biadjo.food.service.dto.FoodDTO;
import ly.biadjo.food.service.dto.FoodImageDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FoodImage} and its DTO {@link FoodImageDTO}.
 */
@Mapper(componentModel = "spring")
public interface FoodImageMapper extends EntityMapper<FoodImageDTO, FoodImage> {
    @Mapping(target = "food", source = "food", qualifiedByName = "foodName")
    FoodImageDTO toDto(FoodImage s);

    @Named("foodName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    FoodDTO toDtoFoodName(Food food);
}
