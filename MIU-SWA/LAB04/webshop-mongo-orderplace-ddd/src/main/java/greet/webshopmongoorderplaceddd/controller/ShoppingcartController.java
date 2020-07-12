package greet.webshopmongoorderplaceddd.controller;

import greet.webshopmongoorderplaceddd.domain.shoppingcart.Shoppingcart;
import greet.webshopmongoorderplaceddd.service.ShoppingcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingcartController {
    @Autowired
    ShoppingcartService shoppingService;

    @PostMapping(value = "/{cartId}/{productId}/{quantity}")
    public ResponseEntity<?> addToCart(@PathVariable Integer cartId,
                                       @PathVariable Integer productId,
                                       @PathVariable int quantity) {
        shoppingService.addToShoppingcart(cartId, productId, quantity);
        return new ResponseEntity<Shoppingcart>(HttpStatus.OK);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable Integer cartId) {
        Shoppingcart cart = shoppingService.getShoppingcart(cartId);
        return new ResponseEntity<Shoppingcart>(cart, HttpStatus.OK);
    }

    @PostMapping(value = "/checkout/{cartId}")
    public ResponseEntity<?> checkoutCart(@PathVariable Integer cartId) {
        shoppingService.checkOut(cartId);
        return new ResponseEntity<Shoppingcart>(HttpStatus.OK);
    }
}
