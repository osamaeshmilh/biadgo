package ly.biadjo.food.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import ly.biadjo.food.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class RestaurantRepositoryWithBagRelationshipsImpl implements RestaurantRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Restaurant> fetchBagRelationships(Optional<Restaurant> restaurant) {
        return restaurant.map(this::fetchCategories);
    }

    @Override
    public Page<Restaurant> fetchBagRelationships(Page<Restaurant> restaurants) {
        return new PageImpl<>(fetchBagRelationships(restaurants.getContent()), restaurants.getPageable(), restaurants.getTotalElements());
    }

    @Override
    public List<Restaurant> fetchBagRelationships(List<Restaurant> restaurants) {
        return Optional.of(restaurants).map(this::fetchCategories).orElse(Collections.emptyList());
    }

    Restaurant fetchCategories(Restaurant result) {
        return entityManager
            .createQuery(
                "select restaurant from Restaurant restaurant left join fetch restaurant.categories where restaurant.id = :id",
                Restaurant.class
            )
            .setParameter("id", result.getId())
            .getSingleResult();
    }

    List<Restaurant> fetchCategories(List<Restaurant> restaurants) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, restaurants.size()).forEach(index -> order.put(restaurants.get(index).getId(), index));
        List<Restaurant> result = entityManager
            .createQuery(
                "select restaurant from Restaurant restaurant left join fetch restaurant.categories where restaurant in :restaurants",
                Restaurant.class
            )
            .setParameter("restaurants", restaurants)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
