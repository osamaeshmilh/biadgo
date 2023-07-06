package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.City;
import ly.biadjo.food.domain.Zone;
import ly.biadjo.food.service.dto.CityDTO;
import ly.biadjo.food.service.dto.ZoneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Zone} and its DTO {@link ZoneDTO}.
 */
@Mapper(componentModel = "spring")
public interface ZoneMapper extends EntityMapper<ZoneDTO, Zone> {
    @Mapping(target = "city", source = "city", qualifiedByName = "cityName")
    ZoneDTO toDto(Zone s);

    @Named("cityName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CityDTO toDtoCityName(City city);
}
