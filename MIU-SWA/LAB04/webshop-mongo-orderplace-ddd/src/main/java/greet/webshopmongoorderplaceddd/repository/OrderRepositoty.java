package greet.webshopmongoorderplaceddd.repository;

import greet.webshopmongoorderplaceddd.domain.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoty extends MongoRepository<Order, Integer> {
}
