package greet.webshopmongoorderplaceddd.controller;

import greet.webshopmongoorderplaceddd.domain.product.Product;
import greet.webshopmongoorderplaceddd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productCatalogService;

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Integer productId) {
        Product product = productCatalogService.getProduct(productId);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping(value = "/{productId}/{description}/{price}")
    public ResponseEntity<?> addProduct(@PathVariable Integer productId, @PathVariable String description,
                                        @PathVariable double price) {
        productCatalogService.addProduct(productId, price, description);
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

    @PostMapping(value = "/stock/{productId}/{quantity}/{locationcode}")
    public ResponseEntity<?> setStock(@PathVariable Integer productId, @PathVariable int quantity, @PathVariable String locationcode) {
        productCatalogService.setStock(productId, quantity, locationcode);
        return new ResponseEntity<Product>(HttpStatus.OK);
    }
}
