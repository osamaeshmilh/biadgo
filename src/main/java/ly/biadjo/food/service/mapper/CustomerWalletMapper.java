package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Customer;
import ly.biadjo.food.domain.CustomerWallet;
import ly.biadjo.food.service.dto.CustomerDTO;
import ly.biadjo.food.service.dto.CustomerWalletDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomerWallet} and its DTO {@link CustomerWalletDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomerWalletMapper extends EntityMapper<CustomerWalletDTO, CustomerWallet> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerName")
    CustomerWalletDTO toDto(CustomerWallet s);

    @Named("customerName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CustomerDTO toDtoCustomerName(Customer customer);
}
