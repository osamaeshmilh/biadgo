package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Driver;
import ly.biadjo.food.domain.DriverLocation;
import ly.biadjo.food.service.dto.DriverDTO;
import ly.biadjo.food.service.dto.DriverLocationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DriverLocation} and its DTO {@link DriverLocationDTO}.
 */
@Mapper(componentModel = "spring")
public interface DriverLocationMapper extends EntityMapper<DriverLocationDTO, DriverLocation> {
    @Mapping(target = "driver", source = "driver", qualifiedByName = "driverName")
    DriverLocationDTO toDto(DriverLocation s);

    @Named("driverName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DriverDTO toDtoDriverName(Driver driver);
}
