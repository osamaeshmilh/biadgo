package ly.biadjo.food.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import ly.biadjo.food.domain.Category;
import ly.biadjo.food.domain.Cuisine;
import ly.biadjo.food.domain.Restaurant;
import ly.biadjo.food.domain.User;
import ly.biadjo.food.service.dto.CategoryDTO;
import ly.biadjo.food.service.dto.CuisineDTO;
import ly.biadjo.food.service.dto.RestaurantDTO;
import ly.biadjo.food.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Restaurant} and its DTO {@link RestaurantDTO}.
 */
@Mapper(componentModel = "spring")
public interface RestaurantMapper extends EntityMapper<RestaurantDTO, Restaurant> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    @Mapping(target = "cuisine", source = "cuisine", qualifiedByName = "cuisineName")
    @Mapping(target = "categories", source = "categories", qualifiedByName = "categoryIdSet")
    RestaurantDTO toDto(Restaurant s);

    @Mapping(target = "removeCategories", ignore = true)
    Restaurant toEntity(RestaurantDTO restaurantDTO);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);

    @Named("cuisineName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CuisineDTO toDtoCuisineName(Cuisine cuisine);

    @Named("categoryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "nameAr", source = "nameAr")
    @Mapping(target = "imageUrl", source = "imageUrl")
    CategoryDTO toDtoCategoryId(Category category);

    @Named("categoryIdSet")
    default Set<CategoryDTO> toDtoCategoryIdSet(Set<Category> category) {
        return category.stream().map(this::toDtoCategoryId).collect(Collectors.toSet());
    }
}
