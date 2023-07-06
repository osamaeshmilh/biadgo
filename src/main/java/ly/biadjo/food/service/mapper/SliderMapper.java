package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Slider;
import ly.biadjo.food.service.dto.SliderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Slider} and its DTO {@link SliderDTO}.
 */
@Mapper(componentModel = "spring")
public interface SliderMapper extends EntityMapper<SliderDTO, Slider> {
}
