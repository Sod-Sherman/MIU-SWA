package greet.service;

import java.util.List;

import greet.model.Shoppingcart;

public interface ShoppingcartService {
	
	Shoppingcart addToCart(Integer shoppingcartId, Integer productId, int quantity);
	List<Shoppingcart> getCart();

}
