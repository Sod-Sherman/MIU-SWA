package greet.service;

import greet.model.Order;
import greet.model.Shoppingcart;

public interface OrderService {
	Order createOrder(Integer orderId, Shoppingcart shoppingcart);

	Order getOrder(Integer orderId);
}
