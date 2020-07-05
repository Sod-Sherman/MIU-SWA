package greet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import greet.service.OrderService;
import greet.service.ShoppingcartService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ShoppingcartService shoppingcartService;
	
	@GetMapping("/get/{orderId}")
	public ResponseEntity<?> getOrder(@PathVariable Integer orderId) {
		return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
	}
	
	@GetMapping("/create/{orderId}/{shoppingcartId}")
	public ResponseEntity<?> createOrder(@PathVariable Integer orderId, @PathVariable Integer shoppingcartId) {
		shoppingcartService.checkOut(shoppingcartId);
		return new ResponseEntity<>(
				orderService.createOrder(orderId, shoppingcartService.getShoppingcart(shoppingcartId)), 
				HttpStatus.OK);
	}

}
