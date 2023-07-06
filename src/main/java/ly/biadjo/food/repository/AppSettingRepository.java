package ly.biadjo.food.repository;

import ly.biadjo.food.domain.AppSetting;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AppSetting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppSettingRepository extends JpaRepository<AppSetting, Long>, JpaSpecificationExecutor<AppSetting> {
}
