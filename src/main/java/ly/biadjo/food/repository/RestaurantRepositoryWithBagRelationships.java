package ly.biadjo.food.repository;

import java.util.List;
import java.util.Optional;

import ly.biadjo.food.domain.Restaurant;
import org.springframework.data.domain.Page;

public interface RestaurantRepositoryWithBagRelationships {
    Optional<Restaurant> fetchBagRelationships(Optional<Restaurant> restaurant);

    List<Restaurant> fetchBagRelationships(List<Restaurant> restaurants);

    Page<Restaurant> fetchBagRelationships(Page<Restaurant> restaurants);
}
