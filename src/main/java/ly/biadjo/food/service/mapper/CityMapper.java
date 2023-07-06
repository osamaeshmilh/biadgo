package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.City;
import ly.biadjo.food.service.dto.CityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link City} and its DTO {@link CityDTO}.
 */
@Mapper(componentModel = "spring")
public interface CityMapper extends EntityMapper<CityDTO, City> {
}
