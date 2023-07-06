package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Driver;
import ly.biadjo.food.domain.User;
import ly.biadjo.food.domain.Zone;
import ly.biadjo.food.service.dto.DriverDTO;
import ly.biadjo.food.service.dto.UserDTO;
import ly.biadjo.food.service.dto.ZoneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Driver} and its DTO {@link DriverDTO}.
 */
@Mapper(componentModel = "spring")
public interface DriverMapper extends EntityMapper<DriverDTO, Driver> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    @Mapping(target = "zone", source = "zone", qualifiedByName = "zoneName")
    DriverDTO toDto(Driver s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);

    @Named("zoneName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ZoneDTO toDtoZoneName(Zone zone);
}
