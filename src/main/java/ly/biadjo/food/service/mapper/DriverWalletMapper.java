package ly.biadjo.food.service.mapper;

import ly.biadjo.food.domain.Driver;
import ly.biadjo.food.domain.DriverWallet;
import ly.biadjo.food.service.dto.DriverDTO;
import ly.biadjo.food.service.dto.DriverWalletDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DriverWallet} and its DTO {@link DriverWalletDTO}.
 */
@Mapper(componentModel = "spring")
public interface DriverWalletMapper extends EntityMapper<DriverWalletDTO, DriverWallet> {
    @Mapping(target = "driver", source = "driver", qualifiedByName = "driverName")
    DriverWalletDTO toDto(DriverWallet s);

    @Named("driverName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DriverDTO toDtoDriverName(Driver driver);
}
