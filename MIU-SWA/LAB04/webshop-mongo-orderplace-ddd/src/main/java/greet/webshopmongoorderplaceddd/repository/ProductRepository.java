package greet.webshopmongoorderplaceddd.repository;

import greet.webshopmongoorderplaceddd.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
}
