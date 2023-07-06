package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Customer;
import ly.biadjo.food.domain.Restaurant;
import ly.biadjo.food.domain.RestaurantReview;
import ly.biadjo.food.service.dto.CustomerDTO;
import ly.biadjo.food.service.dto.RestaurantDTO;
import ly.biadjo.food.service.dto.RestaurantReviewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RestaurantReview} and its DTO {@link RestaurantReviewDTO}.
 */
@Mapper(componentModel = "spring")
public interface RestaurantReviewMapper extends EntityMapper<RestaurantReviewDTO, RestaurantReview> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerName")
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "restaurantName")
    RestaurantReviewDTO toDto(RestaurantReview s);

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
