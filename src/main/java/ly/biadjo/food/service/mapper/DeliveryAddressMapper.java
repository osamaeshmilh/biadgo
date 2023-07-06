package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Customer;
import ly.biadjo.food.domain.DeliveryAddress;
import ly.biadjo.food.domain.Zone;
import ly.biadjo.food.service.dto.CustomerDTO;
import ly.biadjo.food.service.dto.DeliveryAddressDTO;
import ly.biadjo.food.service.dto.ZoneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeliveryAddress} and its DTO {@link DeliveryAddressDTO}.
 */
@Mapper(componentModel = "spring")
public interface DeliveryAddressMapper extends EntityMapper<DeliveryAddressDTO, DeliveryAddress> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerName")
    @Mapping(target = "zone", source = "zone", qualifiedByName = "zoneName")
    DeliveryAddressDTO toDto(DeliveryAddress s);

    @Named("customerName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CustomerDTO toDtoCustomerName(Customer customer);

    @Named("zoneName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ZoneDTO toDtoZoneName(Zone zone);
}
