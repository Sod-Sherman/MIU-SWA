package greet.webshopmongoorderplaceddd.service.impl;

import greet.webshopmongoorderplaceddd.domain.shoppingcart.Product;
import greet.webshopmongoorderplaceddd.domain.shoppingcart.Shoppingcart;
import greet.webshopmongoorderplaceddd.repository.ShoppingcartRepository;
import greet.webshopmongoorderplaceddd.service.OrderService;
import greet.webshopmongoorderplaceddd.service.ProductService;
import greet.webshopmongoorderplaceddd.service.ShoppingcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ShoppingcartServiceImpl implements ShoppingcartService {
    @Autowired
    private ShoppingcartRepository shoppingcartRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @Override
    public boolean addToShoppingcart(Integer shoppingcartId, Integer productId, Integer quantity) {
        greet.webshopmongoorderplaceddd.domain.product.Product productP = productService.getProduct(productId);

        if (productP == null) return false;

        Product product =
                Product.builder().productId(productP.getProductId())
                        .description(productP.getDescription())
                        .price(productP.getPrice())
                        .build();

        Shoppingcart shoppingcart = shoppingcartRepository.findById(shoppingcartId).orElseGet(() ->
                Shoppingcart.builder().shoppingcartId(shoppingcartId).cartLines(new ArrayList<>()).build());
        shoppingcart.addToCartLine(product, quantity);
        shoppingcartRepository.save(shoppingcart);
        return true;
    }

    @Override
    public Shoppingcart getShoppingcart(Integer shoppingcartId) {
        return shoppingcartRepository.findById(shoppingcartId).orElse(null);
    }

    @Override
    public boolean checkOut(Integer shoppingcartId) {
        Shoppingcart shoppingcart = shoppingcartRepository.findById(shoppingcartId).orElse(null);
        if (shoppingcart == null) return false;
        orderService.createOrder(shoppingcart);
        return true;
    }
}
