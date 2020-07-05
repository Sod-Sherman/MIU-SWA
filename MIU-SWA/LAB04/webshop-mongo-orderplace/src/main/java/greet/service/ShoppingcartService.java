package greet.service;

import java.util.List;

import greet.model.Shoppingcart;

public interface ShoppingcartService {

	Shoppingcart addToCart(Integer shoppingcartId, Integer productId, int quantity);
	Shoppingcart getShoppingcart(Integer shoppingcartId);
	List<Shoppingcart> getCart();
	void delete(Shoppingcart shoppingcart);
	boolean checkOut(Integer shoppincartId);

}
