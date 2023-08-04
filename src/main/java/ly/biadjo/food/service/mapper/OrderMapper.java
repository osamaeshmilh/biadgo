package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Coupon;
import ly.biadjo.food.domain.Customer;
import ly.biadjo.food.domain.DeliveryAddress;
import ly.biadjo.food.domain.Driver;
import ly.biadjo.food.domain.Order;
import ly.biadjo.food.domain.Restaurant;
import ly.biadjo.food.service.dto.CouponDTO;
import ly.biadjo.food.service.dto.CustomerDTO;
import ly.biadjo.food.service.dto.DeliveryAddressDTO;
import ly.biadjo.food.service.dto.DriverDTO;
import ly.biadjo.food.service.dto.OrderDTO;
import ly.biadjo.food.service.dto.RestaurantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerName")
    @Mapping(target = "coupon", source = "coupon", qualifiedByName = "couponCode")
    @Mapping(target = "driver", source = "driver", qualifiedByName = "driverName")
    @Mapping(target = "deliveryAddress", source = "deliveryAddress", qualifiedByName = "deliveryAddressAddress")
    @Mapping(target = "restaurant", source = "restaurant", qualifiedByName = "restaurantName")
    OrderDTO toDto(Order s);

    @Named("customerName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CustomerDTO toDtoCustomerName(Customer customer);

    @Named("couponCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    CouponDTO toDtoCouponCode(Coupon coupon);

    @Named("driverName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DriverDTO toDtoDriverName(Driver driver);

    @Named("deliveryAddressAddress")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "address", source = "address")
    DeliveryAddressDTO toDtoDeliveryAddressAddress(DeliveryAddress deliveryAddress);

    @Named("restaurantName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "nameAr", source = "nameAr")
    @Mapping(target = "nameEn", source = "nameEn")
    @Mapping(target = "restaurantImages", source = "restaurantImages")
    RestaurantDTO toDtoRestaurantName(Restaurant restaurant);
}
