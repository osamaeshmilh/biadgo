package ly.biadjo.food.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, ly.biadjo.food.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, ly.biadjo.food.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, ly.biadjo.food.domain.User.class.getName());
            createCache(cm, ly.biadjo.food.domain.Authority.class.getName());
            createCache(cm, ly.biadjo.food.domain.User.class.getName() + ".authorities");
            createCache(cm, ly.biadjo.food.domain.AppSetting.class.getName());
            createCache(cm, ly.biadjo.food.domain.Activation.class.getName());
            createCache(cm, ly.biadjo.food.domain.Customer.class.getName());
            createCache(cm, ly.biadjo.food.domain.CustomerWallet.class.getName());
            createCache(cm, ly.biadjo.food.domain.Cart.class.getName());
            createCache(cm, ly.biadjo.food.domain.Category.class.getName());
            createCache(cm, ly.biadjo.food.domain.Category.class.getName() + ".restaurants");
            createCache(cm, ly.biadjo.food.domain.City.class.getName());
            createCache(cm, ly.biadjo.food.domain.Coupon.class.getName());
            createCache(cm, ly.biadjo.food.domain.Cuisine.class.getName());
            createCache(cm, ly.biadjo.food.domain.DeliveryAddress.class.getName());
            createCache(cm, ly.biadjo.food.domain.Driver.class.getName());
            createCache(cm, ly.biadjo.food.domain.DriverLocation.class.getName());
            createCache(cm, ly.biadjo.food.domain.DriverReview.class.getName());
            createCache(cm, ly.biadjo.food.domain.DriverWallet.class.getName());
            createCache(cm, ly.biadjo.food.domain.FavoriteRestaurant.class.getName());
            createCache(cm, ly.biadjo.food.domain.Food.class.getName());
            createCache(cm, ly.biadjo.food.domain.FoodExtra.class.getName());
            createCache(cm, ly.biadjo.food.domain.FoodIngredient.class.getName());
            createCache(cm, ly.biadjo.food.domain.FoodImage.class.getName());
            createCache(cm, ly.biadjo.food.domain.FoodOrder.class.getName());
            createCache(cm, ly.biadjo.food.domain.Notification.class.getName());
            createCache(cm, ly.biadjo.food.domain.Order.class.getName());
            createCache(cm, ly.biadjo.food.domain.OrderHistory.class.getName());
            createCache(cm, ly.biadjo.food.domain.PaymentMethod.class.getName());
            createCache(cm, ly.biadjo.food.domain.Referral.class.getName());
            createCache(cm, ly.biadjo.food.domain.Restaurant.class.getName());
            createCache(cm, ly.biadjo.food.domain.Restaurant.class.getName() + ".categories");
            createCache(cm, ly.biadjo.food.domain.RestaurantDistancePrice.class.getName());
            createCache(cm, ly.biadjo.food.domain.RestaurantZonePrice.class.getName());
            createCache(cm, ly.biadjo.food.domain.RestaurantWallet.class.getName());
            createCache(cm, ly.biadjo.food.domain.RestaurantReview.class.getName());
            createCache(cm, ly.biadjo.food.domain.RestaurantSchedule.class.getName());
            createCache(cm, ly.biadjo.food.domain.Slider.class.getName());
            createCache(cm, ly.biadjo.food.domain.Transaction.class.getName());
            createCache(cm, ly.biadjo.food.domain.Zone.class.getName());
            createCache(cm, ly.biadjo.food.domain.RestaurantImage.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
