package greet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import greet.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

}
