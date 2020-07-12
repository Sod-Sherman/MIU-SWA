package greet.webshopmongoorderplaceddd.repository;

import greet.webshopmongoorderplaceddd.domain.shoppingcart.Shoppingcart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingcartRepository extends MongoRepository<Shoppingcart, Integer> {
}
