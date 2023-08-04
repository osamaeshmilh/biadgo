package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Category;
import ly.biadjo.food.domain.Food;
import ly.biadjo.food.domain.Restaurant;
import ly.biadjo.food.service.dto.CategoryDTO;
import ly.biadjo.food.service.dto.FoodDTO;
import ly.biadjo.food.service.dto.RestaurantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Food} and its DTO {@link FoodDTO}.
 */
@Mapper(componentModel = "spring")
public interface FoodMapper extends EntityMapper<FoodDTO, Food> {
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "restaurantName")
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryName")
    FoodDTO toDto(Food s);

    @Named("restaurantName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "nameAr", source = "nameAr")
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "restaurantImages", source = "restaurantImages")
    RestaurantDTO toDtoRestaurantName(Restaurant restaurant);

    @Named("categoryName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CategoryDTO toDtoCategoryName(Category category);
}
