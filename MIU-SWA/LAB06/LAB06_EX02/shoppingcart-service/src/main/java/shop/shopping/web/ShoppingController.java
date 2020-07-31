package shop.shopping.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.shopping.service.dto.ShoppingCartDTO;
import shop.shopping.service.ShoppingService;

import java.io.IOException;

@RestController
@Log4j2
public class ShoppingController {
	@Autowired
	ShoppingService shoppingService;
	
	@PostMapping(value = "/cart/{cartId}/{productnumber}/{quantity}")
	public ResponseEntity<?> addToCart(@PathVariable String cartId, @PathVariable String productnumber, @PathVariable int quantity) throws IOException {
		shoppingService.addToCart(cartId, productnumber, quantity);
		return new ResponseEntity<ShoppingCartDTO>(HttpStatus.OK);		
	}
	
	@GetMapping("/cart/{cartId}")
	public ResponseEntity<?> getCart(@PathVariable String cartId) {
		ShoppingCartDTO cart = shoppingService.getCart(cartId);
		return new ResponseEntity<ShoppingCartDTO>(cart, HttpStatus.OK);
	}
	
	@PostMapping(value = "/cart/checkout/{cartId}")
	public ResponseEntity<?> checkoutCart(@PathVariable String cartId) throws IOException {
		String orderId = shoppingService.checkout(cartId);
		if(orderId == null) {
			log.warn("Order cannot be created for cartId: {}", cartId);
			return new ResponseEntity<String>("Order cannot be created for cartId: " + cartId, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("New order created by id: {} for cartId: {}", orderId, cartId);
		return new ResponseEntity<String>(orderId, HttpStatus.OK);
	}
	
}
