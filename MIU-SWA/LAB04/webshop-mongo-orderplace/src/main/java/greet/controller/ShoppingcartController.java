package greet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import greet.model.Shoppingcart;
import greet.service.ShoppingcartService;

@RestController
@RequestMapping("/cart")
public class ShoppingcartController {

	@Autowired
	private ShoppingcartService shoppingcartService;

	@GetMapping("/all")
	public ResponseEntity<List<Shoppingcart>> getAllShoppingcart() {
		return new ResponseEntity<>(shoppingcartService.getCart(), HttpStatus.OK);
	}

	@GetMapping("/add/{shoppingcartId}/{prodId}/{quantity}")
	public ResponseEntity<?> addToCart(@PathVariable Integer shoppingcartId, @PathVariable Integer prodId,
			@PathVariable int quantity) {
		Shoppingcart shoppingcart = shoppingcartService.addToCart(shoppingcartId, prodId, quantity);
		if (shoppingcart != null)
			return new ResponseEntity<>(shoppingcart, HttpStatus.OK);
		return new ResponseEntity<>(new String("Out of Stock!"), HttpStatus.valueOf(404));
	}

}
