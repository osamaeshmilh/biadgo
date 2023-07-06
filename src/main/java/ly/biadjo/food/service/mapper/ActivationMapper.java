package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Activation;
import ly.biadjo.food.service.dto.ActivationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Activation} and its DTO {@link ActivationDTO}.
 */
@Mapper(componentModel = "spring")
public interface ActivationMapper extends EntityMapper<ActivationDTO, Activation> {
}
