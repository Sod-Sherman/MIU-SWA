package greet.webshopmongoorderplaceddd.service;

import greet.webshopmongoorderplaceddd.domain.product.Product;

public interface ProductService {
    Product addProduct(Integer productId, double price, String description);
    Product getProduct(Integer productId);
    boolean setStock(Integer productId, Integer quantity, String location);
}
