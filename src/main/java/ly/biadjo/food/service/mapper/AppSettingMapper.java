package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.AppSetting;
import ly.biadjo.food.service.dto.AppSettingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppSetting} and its DTO {@link AppSettingDTO}.
 */
@Mapper(componentModel = "spring")
public interface AppSettingMapper extends EntityMapper<AppSettingDTO, AppSetting> {
}
