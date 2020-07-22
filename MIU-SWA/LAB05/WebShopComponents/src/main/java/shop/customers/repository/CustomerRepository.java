package shop.customers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import shop.customers.domain.Customer;
import shop.order.domain.Order;


public interface CustomerRepository extends MongoRepository<Customer, String> {

}
