package greet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import greet.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, Integer> {

}
