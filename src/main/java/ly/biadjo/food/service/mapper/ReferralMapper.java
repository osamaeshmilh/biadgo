package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Customer;
import ly.biadjo.food.domain.Referral;
import ly.biadjo.food.service.dto.CustomerDTO;
import ly.biadjo.food.service.dto.ReferralDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Referral} and its DTO {@link ReferralDTO}.
 */
@Mapper(componentModel = "spring")
public interface ReferralMapper extends EntityMapper<ReferralDTO, Referral> {
    @Mapping(target = "referredCustomer", source = "referredCustomer", qualifiedByName = "customerId")
    @Mapping(target = "referrerCustomer", source = "referrerCustomer", qualifiedByName = "customerId")
    ReferralDTO toDto(Referral s);

    @Named("customerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomerDTO toDtoCustomerId(Customer customer);
}
