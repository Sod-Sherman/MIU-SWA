package greet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import greet.model.Shoppingcart;

@Repository
public interface ShoppingcartRepository extends MongoRepository<Shoppingcart, Integer> {

}
