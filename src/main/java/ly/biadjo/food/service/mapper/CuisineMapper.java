package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Cuisine;
import ly.biadjo.food.service.dto.CuisineDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cuisine} and its DTO {@link CuisineDTO}.
 */
@Mapper(componentModel = "spring")
public interface CuisineMapper extends EntityMapper<CuisineDTO, Cuisine> {
}
