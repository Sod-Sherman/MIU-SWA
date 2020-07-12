package greet.webshopmongoorderplaceddd.service.impl;

import greet.webshopmongoorderplaceddd.domain.product.Product;
import greet.webshopmongoorderplaceddd.domain.product.Stock;
import greet.webshopmongoorderplaceddd.repository.ProductRepository;
import greet.webshopmongoorderplaceddd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Integer productId, double price, String description) {
        Product product = Product.builder()
                .productId(productId)
                .price(price)
                .description(description)
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public boolean setStock(Integer productId, Integer quantity, String location) {
        Product product = getProduct(productId);
        if (product == null) return false;
        product.setStock(
                Stock.builder()
                        .quantity(quantity)
                        .location(location)
                        .build());
        productRepository.save(product);
        return true;
    }
}
