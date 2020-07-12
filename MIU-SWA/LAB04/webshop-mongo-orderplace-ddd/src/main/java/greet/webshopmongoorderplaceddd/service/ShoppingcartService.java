package greet.webshopmongoorderplaceddd.service;

import greet.webshopmongoorderplaceddd.domain.shoppingcart.Shoppingcart;

public interface ShoppingcartService {
    boolean addToShoppingcart(Integer shoppingcartId, Integer productId, Integer quantity);
    Shoppingcart getShoppingcart(Integer shoppingcartId);
    boolean checkOut(Integer shoppingcartId);
}
