package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Food;
import ly.biadjo.food.domain.FoodIngredient;
import ly.biadjo.food.service.dto.FoodDTO;
import ly.biadjo.food.service.dto.FoodIngredientDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FoodIngredient} and its DTO {@link FoodIngredientDTO}.
 */
@Mapper(componentModel = "spring")
public interface FoodIngredientMapper extends EntityMapper<FoodIngredientDTO, FoodIngredient> {
    @Mapping(target = "food", source = "food", qualifiedByName = "foodName")
    FoodIngredientDTO toDto(FoodIngredient s);

    @Named("foodName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    FoodDTO toDtoFoodName(Food food);
}
