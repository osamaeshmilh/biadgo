package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Customer;
import ly.biadjo.food.domain.Driver;
import ly.biadjo.food.domain.DriverReview;
import ly.biadjo.food.service.dto.CustomerDTO;
import ly.biadjo.food.service.dto.DriverDTO;
import ly.biadjo.food.service.dto.DriverReviewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DriverReview} and its DTO {@link DriverReviewDTO}.
 */
@Mapper(componentModel = "spring")
public interface DriverReviewMapper extends EntityMapper<DriverReviewDTO, DriverReview> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerName")
    @Mapping(target = "driver", source = "driver", qualifiedByName = "driverName")
    DriverReviewDTO toDto(DriverReview s);

    @Named("customerName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CustomerDTO toDtoCustomerName(Customer customer);

    @Named("driverName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DriverDTO toDtoDriverName(Driver driver);
}
