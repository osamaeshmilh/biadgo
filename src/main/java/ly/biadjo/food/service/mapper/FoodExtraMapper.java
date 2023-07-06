package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Food;
import ly.biadjo.food.domain.FoodExtra;
import ly.biadjo.food.service.dto.FoodDTO;
import ly.biadjo.food.service.dto.FoodExtraDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FoodExtra} and its DTO {@link FoodExtraDTO}.
 */
@Mapper(componentModel = "spring")
public interface FoodExtraMapper extends EntityMapper<FoodExtraDTO, FoodExtra> {
    @Mapping(target = "food", source = "food", qualifiedByName = "foodName")
    FoodExtraDTO toDto(FoodExtra s);

    @Named("foodName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    FoodDTO toDtoFoodName(Food food);
}
