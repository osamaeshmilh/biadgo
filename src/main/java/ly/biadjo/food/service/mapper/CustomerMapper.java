package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Customer;
import ly.biadjo.food.domain.User;
import ly.biadjo.food.service.dto.CustomerDTO;
import ly.biadjo.food.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    CustomerDTO toDto(Customer s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
